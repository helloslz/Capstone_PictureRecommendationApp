package cmu.vlis.luzhengs.capstone.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImagePopulator {
	// populate the picture table
	static void populatePictures(Connection conn, String filePath) throws SQLException, FileNotFoundException {
		File[] allImages = new File(filePath).listFiles();
		
		String sql = "insert into picture(image, rank, rating) values (?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		FileInputStream fis;
		File image;
		for(int i = 0; i < allImages.length; i ++) {
			image = allImages[i];
			fis = new FileInputStream(image);
			ps.setBinaryStream(1, (InputStream)fis, (int)image.length());
			ps.setInt(2, 0);
			ps.setDouble(3, 0.0);
			ps.execute();
			System.out.println(i + 1 + " pictures loaded.");
		}
	}
	
	// set default rank to be maximum
	static void updateRank(Connection conn) throws SQLException {
		String sql = "update picture set rank = (?) where pid = (?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		for(int i = 1; i <= 400; i++) {
			ps.setInt(1, Integer.MAX_VALUE);
			ps.setInt(2, i);
			ps.execute();
		}
	}
	
	static void populateLabels(Connection conn) throws SQLException {
		String sql = "insert into label() values()";
		PreparedStatement ps = conn.prepareStatement(sql);
		for(int i = 0; i < 400; i ++) {
			ps.execute();
		}
	}
	
	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/capstone";
		String username = "root";
		String password = "";
		try {
			Class.forName(driver);	// load the driver class
			Connection conn = DriverManager.getConnection(url, username, password);
//			populatePictures(conn, "W:\\GraduateLife_CMU\\Capstone\\Pictures");
//			updateRank(conn);
			populateLabels(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
