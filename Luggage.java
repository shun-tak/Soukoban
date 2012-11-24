class Luggage {
    private Place place = new Place();

    Luggage() {}
    Luggage(Place place) {
	this.place = place;
    }

    public Place getPlace() {
	return this.place;
    }

    public boolean move(Direction direction) {
	if (direction == Direction.UP) {
	    this.place.setX(this.place.getX()-1);
	    return true;
	} else if (direction == Direction.DOWN) {
	    this.place.setX(this.place.getX()+1);
	    return true;
	} else if (direction == Direction.LEFT) {
	    this.place.setY(this.place.getY()-1);
	    return true;
	} else if (direction == Direction.RIGHT) {
	    this.place.setY(this.place.getY()+1);
	    return true;
	} else {
	    return false;
	}
    }
}
