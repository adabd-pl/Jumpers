package com.example.jumpers;



import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Board {

    private int boardSize =  8;
    private Jumper[][] fields = new Jumper[this.boardSize][this.boardSize];
    private Player player1;
    private Player player2;
    private GridPane pane;
    public Jumper actualChoosed= null;
    private Player actualPlay=player1;
    public Jumper getActualChoosed() {
        return this.actualChoosed;
    }

    public void setActualChoosed(Jumper actualChoosed) {
        System.out.println("Wybrano pionek: " + actualChoosed.getPosition().getX() + " "+ actualChoosed.getPosition().getY());
        this.actualChoosed = actualChoosed;
        System.out.println("ActualChosed: " + this.getActualChoosed() );
    }



    public Board(Player player1 , Player player2, GridPane pane){
        this.pane=pane;
        this.player1=player1;
        this.player2=player2;
        for (int i =0 ; i<this.boardSize ; i++ ){
            System.out.println(i);
            this.fields[0][i]=this.player1.getJumpers().get(2*i);
            this.fields[1][i]=this.player1.getJumpers().get(2*i+1);

            this.pane.add(this.player1.getJumpers().get(2*i).getCircleJumper(),i,0);
            this.pane.add(this.player1.getJumpers().get(2*i+1).getCircleJumper(),i,1);
            this.pane.setHalignment(this.player1.getJumpers().get(2*i).getCircleJumper(), HPos.CENTER);
            this.pane.setHalignment(this.player1.getJumpers().get(2*i+1).getCircleJumper(), HPos.CENTER);
            this.fields[6][i]=this.player2.getJumpers().get(2*i);
            this.fields[7][i]=this.player2.getJumpers().get(2*i+1);

            this.pane.add(this.player2.getJumpers().get(2*i).getCircleJumper(),i,6);
            this.pane.add(this.player2.getJumpers().get(2*i+1).getCircleJumper(),i ,7);
            this.pane.setHalignment(this.player1.getJumpers().get(2*i).getCircleJumper(), HPos.CENTER);
            this.pane.setHalignment(this.player1.getJumpers().get(2*i+1).getCircleJumper(), HPos.CENTER);

            for (int j=2  ; j<this.boardSize-2 ; j++ ) {
                this.fields[j][i] = null;
            }
        }
        for (int i =0 ; i<this.boardSize ; i++ ){
                for (int j =0 ; j<this.boardSize ; j++ ){
                    System.out.println(i +"|"+ j + this.fields[i][j]);
                }
            }

    }

    //MAKE MOVE IF IS POSSIBLE
    public void canMove(Position newPosition ){
        Position oldPosition = this.actualChoosed.getPosition();
        if (checkMove(oldPosition,newPosition) ) {

            makeMove(oldPosition, newPosition);
            this.actualChoosed.setPosition(newPosition);
            this.pane.getChildren().remove(this.actualChoosed.getCircleJumper());
            this.pane.add(this.actualChoosed.getCircleJumper(), newPosition.getY(), newPosition.getX());
            this.pane.setHalignment(this.actualChoosed.getCircleJumper(), HPos.CENTER);

        }
        else{
            System.out.println("Cannot move");
        }
    }

    public boolean checkMove(Position oldPosition, Position newPosition){
        if (oldPosition.normalMove(newPosition) && checkField(newPosition)){
            return true;
        }
        //  if (oldPosition.jumpMove(newPosition)&& checkField(newPosition) && crossJumpCheck(oldPosition,newPosition)){
        //        System.out.println("crossJump");
        //        return true;
        //   }
        if (oldPosition.jumpMove(newPosition)&& checkField(newPosition) && fieldBetween(oldPosition,newPosition)!=null && !checkField(fieldBetween(oldPosition,newPosition))){
            return true;
        }
        return false;
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


    public Position fieldBetween(Position positionOne, Position positionTwo){
        if (positionOne.getY()== positionTwo.getY() && positionOne.getX()< positionTwo.getX()) {
            return new Position(positionOne.getX() + 1, positionOne.getY());
        }
        else if(positionOne.getY()== positionTwo.getY() && positionOne.getX()> positionTwo.getX()){
            return new Position( positionOne.getX()-1, positionOne.getY());
        }
        if (positionOne.getX()== positionTwo.getX() && positionOne.getY()< positionTwo.getY()) {
            return new Position(positionOne.getX() , positionOne.getY()+1);
        }
        else if(positionOne.getX()== positionTwo.getX() && positionOne.getY()> positionTwo.getY()){
            return new Position( positionOne.getX(), positionOne.getY()-1);
        }

        return null;
    }
///taki ruch niepoprawny
  /*  public boolean crossJumpCheck(Position positionOne, Position positionTwo){
        if(Math.abs(positionOne.getX()- positionTwo.getX())==1 && Math.abs(positionOne.getX()- positionTwo.getX())==1 ){
            if (!checkField(new Position(positionOne.getX(), positionTwo.getY())) || !checkField(new Position(positionTwo.getX(), positionOne.getY() ))){
                return true;
            }
        }
        return false;
    }*/


    public boolean checkForWin(Player player){
        int row1,row2;
        if (player.equals(this.player1)){
            row1=0;
            row2=1;
        }
        else{
            row1=6;
            row2=7;
        }
        for(int i =0 ; i<this.boardSize ; i++ ){

            if(this.fields[row1][i]==null || this.fields[row2][i]==null || !this.fields[row1][i].getPlayer().equals(player) ||  !this.fields[row2][i].getPlayer().equals(player) ){
                return false;
            }
        }
        return true;
    }


    public Player getActualPlay() {
        return actualPlay;
    }

    public void setActualPlay(Player actualPlay) {
        this.actualPlay = actualPlay;
    }

    public Label changePlayer(){
        System.out.println(this.actualPlay.equals(this.player1));
        if (this.actualPlay.equals(this.player1)){
            this.actualPlay=this.player2;
            return new Label("Player 2 - Black");
        }else{
            this.actualPlay=this.player1;
            return new Label("Player 1 - Red");
        }

    }
}
