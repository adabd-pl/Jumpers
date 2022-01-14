package com.example.jumpers;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Control;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GridPane pane = new GridPane();
        pane.setGridLinesVisible(true);


        Player player1 =new Player(Color.RED , 0 ,pane );
        Player player2 =new Player(Color.BLACK , 6 ,pane);

        GridPane root = new GridPane();
        final int size = 8 ;
        //CREATE BOARD
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col ++) {
                StackPane square = new StackPane();
                String color ;
                if ((row + col) % 2 == 0) {
                    color = "white";
                } else {
                    color = "gray";
                }
                GridPane.setRowIndex(square, row);
                GridPane.setColumnIndex(square, col);
                square.setStyle("-fx-background-color: "+color+";");
                root.getChildren().add(square);

            }
        }
        for (int i = 0; i < size; i++) {
            root.getColumnConstraints().add(new ColumnConstraints(30, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            root.getRowConstraints().add(new RowConstraints(50, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
        for (int i = 0; i < 3; i++) {
            root.getColumnConstraints().add(new ColumnConstraints(90, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));

        }

        root.minWidth(1000);
        root.minHeight(1000);
        root.setAlignment(Pos.CENTER);
        root.setGridLinesVisible(true);
        root.add(new Label("Play game!"), 9,0 ,1,1);
        Board board1= new Board(player1,player2,root);
        player1.setBoard(board1);
        player2.setBoard(board1);
        board1.setActualPlay(player1);


        final Label[] whoMove = {new Label("Player 1 - Red")};
        root.add(whoMove[0], 9  , 3, 1,1);
        Button endMovePlayer = new Button("End move");
        root.add(endMovePlayer, 9 ,5,1,1);


        //CHANGE PLAYER
        endMovePlayer.setOnMouseClicked(new EventHandler<MouseEvent>()  {

            @Override
            public void handle(MouseEvent event) {
                root.getChildren().remove(whoMove[0]);
                whoMove[0] = board1.changePlayer();

                root.add(whoMove[0], 9  , 3, 1,1);
            }
        });

        Button resetMovePlayer = new Button("Reset");
        root.add(resetMovePlayer,9,7,1,1);
        //RESET MOVE OF ACTUAL PLAYER
        resetMovePlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });



        stage.setScene(new Scene(root, 1000, 650));
        stage.show();

        //CLICK FIELD TO MOVE
        root.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                for( Node node: root.getChildren()) {

                    if( node instanceof StackPane) {
                        if( node.getBoundsInParent().contains(e.getSceneX(),  e.getSceneY())) {
                            System.out.println( "Click on " + GridPane.getRowIndex( node) + "/" + GridPane.getColumnIndex( node));

                            board1.canMove( new Position(GridPane.getRowIndex( node), GridPane.getColumnIndex( node)));
                            if (board1.checkForWin(board1.getActualChoosed().getPlayer())){
                                System.out.println("Wygrywasz gre!");
                            }
                            return;
                        }
                    }
                }
            }
        });
    }

    public static void main(String[] args) {

        launch();
    }

}