class Souko {

    private final int height = 20;
    private final int width = 30;
    private SoukoState[][] soukoStates = new SoukoState[height][width];

    Souko() {
	for (int i = 0; i < this.soukoStates.length; i++) {
	    for (int j = 0; j < this.soukoStates[i].length; j++) {
		if (i == 0 || i == this.soukoStates.length-1
		    || j == 0 || j == this.soukoStates[i].length-1) {
		    setState(i, j, SoukoState.WALL);
		} else {
		    setState(i, j, SoukoState.SPACE);
		}
	    }
	}
	setState(7, 15, SoukoState.LOCATION);
	setState(15, 9, SoukoState.LOCATION);
	setState(15, 21, SoukoState.LOCATION);
	setState(2, 2, SoukoState.WORKER);
	setState(2, 5, SoukoState.LUGGAGE);
	setState(2, 7, SoukoState.LUGGAGE);
	setState(2, 9, SoukoState.LUGGAGE);
    }

    public SoukoState getState(int x, int y) {
	return this.soukoStates[x][y];
    }

    public void setState(int x, int y, SoukoState state) {
	if (this.getState(x, y) != SoukoState.WALL) {
	    this.soukoStates[x][y] = state;
	}
    }

    public boolean isGameCleared() {
	double countLuggageOnLocation = 0;
	double countLuggage = 0;
	double countLocation = 0;
	for (int i = 0; i < this.soukoStates.length; i++) {
	    for (int j = 0; j < this.soukoStates[i].length; j++) {
		if (this.getState(i, j) == SoukoState.LUGGAGE_ON_LOCATION) {
		    countLuggageOnLocation++;
		} else if (this.getState(i, j) == SoukoState.LUGGAGE) {
		    countLuggage++;
		} else if (this.getState(i, j) == SoukoState.LOCATION) {
		    countLocation++;
		}
	    }
	}
	if (countLuggageOnLocation == 3
	    && countLuggage == 0 && countLocation == 0) {
	    return true;
	} else {
	    return false;
	}
    }

    public void view() {
	for (int i = 0; i < this.soukoStates.length; i++) {
	    for (int j = 0; j < this.soukoStates[i].length; j++) {
		if (this.getState(i, j) == SoukoState.WALL) {
		    System.out.print("W");
		} else if (this.getState(i, j) == SoukoState.LOCATION) {
		    System.out.print("_");
		} else if (this.getState(i, j) == SoukoState.LUGGAGE) {
		    System.out.print("o");
		} else if (this.getState(i, j) == SoukoState.WORKER) {
		    System.out.print("i");
		} else {
		    System.out.print(" ");
		}
	    }
	    System.out.println();
	}
    }
}
