package com.example.jumpers;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Jumper {

    Circle guiJumper;

    public Position getPosition() {
        return position;
    }

    Position position;
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

        this.guiJumper = new Circle(15, color);
        this.choose(g);
    }

    public void choose(GridPane g ){
        //Circle animalOn = new Circle(this.getPosition().x*fieldSize + fieldSize/2 + 0.5,this.getPosition().y*fieldSize + fieldSize/2 + 0.5 , (fieldSize-1)/2);
        Circle gui =  this.guiJumper;
        Jumper clicked = this;
        this.guiJumper.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                gui.setFill(Color.YELLOW);
                if (clicked.player.getBoard().getActualChoosed()!=null){
                    clicked.player.getBoard().getActualChoosed().getCircleJumper().setFill(clicked.player.getBoard().getActualChoosed().getColor());

                    clicked.getCircleJumper().setFill(Color.YELLOW);


                }
                clicked.player.getBoard().setActualChoosed(clicked);



            }
        });
    }
}
