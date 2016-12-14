package com.sheremet.checkers.client;

import checkers.pojo.step.Step;

import java.util.*;

/**
 * Created by Oleh on 14.12.2016.
 */
public class StepSolution {

    private static int ID = 0;

    private int id;
    private List<Step> steps = new ArrayList<>();

    public StepSolution(){
        this.id = ++ID;
    }

    public StepSolution(Step step) {
        addStep(step);
        this.id = ++ID;
    }

    public void addStep(Step step) {
        steps.add(step);
    }

    public List<Step> getSteps() {
        return steps;
    }

    public int size() {
        return steps.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StepSolution solution = (StepSolution) o;

        return id == solution.id;

    }

    public StepSolution clone() {
        StepSolution solution = new StepSolution();
        for (Step s : steps) {
            solution.addStep(s);
        }

        return solution;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
