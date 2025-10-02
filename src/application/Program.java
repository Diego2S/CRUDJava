package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		
		
		try{
			conn = DB.getConnection();
			// st = conn.prepareStatement(
			// 	"INSERT INTO seller "
			// 	+"(Name, Email, BirthDate, BaseSalary, DepartmentId) "
			// 	+"VALUES "
			// 	+"(?, ?, ?, ?, ?)",
			// 	Statement.RETURN_GENERATED_KEYS);	
			// st.setString(1, "Greg");
			// st.setString(2, "sdd@exemplo.com");
			// st.setDate(3, new java.sql.Date(sdf.parse("22/07/1991").getTime()));
			// st.setDouble(4, 2000.0);
			// st.setInt(5, 1);

			st = conn.prepareStatement(
				"INSERT INTO department "
				+"(Name) "
				+"VALUES "
				+"(?),(?)",
				Statement.RETURN_GENERATED_KEYS);
			st.setString(1, "D1");
			st.setString(2, "D2");

			int rowsAffected = st.executeUpdate();

			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();

				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
					
				}
				System.out.println("Done! Rows affected: " + rowsAffected);}
			else {
				System.out.println("No rows affected!");
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
			DB.closeConnection();

	}
}

}
