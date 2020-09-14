import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CommandHandler commandHandler = new CommandHandler(new Manager());
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("command: ");
            if(commandHandler.parseCommand(sc.nextLine())) {
                System.out.println("logout");
                return;
            }
        }
    }
}
