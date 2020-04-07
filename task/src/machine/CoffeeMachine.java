package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private enum State {
        READY, BUYING, FILL_ASK_WATER, FILL_ASK_MILK, FILL_ASK_BEANS, FILL_ASK_CUPS, EXIT
    }

    private enum CoffeeCup {
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6);

        final int water, milk, beans, price;

        CoffeeCup(int water, int milk, int beans, int price) {
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.price = price;
        }
    }

    private State state;

    private int water = 400;
    private int milk = 540;
    private int beans = 120;
    private int cups = 9;
    private int money = 550;

    public CoffeeMachine() {
        setReady();
    }

    public boolean isExited() {
        return state == State.EXIT;
    }

    public void process(String command) {
        switch (state) {
            case READY:
                operate(command);
                break;
            case BUYING:
                processBuying(command);
                break;
            case FILL_ASK_WATER:
                water += Integer.parseInt(command);
                setFillAskMilk();
                break;
            case FILL_ASK_MILK:
                milk += Integer.parseInt(command);
                setFillAskBeans();
                break;
            case FILL_ASK_BEANS:
                beans += Integer.parseInt(command);
                setFillAskCups();
                break;
            case FILL_ASK_CUPS:
                cups += Integer.parseInt(command);
                setReady();
                break;
            case EXIT:
                throw new IllegalStateException("I'm switched off");
            default:
                throw new IllegalArgumentException("Unknown state");
        }
    }

    private void operate(String command) {
        switch (command) {
            case "buy":
                setBuying();
                break;
            case "fill":
                setFillAskWater();
                break;
            case "take":
                take();
                break;
            case "remaining":
                reportRemaining();
                break;
            case "exit":
                state = State.EXIT;
                return;
            default:
                throw new IllegalArgumentException("Unknown command: " + command);
        }
    }

    private void processBuying(String command) {
        CoffeeCup aCup;
        switch (command) {
            case "1":
                aCup = CoffeeCup.ESPRESSO;
                break;
            case "2":
                aCup = CoffeeCup.LATTE;
                break;
            case "3":
                aCup = CoffeeCup.CAPPUCCINO;
                break;
            case "back":
                setReady();
                return;
            default:
                throw new IllegalArgumentException("Unknown coffee type");
        }
        if (check(aCup)) {
            serve(aCup);
        }
        setReady();
    }

    private void reportRemaining() {
        System.out.format("%nThe coffee machine has:%n" +
                        "%d of water%n" +
                        "%d of milk%n" +
                        "%d of coffee beans%n" +
                        "%d of disposable cups%n" +
                        "$%d of money%n",
                water, milk, beans, cups, money);
        setReady();
    }

    private void setReady() {
        if (state != null) {
            System.out.println();
        }
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        state = State.READY;
    }

    private void setBuying() {
        System.out.println();
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        state = State.BUYING;
    }

    private void serve(CoffeeCup aCup) {
        System.out.println("I have enough resources, making you a coffee!");
        water -= aCup.water;
        milk -= aCup.milk;
        beans -= aCup.beans;
        cups -= 1;
        money += aCup.price;
    }

    private boolean check(CoffeeCup aCup) {
        if (water < aCup.water) {
            System.out.println("Sorry, not enough water!");
            return false;
        }
        if (milk < aCup.milk) {
            System.out.println("Sorry, not enough milk!");
            return false;
        }
        if (beans < aCup.beans) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        }
        if (cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
            return false;
        }
        return true;
    }

    private void setFillAskWater() {
        System.out.println();
        System.out.println("Write how many ml of water do you want to add:");
        state = State.FILL_ASK_WATER;
    }

    private void setFillAskMilk() {
        System.out.println("Write how many ml of milk do you want to add:");
        state = State.FILL_ASK_MILK;
    }

    private void setFillAskBeans() {
        System.out.println("Write how many grams of coffee beans do you want to add:");
        state = State.FILL_ASK_BEANS;
    }

    private void setFillAskCups() {
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        state = State.FILL_ASK_CUPS;
    }

    private void take() {
        System.out.format("I gave you $%d%n", money);
        money = 0;
        setReady();
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            CoffeeMachine cm = new CoffeeMachine();
            while (!cm.isExited()) {
                String command = scanner.nextLine();
                cm.process(command);
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getClass().getName() + " - " + e.getMessage());
            // e.printStackTrace();
        }
    }
}