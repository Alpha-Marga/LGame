package com.example.lgame;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class LGameGUI extends Application {
    private LGame game;
    private GridPane board;
    private Stage mainStage;
    private int boardSize;

    public LGameGUI() {
        this.game = new LGame();
        this.board = new GridPane();
        this.boardSize = Math.max(board.getRowCount(), board.getColumnCount());
    }

    public void setMainStage(Stage stage) {
        this.mainStage = stage;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("L-Game");

        // Création des cases du plateau
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                StackPane cell = new StackPane();
                cell.setAlignment(Pos.CENTER);
                cell.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1px;");
                board.add(cell, i, j);
            }
        }

        // Placement des pièces sur le plateau
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Piece piece = game.getPiece(i, j);
                if (piece != null) {
                    Circle circle = new Circle(20);
                    circle.setFill(getPieceColor(piece.getColor()));
                    StackPane cell = (StackPane) board.getChildren().get(j * 4 + i);
                    cell.getChildren().add(circle);
                }
            }
        }

        // Création de la scène et affichage
        Scene scene = new Scene(board, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Paint getPieceColor(PieceColor color) {
        switch (color) {
            case RED:
                return Color.RED;
            case BLUE:
                return Color.BLUE;
            default:
                return Color.BLACK;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();

        /*// Sauvegarde de l'état du jeu
        try (FileWriter writer = new FileWriter("sauvegarde.txt")) {
            // Écriture de la couleur du joueur courant
            writer.write(game.getCurrentPlayerColor().toString());
            writer.write(System.lineSeparator());

            // Écriture des positions des pièces sur le plateau
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    Piece piece = game.getBoard().getPieceAtPosition(i, j);
                    if (piece != null) {
                        writer.write(i + "," + j + "," + piece.getPieceType().toString() + "," + piece.getColor().toString());
                        writer.write(System.lineSeparator());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void init() throws Exception {
        super.init();
        // TODO : chargement de l'état du jeu sauvegardé
    }
}




