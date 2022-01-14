package com.example.jumpers;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.geometry.HPos;

import javax.sql.ConnectionPoolDataSource;

public class Jumper {

    Circle guiJumper;

    public Position getPosition() {
        return position;
    }

    Position position;

    public Player getPlayer() {
        return player;
    }

    Player player;


    public Color getColor() {
        return color;
    }

    Color color;
    public VBox getGuiJumper() {
        VBox box = new VBox(this.guiJumper);
        return box;
    }
    public Circle getCircleJumper(){
        return this.guiJumper;
    }


    public Jumper (Player player ,Color color ,Position position  ,GridPane g ){
        this.player = player;
        this.position =position;
        this.color= color;

        this.guiJumper = new Circle(20, color);
        this.choose(g);
    }

    //SHOW CHOOSE JUMPER BY CLICK
    public void choose(GridPane g ){
        Circle gui =  this.guiJumper;
        Jumper clicked = this;
        Board board = this.player.getBoard();
        System.out.println(board );
        this.guiJumper.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (player.getBoard().getActualPlay().equals(player)){
                    gui.setFill(Color.YELLOW);
                    if (clicked.player.getBoard().getActualChoosed()!=null){
                        clicked.player.getBoard().getActualChoosed().getCircleJumper().setFill(clicked.player.getBoard().getActualChoosed().getColor());

                        clicked.getCircleJumper().setFill(Color.YELLOW);
                    }
                    clicked.player.getBoard().setActualChoosed(clicked);

                }

            }
        });


    }



    public void setPosition(Position newPosition) {
        this.position=newPosition;
    }
}
