package com.example.jumpers;

public class Position {
    int x;
    int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }


    //CHECK IF CAN MAKE NORMAL MOVE
    public boolean normalMove(Position toMove) {
        if ((Math.abs(this.x - toMove.x) == 1 && this.y == toMove.y) || (Math.abs(this.y - toMove.y) == 1 && this.x == toMove.x)) {
            return true;
        }
        return false;
    }

    //CHECK IF CAN MAKE JUMP MOVE
    public boolean jumpMove(Position toMove){
        if ((Math.abs(this.x - toMove.x) == 2 && this.y == toMove.y) || (Math.abs(this.y - toMove.y) == 2 && this.x == toMove.x)) {
            return true;
        }
        if(Math.abs(this.x - toMove.x) == 1 && Math.abs(this.y - toMove.y)==1){
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        return ("(" + this.x + "," + this.y);
    }

}