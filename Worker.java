class Worker {

    private Place place = new Place();

    Worker() {
	this.place.setX(2);
	this.place.setY(2);
    }

    public int getX() {
	return this.place.getX();
    }

    public int getY() {
	return this.place.getY();
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
