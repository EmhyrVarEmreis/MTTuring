package xyz.morecraft.dev.mtturing.core;

public class Transition {

    private int stateA;
    private int stateB;
    private int symbolA;
    private int symbolB;
    private int direction;

    public Transition(int stateA, int stateB, int symbolA, int symbolB, int direction) {
        super();
        this.stateA = stateA;
        this.stateB = stateB;
        this.symbolA = symbolA;
        this.symbolB = symbolB;
        this.direction = direction;
    }

    public int getStateA() {
        return stateA;
    }

    public void setStateA(int stateA) {
        this.stateA = stateA;
    }

    public int getStateB() {
        return stateB;
    }

    public void setStateB(int stateB) {
        this.stateB = stateB;
    }

    public int getSymbolA() {
        return symbolA;
    }

    public void setSymbolA(int symbolA) {
        this.symbolA = symbolA;
    }

    public int getSymbolB() {
        return symbolB;
    }

    public void setSymbolB(int symbolB) {
        this.symbolB = symbolB;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

}
