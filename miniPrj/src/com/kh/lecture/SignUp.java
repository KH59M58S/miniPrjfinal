package com.kh.lecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.kh.db.OracleDB;
import com.kh.util.Util;

public class SignUp {

	// 전체 신청 리스트
	// 조회=====================================================================================================
	public static void showAllSignupList() {
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT STU.STU_NAME, C.C_NAME , S.S_TIME, S.S_QUIT_YN" + " FROM SIGNUP S"
				+ " INNER JOIN STUDENT STU" + " ON S.STU_NO = STU.STU_NO" + " INNER JOIN CLASS C"
				+ " ON S.C_NO = c.c_no" + " WHERE S.S_QUIT_YN = 'N'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);

			System.out.println(Util.cPadding("이름", " ", 6) + "|" + Util.cPadding("강의명", " ", 20) + "|"
					+ Util.cPadding("신청일자", " ", 15));
			System.out.println("--------------------------------------------------");
			while (rs.next()) {
				System.out.println(Util.cPadding(rs.getString(1), " ", 6) + Util.cPadding(rs.getString(2), " ", 20)
						+ Util.cPadding(rs.getString(3), " ", 15));
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

	// 수강신청
	public static boolean insertSignUp(int c_no, int stu_no) {
		
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO SIGNUP (S_NO, C_NO, STU_NO)" + "VALUES(SIGNUP_NO.NEXTVAL,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c_no);
			pstmt.setInt(2, stu_no);

			int resualt = pstmt.executeUpdate();
			if (resualt == 1) {
				showStdSignUp(Util.infono);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn == null)
				OracleDB.close(conn);
			if (pstmt == null)
				OracleDB.close(pstmt);
		}

		return false;
	}

	// 학생별 신청내역 검색
	public static void showStdSignUp(int std_no) {
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT STU.STU_NAME,C.C_NO, C.C_NAME , S.S_TIME, S.S_QUIT_YN" + " FROM SIGNUP S"
				+ " INNER JOIN STUDENT STU" + " ON S.STU_NO = STU.STU_NO" + " INNER JOIN CLASS C"
				+ " ON S.C_NO = c.c_no" + " WHERE S.S_QUIT_YN = 'N' AND STU.STU_NO = '" + std_no + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			System.out.println("----------------------------------------------------------");
			System.out.println(Util.cPadding("이름", " ", 6) + "|" + Util.cPadding("강의번호", " ", 4) + "|"
					+ Util.cPadding("강의명", " ", 20) + "|" + Util.cPadding("신청일자", " ", 15));
			System.out.println("----------------------------------------------------------");

			while (rs.next()) {
				System.out.println(Util.cPadding(rs.getString(1), " ", 6) + "|" + Util.cPadding(rs.getString(2), " ", 4)
						+ "|" + Util.cPadding(rs.getString(3), " ", 20) + "|"
						+ Util.cPadding(rs.getString(4), " ", 15));
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

	// 강의별 신청내역 검색
	public static void showLectureSingUp(String c_name) {

		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT C.C_NAME, STU.STU_NAME, S.S_TIME, S.S_QUIT_YN" + " FROM SIGNUP S"
				+ " INNER JOIN STUDENT STU" + " ON S.STU_NO = STU.STU_NO" + " INNER JOIN CLASS C"
				+ " ON S.C_NO = c.c_no" + " WHERE S.S_QUIT_YN = 'N' AND C.C_NAME = '" + c_name + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			System.out.println("----------------------------------------------------------");
			System.out.println(Util.cPadding("이름", " ", 6) + "|" + Util.cPadding("강의명", " ", 20) + "|"
					+ Util.cPadding("신청일자", " ", 15));
			System.out.println("----------------------------------------------------------");
			while (rs.next()) {
				System.out.println(Util.cPadding(rs.getString(2), " ", 6) + "|"
						+ Util.cPadding(rs.getString(1), " ", 20) + "|" + Util.cPadding(rs.getString(3), " ", 15));
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

	// 모든 강의 보기
	public static void showAllLectureList() {
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT C.C_NO, C_NAME, C.C_TIME, C.C_ROOM, P.P_NAME " + "FROM CLASS C " + "INNER JOIN PROF P "
				+ "ON (C.P_NO = P.P_NO) ";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			System.out.println("----------------------------------------------------------");
			System.out.println(Util.cPadding("강의번호", " ", 6) + "|" + Util.cPadding("강의명", " ", 20) + "|"
					+ Util.cPadding("시간", " ", 20) + "|" + Util.cPadding("강의실", " ", 4) + "|"
					+ Util.cPadding("교수명", " ", 6));
			System.out.println("----------------------------------------------------------");

			while (rs.next()) {
				System.out.println(Util.cPadding(rs.getString(1), " ", 6) + "|"
						+ Util.cPadding(rs.getString(2), " ", 20) + "|" + Util.cPadding(rs.getString(3), " ", 20) + "|"
						+ Util.cPadding(rs.getString(4), " ", 4) + "|" + Util.cPadding(rs.getString(5), " ", 6));
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

	public static void main(String[] args) {
		showAllLectureList();
	}

	// 수강신청 취소
	public static int deleteSignUp(int c_no) {
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		int result = 0;

		String sql = "UPDATE SIGNUP SET S_QUIT_YN = 'Y' WHERE STU_NO = ? AND C_NO = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Util.infono);
			pstmt.setInt(2, c_no);
			result = pstmt.executeUpdate();

			return result;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn == null)
				OracleDB.close(conn);
			if (pstmt == null)
				OracleDB.close(pstmt);
		}

		return 0;
	}

}
