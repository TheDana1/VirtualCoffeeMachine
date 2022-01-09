package machine;

public enum CoffeeTypes {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20,7 ),
    CAPPUCCINO(200, 100, 12, 6);

    private int cost;
    private int waterNeeded;
    private int milkNeeded;
    private int beansNeeded;


    CoffeeTypes(int water, int milk, int beans, int cost) {
        this.waterNeeded = water;
        this.milkNeeded = milk;
        this.beansNeeded = beans;
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public int getWaterNeeded() {
        return waterNeeded;
    }

    public int getMilkNeeded() {
        return milkNeeded;
    }

    public int getBeansNeeded() {
        return beansNeeded;
    }

}