package com.kh.lecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.kh.db.OracleDB;
import com.kh.util.Util;

public class Att {

	// 학생 출석 체크
	// ==================================================================================================
	public static void stuAttin() {
		Connection conn = OracleDB.getConnection();
		PreparedStatement psm = null;

		System.out.println("++++++++++ 출석 체크 ++++++++++");
		System.out.print("강의명 입력 : ");
		String c_name = Util.sc.nextLine();
		int c_no = Util.classNameToNo(c_name);

		String sql = "INSERT INTO C_" + c_no + "_ATT (A_CHECK, STU_NO) " + "VALUES ('Y', ?)";

		try {
			psm = conn.prepareStatement(sql);
			psm.setInt(1, Util.infono);
			int rs = psm.executeUpdate();

			if (rs == 0) {
				System.out.println("출석체크 실패");
			} else {
				System.out.println("출석체크 하였습니다.");
			}

		} catch (SQLException e) {
			System.out.println("출석체크 실패");
			e.printStackTrace();
		} finally {
			if(conn == null)OracleDB.close(conn);
			if(psm == null)OracleDB.close(psm);
		}

	}

	// 강의별 출석 확인
	// ==================================================================================================
	public static void showClassAtt() {
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		System.out.print("강의명 입력:");
		String c_name = Util.sc.nextLine();
		int c_no = Util.classNameToNo(c_name);

		String sql = "SELECT CA.A_TIME, A_CHECK, S.STU_NAME FROM C_" + c_no
				+ "_ATT CA INNER JOIN STUDENT S ON CA.STU_NO = S.STU_NO";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println(
						"출석 시간:" + rs.getDate(1) + "   체크여부:" + rs.getString(2) + "   학생이름:" + rs.getString(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(conn == null)OracleDB.close(conn);
			if(pstmt == null)OracleDB.close(pstmt);
			if(rs == null)OracleDB.close(rs);
		}
	}
	
	
	public static String lPadding(String str,String pad, int length) {
		String result = str;
		
		int padl = length - str.length();
		for(int i = 0;i<padl;++i) {
			result += pad;
		}
		
		return result;
	}
	
	public static String rPadding(String str,String pad, int length) {
		String result = "";
		
		int padl = length - str.length();
		for(int i = 0;i<padl;++i) {
			result += pad;
		}
		result += str;
		
		return result;
	}
	
	public static String cPadding(String str,String pad, int length) {
		String result = "";
		
		int padl = (length - str.length())/2;
		for(int i = 0;i<padl;++i) {
			result += pad;
		}
		
		result += str;
		
		for(int i = 0;i<padl;++i) {
			result += pad;
		}
		
		return result;
	}

}
