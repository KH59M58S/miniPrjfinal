package com.kh.prof;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.kh.util.Util;
import com.swy.db.OracleDB;

public class ProfAtt {
	
	
	static Connection conn = OracleDB.getConnection();
	static PreparedStatement pstmt = null;
	static ResultSet rs = null;
	
	public static void classAttendence(String c_name) { //해당 강의의 학생들 전체 출석부
		
	// 1. 강의 입력
	System.out.println("과목을 입력하세요.");
	Util.sc.nextLine();
	
	
	// 2. DB에서 데이터 가져오기 (영어를 입력하면 영어를 듣는 학생들의 출석부 가져오기)
	//디비 연결 얻기
	
		
	//3. 해당 강의에 맞는 출석부 조회하기
	
	try {
		String sql = "SELECT * FROM C_1_ATT";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, c_name);
		rs = pstmt.executeQuery();

		while(rs.next()) {
			System.out.println("출석 번호 : " + rs.getString(1) + "\t" + "출석 체크 시간 : " + rs.getDate(2) + "\t"
					+ "출석 여부 : "+ rs.getByte(3)+ "학생 번호 : "+rs.getLong(4)
					); 
					
		}
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	}  finally {
		if (conn == null)
			OracleDB.close(conn);
		if (pstmt == null)
			OracleDB.close(pstmt);
		if (rs == null)
			OracleDB.close(rs);

	}
	
	
}
	
	
	
	public static void stuAttendence(String std_name) { //해당 학생 출석부
		// 1. 학생 이름 입력
		System.out.println("학생 이름을 입력하세요.");
		Util.sc.nextLine();
		
		String sql = "SELECT * FROM C_1_ATT WHERE STU_NAME = ?";
		
		try {
			pstmt.setString(1, std_name);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	
	}
	
	
	
	
	
}
