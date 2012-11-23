import java.util.Scanner;

public class Soukoban {

    public static void main(String[] args) {

	System.out.println("Soukoban start!");
	Souko souko = new Souko();
	Scanner scan = new Scanner(System.in);
	String input = "";
	while (!souko.isGameCleared()) {
	    souko.view();
	    System.out.println("進行方向を入力してください． (w: up, s: down, a: left, d: right)");
	    input = scan.next();
	    System.out.println(input);
	}
    }
}
