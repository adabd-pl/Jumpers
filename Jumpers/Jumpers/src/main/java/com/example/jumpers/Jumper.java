package com.example.jumpers;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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
        addStyle(gui);
        Jumper clicked = this;
        Board board = this.player.getBoard();
        System.out.println(board );
        this.guiJumper.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (player.getBoard().getActualPlay().equals(player) && !player.getBoard().actualJumperMove()){
                    gui.setFill(Color.YELLOW);
                    if (clicked.player.getBoard().getActualChoosed()!=null){
                        clicked.player.getBoard().getActualChoosed().getCircleJumper().setFill(clicked.player.getBoard().getActualChoosed().getColor());
                        clicked.getCircleJumper().setFill(Color.YELLOW);
                       // clicked.addShadow();
                    }
                    clicked.player.getBoard().setActualChoosed(clicked);

                }

            }
        });


    }

    public void addShadow(){
        DropShadow ds = new DropShadow();
        ds.setOffsetY(0);
        ds.setOffsetX(0);
        ds.setRadius(30);
        ds.setColor(Color.YELLOW);
        this.getCircleJumper().setEffect(ds);

    }

    public void setPosition(Position newPosition) {
        this.position=newPosition;
    }

    public void removeClickOn() {
        if(this.player.getBoard().getActualChoosed()!=null ){ //&& !this.position.equals(this.player.getBoard().getActualChoosed().getPosition()
            this.getCircleJumper().setFill(this.color);

        }
       //  this.player.getBoard().setActualChoosed(null);
    }

    public void addStyle(Circle pawn){
     //   pawn.setStyle(
        //                "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 );"+
      //                  "-fx-text-fill: linear-gradient(white, #d0d0d0);"+
          //              "-fx-padding: 10 20 10 20;");

        pawn.setStyle("    -fx-padding: 8 15 15 15;\n" +
               " -fx-effect: dropshadow( one-pass-box , #2a2a2a, 1 , 0.0 , 5 , 5 );\n"

        );
    }
}
