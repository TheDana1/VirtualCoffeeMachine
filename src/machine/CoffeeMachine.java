package machine;

//A class that represents a coffee machine
public class CoffeeMachine {
    int waterAmount;
    int milkAmount;
    int beansAmount;
    int money;
    int cups;
    MachineStates state;

    public CoffeeMachine() {
        this.waterAmount = 400;
        this.beansAmount = 120;
        this.milkAmount = 540;
        this.money = 550;
        this.cups = 9;
        mainMenu();
    }

    public void mainMenu() {
        System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
        state = MachineStates.MAINMENU;
    }

    public void stateHandler(String input) {
        switch (state) {
            case MAINMENU:
                action(input);
                break;
            case ADDINGWATER:
                addingWater(input);
                break;
            case ADDINGMILK:
                addingMilk(input);
                break;
            case ADDINGBEANS:
                addingBeans(input);
                break;
            case ADDINGCUPS:
                addingCups(input);
                break;
            case CHOOSINGCOFFEE:
                coffee(input);
                break;
        }
    }

    public void addingWater(String input) {
        waterAmount += Integer.parseInt(input);
        state = MachineStates.ADDINGMILK;
        System.out.println("Write how many ml of milk you want to add:");
    }

    public void addingMilk(String input) {
        milkAmount += Integer.parseInt(input);
        state = MachineStates.ADDINGBEANS;
        System.out.println("Write how many grams of coffee beans you want to add:");
    }

    public void addingBeans(String input) {
        beansAmount += Integer.parseInt(input);
        state = MachineStates.ADDINGCUPS;
        System.out.println("Write how many disposable cups of coffee you want to add: ");
    }

    public void addingCups(String input) {
        this.cups += Integer.parseInt(input);
        mainMenu();
    }

    public void machineStatus(){
        System.out.printf("\nThe coffee machine has:\n" +
                        "%d ml of water\n%d ml of milk\n%d g of coffee beans\n" +
                        "%d disposable cups\n$%d of money \n", this.waterAmount, this.milkAmount,
                this.beansAmount, this.cups, this.money);
        state = MachineStates.PRINTINGSTATUS;
        mainMenu();
    }

    public void action(String input) {
        switch (input) {
            case "buy":
                buy();
                state = MachineStates.CHOOSINGCOFFEE;
                break;
            case "take":
                this.take();
                break;
            case "remaining":
                this.machineStatus();
                break;
            case "exit":
                System.exit(0);
                break;
            case "fill":
                System.out.println("Write how many ml of water you want to add:");
                state = MachineStates.ADDINGWATER;
            default:
                break;
        }
    }

    public void buy(){
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
    }


    public void coffee(String choice){
        CoffeeTypes type = CoffeeTypes.ESPRESSO;
        switch (choice) {
            case "back":
                mainMenu();
                break;
            case "1":
                type = CoffeeTypes.ESPRESSO;
                break;
            case "2":
                type = CoffeeTypes.LATTE;
                break;
            case "3":
                type = CoffeeTypes.CAPPUCCINO;
                break;
            default:
                System.out.println("Invalid choice");
                mainMenu();
                break;
        }
        if (!choice.equals("back")) {
            ingredientsCalculator(type);
            collectMoney(type.getCost());
        }
    }

    public void collectMoney(int cost ){
        money += cost;
    }

    public void ingredientsCalculator(CoffeeTypes coffeeType) {
        if (waterAmount < coffeeType.getWaterNeeded()) {
            System.out.println("Sorry, not enough water!");
            mainMenu();
        }else if (milkAmount < coffeeType.getMilkNeeded()) {
            System.out.println("Sorry, not enough milk!");
            mainMenu();
        } else if (beansAmount < coffeeType.getBeansNeeded()) {
            System.out.println("Sorry, not enough coffee beans!");
            mainMenu();
        } else if (cups < 1) {
            System.out.println("Sorry, not enough cups!");
            mainMenu();
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            waterAmount -= coffeeType.getWaterNeeded();
            beansAmount -= coffeeType.getBeansNeeded();
            milkAmount -= coffeeType.getMilkNeeded();
            cups--;
            mainMenu();
        }
    }



    public void take(){
        System.out.printf("I gave you $%d \n", this.money);
        this.money = 0;
        state = MachineStates.EMPTYINGMONEY;
        mainMenu();

    }


}
