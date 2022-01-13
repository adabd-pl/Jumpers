package com.example.jumpers;


import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Player {
    private ArrayList<Jumper> jumpers = new ArrayList<Jumper>(){};
    private Color color;
    private Board board;
    public ArrayList<Jumper> getJumpers() {
        return jumpers;
    }

    public Player(Color color, int startRow , GridPane g ) {
        this.color=color;
        for(int i=0; i<8; i++){
            jumpers.add(new Jumper(this, color, new Position(startRow , i ) , g ));
            jumpers.add(new Jumper(this, color, new Position(startRow+1 , i) , g ));
        }
    }

    public void setBoard(Board board) {
        this.board=board;
    }

    public Board getBoard() {
        return this.board;
    }
}
