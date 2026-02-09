import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class FullCrud {
    static final String url = "jdbc:mysql://localhost:3306/AdvanceJava";
    static final String username = "root";
    static final String pass = "SritamMySQL007@";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(url, username, pass)) {
            System.out.println("Connection established ...");

            Scanner sc = new Scanner(System.in);

            System.out.println("Enter the student details:");
            System.out.print("ID: ");
            int id = sc.nextInt();

            System.out.print("Roll No: ");
            int rollno = sc.nextInt();

            sc.nextLine();

            System.out.print("Name: ");
            String name = sc.nextLine();


            insert_student(connection, id, rollno, name);
            update_student(connection ,sc);

            sc.close();

        } catch (SQLException e) {
            System.err.println("Database connection error:");
            e.printStackTrace();
        }
    }


    public static void insert_student(Connection connection, int id, int rollno, String s_name) {
        if (connection == null) {
            System.err.println("Connection is null!");
            return;
        }

        String sql = "{call insert_student(?,?,?)}";

        try (CallableStatement csmt = connection.prepareCall(sql)) {
            csmt.setInt(1, id);
            csmt.setInt(2, rollno);
            csmt.setString(3, s_name);

            csmt.execute();
            System.out.println("Data Inserted Successfully....");
        } catch (Exception e) {
            System.err.println("Error calling stored procedure:");
            e.printStackTrace();
        }
    }

    public static void update_student(Connection connection , Scanner sc){
        String sql = "{call update_student_details(?,?,?)}";
        System.out.println("Enter the ID which you want to update :");
        int id = sc.nextInt();
        System.out.println("Enter Updated Details :");
        System.out.println("Roll :");
        int roll = sc.nextInt();
        sc.nextLine();
        System.out.println("Name :");
        String name = sc.nextLine();
        try(CallableStatement csmt = connection.prepareCall(sql)){
            csmt.setInt();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}