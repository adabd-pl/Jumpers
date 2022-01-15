package com.example.jumpers;

import com.sun.javafx.scene.control.behavior.ListCellBehavior;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {



        //GAME SCENE
        GridPane root = new GridPane();


        Label alert= new Label("");
        addStyleAlerts(alert);
        addStyleAlerts(alert);
        root.add(alert, 9,4,1,1);
        BackgroundImage myB= new BackgroundImage(new Image("com\\example\\jumpers\\background.jpg",1000,800,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1000.0,800.0,false,false,false,false));
//then you set to your node
        root.setBackground(new Background(myB));

        final int size = 8 ;
        //CREATE BOARD
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col ++) {
                StackPane square = new StackPane();
                String color ;
                if ((row + col) % 2 == 0) {
                    color = "#9ECEE6";
                } else {
                    color = "#2978A0";
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
      //  root.setGridLinesVisible(true);
        Label title =new Label("Jumpers");
        addStyle(title);
        root.add(title, 9,1 ,1,1);
        Board board1= new Board(root);


        final Label[] whoMove = {new Label("")};
        addStyleAlerts(whoMove[0]);
        root.add(whoMove[0], 9  , 3, 1,1);
        Button endMovePlayer = new Button("End move");
        root.add(endMovePlayer, 9 ,5,1,1);


        //CHANGE PLAYER
        addButtonStyle(endMovePlayer);
        endMovePlayer.setOnMouseClicked(new EventHandler<MouseEvent>()  {

            @Override
            public void handle(MouseEvent event) {
                //root.getChildren().remove(whoMove[0]);
                board1.changePlayer(whoMove[0] ,alert);
               // addStyleAlerts(whoMove[0]);


                //  root.add(whoMove[0], 9  , 3, 1,1);
            }
        });

        Button resetMovePlayer = new Button("Reset move");
        root.add(resetMovePlayer,9,7,1,1);

        //RESET MOVE OF ACTUAL PLAYER
        addButtonStyle(resetMovePlayer);
        resetMovePlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                board1.resetMove(root,alert);
            }
        });


        Button undoMovePlayer = new Button("Undo move");
        root.add(undoMovePlayer,9,6,1,1);

        //RESET MOVE OF ACTUAL PLAYER
        addButtonStyle(undoMovePlayer);
        undoMovePlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                board1.undoMove(root , alert);
            }
        });

        Button resetGame = new Button("Reset game");
        root.add(resetGame,10,7,1,1);
        addButtonStyle(resetGame);

        resetGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                board1.restartGame(whoMove[0]);

            }
        });


        undoMovePlayer.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                board1.undoMove(root , alert);
            }
        });

        //ENTRY SCENE
        GridPane pane = new GridPane();
        pane.setGridLinesVisible(true);
        pane.minWidth(600);
        pane.minHeight(1000);
        pane.setAlignment(Pos.CENTER);
        for (int i = 0; i < 6; i++) {
            pane.getColumnConstraints().add(new ColumnConstraints(50, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            pane.getRowConstraints().add(new RowConstraints(50, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
        BackgroundImage myBI= new BackgroundImage(new Image("com\\example\\jumpers\\background.jpg",1000,800,false,true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1000.0,800.0,false,false,false,false));
        pane.setBackground(new Background(myBI));
        Label entryTitle =new Label("Jumpers");
        addStyle(entryTitle);
        pane.add(entryTitle,2,1,2,1);
        TextField nickPlayer1 = new TextField("Player One");
        TextField nickPlayer2 = new TextField("Player Two");
        pane.add(nickPlayer1, 2,2,1,1);
        pane.add(nickPlayer2, 2,3,1,1);

        final ColorPicker colorPicker1 = new ColorPicker();
        colorPicker1.setValue(Color.RED);
        colorPicker1.setMaxWidth(40);
        final ColorPicker colorPicker2 = new ColorPicker();
        colorPicker2.setValue(Color.BLACK);
        colorPicker2.setMaxWidth(40);


        pane.add(colorPicker1, 3,2,1,1);
        pane.add(colorPicker2, 3,3,1,1);
        Scene gameScene= new Scene(root, 1000, 600);
        Button submit = new Button("Start");
        addButtonStyle(submit);
        pane.add(submit,2,4,2,1);
        submit.setOnMouseClicked(new EventHandler<MouseEvent>()  {

            @Override
            public void handle(MouseEvent event) {
                Player player1 =new Player(colorPicker1.getValue() , 0 ,root);
                Player player2 =new Player(colorPicker2.getValue() , 6 ,root);
                board1.addPlayers(player1,player2);
                player1.setBoard(board1);
                player2.setBoard(board1);
                board1.setActualPlay(player1);
                whoMove[0].setText("Now " +nickPlayer1.getText());
                board1.setNicksAndColors(nickPlayer1.getText(), nickPlayer2.getText() ,colorPicker1.getValue() , colorPicker2.getValue());
                stage.setScene(gameScene);
                stage.show();
            }
        });
        Scene entryScene = new Scene(pane,1000,600);
        stage.setScene(entryScene);


        Button backToMenu = new Button("Back");
        root.add(backToMenu,10,0,1,1);
        addButtonStyle(backToMenu);
        backToMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                colorPicker1.setValue(Color.RED);
                colorPicker2.setValue(Color.BLACK);
                nickPlayer1.setText("Player One");
                nickPlayer2.setText("Player Two");
                stage.setScene(entryScene);
                stage.show();

            }
        });

        stage.show();




        //CLICK FIELD TO MOVE
        root.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                for( Node node: root.getChildren()) {

                    if( node instanceof StackPane) {
                        if( node.getBoundsInParent().contains(e.getSceneX(),  e.getSceneY())) {
                            System.out.println( "Click on " + GridPane.getRowIndex( node) + "/" + GridPane.getColumnIndex( node));
                            boolean ifMove = board1.canMove( new Position(GridPane.getRowIndex( node), GridPane.getColumnIndex( node)));
                            alert.setText("");
                            if ( board1.getActualChoosed()!=null ){
                        //        board1.getActualChoosed().removeClickOn();
                                if (board1.checkForWin(board1.getActualChoosed().getPlayer())){
                                    System.out.println("Wygrywasz gre!");
                                }
                            }

                            return;
                        }
                    }
                }
            }
        });
    }
    public void addStyle(Label text){
        text.setStyle("  -fx-font-size: 32px;\n" +
                "   -fx-font-family: \"Arial Black\";\n" +
                "   -fx-fill: #881a1a;\n" +
                "   -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 2, 0.0 , 0 , 2 );");
    }

    public  void addStyleAlerts(Label text){
        text.setStyle("  -fx-font-size: 20px;\n" +
                "   -fx-font-family: \"Arial\";\n" +
                "   -fx-fill: #881a1a;\n" +
                "   -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 2, 0.0 , 0 , 2 );");
    };

    public void addButtonStyle(Button button){
        button.setStyle(
                "-fx-background-color:#285cc4;"+

                "-fx-text-fill: white;\n"+
                "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );"
                +"-fx-font-family: \"Arial\";"+
                "-fx-text-fill: linear-gradient(white, #d0d0d0);"
                +"-fx-font-size: 12px;"+
                "-fx-padding: 10 20 10 20;");
    }

    public static void main(String[] args) {

        launch();
    }

}