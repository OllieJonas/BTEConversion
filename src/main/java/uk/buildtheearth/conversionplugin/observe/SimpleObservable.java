package uk.buildtheearth.conversionplugin.observe;

import uk.buildtheearth.conversionplugin.job.step.ObservableStep;

import java.util.Vector;

public class SimpleObservable implements Observable {

    private State state;

    private String message;

    private final Vector<IObserver> observers;

    public SimpleObservable(State state, Vector<IObserver> observers) {
        this.state = state;
        this.observers = observers;
    }

    public SimpleObservable() {
        this(State.READY, new Vector<>());
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void updateMessage(String message) {
        this.message = message;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    public State nextState() {
        return state.next();
    }

    @Override
    public void attach(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public <T extends ObservableStep<?>> void notifyAllObservers(T step, Object... args) {

    }
}
