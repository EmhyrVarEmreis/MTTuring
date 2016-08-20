package xyz.morecraft.dev.mtturing.core;

import xyz.morecraft.dev.mtturing.main.Main;

import java.util.ArrayList;
import java.util.List;

public class MTRwT extends Thread {

    private List<Integer> tape;
    private List<Transition> transitionList;
    private int currentState;
    private int position;
    private int m, n;
    private int time;
    private int transitionCounter;
    private boolean isEnd;

    public MTRwT(int m, int n) {
        resetMT();
        setTransitions();
        setNumbers(m, n);
    }

    public void resetMT() {
        this.time = 700;
        this.tape = new ArrayList<>();
        this.currentState = 0;
        this.position = 0;
        this.transitionList = new ArrayList<>();
        this.isEnd = false;
        this.transitionCounter = 0;
    }

    public void setNumbers(int m, int n) {
        this.m = m;
        this.n = n;
        createTape();
    }

    public List<Integer> getTape() {
        return tape;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int stan) {
        this.currentState = stan;
    }

    public List<Transition> getTransitionList() {
        return transitionList;
    }

    public void setTimer(int t) {
        this.time = t;
    }

    public String getTapeAsString() {
        String s = "[";
        for (Integer i : tape) {
            s += " " + (tape.get(i) == -666 ? "..." : (tape.get(i) == 8 ? "B" : String.valueOf(tape.get(i))));
        }
        s += " ]";
        return s;
    }

    public String getTapeAndWhereIsHead() {
        String s = "[";
        for (int i = 0; i < tape.size(); i++) {
            s += " " + (i == position ? "|" : "") + (tape.get(i) == -666 ? "..." : (tape.get(i) == 8 ? "B" : String.valueOf(tape.get(i)))) + (i == position ? "|" : "");
        }
        s += " ]";
        return s;
    }

    /**
     * Make transition
     */
    public void makeTransition() {
        if (!isEnd) {
            transitionCounter++;
            Transition p = getNextTransition();
            if (p != null) {
                currentState = p.getStateB();
                tape.set(position, p.getSymbolB());
                position += p.getDirection();
            }
            isEnd = checkIfEndState();
        }
    }

    /**
     * Run machine
     */
    public void makeAll() {
        transferData();

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(2);
        }

        while (!isEnd) {

            makeTransition();

            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(2);
            }

            transferData();
        }

        Main.app.setVerdict(checkIfProperResult());
        Main.app.addToMainConsole("---------------------------------------------");
        Main.app.addToMainConsole(checkIfProperResult() ? "SUCCESS - PROPER RESULT" : "INCORRECT RESULT");
    }

    public int getCountOfZerosOnTape() {
        int i = 0;
        for (Integer j : tape) {
            if (j == 0) {
                i++;
            }
        }
        return i;
    }

    public boolean checkIfProperResult() {
        if (currentState == 6 && (m < n ? 0 : m - n) == getCountOfZerosOnTape()) {
            return true;
        } else {
            return false;
        }
    }

    private Transition getNextTransition() {
        for (Transition p : transitionList) {
            if (p.getStateA() == getCurrentState()) {
                if (p.getSymbolA() == tape.get(position)) {
                    return p;
                }
            }
        }
        return null;
    }


    /**
     * Generate unary tape
     */
    private void createTape() {

        for (int i = 0; i < m; i++) {
            tape.add(0);
        }

        tape.add(1);

        for (int i = 0; i < n; i++) {
            tape.add(0);
        }

        tape.add(8);
        tape.add(8);
        tape.add(8);

        tape.add(-666);
    }

    private boolean checkIfEndState() {
        if (this.currentState == 6) {
            return true;
        } else {
            return false;
        }
    }

    private void transferData() {
        Main.app.pRys.drawTape(tape.toArray(new Integer[0]), position, currentState);
        Main.app.setCountOfZerosOnTape(getCountOfZerosOnTape());
        Main.app.setTransitionCount(transitionCounter);
        Main.app.setMachineState(currentState);
        Main.app.addToMainConsole(
                "Transition: " + String.valueOf(transitionCounter)
                        + "   Tape: " + getTapeAndWhereIsHead()
                        + "   State: Q" + currentState
                        + "   Count of zeros on tape: " + String.valueOf(getCountOfZerosOnTape())
        );
    }

    private void setTransitions() {
        transitionList.add(new Transition(0, 1, 0, 8, 1));
        transitionList.add(new Transition(0, 5, 1, 8, 1));
        transitionList.add(new Transition(1, 1, 0, 0, 1));
        transitionList.add(new Transition(1, 2, 1, 1, 1));
        transitionList.add(new Transition(2, 3, 0, 1, -1));
        transitionList.add(new Transition(2, 2, 1, 1, 1));
        transitionList.add(new Transition(2, 4, 8, 8, -1));
        transitionList.add(new Transition(3, 3, 0, 0, -1));
        transitionList.add(new Transition(3, 3, 1, 1, -1));
        transitionList.add(new Transition(3, 0, 8, 8, 1));
        transitionList.add(new Transition(4, 4, 0, 0, -1));
        transitionList.add(new Transition(4, 4, 1, 8, -1));
        transitionList.add(new Transition(4, 6, 8, 0, 1));
        transitionList.add(new Transition(5, 5, 0, 8, 1));
        transitionList.add(new Transition(5, 5, 1, 8, 1));
        transitionList.add(new Transition(5, 6, 8, 8, 1));
    }

    public void run() {
        makeAll();
        Main.app.setEnabledControl(true);
    }

}
