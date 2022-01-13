package com.example.jumpers;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GridPane pane = new GridPane();
        pane.setGridLinesVisible(true);


        final int numCols =8 ;
        final int numRows =8 ;

        for(int i= 0; i<numCols ;i++){
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            pane.getColumnConstraints().add(colConst);

            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            pane.getRowConstraints().add(rowConst);
        }
        Player player1 =new Player(Color.RED , 0 ,pane );
        Player player2 =new Player(Color.GREY , 6 ,pane);

        Board board = new Board(player1, player2 ,pane);
        player1.setBoard(board);
        player2.setBoard(board);
        Label label = new Label("Label " + 4+ "/" + 4);
        label.setMouseTransparent(true);
        GridPane.setRowIndex(label, 4);
        GridPane.setColumnIndex(label, 4);
        pane.getChildren().add(label);
        Scene scene = new Scene( pane,320, 240);
        stage.setTitle("Jumpers");

        stage.setScene(scene);
        stage.show();

        ///return object from clicked field
        pane.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                for( Node node: pane.getChildren()) {

                    if( node instanceof Label) {
                        if( node.getBoundsInParent().contains(e.getSceneX(),  e.getSceneY())) {
                            System.out.println( "Node: " + node + " at " + GridPane.getRowIndex( node) + "/" + GridPane.getColumnIndex( node));
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