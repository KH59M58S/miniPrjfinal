package com.kh.lecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.kh.util.Util;
import com.swy.db.OracleDb;

public class RegistrationLecture {
	
	public boolean RegistrationClass() {
		System.out.print("강의 번호 : ");
		int c_no = Util.scInt();
		System.out.print("강의명 : ");
		String c_name = Util.sc.nextLine();
		System.out.println("ex) xxxx년 xx월 xx일 xx시 xx분");
		System.out.print("강의 시간 : ");
		String c_time = Util.sc.nextLine();
		System.out.print("강의실 : ");
		char c_room = Util.sc.nextLine().charAt(0);
		// 교수 번호는 로그인한 교수 아이디로 가져옴
		
		
		// 시간 sql.date 타입으로 변환
		DateFormat df = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분");
		Date date = null;
		try {
			date = df.parse(c_time);
		} catch (ParseException e1) {
			System.out.println("형식에 맞게 입력해주세요");
			RegistrationClass();
		}
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		
		
		
		Connection conn = LectureOracle.getConnection();
		String sql = "INSERT INTO CLASS(C_NO,C_NAME,C_TIME,C_ROOM,P_NO)"
				+ " VALUES(?,?,?,?,?)";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_no);
			pstmt.setString(2, c_name);
			pstmt.setTimestamp(3, ts);
			pstmt.setString(4, String.valueOf(c_room));
			pstmt.setInt(5, 1);
			
			int result = pstmt.executeUpdate();
			
			if ( result == 1 ) {
				System.out.println("강의 등록 완료");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDb.close(conn);
			OracleDb.close(pstmt);
		}
		
		System.out.println("실패");
		
		
		
		return false;
		
	}
	
	

}
