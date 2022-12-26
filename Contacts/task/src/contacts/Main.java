package contacts;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Contacts contacts = new Contacts();

        UserInterface.action(scanner, contacts);
    }
}
