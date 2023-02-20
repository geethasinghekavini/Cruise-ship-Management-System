/** part 3 **/
/** Add Queue **/

import java.io.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Cruiser {
    static String select;
    static String answer;
    static int cabNumber=1;
    static int x;
    static int cabinNumber=1;
    static double totalExpenses=0.0;
    static int cabCount;
    static Cabin[] myCruise=new Cabin[13];
    static Scanner input=new Scanner(System.in);
    static Queue queue = new Queue();

    /** all cabins assign to "e" **/
    private static void initialise(Cabin[] myCruise) {
        for (x=1; x < 13; x++) {
            myCruise[x]=new Cabin("e", new Passenger(" ", " ", 0));
        }
    }

    public static void main(String[] args) {
        initialise(myCruise);
        menu();
    }

    private static void menu() {
        System.out.println("==========================================================");
        System.out.println("            Cruise Ship Boarding System");
        System.out.println("==========================================================");
        System.out.println("    V - View all cabins");
        System.out.println("    A -Add customer to a cabin");
        System.out.println("    E - display Empty cabins");
        System.out.println("    D - Delete customer from a cabin");
        System.out.println("    F - Find cabin from customer name");
        System.out.println("    S - Store data into a text file");
        System.out.println("    T - Passenger Expenses");
        System.out.println("    L - Load program data from file");
        System.out.println("    O - view cabins alphabetically Ordered by customer name");
        System.out.println("    T - view expenses                                      ");
        System.out.println("    Q - Quit");
        System.out.println("==========================================================");

        do {
            System.out.print("Enter your selection (v , A , E , D , F , S , L , O , Q ): ");
            select=input.next();
            String selection=select.toLowerCase();/**Convert Uppercase to Lowercase because java is case-sensitive**/

            switch (selection) {
                case "v":
                    viewCabins(myCruise);
                    break;
                case "a":
                    if(cabCount >=  12) {
                        System.out.println("----Waiting List----");
                        queue.enQueue();
                        menu();
                    }else {
                        addCustomer(myCruise);
                        break;
                    }
                    break;
                case "e":
                    displayEmptyCabins(myCruise);
                    break;
                case "d":
                    if( cabCount >=  12) {
                        System.out.println("---data adding from queue---");
                        deleteCustomerQ(myCruise);
                    } else {
                        deleteCustomer(myCruise);
                        break;
                    }
                    break;
                case "f":
                    findCabin(myCruise);
                    break;
                case "s":
                    storeData(myCruise);
                    break;
                case "l":
                    retrieveData(myCruise);
                    break;
                case "o":
                    AlphabeticalOrder(myCruise);
                    break;
                case "t":
                    ViewExpenses(myCruise);
                    break;
                case "q":
                    System.out.println("Program closed - Thank Tou !");
                    break;
                default:
                    System.out.println("Invalid Input!.Enter your selection again! ");
            }
        } while (!(select.equalsIgnoreCase("v") || select.equalsIgnoreCase("a") || select.equalsIgnoreCase("e") || select.equalsIgnoreCase("d") || select.equalsIgnoreCase("f") || select.equalsIgnoreCase("s") || select.equalsIgnoreCase("l") || select.equalsIgnoreCase("o") || select.equalsIgnoreCase("q")));/** avoid case sensitive errors**/
    }

    /** add customers to the cruise . If cabins are full customers will automatically add to the waiting list **/
    private static void addCustomer(Cabin[] myCruise) {
    try {
    try{
    while (cabNumber <  13) {
        System.out.println("enter cabin number: ");
        int cabinNumber=input.nextInt();
        input.nextLine();

        if (myCruise[cabinNumber].mainName.equals("e")) {
            System.out.println("you can add you data until all cabins are full.  " );
            if (myCruise[cabinNumber].getP1() == null) {
                System.out.println("enter passenger 1 firstname: ");
                String firstname=input.next();
                System.out.println("enter passenger 1 surname: ");
                String surname=input.next();
                System.out.println("enter passenger 1 expenses: ");
                double expenses=input.nextDouble();
                myCruise[cabinNumber].addCustomer01(firstname, surname, expenses);
                cabCount++;

            } else if (myCruise[cabinNumber].getP2() == null && cabCount < 13) {
                System.out.println("enter passenger 2 firstname: ");
                String firstname=input.next();
                System.out.println("enter passenger 2 surName: ");
                String surname=input.next();
                System.out.println("enter passenger 2 expenses: ");
                double expenses=input.nextDouble();
                myCruise[cabinNumber].addCustomer02(firstname, surname, expenses);
            }
            else if (myCruise[cabinNumber].getP3() == null && cabCount < 13) {
                System.out.println("enter passenger 3 firstname: ");
                String firstname=input.next();
                System.out.println("enter passenger 3 surName: ");
                String surname=input.next();
                System.out.println("enter passenger 3 expenses: ");
                double expenses=input.nextDouble();
                myCruise[cabinNumber].addCustomer03(firstname, surname, expenses);
            }
            if (myCruise[cabinNumber].getP1() != null || myCruise[cabinNumber].getP2() != null || myCruise[cabinNumber].getP3() != null) {
                myCruise[cabinNumber].mainName.equals("o");
            }
            do {
                if (cabCount < 12) {
                    System.out.println("do you want to add another record(Y/N): ");
                    answer=input.next();
                    String selection=answer.toLowerCase();
                    switch (selection) {
                        case "y":
                            addCustomer(myCruise);
                        case "n":
                            menu();
                    }
                } else {
                    queue.isFull();
                    menu();
                }

            } while (!(answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n")));
        }
        break;
    }
    /**avoid unnecessary crashes in the program **/
    }catch (IndexOutOfBoundsException e){
        System.out.println("invalid input! enter a number between (1-12). ");
     }
    }catch ( InputMismatchException e){
        System.out.println("invalid input! enter a number between (1-12).");
     }
    }

    /** display cabin details **/
    /** if cabins are empty shows empty **/
    /** if cabins are occupied to passengers shows   first name and surname of the passenger **/
    private static void viewCabins(Cabin[] myCruise) {
        for (int x=1; x < 13; x++) {
            if (myCruise[x].getP1() != null && myCruise[x].getP2() != null && myCruise[x].getP3() != null) {
                System.out.println("passenger 01 in Cabin " + x + " is " + myCruise[x].getP1().getFName() + " " + myCruise[x].getP1().getSName());
                System.out.println("passenger 02 in Cabin " + x + " is " + myCruise[x].getP2().getFName() + " " + myCruise[x].getP2().getSName());
                System.out.println("passenger 03 in Cabin " + x + " is " + myCruise[x].getP3().getFName() + " " + myCruise[x].getP3().getSName());
            } else if (myCruise[x].getP1() != null && myCruise[x].getP2() != null) {
                System.out.println("passenger 01 in Cabin " + x + " is " + myCruise[x].getP1().getFName() + " " + myCruise[x].getP1().getSName());
                System.out.println("passenger 02 in Cabin " + x + " is " + myCruise[x].getP2().getFName() + " " + myCruise[x].getP2().getSName());
            } else if (myCruise[x].getP2() != null && myCruise[x].getP3() != null) {
                System.out.println("passenger 01 in Cabin " + x + " is " + myCruise[x].getP2().getFName() + " " + myCruise[x].getP2().getSName());
                System.out.println("passenger 02 in Cabin " + x + " is " + myCruise[x].getP3().getFName() + " " + myCruise[x].getP3().getSName());
            } else if (myCruise[x].getP3() != null && myCruise[x].getP1() != null) {
                System.out.println("passenger 01 in Cabin " + x + " is " + myCruise[x].getP1().getFName() + " " + myCruise[x].getP1().getSName());
                System.out.println("passenger 02 in Cabin " + x + " is " + myCruise[x].getP3().getFName() + " " + myCruise[x].getP3().getSName());
            } else if (myCruise[x].getP1() != null) {
                System.out.println("passenger 01 in Cabin " + x + " is " + myCruise[x].getP1().getFName() + " " + myCruise[x].getP1().getSName());
            } else if (myCruise[x].getP2() != null) {
                System.out.println("passenger 01 in Cabin " + x + " is " + myCruise[x].getP2().getFName() + " " + myCruise[x].getP2().getSName());
            } else if (myCruise[x].getP3() != null) {
                System.out.println("passenger 01 in Cabin " + x + " is " + myCruise[x].getP3().getFName() + " " + myCruise[x].getP3().getSName());
            } else {
                System.out.println("cabin " + x + " is empty");
            }
        }
        menu();
    }

    /**display empty cabins **/
    private static void displayEmptyCabins(Cabin[] myCruise) {

        for (int x=1; x < 13; x++) {
            if (myCruise[x].getP1() == null && myCruise[x].getP2() == null && myCruise[x].getP3() == null) {
                System.out.println("Cabin " + x + " is empty");
            }
        }
        menu();
    }

     /** find  passenger from name and set passenger status to null **/
     /** do this when cruise ship is not full **/
    private static void deleteCustomer(Cabin[ ] myCruise) {

        System.out.println("enter customer name:");
        String customerName=input.next();
        boolean delete = false;
        for (int i=0; i < 12; i++) {
            try {
                if (myCruise[i].getP1() != null) {
                    if (myCruise[i].getP1().getFName().equals(customerName)) {
                        myCruise[i].setP1null();
                        delete=true;
                        System.out.println(customerName + " removed from cabin " + cabinNumber);
                    }
                }
                if (myCruise[i].getP2() != null) {
                    if (myCruise[i].getP2().getFName().equals(customerName)) {
                        myCruise[i].setP2nul();
                        delete=true;
                        System.out.println(customerName + " removed from cabin " + cabinNumber);
                    }
                }
                if (myCruise[i].getP3() != null) {
                    if (myCruise[i].getP3().getFName().equals(customerName)) {
                        myCruise[i].setP3nul();
                        delete=true;
                        System.out.println(customerName + " removed from cabin " + cabinNumber);
                    }
                }
                if (myCruise[i].getP1() == null && myCruise[i].getP2() == null && myCruise[i].getP3() == null) {
                    myCruise[cabinNumber].mainName.equals("e");
                }
            } catch (NullPointerException e) {
            }}
        if (!delete) {
            System.out.println("there is no cabin booked under " + customerName);
        }
        menu();
    }

    /** if cruise ship is full deleted passenger replace by passengers in waiting list **/
    private static void deleteCustomerQ(Cabin[] myCruise ) {

        System.out.println("enter customer name: ");
        String customerName=input.next();
        boolean deleted=false;
        for (int i=0; i < myCruise.length; i++) {
            try {
                if (myCruise[i].getP1( ) != null) {
                    if (myCruise[i].getP1().getFName().equals(customerName)) {
                        myCruise[i].setP1null();
                        queue.deQueue();
                    }
                }
                if (myCruise[i].getP2() != null) {
                    if (myCruise[i].getP2().getFName().equals(customerName)) {
                        myCruise[i].setP2nul();
                        queue.deQueue();
                    }
                }
                if (myCruise[i].getP3() != null) {
                    if (myCruise[i].getP3().getFName().equals(customerName)) {
                        myCruise[i].setP3nul();
                        queue.deQueue();
                    }
                }
                if (myCruise[i].getP1() == null && myCruise[i].getP2() == null && myCruise[i].getP3() == null) {
                    myCruise[cabinNumber].mainName.equals("e");
                }
            } catch (NoSuchElementException e) {
            }
            catch (NullPointerException e) {

            }
        }

        menu();
    }

    /** find cabin by customer name **/
    private static void findCabin( Cabin[] myCruise) {
        System.out.println("enter customer name:");
        String customerName=input.next();
        boolean customerFound=false;
        for (int i=0; i < 12; i++) {
            try {
                if (myCruise[i].getP1() != null) {
                    if (myCruise[i].getP1().getFName().equals(customerName)) {
                        customerFound=true;
                        System.out.println(customerName + " is in " + i);
                    }
                }
                if (myCruise[i].getP2() != null) {
                    if (myCruise[i].getP2().getFName().equals(customerName)) {
                        myCruise[i].setP2nul();
                        customerFound=true;
                        System.out.println(customerName + " is in " + i);
                    }
                }
                if (myCruise[i].getP3() != null) {
                    if (myCruise[i].getP3().getFName().equals(customerName)) {
                        myCruise[i].setP3nul();
                        customerFound=true;
                        System.out.println(customerName + " is in " + i);
                    }
                }
            } catch (NullPointerException e) {
            }}
        if (!customerFound) {
            System.out.println("There is no customer named  " + customerName);
        }
        menu();
    }

    /**store data to the text.txt file **/
    private static void storeData(Cabin[] myCruise) {
        try {
            BufferedWriter w=new BufferedWriter(new FileWriter("text.txt", false));
            for (int x=1; x < 13; x++) {
                if (myCruise[x].getP1() != null && myCruise[x].getP2() != null && myCruise[x].getP3() != null) {
                    w.write("passenger 01 in Cabin " + x + " is " + myCruise[x].getP1().getFName() + " " + myCruise[x].getP1().getSName()+" " +myCruise[x].getP1().getExpenses()+" / ");
                    w.write("passenger 02 in Cabin " + x + " is " + myCruise[x].getP2().getFName() + " " + myCruise[x].getP2().getSName()+" "+myCruise[x].getP2().getExpenses()+" / ");
                    w.write("passenger 03 in Cabin " + x + " is " + myCruise[x].getP3().getFName() + " " + myCruise[x].getP3().getSName()+" "+myCruise[x].getP3().getExpenses()+" / ");
                } else if (myCruise[x].getP1() != null && myCruise[x].getP2() != null) {
                    w.write("passenger 01 in Cabin " + x + " is " + myCruise[x].getP1().getFName() + " " + myCruise[x].getP1().getSName()+" " +myCruise[x].getP1().getExpenses()+" / ");
                    w.write("passenger 02 in Cabin " + x + " is " + myCruise[x].getP2().getFName() + " " + myCruise[x].getP2().getSName()+" "+myCruise[x].getP2().getExpenses()+" / ");
                } else if (myCruise[x].getP2() != null && myCruise[x].getP3() != null) {
                    w.write("passenger 01 in Cabin " + x + " is " + myCruise[x].getP2().getFName() + " " + myCruise[x].getP2().getSName()+" "+myCruise[x].getP2().getExpenses()+" / ");
                    w.write("passenger 02 in Cabin " + x + " is " + myCruise[x].getP3().getFName() + " " + myCruise[x].getP3().getSName()+" "+myCruise[x].getP3().getExpenses()+" / ");
                } else if (myCruise[x].getP3() != null && myCruise[x].getP1() != null) {
                    w.write("passenger 01 in Cabin " + x + " is " + myCruise[x].getP1().getFName() + " " + myCruise[x].getP1().getSName()+" " +myCruise[x].getP1().getExpenses()+" / ");
                    w.write("passenger 02 in Cabin " + x + " is " + myCruise[x].getP3().getFName() + " " + myCruise[x].getP3().getSName()+" "+myCruise[x].getP3().getExpenses()+" / ");
                } else if (myCruise[x].getP1() != null) {
                    w.write("passenger 01 in Cabin " + x + " is " + myCruise[x].getP1().getFName() + " " + myCruise[x].getP1().getSName()+" " +myCruise[x].getP1().getExpenses()+" / ");
                } else if (myCruise[x].getP2() != null) {
                    w.write("passenger 01 in Cabin " + x + " is " + myCruise[x].getP2().getFName() + " " + myCruise[x].getP2().getSName()+" "+myCruise[x].getP2().getExpenses()+" / ");
                } else if (myCruise[x].getP3() != null) {
                    w.write("passenger 01 in Cabin " + x + " is " + myCruise[x].getP3().getFName() + " " + myCruise[x].getP3().getSName()+" "+myCruise[x].getP3().getExpenses()+" / ");
                }
                else{
                    w.write("cabin" + x + "is empty");
                }
                w.newLine();
                w.flush();
            }
        } catch(
                IOException empty)
        {
            System.err.println("file not found");
        }
        System.out.println("Data saved to the text file");

        menu();
    }

    /** load data from the text file **/
    private static void retrieveData(Cabin[] myCruise) {
        try {
            BufferedReader r=new BufferedReader(new FileReader("text.txt"));
            for (int x=1; x < myCruise.length; x++) {
                String read=r.readLine();
                if (read.equals("cabin " + x + "is empty")) {
                    read="empty";
                }
                myCruise[x].mainName= read;
            }
        } catch (IOException empty) {
            System.err.println("file not found");
        }
        System.out.println("data loaded from text file");
        menu();
    }

    /**order customers using their first name **/

    private static void AlphabeticalOrder( Cabin[] myCruise) {
        String fNames =String.valueOf(myCruise[cabinNumber].passenger1.getFName());
        String[] names=fNames.split(" ");
        for (int x=1; x < names.length; x++) {
            for (int j=0; j < names.length; j++) {
                if (names[j].compareTo(names[j + 1]) > 0) {
                    String temp=names[j];
                    names[j]=names[j + 1];
                    names[j + 1]=temp;
                }
            }
        }
        for (int i=1; i < names.length; i++) {
            for (int j=i + 1; j < names.length; j++) {
                System.out.println(names[i]);
            }
        }
        menu();
    }

    /**display expences per customer and total expences **/
    private static void ViewExpenses(Cabin[] myCruise) {

        for (int x=1; x < 13; x++) {
            if (myCruise[x].getP1() != null && myCruise[x].getP2() != null && myCruise[x].getP3() != null) {
                System.out.println("Expenses of passenger 01 in cabin " + x + ": " + myCruise[x].getP1().getExpenses());
                System.out.println("Expenses of passenger 02 in cabin " + x + ": " + myCruise[x].getP2().getExpenses());
                System.out.println("Expenses of passenger 03 in cabin " + x + ": " + myCruise[x].getP3().getExpenses());
                totalExpenses=totalExpenses + myCruise[x].getP1().getExpenses() + myCruise[x].getP2().getExpenses() + myCruise[x].getP3().getExpenses();

            } else if (myCruise[x].getP1() != null && myCruise[x].getP2() != null) {
                System.out.println("Expenses of passenger 01 in cabin " + x + ": " + myCruise[x].getP1().getExpenses());
                System.out.println("Expenses of passenger 02 in cabin " + x + ": " + myCruise[x].getP2().getExpenses());
                totalExpenses=totalExpenses + myCruise[x].getP1().getExpenses() + myCruise[x].getP2().getExpenses();

            } else if (myCruise[x].getP2() != null && myCruise[x].getP3() != null) {
                System.out.println("Expenses of passenger 02 in cabin " + x + ": " + myCruise[x].getP2().getExpenses());
                System.out.println("Expenses of passenger 03 in cabin " + x + ": " + myCruise[x].getP3().getExpenses());
                totalExpenses=totalExpenses + myCruise[x].getP2().getExpenses() + myCruise[x].getP3().getExpenses();

            } else if (myCruise[x].getP3() != null && myCruise[x].getP1() != null) {
                System.out.println("Expenses of passenger 01 in cabin " + x + ": " + myCruise[x].getP1().getExpenses());
                System.out.println("Expenses of passenger 03 in cabin " + x + ": " + myCruise[x].getP3().getExpenses());
                totalExpenses=totalExpenses + myCruise[x].getP1().getExpenses() + myCruise[x].getP3().getExpenses();

            } else if (myCruise[x].getP1() != null) {
                System.out.println("Expenses of passenger 01 in cabin " + x + ": " + myCruise[x].getP1().getExpenses());
                totalExpenses=totalExpenses + myCruise[x].getP1().getExpenses();

            } else if (myCruise[x].getP2() != null) {
                System.out.println("Expenses of passenger 02 in cabin " + x + ": " + myCruise[x].getP2().getExpenses());
                totalExpenses=totalExpenses + myCruise[x].getP2().getExpenses();

            } else if (myCruise[x].getP3() != null) {
                System.out.println("Expenses of passenger 03 in cabin " + x + ": " + myCruise[x].getP3().getExpenses());
                totalExpenses=totalExpenses + myCruise[x].getP3().getExpenses();
            }
        }
        System.out.println("total: " + totalExpenses);
        menu();
    }
}







