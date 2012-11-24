class Souko {

    private final int height = 20;
    private final int width = 30;
    private final int numberOfLocations = 3;
    private final int numberOfLuggages = 3;
    private Worker worker = new Worker();
    private Location[] locations = new Location[numberOfLocations];
    private Luggage[] luggages = new Luggage[numberOfLuggages];
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
	locations[0] = new Location(new Place(7, 15));
	locations[1] = new Location(new Place(15, 9));
	locations[2] = new Location(new Place(15, 21));
	luggages[0] = new Luggage(new Place(2, 5));
	luggages[1] = new Luggage(new Place(2, 7));
	luggages[2] = new Luggage(new Place(2, 9));
    }

    private SoukoState getState(int x, int y) {
	return this.soukoStates[x][y];
    }

    private void setState(int x, int y, SoukoState state) {
	if (this.getState(x, y) != SoukoState.WALL) {
	    this.soukoStates[x][y] = state;
	}
    }

    private boolean movable(Place fromPlace, Direction direction) {
	Place toPlace = new Place(fromPlace.getX(), fromPlace.getY());
	if (direction == Direction.UP) {
	    toPlace.setX(fromPlace.getX()-1);
	} else if (direction == Direction.DOWN) {
	    toPlace.setX(fromPlace.getX()+1);
	} else if (direction == Direction.LEFT) {
	    toPlace.setY(fromPlace.getY()-1);
	} else if (direction == Direction.RIGHT) {
	    toPlace.setY(fromPlace.getY()+1);
	}

	switch (this.getState(toPlace.getX(), toPlace.getY())) {
	case SPACE:
	    for (Luggage luggage: luggages) {
		if (!toPlace.equals(luggage.getPlace())) {
		    return true;
		} else {
		    if(movable(toPlace, direction)) {
			return true;
		    }
		    return false;
		}
	    }
	case WALL: return false;
	default: return false;
	}
    }

    public boolean moveWorker(Direction direction) {
	Place toPlace = new Place(this.worker.getX(), this.worker.getY());
	if (direction == Direction.UP) {
	    toPlace.setX(toPlace.getX()-1);
	} else if (direction == Direction.DOWN) {
	    toPlace.setX(toPlace.getX()+1);
	} else if (direction == Direction.LEFT) {
	    toPlace.setY(toPlace.getY()-1);
	} else if (direction == Direction.RIGHT) {
	    toPlace.setY(toPlace.getY()+1);
	}
	if (movable(this.worker.getPlace(), direction)) {
	    for (Luggage luggage: luggages) {
		if (toPlace.equals(luggage.getPlace())) {
		    luggage.move(direction);
		    break;
		}
	    }
	    return this.worker.move(direction);
	}
	return false;
    }

    public boolean isComplete() {
	double countLuggageOnLocation = 0;
	for (Luggage luggage: luggages) {
	    for (Location location: locations) {
		if (location.getPlace().equals(luggage.getPlace())) {
		    countLuggageOnLocation++;
		}
	    }
	}
	if (countLuggageOnLocation == locations.length) {
	    return true;
	} else {
	    return false;
	}
    }

    public void view() {
	Place here = new Place();
	for (int i = 0; i < this.soukoStates.length; i++) {
	    yLoop:
	    for (int j = 0; j < this.soukoStates[i].length; j++) {
		if (this.getState(i, j) == SoukoState.WALL) {
		    System.out.print("W");
		} else {
		    here.setX(i);
		    here.setY(j);
		    if (here.equals(worker.getPlace())) {
			System.out.print("i");
			continue yLoop;
		    }
		    for (Luggage luggage: luggages) {
			if (here.equals(luggage.getPlace())) {
			    System.out.print("o");
			    continue yLoop;
			}
		    }
		    for (Location location: locations) {
			if (here.equals(location.getPlace())) {
			    System.out.print("_");
			    continue yLoop;
			}
		    }
		    System.out.print(" ");
		}
	    }
	    System.out.println();
	}
    }
}
