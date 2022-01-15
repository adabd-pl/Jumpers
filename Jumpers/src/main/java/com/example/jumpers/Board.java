package com.example.jumpers;



import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static java.lang.System.out;

public class Board {

    private int boardSize =  8;
    private Jumper[][] fields = new Jumper[this.boardSize][this.boardSize];

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    private Player player1;
    private Player player2;
    private GridPane pane;
    public Jumper actualChoosed= null;
    private Player actualPlay=player1;
    private Position startOfMoves  = null;
    private Boolean actualChoosedMovedNormal =false;
    private Boolean actualChoosedMovedJump =false;
    private ArrayList<Position> movesOfActual = new ArrayList<>();
    //return true if player make any move
    public boolean actualJumperMove(){
        return actualChoosedMovedJump || actualChoosedMovedNormal;
    }

    public Position getStartOfMoves() {
        return startOfMoves;
    }

    public Jumper getActualChoosed() {
        return this.actualChoosed;
    }


    public void setActualChoosed(Jumper actualChoosed) {
        if (!this.actualChoosedMovedNormal && !this.actualChoosedMovedJump) {
            System.out.println("Wybrano pionek: " + actualChoosed.getPosition().getX() + " " + actualChoosed.getPosition().getY());
            this.actualChoosed = actualChoosed;
            this.startOfMoves = actualChoosed.getPosition();
            this.movesOfActual.clear();
            this.movesOfActual.add(actualChoosed.getPosition());
            System.out.println("ActualChosed: " + this.getActualChoosed());
        }else{
           // this.actualChoosed.removeClickOn();
        }
        }



    public Board( GridPane pane){
        this.pane=pane;

    }

    //MAKE MOVE IF IS POSSIBLE
    public boolean canMove(Position newPosition ){
        if (this.actualChoosed==null)
        {
            return false;}

        Position oldPosition = this.actualChoosed.getPosition();
        if (checkMove(oldPosition,newPosition) ) {

            makeMove(oldPosition, newPosition);
            this.actualChoosed.setPosition(newPosition);
            this.pane.getChildren().remove(this.actualChoosed.getCircleJumper());
            this.pane.add(this.actualChoosed.getCircleJumper(), newPosition.getY(), newPosition.getX());
            this.pane.setHalignment(this.actualChoosed.getCircleJumper(), HPos.CENTER);
            this.movesOfActual.add(newPosition);
            return true;
        }
        else{
            if(!actualJumperMove()) {
               // this.actualChoosed.removeClickOn();
            }
            System.out.println("Cannot move from: "  +this.actualChoosed.getPosition().getX()  + "/" + this.actualChoosed.getPosition().getY());
            return false;
        }
    }

    public boolean checkMove(Position oldPosition, Position newPosition){
        if (!this.actualChoosedMovedJump && !this.actualChoosedMovedNormal && oldPosition.normalMove(newPosition) && checkField(newPosition)){

            this.actualChoosedMovedNormal=true;
            return true;

        }
        //  if (oldPosition.jumpMove(newPosition)&& checkField(newPosition) && crossJumpCheck(oldPosition,newPosition)){
        //        System.out.println("crossJump");
        //        return true;
        //   }
        if (!this.actualChoosedMovedNormal && oldPosition.jumpMove(newPosition)&& checkField(newPosition) && fieldBetween(oldPosition,newPosition)!=null && !checkField(fieldBetween(oldPosition,newPosition))){
            this.actualChoosedMovedJump=true;
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
        if (player.equals(this.player2)){
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

    public void changePlayer(Label playerLabel,Label alert){
        if(!actualJumperMove()){
            alert.setText("Make a move!");
            return;
        }
        this.actualChoosedMovedJump=false;
        this.actualChoosedMovedNormal=false;
        System.out.println(this.actualPlay.equals(this.player1));
        if (this.actualPlay.equals(this.player1)){
            this.actualPlay=this.player2;
            playerLabel.setText("Your turn: "+ this.player2.getNick());
        }else{
            this.actualPlay=this.player1;
            playerLabel.setText("Your turn: " + this.player1.getNick());
        }
        getActualChoosed().removeClickOn();

    }

    public void resetMove(GridPane root) {

        this.actualChoosedMovedJump=false;
        this.actualChoosedMovedNormal=false;
        int numberOfMoves= this.movesOfActual.size();

        Thread thread = new Thread(() -> {
            try {
                for (int i= 0; i<numberOfMoves ;i++) {
                    int finalI = i;
                    Platform.runLater(() -> {
                        makeMove(actualChoosed.getPosition(), movesOfActual.get(numberOfMoves - 1 - finalI));
                        actualChoosed.setPosition(movesOfActual.get(numberOfMoves - finalI - 1));
                        actualChoosed.removeClickOn();
                        pane.getChildren().remove(actualChoosed.getCircleJumper());
                        pane.add(actualChoosed.getCircleJumper(), movesOfActual.get(numberOfMoves - finalI - 1).getY(), movesOfActual.get(numberOfMoves - finalI - 1).getX());
                        pane.setHalignment(actualChoosed.getCircleJumper(), HPos.CENTER);
                    });


                Thread.sleep(600);
                }

            } catch (InterruptedException exc) {
                throw new Error("Unexpected interruption");
            }
        });
        thread.start();
        root.add(new Text("Anulowano ruch"), 9  , 4, 1,1);
    }

    public void undoMove(GridPane root , Label alert) {

        int numberOfMoves= this.movesOfActual.size()-1;
        if(numberOfMoves<1){
            alert.setText("Nie można cofnąć!");
            return;
        }

        out.println("ile ruchów zrobiono: " + numberOfMoves);
        makeMove(actualChoosed.getPosition(), movesOfActual.get(numberOfMoves - 1 ));
        actualChoosed.setPosition(movesOfActual.get(numberOfMoves - 1));
        pane.getChildren().remove(actualChoosed.getCircleJumper());
        pane.add(actualChoosed.getCircleJumper(), movesOfActual.get(numberOfMoves  - 1).getY(), movesOfActual.get(numberOfMoves- 1).getX());
        //pane.setHalignment(actualChoosed.getCircleJumper(), HPos.CENTER);
        this.movesOfActual.remove(numberOfMoves-1);


    }

    public void setNicksAndColors(String nickFirst, String nickSecond , Color colorPlayer1 , Color colorPlayer2){
        this.player1.setNick(nickFirst);
        this.player2.setNick(nickSecond);
        this.player1.setColor(colorPlayer1);
        this.player2.setColor(colorPlayer2);

    }

    public void addPlayers(Player player1, Player player2) {
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
    }
}
