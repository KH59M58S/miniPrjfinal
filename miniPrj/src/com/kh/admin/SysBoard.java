package com.kh.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.util.Util;
import com.swy.db.OracleDB;

public class SysBoard {
	
	public void showSysBoard(int no) {
		String sql = "SELECT SYS_NO,SYS_TITLE,SYS_DATE, SYS_CONTENT"
				+ " FROM SYSBOARD"
				+ " WHERE SYS_NO = ?";
		
		Connection conn = OracleDB.getConnection();
		
		PreparedStatement pstmt = 	null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if ( rs.next()) {
				System.out.println(" 번호 : " + rs.getInt(1));
				System.out.println(" 제목 : " + rs.getString(2));
				System.out.println(" 작성 시간 : " + rs.getDate(3));
				System.out.println(" 내용 : " + rs.getString(4));
			} else {
				System.out.println("없는 번호 입니다.");
				showAllSysBoard();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
		if ( Util.info.equals("admin")) {
			System.out.println("뒤로가기 : -1 | 글 수정 : -2");
			int input = Util.scInt();
			if ( input == -1 ) {
				showAllSysBoard();
			} else if ( input == -2 ) {
				editSysBoard(no);
			}
		} else if (Util.info.equals("Student")) {
			System.out.println("뒤로가기 : -1");
			int input = Util.scInt();
			if ( input == -1 ) {
				showAllSysBoard();
			}
		}
		
		
		
		
	}
	
	public void writeSysBoard() {
		
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
			pstmt.setInt(3, Util.infono);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}
		
		showAllSysBoard();
	}
	
	public void showAllSysBoard() {

		String sql = "SELECT SYS_NO,SYS_TITLE FROM SYSBOARD";
		
		Connection conn = OracleDB.getConnection();
		
		PreparedStatement pstmt = 	null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			System.out.println("번호\t\t\t제목");
			while (rs.next()) {
				int no = rs.getInt(1);
				String title = rs.getString(2);
				System.out.println(no + "\t\t" + title );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
		new AdminScreen().showSysBoardMenu();
		
	
	}
	
	public void editSysBoard(int no) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String title = null, content = null;
		String sql = "SELECT SYS_TITLE,SYS_CONTENT "
				+ " FROM SYSBOARD "
				+ " WHERE SYS_NO = ? ";
				
		try {
			pstmt = OracleDB.getConnection().prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if ( rs.next()) {
				title = rs.getString(1);
				content = rs.getString(2);
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		System.out.print("제목 : ");
		title = Util.sc.nextLine();
		System.out.print("내용 : ");
		content = Util.sc.nextLine();
		
		String sql2 = "INSERT INTO SYSBOARD(SYS_TITLE,SYS_CONTENT,AD_NO) "
				+ "VALUES(SYSBOARD_NO.NEXTVAL,?,?,?)";
		
		
		Connection conn = OracleDB.getConnection();
		try {
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, Util.infono);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}
		
		showAllSysBoard();
		
		
		
	}
}
