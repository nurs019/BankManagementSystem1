import java.util.Objects;
import java.util.Scanner;

public class Manager extends User {

    private Scanner scanner = new Scanner(System.in);

    public Manager(int id , String name , String surname , String IIN){
        super(id , name , surname , IIN , 999999);
    }

    public void initializeManager(User mng) {
        super.setId(mng.getId());
        super.setName(mng.getName());
        super.setSurname(mng.getSurname());
        super.setIIN(mng.getIIN());
        super.setBalance(999999);

    }
    public void createUser(){
        System.out.println("Enter data for new user");
        System.out.println("Enter name for new user");
        String name = scanner.next();
        System.out.println("Enter surname for new user");
        String surname = scanner.next();
        System.out.println("Enter IIN for new user");
        String IIN = scanner.next();
        System.out.println("Enter balance for new user");
        int balance = scanner.nextInt();
        System.out.println("Enter password");
        String password = scanner.next();
        User user = new User(999, name, surname, IIN, balance);
        DBclass.createUserDB(user , password);
    }

    public void deleteUser(){
        System.out.println("Enter ID of user to delete");
        int id = scanner.nextInt();
        DBclass.deleteUserDB(id);
    }

    public void updateUser(){
        System.out.println("Enter ID of user to update");
        int id = scanner.nextInt();
        System.out.println("Enter new name for user");
        String name = scanner.next();
        System.out.println("Enter new surname for user");
        String surname = scanner.next();
        System.out.println("Enter new IIN for user");
        String IIN = scanner.next();
        System.out.println("Enter new balance for user");
        int balance = scanner.nextInt();
        User user = new User(id, name, surname, IIN, balance);
        DBclass.updateUserDB(user);
    }

    public void viewUser(){
        System.out.println("Enter ID of user to view");
        int id = scanner.nextInt();
        User user = DBclass.getUserDB(id);
        if(user!= null) {
            System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Surname: " + user.getSurname());
            System.out.println("IIN: " + user.getIIN());
            System.out.println("Balance: " + user.getBalance());
        }else{
            System.out.println("User not found");
        }
    }

    public void viewAllUsers(){
        for(User user : DBclass.getAllUsersDB()){
            System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Surname: " + user.getSurname());
            System.out.println("IIN: " + user.getIIN());
            System.out.println("Balance: " + user.getBalance());
        }
        System.out.println();
    }

    public void updateBalance(){
        System.out.println("Enter ID of user to update");
        int id = scanner.nextInt();
        System.out.println("Enter new balance for user");
        int balance = scanner.nextInt();
        DBclass.updateBalanceDB(id, balance);
    }

    public void logout() {
        initializeManager(new Manager(0, "", "", ""));
    }

}
