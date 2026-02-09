import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CrudOperation {
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
		int ch = 0 ;
		do{
			System.out.println("1.Insertion");
			System.out.println("2.Updation");
			System.out.println("3.Deletion");
			System.out.println("4.show");
			System.out.println("5.Login");
			System.out.println("6.Exit");
			System.out.println("Enter your choice.");
			ch = sc.nextInt();
			switch(ch) {
			case 1:
				CrudOperation r = new CrudOperation();
				r.Insert(url,username,pass);
				break;
			
			case 2:
				CrudOperation r1 = new CrudOperation();
				r1.Update(url,username,pass);
				break;
				
			case 3:
				CrudOperation r2 = new CrudOperation();
				r2.Delete(url,username,pass);
				break;
				
			case 4:
				CrudOperation r3 = new CrudOperation();
				r3.show(url,username,pass);
				break;
				default:
					break;
					
			case 5:
				CrudOperation r4 = new CrudOperation();
				r4.login(url, username, pass);
			}	
		}while(ch!=6);
	}
		
	
	public void Insert(String url,String username,String pass) {
		try {
			Scanner sc = new Scanner(System.in);
			Connection connection = DriverManager.getConnection(url,username,pass);
			System.out.println("Connection established..");
			System.out.println("Enter the name you want to insert.");
			String name = sc.nextLine();
			System.out.println("Enter the salary you want to insert.");
			int sal = sc.nextInt();
			System.out.println("Enter the employee id you want to insert.");
			int e_id = sc.nextInt();
			//insert Query 
			String queryInsert = "Insert into employee(e_name , e_id , sal) values(?,?,?);";
			PreparedStatement ps = connection.prepareStatement(queryInsert);
			ps.setString(1,name);
			ps.setInt(2, e_id);
			ps.setInt(3, sal);
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected > 0) {
				System.out.println("Data Inserted successfully.");
			}
			else {
				System.out.println("Data failed");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void Update(String url,String username,String pass) {
		try {
			Scanner sc = new Scanner(System.in);
			Connection connection = DriverManager.getConnection(url,username,pass);
			System.out.println("Connection established..");
			System.out.println("Enter the salary you want to Update.");
			int sal = sc.nextInt();
			System.out.println("Enter the employee id you want to Update.");
			int e_id = sc.nextInt();
			//insert Update 
			String queryUpdate = "update employee set sal = ? where e_id = ?;";
			PreparedStatement ps = connection.prepareStatement(queryUpdate);
			ps.setInt(1,sal);
			ps.setInt(2, e_id);
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected > 0) {
				System.out.println("Data Updated successfully.");
			}
			else {
				System.out.println("Data updation failed");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void Delete(String url,String username,String pass) {
		try {
			Scanner sc = new Scanner(System.in);
			Connection connection = DriverManager.getConnection(url,username,pass);
			System.out.println("Connection established..");
			System.out.println("Enter the employee id you want to Delete.");
			int e_id = sc.nextInt();
			//insert queryDelete
			String queryDelete = "Delete from employee where e_id = ?";
			PreparedStatement ps = connection.prepareStatement(queryDelete);
			ps.setInt(1, e_id);
			int rowsAffected = ps.executeUpdate();
			if(rowsAffected > 0) {
				System.out.println("Data Deleted successfully.");
			}
			else {
				System.out.println("Data deletion failed");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void show(String url,String username,String pass) {
		try {
			Connection connection = DriverManager.getConnection(url, username, pass);
			System.out.println("Connection established.");
			String queryPrint = "select * from employee;";
			PreparedStatement ps = connection.prepareStatement(queryPrint);
			ResultSet resultset = ps.executeQuery();
			while(resultset.next()) {
				String name = resultset.getString("e_name");
				int e_id = resultset.getInt("e_id");
				int sal = resultset.getInt("sal");
				System.out.print(" Id = "+e_id+" Name = "+name+" salary ="+sal);
				System.out.println();
			}
	}catch(Exception e){
		e.printStackTrace();
	}
}
	
	public void login(String url,String username,String pass) {
		try {
			Connection connection = DriverManager.getConnection(url, username, pass);
			System.out.println("Connection established.");
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the userid and password for login");
			int id = sc.nextInt();
			sc.nextLine();
			String pass1 = sc.nextLine();
			String query ="Select * from login where e_id = ? and pass = ?;";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,id);
			ps.setString(2,pass1);
			ResultSet resultset = ps.executeQuery();
			while(resultset.next()) {
				System.out.println("Login successful");
				}
			
		}catch(Exception e) {
			System.out.println("The id or pass is incorrect check again.");
		}
	}
}
