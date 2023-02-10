package fr.mathis_bruel.spacecube.bedwars.game;

public class ManagerState {
    private Arena arena;
    private State currentState;

    ManagerState(Arena arena) {
        this.currentState = State.WAITING;
        this.arena = arena;
    }

    public State getCurrentState() {
        return this.currentState;
    }
    public Arena getArena(){
        return this.arena;
    }

    public void setState(State state){
        this.currentState = state;
    }
    public boolean isState(State state){
        if(this.currentState == state) return true;
        else return false;
    }
}
