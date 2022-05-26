package com.kh.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.kh.util.Util;
import com.swy.db.OracleDB;

public class SysBoard {
	
	public void showSysBoard() {
		String sql = "SELECT SYS_NO,SYS_TITLE,SYS_DATE,SYS_CONTENT FROM SYSBOARD";
		
		Connection conn = OracleDB.getConnection();
		
		PreparedStatement pstmt = 	null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			System.out.println("번호\t\t\t\t제목\t\t\t\t\t시간\t\t\t\t\t\t\t내용");
			while (rs.next()) {
				int no = rs.getInt(1);
				String title = rs.getString(2);
				Timestamp time = rs.getTimestamp(3);
				String content = rs.getString(4);
				System.out.print(no + "\t" + title + "\t" + time + "\t\t" + content);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	public void writeSysBoard(int a_no) {
		
		String sql = "INSERT INTO SYSBOARD(SYS_TITLE,SYS_CONTENT,AD_NO) "
				+ "VALUES(SYSBOARD_NO.NEXTVAL,?,?,?)";
		System.out.print("제목 : ");
		String title = Util.sc.nextLine();
		System.out.print("내용 : ");
		String content = Util.sc.nextLine();
		
		
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, a_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
