public class Main {
    public static void main(String[] args) {
        DBclass db = new DBclass();
        db.createConnection();
        interaction.start();
    }
}