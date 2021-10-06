import java.util.ArrayList;
import java.util.Scanner;

/**
 * Assignment 0.
 *
 * @author Charit Patel
 * Written by: Charit Patel, id: 40160658
 * <p>
 * This is the Driver class.
 * It handles all the operations that can be performed on the computer class. It includes
 * 1. Insertion of computer details (Brand name, Model name, Serial number, Price)
 * 2. Modification of computer details
 * 3. Modification of computer brand name
 * 4. Displaying computer details cheaper than specific price value
 * <p>
 * For performing task 1 and 2, password is required. Here users have given three chnaces to enter the password.
 * If password is corrct, then only they can perform task 1 and task 2.
 */
public class Driver {
    static String password = "password";    //static password value
    static int availableSpace;

    Scanner sc = new Scanner(System.in);

    /**
     * Allows user to enter password three times if password is incorrect.
     *
     * @return true if user enters correct password, otherwise false
     */
    public boolean checkPasswordCount() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Enter password:");
            String pass = sc.next();
            boolean isCorrect = checkPassword(pass);
            if (isCorrect) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the password entered by the user is correct of not.
     *
     * @param pass String representing password
     * @return true if correct, false otherwise
     */
    public static boolean checkPassword(String pass) {
        if (pass.equals(password))
            return true;
        else
            return false;
    }

    /**
     * Displays information about the specific computer.
     *
     * @param computerObject Object representing computer.
     */
    public static void displayComputerDetails(Computer computerObject) {
        System.out.println(computerObject);
    }

    /**
     * Finds all the computers having the brand name provided by the user and displays its details.
     *
     * @param brandName Brand name
     * @param inventory Array of computer objects.
     */
    public static void findComputersBy(String brandName, ArrayList<Computer> inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getBrand().equals(brandName)) {
                displayComputerDetails(inventory.get(i));
            }
        }
    }

    /**
     * Finds all the computers cheaper than the price provided by the user and displays its details.
     *
     * @param price     Price
     * @param inventory Array of computer objects.
     */
    public static void findCheaperThan(double price, ArrayList<Computer> inventory) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getPrice() < price) {
                displayComputerDetails(inventory.get(i));
            }
        }
    }

    /**
     * Creates the computer object and inserts it into the array storing objects of the computer
     *
     * @param inventory Array of computer objects.
     * @return true if computer is successfully added, otherwise false.
     */
    public boolean addComputer(ArrayList<Computer> inventory) {
        System.out.println("How many computers you want to enter?");
        int numOfComputers = sc.nextInt();
        if (numOfComputers <= availableSpace) {
            availableSpace -= numOfComputers;
            for (int i = 0; i < numOfComputers; i++) {
                Computer computer = new Computer();
                inventory.add(computer);
                System.out.println("Enter the details of " + (i + 1) + " computer");
                System.out.println("Enter brand name");
                computer.setBrand(sc.next());
                System.out.println("Enter model name");
                computer.setModel(sc.next());
                System.out.println("Enter serial number");
                computer.setSN(sc.nextLong());
                System.out.println("Enter price");
                computer.setPrice(sc.nextDouble());
                System.out.println("Computer is successfully added");
            }
            return true;
        } else {
            System.out.println("You have space for " + availableSpace + " computers only. You cannot enter more computers.");
            return false;
        }
    }

    /**
     * This is a control method.
     * It performs the all the operations mentioned in the question.
     */
    public void control() {
        System.out.println("Hello, Good morning!! Welcome to our webpage.");
        System.out.println("Enter the maximum number of computers that the computerstore can contain");
        int maxComputers = sc.nextInt();
        ArrayList<Computer> inventory = new ArrayList<Computer>();
        availableSpace = maxComputers;
        boolean run = true;
        while (run) {
            System.out.println("What do you want to do?");
            System.out.println("1. Enter new computers (password required)");
            System.out.println("2. Change information of a computer (password required)");
            System.out.println("3. Display all computers by a specific brand");
            System.out.println("4. Display all computers under a certain price");
            System.out.println("5. Quit");
            System.out.println("Please enter your choice>");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    if (checkPasswordCount()) {
                        System.out.println("Password is correct");
                        addComputer(inventory);
                    } else {
                        System.out.println("You have entered wrong password for all the three times. Please select option again.");
                    }
                    break;

                case 2:
                    if (checkPasswordCount()) {
                        System.out.println("Password is correct");
                        System.out.println("Enter the computer number you want to modify:");
                        int computerNumber = sc.nextInt();
                        Computer computer = inventory.get(computerNumber);
                        if (computer != null) {
                            System.out.println("Computer found.");
                            System.out.println("Computer #" + computerNumber);
                            displayComputerDetails(computer);
                            int flag = 0;
                            do {
                                System.out.println("What information would you like to change?");
                                System.out.println("1. brand");
                                System.out.println("2. model");
                                System.out.println("3. SN");
                                System.out.println("4. price");
                                System.out.println("5. Quit");
                                System.out.println("Enter your choice>");

                                int selectedChoice = sc.nextInt();
                                switch (selectedChoice) {
                                    case 1:
                                        System.out.println("Enter new brand name:");
                                        computer.setBrand(sc.next());
                                        System.out.println("Brand name is changed.");
                                        displayComputerDetails(computer);
                                        break;
                                    case 2:
                                        System.out.println("Enter new model name:");
                                        computer.setModel(sc.next());
                                        System.out.println("Model name is changed.");
                                        displayComputerDetails(computer);
                                        break;
                                    case 3:
                                        System.out.println("Enter new serial number:");
                                        computer.setSN(sc.nextLong());
                                        System.out.println("Serial number is changed.");
                                        displayComputerDetails(computer);
                                        break;
                                    case 4:
                                        System.out.println("Enter new price:");
                                        computer.setPrice(sc.nextDouble());
                                        System.out.println("Price is changed.");
                                        displayComputerDetails(computer);
                                        break;
                                    case 5:
                                        flag = 1;
                                        break;
                                    default:
                                        System.out.println("Please select the correct choice value.");
                                        break;
                                }
                            } while (flag == 0);
                        } else {
                            System.out.println("No computer found");
                            System.out.println("Press 1. to add new computer");
                            System.out.println("Press 2. to quit");
                            int option = sc.nextInt();
                            switch (option) {
                                case 1:
                                    System.out.println("You have selected option 1.");
                                    do {
                                        addComputer(inventory);
                                    } while (!addComputer(inventory));
                                    break;
                                case 2:
                                    System.out.println("You have selected option 2.");
                                    break;
                                default:
                                    System.out.println("Please select the correct option");
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.println("Enter the brand name you want to search:");
                    String brandName = sc.next();
                    findComputersBy(brandName, inventory);
                    break;

                case 4:
                    System.out.println("Enter the price:");
                    double price = sc.nextDouble();
                    findCheaperThan(price, inventory);
                    break;

                case 5:
                    System.out.println("Bye. See you again...");
                    run = false;
                    break;

                default:
                    System.out.println("Please select correct option.");
                    break;
            }
        }
    }

    public static void main(String args[]) {
        Driver driver = new Driver();
        driver.control();
    }
}