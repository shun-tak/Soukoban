class Place {

    private int x;
    private int y;

    Place() {
	this.x = 0;
	this.y = 0;
    }
    Place(int x, int y) {
	this.x = x;
	this.y = y;
    }

    public int getX() {
	return this.x;
    }

    public void setX(int x) {
	this.x = x;
    }

    public int getY() {
	return this.y;
    }

    public void setY(int y) {
	this.y = y;
    }

    public boolean equals(Place place) {
	if (this.x == place.x && this.y == place.y) {
	    return true;
	}
	return false;
    }
}
