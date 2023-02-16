import java.util.Scanner;

public class interaction {
    private static Scanner sc = new Scanner(System.in);
    private static boolean working = true;
    private static boolean isManager = false;
    private static boolean isLogin = false;

    private static User user = new User(0 , "" , "" , "" , 0);
    private static Manager manager = new Manager(0, "", "", "");
    public static void start(){
        System.out.println("Hello this is Bank managament system");
        while(working){
            if(isLogin){
                if(isManager){
                    managerBranch();
                }
                else{
                    userBranch();
                }
            }
            System.out.println("Choose an option: \nLogin -> 1\n Exit -> 2");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    login();
                    break;
                case 2:
                    working = false;
                    break;
            }
        }

    }

    public static void login(){
        System.out.println("Enter your IIN");
        String IIN = sc.next();
        System.out.println("Enter your password");
        String password = sc.next();
        if(DBclass.checkPasswordDB(IIN,password)){
            int id = DBclass.getIDWithIINDB(IIN);
            if(DBclass.isManagerDB(id)){
                manager.initializeManager(DBclass.getUserDB(id));
                isManager = true;
                isLogin = true;
            }else{
                user.initializeUser(DBclass.getUserDB(id));
                isLogin = true;
            }
        }else{
            System.out.println("Wrong IIN or password");
        }
    }

    public static void logout(){
        if(isManager){
            manager.logout();
            isManager = false;
        }else{
            user.logout();
        }
        isLogin = false;
    }

    public static void userBranch(){
        System.out.println("This is user interface!");
        while(working){
            System.out.println("Choose an option: Show info about me -> 1\nTransfer money -> 2\nWithdraw money -> 3\nLogout -> 4");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    user.printinfo();
                    break;
                case 2:
                    user.transferMoney();
                    break;
                case 3:
                    user.withdrawMoney();
                    break;
                case 4:
                    logout();
                    working = false;
                    break;
                    default:
                        System.out.println("You entered an invalid option");

            }

        }
        working = true;
    }

    public static void managerBranch(){
        System.out.println("This is manager interface!");
        while(working){
            System.out.println("Choose an option: Show info about me -> 1\nCreate user -> 2\nDelete user -> 3\nUpdate user -> 4\nUpdate balance -> 5\nView user -> 6\nView all users -> 7\nLogout -> 8");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    manager.printinfo();
                    break;
                case 2:
                    manager.createUser();
                    break;
                case 3:
                    manager.deleteUser();
                    break;
                case 4:
                    manager.updateUser();
                    break;
                case 5:
                    manager.updateBalance();
                    break;
                case 6:
                    manager.viewUser();
                    break;
                case 7:
                    manager.viewAllUsers();
                    break;
                case 8:
                    logout();
                    working = false;
                    break;
                    default:
                        System.out.println("You entered an invalid option");

            }

        }
        working = true;
    }
}
