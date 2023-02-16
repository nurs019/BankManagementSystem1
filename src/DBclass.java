import java.sql.*;
import java.util.ArrayList;

public class DBclass {
    private static final String log = "postgres";
    private static final String pass = "1234";
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";

    private static Connection conn;

    public void createConnection(){
        try{
            conn = DriverManager.getConnection(url, log, pass);
            System.out.println("Connection established");
        }catch (SQLException e){
            System.out.println("Error of connection");
        }
    }

    public static void deleteUserDB(int id){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("DELETE FROM users WHERE id = " + id + "RETURNING true");
            if(rs.next()){
                System.out.println("User deleted");
            }
        }catch (SQLException e){
            System.out.println("Error deleting user");
        }
    }

    public static void updateUserDB(User temp){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("UPDATE users SET name = '" + temp.getName() + "', surname = '" + temp.getSurname() + "', IIN = '" + temp.getIIN() + "', balance = '" + temp.getBalance() + "' WHERE id = " + temp.getId() + "RETURNING true");
            if(rs.next()){
                System.out.println("User updated");
            }
        }catch (SQLException e){
            System.out.println("Error updating user");
        }
    }

    public static User getUserDB(int id){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id = " + id);
            if(rs.next()){
                return new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("IIN"), rs.getInt("balance"));
            }
        }catch (SQLException e){
            System.out.println("Error getting user");
        }
        return null;
    }

    public static ArrayList<User> getAllUsersDB(){
        ArrayList<User> users = new ArrayList<>();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while(rs.next()){
                users.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("IIN"), rs.getInt("balance")));
            }
        }catch (SQLException e){
            System.out.println("Error getting all users");
        }
        return users;
    }

    public static void createUserDB(User temp, String password){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("INSERT INTO users(name , surname , IIN , balance , password) VALUES ('" + temp.getName() + "','" + temp.getSurname() + "','" + temp.getIIN() + "','" + temp.getBalance() + "','" + password + "') RETURNING true");
            if(rs.next()){
                System.out.println("User created");
            }
        }catch (SQLException e){
            System.out.println("Error creating user");
        }
    }

    public static void updateBalanceDB(int id , int balance){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("UPDATE users SET balance = '" + balance + "' WHERE id = " + id + "RETURNING true");
            if(rs.next()){
                System.out.println("Balance updated");
            }
        }catch (SQLException e){
            System.out.println("Error updating balance");
        }
    }

    public static int getBalanceDB(int id){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT balance FROM users WHERE id = " + id);
            if(rs.next()){
                return rs.getInt("balance");
            }
        }catch (SQLException e){
            System.out.println("Error getting balance");
        }
        return 0;
    }

    public static int getIDWithIINDB(String IIN){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM users WHERE IIN = '" + IIN + "'");
            if(rs.next()){
                return rs.getInt("id");
            }
        }catch (SQLException e){
            System.out.println("Error getting id");
        }
        return 0;
    }

    public static void transferMoneyDB(int id1, String IIN, int amount){
        try{
            int balance1 = getBalanceDB(id1);
            int id2 = getIDWithIINDB(IIN);
            int balance2 = getBalanceDB(id2);
            updateBalanceDB(id1, balance1 - amount);
            updateBalanceDB(id2, balance2 + amount);
        }catch(Exception e){
            System.out.println("Error transferring money");
        }

    }

    public static void withdrawMoneyDB(int id1, int amount){
        try{
            int balance1 = getBalanceDB(id1);
            updateBalanceDB(id1, balance1 - amount);
        }catch (Exception e){
            System.out.println("Error withdrawing money");
        }

    }

    public static void updatePasswordDB(int id, String password){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("UPDATE users SET password = '" + password + "' WHERE id = " + id + "RETURNING true");
            if(rs.next()){
                System.out.println("Password updated");
            }
        }catch (SQLException e){
            System.out.println("Error updating password");
        }
    }

    public static boolean checkPasswordDB(String IIN, String password){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT password FROM users WHERE IIN = '" + IIN + "'");
            if(rs.next()){
                String checkPassword = rs.getString("password");
                if(checkPassword.equals(password)){
                    return true;
                }else{
                    return false;
                }
            }
        }catch (SQLException e){
            //System.out.println("Error checking password");
            System.out.println(e);
        }
        return false;
    }

    public static boolean isManagerDB(int id){
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT manager FROM users WHERE id = " + id);
            if(rs.next()){
                if(rs.getBoolean("manager") == true){
                    return true;
                }
                else{
                    return false;
                }
            }
        }catch (SQLException e){
            System.out.println("Error checking manager");
        }
        return false;
    }



    }
