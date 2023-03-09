package com.example.lgame;

public class LGame {
    private Piece redL;
    private Piece blueL;
    private Piece[] neutralPawns;
    private Piece[][] board;
    private PieceColor currentPlayerColor;

    public LGame() {
        this.redL = new Piece(PieceColor.RED);
        this.blueL = new Piece(PieceColor.BLUE);
        this.neutralPawns = new Piece[]{new Piece(PieceColor.NEUTRAL), new Piece(PieceColor.NEUTRAL)};
        this.board = new Piece[4][4];
        initBoard();
        this.currentPlayerColor = PieceColor.RED;
    }

    private void initBoard() {
        board[0][0] = redL;
        board[3][3] = blueL;
        board[1][1] = neutralPawns[0];
        board[2][2] = neutralPawns[1];
    }

    public boolean isLegalMove(int startX, int startY, int endX, int endY) {
        Piece startPiece = board[startX][startY];
        Piece endPiece = board[endX][endY];

        // Vérification que les coordonnées de départ correspondent à une pièce sur le plateau
        if (startPiece == null) {
            return false;
        }

        // Vérification que les coordonnées de fin correspondent à une case vide ou à une case occupée par une pièce de l'autre joueur
        if (endPiece != null && endPiece.getColor() == startPiece.getColor()) {
            return false;
        }

        // Vérification que le déplacement se fait bien sur le plateau (cases en dehors de la grille 4x4)
        if (endX < 0 || endX > 3 || endY < 0 || endY > 3) {
            return false;
        }

        // Vérification que le déplacement ne se fait que sur une case à une distance horizontale ou verticale de la position actuelle du L
        int deltaX = Math.abs(startX - endX);
        int deltaY = Math.abs(startY - endY);
        if (deltaX > 1 || deltaY > 1 || (deltaX == 0 && deltaY == 0) || (deltaX == 1 && deltaY == 1)) {
            return false;
        }

        // Vérification que le déplacement ne se fait pas sur une case occupée par une pièce autre que son propre L
        if (endPiece != null && endPiece.getColor() != startPiece.getColor() && !startPiece.isL() && !endPiece.isL()) {
            return false;
        }

        return true;
    }

    public boolean isGameOver() {
        boolean redCanMove = false;
        boolean blueCanMove = false;

        // Vérifier si le L rouge peut être déplacé
        Piece redPiece = getPiece(0, 0);
        if (redPiece.getColor() == PieceColor.RED) {
            if (canMove(redPiece)) {
                redCanMove = true;
            }
        }

        // Vérifier si le L bleu peut être déplacé
        Piece bluePiece = getPiece(3, 3);
        if (bluePiece.getColor() == PieceColor.BLUE) {
            if (canMove(bluePiece)) {
                blueCanMove = true;
            }
        }

        return !(redCanMove || blueCanMove);
    }

    private boolean canMove(Piece piece) {
        int startX = -1;
        int startY = -1;

        // Trouver les coordonnées du L
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == piece) {
                    startX = i;
                    startY = j;
                    break;
                }
            }
            if (startX != -1) {
                break;
            }
        }

        // Vérifier si le L peut être déplacé sur une case vide ou occupée par une pièce adverse
        for (int i = startX - 1; i <= startX + 1; i++) {
            for (int j = startY - 1; j <= startY + 1; j++) {
                if (i >= 0 && i < 4 && j >= 0 && j < 4 && (i == startX || j == startY) && !(i == startX && j == startY)) {
                    if (isLegalMove(startX, startY, i, j)) {
                        Piece endPiece = board[i][j];
                        if (endPiece == null || (endPiece.getColor() != piece.getColor() && !endPiece.isL() && piece.getColor() != PieceColor.NEUTRAL)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }


    public void move(int startX, int startY, int endX, int endY) {
        // On récupère la pièce à déplacer
        Piece piece = board[startX][startY];
        if (piece == null) {
            // Il n'y a pas de pièce à la position initiale
            return;
        }
        if (!isLegalMove(startX, startY, endX, endY)) {
            // Le mouvement n'est pas autorisé
            return;
        }
        // On déplace la pièce
        board[startX][startY] = null;
        board[endX][endY] = piece;
    }

    /*public PieceColor getCurrentPlayerColor() {
        int redMoves = 0;
        int blueMoves = 0;

        // On compte le nombre de mouvements effectués par chaque joueur
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Piece piece = board[i][j];
                if (piece != null && !piece.isNeutral()) {
                    if (piece.getColor() == PieceColor.RED) {
                        redMoves++;
                    } else {
                        blueMoves++;
                    }
                }
            }
        }

        // Le joueur avec le moins de mouvements a la couleur courante
        if (redMoves <= blueMoves) {
            return PieceColor.RED;
        } else {
            return PieceColor.BLUE;
        }
    }*/

    public PieceColor getCurrentPlayerColor() {
        return currentPlayerColor;
    }

    public void setCurrentPlayerColor(PieceColor currentPlayerColor) {
        this.currentPlayerColor = currentPlayerColor;
    }

    public void makeMove(int startX, int startY, int endX, int endY) {
        Piece startPiece = board[startX][startY];
        board[startX][startY] = null;
        board[endX][endY] = startPiece;
        startPiece.setOnBoard(true);

        if (startPiece.getColor() == PieceColor.RED) {
            currentPlayerColor = PieceColor.BLUE;
        } else if (startPiece.getColor() == PieceColor.BLUE) {
            currentPlayerColor = PieceColor.RED;
        }
    }


    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

}

