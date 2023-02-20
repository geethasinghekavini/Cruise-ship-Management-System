/** part 1 **/
/** Arrays version **/

import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    static String select;
    static String answer;
    static String customerName;
    static int cabinNumber=0;
    static String[] cruise=new String[13];
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        initialize(cruise);
        menu();
    }

    public static void initialize(String[] CruiseRef) {
        /**Cruise array contains null values at the beginning.String value "empty" assigned to null values.**/
        for (int x=1; x < 13; x++) {
            CruiseRef[x]="empty";
        }
    }

    private static void menu(){
        System.out.println("            Cruise Ship Boarding System");
        System.out.println("---------------------------------------------------------");
        System.out.println("    V - View all cabins");
        System.out.println("    A -Add customer to a cabin");
        System.out.println("    E - display Empty cabins");
        System.out.println("    D - Delete customer from a cabin");
        System.out.println("    F - Find cabin from customer name");
        System.out.println("    S - Store data into a text file");
        System.out.println("    L - Load program data from file");
        System.out.println("    O - view cabins alphabetically Ordered by customer name");
        System.out.println("    Q - Quit");
        System.out.println("----------------------------------------------------------");


        do {
            System.out.print("Enter your selection (v , A , E , D , F , S , L , O , Q ): ");
            select=input.next();
            String selection = select.toLowerCase();/**Convert Uppercase to Lowercase because java is case-sensitive**/

            switch (selection) {
                case "v":
                    viewCabins();
                    break;
                case "a":
                    addCustomer();
                    break;
                case "e":
                    displayEmptyCabins();
                    break;
                case "d":
                    deleteCustomer();
                    break;
                case "f":
                    findRoom();
                    break;
                case "s":
                    storeData();
                    break;
                case "l":
                    retriveData();
                    break;
                case "o":
                    AlphabeticalOrder();
                    break;
                case "q":
                    System.out.println("Program closed - Thank Tou !");
                    break;
                default:
                    System.out.println("Invalid Input!.Enter your selection again: ");
            }
        }
        while (!(select.equalsIgnoreCase("v") || select.equalsIgnoreCase("a") || select.equalsIgnoreCase("e") || select.equalsIgnoreCase("d") || select.equalsIgnoreCase("f") || select.equalsIgnoreCase("s") || select.equalsIgnoreCase("l") || select.equalsIgnoreCase("o") || select.equalsIgnoreCase("q")));
        /** looping while condition is true**/
    }

    /** if cabins are not empty display customer name **/
    /** if cabins are  empty display empty **/
    /** takes cabin numbers less than 13 **/
    private static void viewCabins() {
        while (cabinNumber < 13) {
            for (int x=1; x < 13; x++) {
                if (!(cruise[x].equals("empty"))) {
                    System.out.println("Cabin  " + x + " -  " + cruise[x]);
                } else {
                    System.out.println("cabin " + x + " is empty.");
                }
            }
            break;
        }
        menu();
    }
    /** if incorrectCabinNumber=false customer will add  to the cabin **/
    private static void addCustomer() {
        boolean incorrectCabinNumber;
        do {
            incorrectCabinNumber=false;
            try {
                System.out.println("Enter cabin number:  ");
                cabinNumber=input.nextInt();

                if (!(cruise[cabinNumber].equals("empty"))) {
                    incorrectCabinNumber=true;
                    System.out.println("this cabin is reserved for " + cruise[cabinNumber]);
                }
                else if (cabinNumber > 0 && cabinNumber<13){
                    incorrectCabinNumber=false;
                }
                else {
                    incorrectCabinNumber=true;
                    System.out.println("Invalid input. Try again!");
                }
            }
            /**if entered number is incorrect avoid crashing the program **/
            catch (IndexOutOfBoundsException empty) {
                incorrectCabinNumber=true;
                System.out.println("Invalid input!");
                input.next();
            }
            catch (InputMismatchException empty) {
                incorrectCabinNumber=true;
                System.out.println("invalid input!");
                input.next();
            }
        } while (incorrectCabinNumber);
        System.out.println("Enter customer name: ");
        customerName = input.next();
        cruise[cabinNumber]=customerName;

        do {
            System.out.println("do you want to add another record(Y/N): ");
            answer = input.next();
            String selection = answer.toLowerCase();
            switch (selection) {
                case "y":
                    addCustomer();
                case "n":
                    menu();
            }
        } while (!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")));
    }
    /**display empty cabins only **/
    private static void displayEmptyCabins() {
        for (int x=1; x < 13; x++) {
            if (cruise[x].equals("empty")) {
                System.out.println("cabin " + x + " is empty");
            }
        }
        menu();
    }

    /**if invalidInput is false cabin number assign to empty**/
    private static void deleteCustomer() {
        boolean invalidInput;
        do {
            invalidInput=false;
            try {
                System.out.println("enter cabin number:");
                cabinNumber=input.nextInt();

                if (!cruise[cabinNumber].equals("empty")) {
                    invalidInput=false;
                    cruise[cabinNumber]="empty";
                } else {
                    invalidInput=true;
                    System.out.println(cabinNumber + " is empty");

                }
            /**avoid unnecessary crashes**/
            } catch (InputMismatchException empty) {
                invalidInput=true;
                System.out.println("invalid cabin number!");
                input.next();
            } catch (IndexOutOfBoundsException empty) {
                invalidInput=true;
                System.out.println("invalid cabin number!");
                input.next();
            }
        } while (invalidInput);
        System.out.println("cabin " + cabinNumber + " is empty now!");
        menu();
    }
   /** finds customer from name**/
    private static void findRoom() {
        System.out.println("enter customers' name: ");
        boolean customerFound=false;
        String find=input.next();

        for (int y=1; y < 13; y++) {
            if (cruise[y].equalsIgnoreCase(find)) {
                customerFound=true;
                System.out.println(find + " is in cabin " + y);
                menu();
            }

        }
        /** customer not in cruise **/
        if (customerFound == false) {
            System.out.println(find + " is not staying in our cruise");
            menu();
        }

    }

    /**order customer names alphabetically**/
    private static void AlphabeticalOrder() {
        int index=0;
        String[] names=new String[13];

        for (int x=1; x < 13; x++) {
            names[x]=cruise[x].toLowerCase();/**Avoid case sensitive errors**/
        }
        for (int i=1; i < names.length; i++) {
            for (int j=i+1; j < names.length; j++) {
                if (names[j].compareTo(names[i])<1) {
                    String temp=names[j];
                    names[j]=names[i];
                    names[i]=temp;
                }
            }

        }
        for (int x=1; x < names.length; x++) {
            if (!(names[x].equals("empty"))) {
                for (int i=1; i < cruise.length; i++) {
                    if (cruise[i].toLowerCase().equals(names[x])) {
                        index=i;
                    }
                }
                System.out.println(names[x] + " is in cabin " + index);
            }
        }
        menu();
    }
    /**store data in to text.txt file**/
    private static void storeData() {
        try {
            BufferedWriter w=new BufferedWriter(new FileWriter("text.txt", false));
            for (int x=1; x < cruise.length; x++) {
                String file;
                file=cruise[x];
                if (file.equals("empty")) {
                    w.write("cabin " + x + " is empty.");
                } else {
                    w.write(file);
                }
                w.newLine();
                w.flush();
            }
        } catch (IOException empty) {
            System.err.println("file not found");
        }
        System.out.println("Data saved to the text file");
        menu();
    }

    /** if data change in the text file it will load to the program **/

    private static void retriveData() {
        try {
            BufferedReader r =new BufferedReader(new FileReader("text.txt"));
            for (int x=1; x < cruise.length; x++) {
                String read=r.readLine();
                if (read.equals("cabin " + x + " is empty.")) {
                    read="empty";
                }
                cruise[x]=read;
            }
        } catch (IOException empty) {
            System.err.println("file not found");
        }
        System.out.println("data loaded from text file");
        menu();
    }
}






