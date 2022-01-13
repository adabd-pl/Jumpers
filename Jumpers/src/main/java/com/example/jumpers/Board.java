package com.example.jumpers;



import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Board {
    private int boardSize =  8;
    private Jumper[][] fields = new Jumper[this.boardSize][this.boardSize];
    private Player player1;
    private Player player2;
    private GridPane pane;

    public Jumper getActualChoosed() {
        return actualChoosed;
    }

    public void setActualChoosed(Jumper actualChoosed) {
        System.out.println("nwybrano pionek" + actualChoosed.getPosition().getX() + " "+ actualChoosed.getPosition().getY());
        this.actualChoosed = actualChoosed;
    }

    private Jumper actualChoosed= null;

    public Board(Player player1 , Player player2, GridPane pane){
        this.pane=pane;
        this.player1=player1;
        this.player2=player2;
        for (int i =0 ; i<this.boardSize ; i++ ){
            System.out.println(i);
            this.fields[0][i]=this.player1.getJumpers().get(2*i);
            this.fields[1][i]=this.player1.getJumpers().get(2*i+1);

            this.pane.add(this.player1.getJumpers().get(2*i).getGuiJumper(),i,0);
            this.pane.add(this.player1.getJumpers().get(2*i+1).getGuiJumper(),i,1);
            //this.pane.setHalignment(this.player1.getJumpers().get(2*i).getGuiJumper(), HPos.CENTER);
           // this.pane.setHalignment(this.player1.getJumpers().get(2*i+1).getGuiJumper(), HPos.CENTER);
            this.fields[6][i]=this.player2.getJumpers().get(2*i);
            this.fields[7][i]=this.player2.getJumpers().get(2*i+1);

            this.pane.add(this.player2.getJumpers().get(2*i).getGuiJumper(),i,6);
            this.pane.add(this.player2.getJumpers().get(2*i+1).getGuiJumper(),i ,7);

        }
    }

    public void canMove(Position oldPosition, Position newPosition , Player player){
        if (oldPosition.normalMove(newPosition) && checkField(newPosition) ){
             makeMove(oldPosition,newPosition);
        }
    }

    public boolean checkField(Position position){
        if(this.fields[position.getX()][position.getY()] ==null){
            return true;
        }
        return false;
    }

    public void makeMove(Position oldPosition, Position newPosition){
        this.fields[newPosition.getX()][newPosition.getY()]=this.fields[oldPosition.getX()][oldPosition.getY()];
        this.fields[oldPosition.getX()][oldPosition.getY()]=null;

    }



}
