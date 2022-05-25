package com.kh.lecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.kh.util.Util;

public class RegistrationClass {

	public static void main(String[] args) {
		RegistrationClass1();
	}
	
	public static boolean RegistrationClass1() {
		System.out.print("강의 번호 : ");
		int c_no = Util.scInt();
		System.out.print("강의명 : ");
		String c_name = Util.sc.nextLine();
		System.out.println("ex) xxxx년 xx월 xx일 xx시 xx분");
		System.out.print("강의 시간 : ");
		String c_time = Util.sc.nextLine();
		System.out.print("강의실 : ");
		String c_room = Util.sc.nextLine();
		// 교수 번호는 로그인한 교수 아이디로 가져옴
		
		
		Connection conn = LectureOracle.getConnection();
		
		String sql = "INSERT INTO(C_NO,C_NAME,C_TIME,C_ROOM,P_NO)"
				+ " VALUES(?,?,?,?,?)";
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setString(2, c_name);
			pstmt.setString(3, c_time);
			pstmt.setString(4, c_room);
			
			int result = pstmt.executeUpdate();
			
			if ( result == 1 ) {
				System.out.println("강의 등록 완료");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	

}
