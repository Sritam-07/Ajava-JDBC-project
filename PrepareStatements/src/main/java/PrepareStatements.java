import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PrepareStatements {
	public static void main(String args[]) {
		final String url = "jdbc:mysql://localhost:3306/PrepareStatements";
		final String username = "root";
		final String pass = "SritamMySQL007@";
		Scanner sc = new Scanner(System.in);
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Connection connection = DriverManager.getConnection(url,username,pass);
			System.out.println("Connection established..");
			//insert Query 
//			String queryInsert = "Insert into employee(e_name , e_id , sal) values(?,?,?);";
			//Delete Query 
//			String queryDelete = "Delete from employee where e_id = ?";
			//update query 
			System.out.println("Enter the salary you want to update.");
			int sal = sc.nextInt();
			System.out.println("Enter the employee id you want to update.");
			int e_id = sc.nextInt();
			String queryUpdate = "update employee set sal = ? where e_id = ?;";
			PreparedStatement ps = connection.prepareStatement(queryUpdate);
			ps.setInt(1,sal);
			ps.setInt(2, e_id);
			//for deleting 
//			ps.setInt(1, 100);
			//for inserting
//			ps.setString(1,"Sritam");
//			ps.setInt(2,100);
//			ps.setInt(3,10000);
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected > 0) {
				System.out.println("Data updated successfully.");
			}
			else {
				System.out.println("Data updation failed");
			}
			
//			if(rowsAffected > 0) {
//				System.out.println("Successfully inserted data.");
//			}
			
		}catch(SQLException e) {
			
		}
	}
}
