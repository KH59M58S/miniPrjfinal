package com.kh.prof;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.kh.util.Util;
import com.swy.db.OracleDB;

public class ProfAtt {
	// 2. DB에서 데이터 가져오기 (영어를 입력하면 영어를 듣는 학생들의 출석부 가져오기)
	//디비 연결 얻기
	
			static Connection conn = OracleDB.getConnection();
			static PreparedStatement pstmt = null;
			static ResultSet rs = null;
	
	
	
	public void classAttendence() { //해당 강의의 학생들 전체 출석부
		
	// 1. 강의 입력
		System.out.println("과목을 입력하세요.");
		String c_name = Util.sc.nextLine();
		
		
	//3. 해당 강의에 맞는 출석부 조회하기
	
	try {
		String sql = "SELECT * FROM C_"+Util.classNameToNo(c_name)+"_ATT "; //바꾸기
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		

		while(rs.next()) {
			int a_no = rs.getInt(1);
			Timestamp a_time = rs.getTimestamp(2);
			String a_check = rs.getString(3);
			int stu_no = rs.getInt(4);
			
			
			System.out.print("출석번호 : "+ a_no+ " | ");
			System.out.print("출석체크 시간 : " +a_time+ " | ");
			System.out.print("출석여부 : "+ a_check+ " | ");
			System.out.println("학생번호 : "+ stu_no+ " | ");
		}
//			System.out.println("출석 번호 : " + rs.getString(1) + "\t" + "출석 체크 시간 : " + rs.getDate(2) + "\t"
//					+ "출석 여부 : "+ rs.getByte(3)+ "학생 번호 : "+rs.getLong(4)
//					);
					
	
		
			
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
	
	//2시간 20분
	
	public static void stuAttendence() { //해당 학생 출석부
		// 1. 학생 번호 입력
		System.out.println("학생 번호를 입력하세요.");
		int stu_no = Util.scInt();
		
		
		
		
		String sql = "SELECT * FROM C_?_ATT WHERE STU_NO = ?";
		 
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stu_no);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				
				int a_no = rs.getInt(1);
				Timestamp a_time = rs.getTimestamp(2);
				byte a_check = rs.getByte(3);
				int stu_no = rs.getInt(4);
				
				
				System.out.print(a_no +" 번 학생의 출석 번호");
				System.out.print(a_time);
				System.out.print(a_check);
				System.out.print(stu_no +" 번 학생의 학번 ");
				
				
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	
	}
	
	
	
	
	
}
