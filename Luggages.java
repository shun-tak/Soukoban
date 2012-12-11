import java.util.ArrayList;

class Luggages<E> extends ArrayList<E> {

    public boolean isOn(Place place) {
	for (E e: this) {
	    Luggage l = (Luggage) e;
	    if (l.getPlace().equals(place)) {
		return true;
	    }
	}
	return false;
    }
}
