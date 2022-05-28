package com.kh.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.db.OracleDB;

public class Attenment {
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
			if (conn == null)
				OracleDB.close(conn);
			if (psm == null)
				OracleDB.close(psm);
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
		} finally {
			if (conn == null)
				OracleDB.close(conn);
			if (pstmt == null)
				OracleDB.close(pstmt);
			if (rs == null)
				OracleDB.close(rs);
		}
	}

	public void stuInClass() {

		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		System.out.print("과목을 입력하세요 : ");
		String c_name = Util.sc.nextLine();

		try {
			String sql = "SELECT SUBSTR(RPAD(S.STU_NAME,10,' '),1,10) " + " FROM C_" + Util.classNameToNo(c_name) + "_ATT  C "
					+ " INNER JOIN STUDENT S" + " ON S.STU_NO = C.STU_NO";
			
			String sql2 = "SELECT SUBSTR(RPAD(C_NAME ,8,' ' ),1,8)FROM CLASS "
					+ " WHERE C_NO = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt2 = conn.prepareStatement(sql2);
			
			rs = pstmt.executeQuery();
			pstmt2.setInt(1, Util.classNameToNo(c_name));
			rs2 = pstmt2.executeQuery();
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("\n 순서 " + "|" + "  강의명  " + "|" + "   학생명   ");
			System.out.println("===================================");
			while (rs.next()) {
				String cname = null;
				if (rs2.next()) {
					cname = rs2.getString(1);
				}
				int cnt = 0;
				cnt ++;
				String stuname = rs.getString(1);
				System.out.print("  " + cnt + "     ");
				System.out.print("|    ");
				System.out.print(cname);
				System.out.print("|   ");
				System.out.print(stuname);
				System.out.println("\n-----------------------------------");
			}
			System.out.println("===================================");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("없는 강의 입니다.");
			stuInClass();
		} finally {
			if (conn == null)
				OracleDB.close(conn);
			if (pstmt == null)
				OracleDB.close(pstmt);
			if (rs == null)
				OracleDB.close(rs);

		}

	}

	public void dayatAtt() {

		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.print("과목을 입력하세요 : ");
		String c_name = Util.sc.nextLine();
		System.out.print("시간을 입력하세요 (ex 22-05-27) : ");
		String a_time = Util.sc.nextLine();

		try {
			String sql = "SELECT LPAD(ROWNUM,30,' ') , SUBSTR(RPAD( S.STU_NAME,8,' '),1,8) " + " FROM C_" + Util.classNameToNo(c_name) + "_ATT  C "
					+ " INNER JOIN STUDENT S" + " ON S.STU_NO = C.STU_NO" + " WHERE TO_CHAR(C.A_TIME,'YY-MM-DD') = '"
					+ a_time + "'";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			
			System.out.println("\n 번호 " + "|" + "  학생명  " + "|" + "       날 짜       ");
			System.out.println("=============================");
			while (rs.next()) {
				int stuNo = rs.getInt(1);
				String stuname = rs.getString(2);
				
				System.out.print(" " + stuNo + "      ");
				System.out.print("|  ");
				System.out.print(stuname);
				System.out.print("|  ");
				System.out.print(a_time);
				System.out.println("\n-----------------------------------");
			}
			System.out.println("===================================");
			
		} catch (SQLException e) {
			System.out.println("없는 강의 입니다.");
			stuInClass();
		} finally {
			if (conn == null)
				OracleDB.close(conn);
			if (pstmt == null)
				OracleDB.close(pstmt);
			if (rs == null)
				OracleDB.close(rs);

		}

	}

}
