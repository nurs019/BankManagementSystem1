import java.util.Scanner;

public class User {

    private int id;
    private String IIN;
    private String name;
    private String surname;

    private int balance;

    public User(int id , String name , String surname , String IIN , int balance){
        this.balance = balance;
        this.IIN = IIN;
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    public void initializeUser(User temp){
        this.balance = temp.balance;
        this.IIN = temp.IIN;
        this.name = temp.name;
        this.surname = temp.surname;
        this.id = temp.id;
    }

    public void printinfo(){
        System.out.println(IIN + " " + name + " " + surname + " " + balance);
    }

    public void transferMoney(){
        System.out.println("Enter IIN of recepient and amount to transfer");
        Scanner scanner = new Scanner(System.in);
        String IIN = scanner.next();
        int n = scanner.nextInt();
        if(balance >= n){
            DBclass.transferMoneyDB(this.id, IIN , n);
            balance -= n;
        }else{
            System.out.println("Insufficient balance");
        }
    }

    public void withdrawMoney(){
        System.out.println("Enter amount of money to withdraw");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if(balance >= n){
            DBclass.withdrawMoneyDB(this.id, n);
            balance -= n;
        }else{
            System.out.println("Insufficient balance");
        }
    }

    public String getIIN(){
        return IIN;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public int getBalance(){
        return balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIIN(String IIN) {
        this.IIN = IIN;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void logout() {
        initializeUser(new User(0, "", "", "" , 0));
    }
}
