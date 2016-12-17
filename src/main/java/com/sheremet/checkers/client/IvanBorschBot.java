package com.sheremet.checkers.client;

import checkers.client.CheckersBot;
import checkers.pojo.board.Board;
import checkers.pojo.board.Letters;
import checkers.pojo.board.Numbers;
import checkers.pojo.checker.Checker;
import checkers.pojo.checker.CheckerColor;
import checkers.pojo.checker.CheckerType;
import checkers.pojo.checker.Position;
import checkers.pojo.step.Step;
import checkers.pojo.board.StepCollector;
import checkers.pojo.step.StepUnit;
import checkers.utils.Validator;
import com.sheremet.checkers.client.units.ValidationUtil;

import java.util.*;

/**
 * Created by Oleh on 14.12.2016.
 */
public class IvanBorschBot implements CheckersBot {

    private CheckerColor color;
    private final String name;
    private BoardRenderer renderer;
    private Validator validator;
    private HashMap<Position, Integer> weights = new HashMap<>();


    public IvanBorschBot(BoardRenderer renderer) {
        this.name = "ivan_borsch_super_bot";
        this.renderer = renderer;
        this.validator = new Validator();
        initWeights();
    }

    @Override
    public Step next(final Board board) {
        return getMinMax(getSolutions(board), true);
    }

    public Step getMinMax(List<Solution> solutions, boolean max) {
        if (!max) {
            solutions.sort(new Comparator<Solution>() {
                @Override
                public int compare(Solution o1, Solution o2) {
                    return o1.getValue() - o2.getValue();
                }
            });
        } else {
            solutions.sort(new Comparator<Solution>() {
                @Override
                public int compare(Solution o1, Solution o2) {
                    return o2.getValue() - o1.getValue();
                }
            });
        }
        if (Math.random() < 0.33) {
            int randIndex = randInRange(0, Math.min(5, solutions.size()-1));
            return solutions.get(randIndex).getStep();
        } else {
            return solutions.get(0).getStep();
        }
    }

    private List<Solution> getSolutions(Board origin) {
        StepCollector stepCollector = new StepCollector();
        List<Step> steps = stepCollector.getSteps(origin);
        List<Solution> solutions = new ArrayList<>();
        for (Step step: steps) {
            try {
                Board clone = origin.clone();
                clone.apply(step);
                solutions.add(new Solution(clone, step, evaluate(clone)));
            } catch (Exception e) {

            }
        }
        return solutions;
    }

    private void initWeights () {
        weights.put(new Position(Letters.A, Numbers._1), 4);
        weights.put(new Position(Letters.A, Numbers._3), 4);
        weights.put(new Position(Letters.A, Numbers._5), 4);
        weights.put(new Position(Letters.A, Numbers._7), 4);
        weights.put(new Position(Letters.B, Numbers._2), 3);
        weights.put(new Position(Letters.B, Numbers._4), 3);
        weights.put(new Position(Letters.B, Numbers._6), 3);
        weights.put(new Position(Letters.B, Numbers._8), 4);

        weights.put(new Position(Letters.C, Numbers._1), 4);
        weights.put(new Position(Letters.C, Numbers._3), 2);
        weights.put(new Position(Letters.C, Numbers._5), 2);
        weights.put(new Position(Letters.C, Numbers._7), 3);
        weights.put(new Position(Letters.D, Numbers._2), 3);
        weights.put(new Position(Letters.D, Numbers._4), 1);
        weights.put(new Position(Letters.D, Numbers._6), 2);
        weights.put(new Position(Letters.D, Numbers._8), 4);

        weights.put(new Position(Letters.E, Numbers._1), 4);
        weights.put(new Position(Letters.E, Numbers._3), 2);
        weights.put(new Position(Letters.E, Numbers._5), 1);
        weights.put(new Position(Letters.E, Numbers._7), 3);
        weights.put(new Position(Letters.F, Numbers._2), 3);
        weights.put(new Position(Letters.F, Numbers._4), 2);
        weights.put(new Position(Letters.F, Numbers._6), 2);
        weights.put(new Position(Letters.F, Numbers._8), 4);

        weights.put(new Position(Letters.G, Numbers._1), 4);
        weights.put(new Position(Letters.G, Numbers._3), 3);
        weights.put(new Position(Letters.G, Numbers._5), 3);
        weights.put(new Position(Letters.G, Numbers._7), 3);
        weights.put(new Position(Letters.H, Numbers._2), 4);
        weights.put(new Position(Letters.H, Numbers._4), 4);
        weights.put(new Position(Letters.H, Numbers._6), 4);
        weights.put(new Position(Letters.H, Numbers._8), 4);
    }

    private int evaluate(Board board) {
        int res = 0;
        for (Checker checker:board.getCheckers()) {
            int coef = (checker.getColor().equals(color)) ? 1 : -1;
            if (checker.getType().equals(CheckerType.QUEEN))
                coef*=2;
            res += coef*weights.get(checker.getPosition());
        }
        return res;
    }

    public int randInRange (int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    @Override
    public void onGameStart(CheckerColor checkerColor) {
        color = checkerColor;
    }

    @Override
    public void onGameEnd(String msg) {
        renderer.showMsg(msg);
    }

    @Override
    public String clientBotName() {
        return name;
    }

    @Override
    public void show(Board board) {
        renderer.render(board);
    }
}
