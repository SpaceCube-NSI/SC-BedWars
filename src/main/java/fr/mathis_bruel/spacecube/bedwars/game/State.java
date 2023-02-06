package fr.mathis_bruel.spacecube.bedwars.game;

public enum State {
    WAITING,
    STARTING,
    RUNNING,
    ENDING,
    ENDED;

    private static State currentState = WAITING;

    public static State getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(State currentState) {
        State.currentState = currentState;
    }

    public static boolean isState(State state) {
        return currentState == state;
    }

    public static boolean isState(State... states) {
        for (State state : states) {
            if (currentState == state)
                return true;
        }
        return false;
    }


}
