package uk.buildtheearth.conversionplugin.observe;

import uk.buildtheearth.conversionplugin.job.step.ObservableStep;

public interface Observable {

    String getMessage();

    void updateMessage(String message);

    State getState();

    void setState(State state);

    void attach(IObserver observer);

    <T extends ObservableStep<?>> void notifyAllObservers(T step, Object... args);


    enum State {
        WAITING, READY, ACTIVE, COMPLETED, INTERRUPTED;

        public static State[] values = values();

        public State next() {
            return this == State.COMPLETED ? State.COMPLETED : values[this.ordinal() + 1];
        }
    }
}
