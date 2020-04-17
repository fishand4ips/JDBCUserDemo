import java.sql.*;

public class JDBCTest {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/JDBC_test";
    static final String USER = "root";
    static final String PASSWORD = "1234";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        System.out.println("Registering JDBC driver...");
        Class.forName(JDBC_DRIVER);

        System.out.println("Connecting to database...");
        Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Creating statement...");
        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM user";

        Boolean isRetrieved = statement.execute(sql);

        statement.executeUpdate("insert into user(first_name, last_name) VALUE ('Sofya', 'Petrova')");

        System.out.println("Is data retrieved: " + isRetrieved);

        System.out.println("Displaying retrieved data:");
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String fisrt_name = resultSet.getString("first_name");
            String last_name = resultSet.getString("last_name");

            System.out.println("id: " + id);
            System.out.println("fisrt_name: " + fisrt_name);
            System.out.println("last_name: " + last_name);
            System.out.println("\n===================\n");
        }

        System.out.println("Closing connection and releasing resources...");

        try {
            resultSet.close();
            statement.close();
            connection.close();
        }finally {
            if(statement !=null){
                statement.close();
            }
            if(connection!=null){
                connection.close();
            }
        }
        System.out.println("Thank You.");
    }
}
