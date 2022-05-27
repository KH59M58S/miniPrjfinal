package com.kh.lecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kh.db.OracleDB;
import com.kh.util.Util;

public class RegistrationLecture {
	
	public boolean RegistrationClass(int p_no) {
		System.out.print("강의명 : ");
		String c_name = Util.sc.nextLine();
		System.out.println("ex) 월 12:00 ~ 15:00");
		System.out.print("강의 시간 : ");
		String c_time = Util.sc.nextLine();
		System.out.println("길게 입력하면 첫번째만 입력됩니다.");
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}
		
		
		return registrationAtt(getSeq());
		
	}
	
	public int getSeq() {
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String seqSql = "SELECT C_ATT_SQC.NEXTVAL FROM DUAL";
		int seq = 0;
		try {
			pstmt = conn.prepareStatement(seqSql);
			rs = pstmt.executeQuery();
			if ( rs.next()) {
				seq = rs.getInt(1);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
		return seq;
	}
	
	public boolean registrationAtt(int seq) {
		
		Connection conn = OracleDB.getConnection();
		
		Statement stmt = null;
		String createSql = "CREATE TABLE C_"+seq+"_ATT("
				+ " A_TIME TIMESTAMP DEFAULT SYSDATE,"
				+ " A_CHECK CHAR(1), "
				+ " STU_NO NUMBER,"
				+ " CONSTRAINT A_NOFK"+seq+" FOREIGN KEY (STU_NO) REFERENCES  STUDENT(STU_NO))";
		
		try {
			stmt = conn.createStatement();
			int i = stmt.executeUpdate(createSql);
			if (i==0) {
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(stmt);
		}
		
		return false;
		
	}
	
	

}
