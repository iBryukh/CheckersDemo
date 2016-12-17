package com.sheremet.checkers.client;

import checkers.pojo.board.Board;
import checkers.pojo.step.Step;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;

/**
 * Created by Polomani on 15.12.2016.
 */
public class Solution {
    private Board board;
    private Step step;
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Solution(Board board, Step step, int value) {
        this.board = board;
        this.step = step;
        this.value = value;

    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }
}
