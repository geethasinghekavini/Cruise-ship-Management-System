public class Passenger {
    private String fName , sName;
    private  double expenses;
    private static int totPassengers;

public Passenger(){ }

public Passenger(String fName,String sName, double Expenses){
    this.fName = fName;
    this.sName = sName;
    this.expenses = Expenses;
    this.totPassengers++;
}
    public String getFName(){return fName;}
    public String getSName(){return sName;}
    public double getExpenses(){return expenses;}

}
