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
			
			st = conn.prepareStatement(
				"DELETE FROM department " +
				"WHERE " +
				"Id = ?");
			st.setInt(1, 2);
			

			int rowsAffected = st.executeUpdate();
			System.out.println("Done! Rows affected: " + rowsAffected);


		}catch(SQLException e) {
			throw new db.DbIntegrityException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeConnection();

	}
}

}
