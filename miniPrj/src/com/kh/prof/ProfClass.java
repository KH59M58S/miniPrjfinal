package com.kh.prof;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.swy.db.OracleDB;

public class ProfClass {

	
	public static void ClassList() {
		System.out.println("===== 전체 강의 목록 보기 =====");
		
	
		
		
		
		
	}
	
	public static void ClassAdd() {
		System.out.println("===== 강의 추가 하기 =====");
		
		Connection conn = OracleDB.getConnection();
		
		try {
			
			String sqlInsert 
				= "INSERT INTO MEMBER(C_NO,C_NAME,C_TIME,C_ROOM,P_NO) "
						+ "VALUES(?,?,?,?,?)";
//			Statement stmtInsert = conn.createStatement();
//			int result = stmtInsert.executeUpdate(sqlInsert);
			PreparedStatement pstmt  = conn.prepareStatement(sqlInsert);
			pstmt.setInt(1, 5);
			pstmt.setString(2, "영어");
			pstmt.setString(3, "2022-05-25");
			pstmt.setString(4, "POPO");
			int result = pstmt.executeUpdate();
			
			
			if(result == 1) {
				System.out.println("ȸ�� ���� ���� !!!");
				return;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("ȸ������ ����...");
		return;
	}
	
	}
	
	

