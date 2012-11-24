class Location {
    private Place place = new Place();

    Location() {}
    Location(Place place) {
	this.place = place;
    }

    public Place getPlace() {
	return this.place;
    }
}
