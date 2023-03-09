package com.example.lgame;

public class Piece {
    private PieceColor color;
    private boolean isOnBoard;

    public Piece(PieceColor color) {
        this.color = color;
        this.isOnBoard = false;
    }

    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }

    public boolean isOnBoard() {
        return isOnBoard;
    }

    public void setOnBoard(boolean isOnBoard) {
        this.isOnBoard = isOnBoard;
    }


    public boolean isL() {
        return color == PieceColor.RED || color == PieceColor.BLUE;
    }

    public boolean isNeutral() {
        return color == PieceColor.NEUTRAL;
    }
}
