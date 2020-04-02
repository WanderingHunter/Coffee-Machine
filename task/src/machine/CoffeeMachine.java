package machine;

import java.util.Scanner;

public class CoffeeMachine {

    public static int coffeeBeans = 120;
    public static int water = 400;
    public static int milk = 540;
    public static int cups = 9;
    public static int cash = 550;

    public static void report() {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(coffeeBeans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(cash + " of money");
    }

    public static void buy(String coffeeType) {
        switch (coffeeType) {
            case "1":
                if ((water >= 250) && (coffeeBeans >= 16) && (cups >= 1)) {
                    water -= 250;
                    coffeeBeans -= 16;
                    cups -= 1;
                    cash += 4;
                    System.out.println("I have enough resources, making you a coffee!");
                } else System.out.println("Can't make a cup of coffee, please restock");
                break;
            case "2":
                if ((water >= 350) && (milk >= 75) && (coffeeBeans >= 20) && (cups >= 1)) {
                    water -= 350;
                    coffeeBeans -= 20;
                    milk -= 75;
                    cups -= 1;
                    cash += 7;
                    System.out.println("I have enough resources, making you a coffee!");
                } else System.out.println("Can't make a cup of coffee, please restock");
                break;
            case "3":
                if ((water >= 200) && (milk >= 100) && (coffeeBeans >= 12) && (cups >= 1)) {
                    water -= 200;
                    coffeeBeans -= 12;
                    milk -= 100;
                    cups -= 1;
                    cash += 6;
                    System.out.println("I have enough resources, making you a coffee!");
                } else System.out.println("Can't make a cup of coffee, please restock");
                break;
            case "back":
                startUp();
                break;
            default:
                System.out.println("Can't make a cup of coffee, please restock");
        }
    }

    public static void take() {
        System.out.println("I gave you $" + cash);
        cash = 0;
    }

    public static void fill(int fillWater, int fillMilk, int fillCoffeeBeans, int fillCups) {
        water += fillWater;
        milk += fillMilk;
        coffeeBeans += fillCoffeeBeans;
        cups += fillCups;
    }

    public static void startUp() {

        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("Write an action (buy, fill, take, exit, remaining):");
        String action = scanner.nextLine();

        switch (action) {
            case "buy":
                System.out.println("What do you want to buy? 1- espresso, 2- latte, 3- cappuccino:, or type back to return to the main menu");
                String coffeeSelection = scanner.next();
                buy(coffeeSelection);
                startUp();
                break;
            case "fill":
                System.out.println("Write how many ml of water you want to add:");
                int waterFill = scanner.nextInt();
                System.out.println("Write how many ml of milk you want to add:");
                int milkFill = scanner.nextInt();
                System.out.println("Write how many grams of coffee beans you want to add:");
                int beansFill = scanner.nextInt();
                System.out.println("Write how many disposable cups you want to add:");
                int cupsFill = scanner.nextInt();
                fill(waterFill, milkFill, beansFill, cupsFill);
                startUp();
                break;
            case "take":
                take();
                startUp();
                break;
            case "remaining":
                report();
                startUp();
                break;
            case "exit":
                System.exit(0);
                break;
            default:
                System.out.println("Please type a valid selection");
                startUp();
        }
    }

    public static void main(String[] args) {
        startUp();
    }
}
