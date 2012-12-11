class Souko {

    private final int height = 12;
    private final int width = 21;
    private final int numberOfLocations = 3;
    private final int numberOfLuggages = numberOfLocations;
    private Worker worker = new Worker();
    private Luggages<Luggage> luggages = new Luggages<Luggage>();
    private States states = new States(height, width);

    Souko() {
	for (int i = 0; i < height; i++) {
	    for (int j = 0; j < width; j++) {
		if (i == 0 || i == height-1
		    || j == 0 || j == width-1) {
		    states.set(i, j, State.WALL);
		} else {
		    states.set(i, j, State.SPACE);
		}
	    }
	}
	for (int i = 0; i < height; i++) {
	    if (i==5 || i==6) continue;
	    for (int j = 5; j < width; j += 5) {
		states.set(i, j, State.WALL);
	    }
	}
	states.set(1, 11, State.LOCATION);
	states.set(1, 16, State.LOCATION);
	states.set(10, 14, State.LOCATION);
	luggages.add(new Luggage(new Place(2, 7)));
	luggages.add(new Luggage(new Place(8, 7)));
	luggages.add(new Luggage(new Place(6, 3)));
    }

    private boolean movable(Place fromPlace, Direction direction) {
	Place toPlace = new Place(fromPlace.getX(), fromPlace.getY());
	Place beyondToPlace = new Place(fromPlace.getX(), fromPlace.getY());
	if (direction == Direction.UP) {
	    toPlace.setX(fromPlace.getX()-1);
	    beyondToPlace.setX(toPlace.getX()-1);
	} else if (direction == Direction.DOWN) {
	    toPlace.setX(fromPlace.getX()+1);
	    beyondToPlace.setX(toPlace.getX()+1);
	} else if (direction == Direction.LEFT) {
	    toPlace.setY(fromPlace.getY()-1);
	    beyondToPlace.setY(toPlace.getY()-1);
	} else if (direction == Direction.RIGHT) {
	    toPlace.setY(fromPlace.getY()+1);
	    beyondToPlace.setY(toPlace.getY()+1);
	}

	switch (states.get(toPlace)) {
	case SPACE:
	case LOCATION:
	    if (luggages.isOn(toPlace)) {
		if (luggages.isOn(beyondToPlace)) {
		    return false;
		}
		switch (states.get(beyondToPlace)) {
		case SPACE: return true;
		case LOCATION: return true;
		case WALL: return false;
		default: return false;
		}
	    }
	    return true;
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
	    if (states.get(luggage.getPlace()) == State.LOCATION) {
		countLuggageOnLocation++;
	    }
	}
	if (countLuggageOnLocation == numberOfLocations) {
	    return true;
	} else {
	    return false;
	}
    }

    public void view() {
	Place here = new Place();
	for (int i = 0; i < height; i++) {
	    yLoop:
	    for (int j = 0; j < width; j++) {
		if (states.get(i, j) == State.WALL) {
		    System.out.print("W");
		} else {
		    here.setX(i);
		    here.setY(j);
		    if (here.equals(worker.getPlace())) {
			System.out.print("i");
			continue yLoop;
		    }
		    if (luggages.isOn(here)) {
			System.out.print("o");
			continue yLoop;
		    }
		    if (states.get(here) == State.LOCATION) {
			System.out.print("_");
			continue yLoop;
		    }
		    System.out.print(" ");
		}
	    }
	    System.out.println();
	}
    }
}
