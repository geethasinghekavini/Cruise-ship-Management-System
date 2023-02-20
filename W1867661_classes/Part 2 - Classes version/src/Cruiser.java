/** part 2 **/
/** classes version **/
import java.io.*;
import java.util.Scanner;

public class Cruiser {
    static String select;
    static int cabNumber=1;
    static int x;
    static int cabinNumber=1;
    static  double totalExpenses =0.0;
    static Cabin[] myCruise=new Cabin[13];
    static Scanner input=new Scanner(System.in);


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
                    addCustomer(myCruise);
                    break;
                case "e":
                    displayEmptyCabins(myCruise);
                    break;
                case "d":
                    deleteCustomer(myCruise);
                    break;
                case "f":
                    findRoom(myCruise);
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
                    System.out.println("Invalid Input!.Enter your selection again: ");
            }
        } while (!(select.equalsIgnoreCase("v") || select.equalsIgnoreCase("a") || select.equalsIgnoreCase("e") || select.equalsIgnoreCase("d") || select.equalsIgnoreCase("f") || select.equalsIgnoreCase("s") || select.equalsIgnoreCase("l") || select.equalsIgnoreCase("o") || select.equalsIgnoreCase("q")));
    }

    /** adds up to three customers in to one cabin**/
    private static void addCustomer(Cabin[] myCruise){
        while (cabNumber < 13) {
            System.out.println("enter cabin number: ");
            int cabinNumber=input.nextInt( );
            input.nextLine();
            if (myCruise[cabinNumber].mainName.equals("e")){
                if (myCruise[cabinNumber].getP1() == null){
                    System.out.println("enter firstname: ");
                    String firstname=input.next();
                    System.out.println("enter surname: ");
                    String surname=input.next();
                    System.out.println("enter p1 expenses: ");
                    double expenses=input.nextDouble();
                    myCruise[cabinNumber].addCustomer01(firstname, surname, expenses);

                } else if (myCruise[cabinNumber].getP2() == null){
                    System.out.println("enter firstname: ");
                    String firstname=input.next();
                    System.out.println("enter surName: ");
                    String surname=input.next();
                    System.out.println("enter p2 expenses: ");
                    double expenses=input.nextDouble();
                    myCruise[cabinNumber].addCustomer02(firstname, surname, expenses);

                } else if (myCruise[cabinNumber].getP3() == null) {
                    System.out.println("enter firstname: ");
                    String firstname=input.next();
                    System.out.println("enter surName: ");
                    String surname=input.next();
                    System.out.println("enter p3 expenses: ");
                    double expenses=input.nextDouble();
                    myCruise[cabinNumber].addCustomer03(firstname, surname, expenses);
                } else {
                    System.out.println("Cabin is full ! ");
                }
            }
            if (myCruise[cabinNumber].getP1() != null || myCruise[cabinNumber].getP2() != null || myCruise[cabinNumber].getP3() != null) {
                myCruise[cabinNumber].mainName.equals("o");
            }
            break;
        }
        menu();
    }

     /** display cabin status **/
    private static void viewCabins(Cabin[] myCruise){
        for (int x=1; x < 13; x++) {
            /** check for passengers in cabin are not equals to null **/
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
        // int cabinNumber=1;
        for (int x=1; x < 13; x++) {
            if (myCruise[x].getP1() == null && myCruise[x].getP2() == null && myCruise[x].getP3() == null) {
                System.out.println("Cabin " + x + " is empty");
            }
        }
        menu();
    }

    /** find customer by  name and delete **/
    private static void deleteCustomer(Cabin[] myCruise) {

        System.out.println("enter customer name:");
        String customerName=input.next();
        boolean deleted = false;
        for (int i=0; i < 12; i++) {
            try {
                if (myCruise[i].getP1() != null) {
                    if (myCruise[i].getP1().getFName().equals(customerName)) {
                        myCruise[i].setP1null();
                        deleted=true;
                        System.out.println(customerName + " removed from cabin " + i);
                    }
                }
                if (myCruise[i].getP2() != null) {
                    if (myCruise[i].getP2().getFName().equals(customerName)) {
                        myCruise[i].setP2nul();
                        deleted=true;
                        System.out.println(customerName + " removed from cabin " + i);
                    }
                }
                if (myCruise[i].getP3() != null) {
                    if (myCruise[i].getP3().getFName().equals(customerName)) {
                        myCruise[i].setP3nul();
                        deleted=true;
                        System.out.println(customerName + " removed from cabin " + i);
                    }
                }
                if (myCruise[i].getP1() == null && myCruise[i].getP2() == null && myCruise[i].getP3() == null) {
                    myCruise[cabinNumber].mainName.equals("e");
                }
            } catch (NullPointerException e) {
            }}
            if (!deleted) {
                System.out.println("there is no cabin booked under " + customerName);
            }
        menu();
    }

     /**finds customer using first name **/
    private static void findRoom(Cabin[] myCruise) {
        System.out.println("enter customer name:");
        String customerName=input.next();
        boolean customerFound=false;
        for (int i=0; i < 12; i++) {
            try {
                if (myCruise[i].getP1() != null) {
                    if (myCruise[i].getP1().getFName().equals(customerName)) {
                        customerFound=true;
                        System.out.println(customerName + " is in cabin  " + i);
                    }
                }
                if (myCruise[i].getP2() != null) {
                    if (myCruise[i].getP2().getFName().equals(customerName)) {
                        myCruise[i].setP2nul();
                        customerFound=true;
                        System.out.println(customerName + " is in cabin  " + i);
                    }
                }
                if (myCruise[i].getP3() != null) {
                    if (myCruise[i].getP3().getFName().equals(customerName)) {
                        myCruise[i].setP3nul();
                        customerFound=true;
                        System.out.println(customerName + " is in cabin " + i);
                    }
                }

            } catch (NullPointerException e) {
            }}
        if (!customerFound) {
            System.out.println("There is no customer named  " + customerName);
        }
        menu();
    }

    /** store data to text.txt file **/
    private static void storeData(Cabin[] myCruise) {
        try {
            BufferedWriter w=new BufferedWriter(new FileWriter("text.txt", false));
            for (int x=1; x < 13; x++){
                /**check for passengers in cabin are not equals to null**/
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
            /**  avoid crashes in program when  IO operation fails **/
        } catch(
                IOException empty)
        {
            System.err.println("file not found");
        }
        System.out.println("Data saved to the text file");
        menu();
    }
     /**load data from text file**/
    private static void retrieveData(Cabin[] myCruise) {

        int lines = 1;

                try {
                    BufferedReader r=new BufferedReader(new FileReader("text.txt"));
                     Scanner input = new Scanner(r);
                     String fileLine;

                     while(input.hasNext()){
                         fileLine = input.nextLine();
                         System.out.println(fileLine);
                         lines++;
                     }
                     input.close();
                    /**  avoid crahes in program when  IO operation fails **/
                } catch (IOException empty) {
                    System.err.println("file not found");
                }
                System.out.println("data loaded from text file");
                menu();
            }

    /**order customers first name alphabetically**/
    private static void AlphabeticalOrder(Cabin[] myCruise) {
        try {

            int index=0;
            String[] names=new String[13];
            for (int x=1; x < 13; x++){
                names[x]=myCruise[x].mainName.toLowerCase();/**Avoid case sensitive errors**/
            }
            for (int i=1; i < names.length; i++) {
                for (int j=i + 1; j < names.length; j++){
                    if (names[j].compareTo(names[i]) < 1){
                        String temp=names[j];
                        names[j]=names[i];
                        names[i]=temp;
                    }
                }
            }

            for (int x=1; x < names.length; x++) {
                if (!(names[x].equals("empty"))) {
                    for (int i=1; i < myCruise.length; i++) {
                        if (myCruise[i].getP1().getFName().toLowerCase().equals(names[x])) {
                            index=i;
                        } else if (myCruise[i].getP2().getFName().toLowerCase().equals(names[x])) {
                            index=i;
                        } else if (myCruise[i].getP3().getFName().toLowerCase().equals(names[x])) {
                            index=i;
                        }
                    }
                    System.out.println(names[x] + " is in cabin " + index);
                }
            }
        }catch(NullPointerException e){

        }
            menu();
        }
    /** outputs expenses per customer and total expense **/
    private static void ViewExpenses(Cabin[] myCruise) {
        for (int x=1; x < 13; x++) {
            if (myCruise[x].getP1() != null && myCruise[x].getP2() != null && myCruise[x].getP3() != null) {
                System.out.println("Expenses of passenger 01 in cabin " + x + " : " + myCruise[x].getP1().getExpenses());
                System.out.println("Expenses of passenger 02 in cabin " + x + " : " + myCruise[x].getP2().getExpenses());
                System.out.println("Expenses of passenger 03 in cabin " + x + " : " + myCruise[x].getP3().getExpenses());
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
            System.out.println("total: "+totalExpenses);


        menu();
    }
}






