package com.kh.lecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kh.util.Util;
import com.swy.db.OracleDB;

public class RegistrationLecture {
	
	public boolean RegistrationClass(int p_no) {
		System.out.print("강의명 : ");
		String c_name = Util.sc.nextLine();
		System.out.println("ex) 월 12:00 ~ 15:00");
		System.out.print("강의 시간 : ");
		String c_time = Util.sc.nextLine();
		System.out.print("강의실 : ");
		char c_room = Util.sc.nextLine().charAt(0);
		// 교수 번호는 로그인한 교수 아이디로 가져옴
		
		
		Connection conn = OracleDB.getConnection();
		String sql = "INSERT INTO CLASS(C_NO,C_NAME,C_TIME,C_ROOM,P_NO)"
				+ " VALUES(CLASS_NO.NEXTVAL,?,?,?,?)";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_name);
			pstmt.setString(2,c_time);
			pstmt.setString(3, String.valueOf(c_room));
			pstmt.setInt(4, p_no);
			
			int result = pstmt.executeUpdate();
			
			if ( result == 1 ) {
				System.out.println("강의 등록 완료");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}
		
		String sql2 = "CREATE TABLE C_?_ATT("
				+ " A_NO NUMBER PRIMARY KEY,"
				+ " A_TIME TIMESTAMP DEFAULT SYSDATE,"
				+ " A_CHECK CHAR(1), "
				+ " STU_NO NUMBER,"
				+ " CONSTRAINT A_NOFK? FOREIGN KEY (STU_NO) REFERENCES  STUDENT(STU_NO));";
		PreparedStatement pstmt2 = null;
		
		try {
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, c_room);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		System.out.println("실패");
		
		
		
		return false;
		
	}
	
	

}
