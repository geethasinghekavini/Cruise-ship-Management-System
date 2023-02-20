public class Cabin {

    public String mainName;
    public Passenger passenger1,passenger2,passenger3;

    public Cabin(String mainName, Passenger passenger){
        this.mainName =mainName;
    }

    public void addCustomer01(String fName, String sName, double expenses){
        passenger1=new Passenger(fName,sName,expenses);
    }

    public void addCustomer02(String fName, String sName, double expenses){
        passenger2=new Passenger(fName,sName,expenses);
    }

    public void addCustomer03(String fName, String sName, double expenses){
        passenger3=new Passenger(fName,sName,expenses);
    }

    public Passenger getP1(){return passenger1;}
    public Passenger getP2(){return passenger2;}
    public Passenger getP3(){return passenger3;}

    public  void setP1null(){ passenger1 = null;}
    public  void setP2nul(){  passenger2 = null;}
    public  void setP3nul(){  passenger3 = null;}
}