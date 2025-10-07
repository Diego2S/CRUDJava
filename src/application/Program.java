package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		Statement st = null;
		
		
		try{
			conn = DB.getConnection();

			st = conn.createStatement();

			conn.setAutoCommit(false);

			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");
			int x =5;
			if (x < 2) {
				throw new SQLException("Fake error");
				
			}

			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");
			
			conn.commit();

			System.out.println("Done! Rows1 : " + rows1);
			System.out.println("Done! Rows2 : " + rows2);


		}catch(SQLException e) {
			try{
				conn.rollback();
				throw new DbException("Transaction rolled back! Caused by: "+e.getMessage());}
			catch(SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: "+e1.getMessage());
			}
		}finally {
			DB.closeStatement(st);
			DB.closeConnection();

	}
}

}
