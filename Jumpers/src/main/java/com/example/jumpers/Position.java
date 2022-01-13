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



    public boolean canJumpOver(Position toJumpOver) {
        if ((Math.abs(this.x - toJumpOver.x) == 2 && this.y == toJumpOver.y) || (Math.abs(this.y - toJumpOver.y) == 2 && this.y == toJumpOver.y)) {
            return true;
        }
        return false;
    }

    public boolean normalMove(Position toMove) {
        if ((Math.abs(this.x - toMove.x) == 1 && this.y == toMove.y) || (Math.abs(this.y - toMove.y) == 1 && this.y == toMove.y)) {
            return true;
        }
        return false;
    }
}