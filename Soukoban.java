import java.util.Scanner;

public class Soukoban {

    public static void main(String[] args) {

	System.out.println("Start the Soukoban game!");
	Souko souko = new Souko();
	Scanner scan = new Scanner(System.in);
	String input = "";
	while (!souko.isComplete()) {
	    souko.view();
	    System.out.println("Input direction: w=>up, s=>down, a=>left, d=>right");
	    input = scan.next();
	    if (input.equals("w")) {
		souko.moveWorker(Direction.UP);
	    } else if (input.equals("s")) {
		souko.moveWorker(Direction.DOWN);
	    } else if (input.equals("a")) {
		souko.moveWorker(Direction.LEFT);
	    } else if (input.equals("d")) {
		souko.moveWorker(Direction.RIGHT);
	    } else {
		System.out.println(input);
	    }
	}
	souko.view();
	System.out.println("Complete!");
    }
}
