class States {
    private State[][] states;

    States(int height, int width) {
	this.states = new State[height][width];
    }

    public State get(int x, int y) {
	return this.states[x][y];
    }
    public State get(Place place) {
	return this.states[place.getX()][place.getY()];
    }

    public boolean set(int x, int y, State state) {
	if (this.states[x][y] != State.WALL) {
	    this.states[x][y] = state;
	    return true;
	}
	return false;
    }
}
