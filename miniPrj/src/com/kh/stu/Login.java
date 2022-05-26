package com.kh.stu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.util.Util;
import com.swy.db.OracleDB;

public class Login {

	// 선별
	public static void try_login() {
		System.out.println("==== 로그인 하려는 계정을 선택해주세요. ====");
		System.out.println("1. 학생  2. 교수  3. 관리자");
		int num = Util.scInt();
		if (num == 1) {
			stu_Login();
		}
		if (num == 2) {
			prof_Login();
		}
		if (num == 3) {
			admin_Login();
		}

	}

	// 학생 로그인
	public static void stu_Login() {
		System.out.println("==== 로그인 하기 ====");
		System.out.print("아이디 : ");
		String id = Util.sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = Util.sc.nextLine();

		// DB연결
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 해당 아이디에 맞는 패스워드 DB에서 조회하기 SELECT = executeQuery();
		String sql = "SELECT STU_PWD FROM STUDENT WHERE STU_ID = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String dbPwd = rs.getString(1);
				if (dbPwd.equals(pwd)) {
					// 로그인 성공
					System.out.println("로그인 성공!!!");
					stuMenu.showMenu();
				} 
			}else
				System.out.println("로그인 실패!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!! SQL 예외 발생 !!");
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
	}

	// 교수 로그인
	public static void prof_Login() {
		System.out.println("==== 로그인 하기 ====");
		System.out.print("아이디 : ");
		String id = Util.sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = Util.sc.nextLine();

		// DB연결
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 해당 아이디에 맞는 패스워드 DB에서 조회하기 SELECT = executeQuery();
		String sql = "SELECT P_PWD FROM PROF WHERE P_ID = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String dbPwd = rs.getString(1);
				if (dbPwd.equals(pwd)) {
					// 로그인 성공
					System.out.println("로그인 성공!!!");
					System.out.println("교수 메뉴 이동");
				} 
			}else
				System.out.println("로그인 실패!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!! SQL 예외 발생 !!");
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
	}

	// 관리자 로그인
	public static void admin_Login() {
		System.out.println("==== 로그인 하기 ====");
		System.out.print("아이디 : ");
		String id = Util.sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = Util.sc.nextLine();

		// DB연결
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 해당 아이디에 맞는 패스워드 DB에서 조회하기 SELECT = executeQuery();
		String sql = "SELECT AD_PWD FROM ADMIN WHERE AD_ID = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String dbPwd = rs.getString(1);
				if (dbPwd.equals(pwd)) {
					// 로그인 성공
					System.out.println("로그인 성공!!!");
					System.out.println("관리자 메뉴 이동");
				} 
			}else
				System.out.println("로그인 실패!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!! SQL 예외 발생 !!");
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
	}
	
	
}

