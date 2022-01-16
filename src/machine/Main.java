package machine;

import java.util.Scanner;

//Main class
public class Main {

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        final Scanner scanner = new Scanner(System.in);
        while (coffeeMachine.state != MachineState.OFF) {
            coffeeMachine.stateHandler(scanner.nextLine());
        }
    }
}

