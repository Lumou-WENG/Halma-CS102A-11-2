package xyz.chengzi.halma.controller;

import xyz.chengzi.halma.listener.GameListener;
import xyz.chengzi.halma.model.ChessBoard;
import xyz.chengzi.halma.model.ChessBoardLocation;
import xyz.chengzi.halma.model.ChessPiece;
import xyz.chengzi.halma.view.ChessBoardComponent;
import xyz.chengzi.halma.view.ChessComponent;
import xyz.chengzi.halma.view.SquareComponent;

import java.awt.*;

public class GameController implements GameListener {
    private ChessBoardComponent view;
    private ChessBoard model;

    private Color currentPlayer;
    private ChessPiece selectedPiece;
    private ChessBoardLocation selectedLocation;

    public GameController(ChessBoardComponent boardComponent, ChessBoard chessBoard) {
        this.view = boardComponent;
        this.model = chessBoard;

        this.currentPlayer = Color.RED;//设置先手玩家
        view.registerListener(this);//（大概）开启鼠标监听
        initGameState();
    }

    public void initGameState() {
        for (int row = 0; row < model.getDimension(); row++) {
            for (int col = 0; col < model.getDimension(); col++) {
                ChessBoardLocation location = new ChessBoardLocation(row, col);
                ChessPiece piece = model.getChessPieceAt(location);
                if (piece != null) {
                    view.setChessAtGrid(location, piece.getColor());
                }
            }
        }
        view.repaint();//将add的内容重新画出来
    }

    public Color nextPlayer() {//切换到下一个玩家
        //TODO: 允许四人游戏
        return currentPlayer = currentPlayer == Color.RED ? Color.GREEN : Color.RED;
    }

    @Override
    public void onPlayerClickSquare(ChessBoardLocation location, SquareComponent component) {
        if (selectedLocation != null && model.isValidMove(selectedLocation, location)) {
            //如果格子不是空的，并且可以移动
            model.moveChessPiece(selectedLocation, location);
            view.setChessAtGrid(location, selectedPiece.getColor());
            view.removeChessAtGrid(selectedLocation);
            view.repaint();
            selectedPiece = null;
            selectedLocation = null;
            nextPlayer();//下一个玩家
        }
    }

    @Override
    public void onPlayerClickChessPiece(ChessBoardLocation location, ChessComponent component) {
        ChessPiece piece = model.getChessPieceAt(location);
        //找到当前棋子的位置
        if (piece.getColor() == currentPlayer && (selectedPiece == piece || selectedPiece == null)) {
            //如果选中的棋子颜色 == 当前玩家 && （ || ）
            if (selectedPiece == null) {
                selectedPiece = piece;
                selectedLocation = location;
            } else {//再次点击棋子，取消选中
                selectedPiece = null;
                selectedLocation = null;
            }
            component.setSelected(!component.isSelected());//选中状态 反相
            component.repaint();//重新画 【选中十字标识】
        }
    }
}
