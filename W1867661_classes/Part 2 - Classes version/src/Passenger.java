public class Passenger {
private String fName , sName;//Encapsulation
private  double expenses;
private static int totPassengers;
//polymophism
public Passenger(){

}
//polymophism
public Passenger(String fName,String sName, double Expenses){
    this.fName = fName;
    this.sName = sName;
    this.expenses = Expenses;
    this.totPassengers++;
}
//getter encapsulation
    public String getFName(){return fName;}
    public String getSName(){return sName;}
    public double getExpenses(){return expenses;}
    public int getPassengerCount(){return totPassengers;}
    public void reducePassengerCount(){totPassengers--;}

    public void printDetails(){
        System.out.println(fName);
        System.out.println(sName);
        System.out.println(expenses);

    }
}
