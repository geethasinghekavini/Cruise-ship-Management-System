/** Queue class **/
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Queue {
    private static int front;
    private static int rear;
    private static final Passenger[] Details=new Passenger[5];

    public Queue() {
        this.front=this.rear=-1;
    }

    public boolean isFull() {
        return (rear + 1) % Details.length == front;
    }

    public  boolean isEmpty() {
        return front == -1;
    }

    /** add data to the waiting list **/
    public  void enQueue( ) {
        if (isFull())
           System.out.println("waiting list is full!");
        if (isEmpty()||!isFull()) {
            Scanner input = new Scanner(System.in);
            System.out.println("Please enter your first name: ");
            String firstname=input.next();
            System.out.println("Please enter your surname: ");
            String surname=input.next();
            System.out.println("Please enter your expenses: ");
            double expenses=input.nextInt();
            Passenger pDetails = new Passenger(firstname, surname, expenses);
            front++;
            rear=(rear + 1) % Details.length;
            Details[rear]=pDetails;
            for(int x=0; x< Details.length; x++) {
                if(Details[x] !=null) {
                    System.out.println("Details added to waiting list:  ");
                    System.out.println(Details[x].getFName() +"  "+Details[x].getSName()+"  "+Details[x].getExpenses());
                }
            }
        }
    }
    /**get details from queue and return it**/
    public Passenger deQueue( ) {
        if (isEmpty())
            throw new NoSuchElementException();
        Passenger temp=Details[front];
        if (front == rear)
            front=rear=-1;
        else
            front=(front + 1) % Details.length;
        return temp;
    }
}
