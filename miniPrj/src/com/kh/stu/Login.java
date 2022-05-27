package com.kh.stu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.admin.AdminScreen;
import com.kh.prof.ProfScreen;
import com.kh.screen.EnterKey;
import com.kh.screen.Screen;
import com.kh.util.Util;
import com.swy.db.OracleDB;

public class Login {

	// 선별
	public static void try_login() {
		System.out.println("++++++++++ 로그인 하려는 계정을 선택해주세요. ++++++++++");
		System.out.println("1. 학생  2. 교수  3. 관리자");
		new EnterKey();
		int num = Util.scInt();
		if (num == 1) {
			stu_Login();
		} else if (num == 2) {
			prof_Login();
		} else if (num == 3) {
			admin_Login();
		}else {
			Screen.mainMenu_show();
		}

	}
	
	// 학생 로그인
	public static void stu_Login() {
		System.out.println("+++++ 로그인 하기 +++++");
		new EnterKey();
		System.out.println("↓ 아이디를 입력해주세요 ↓");
		String id = Util.sc.nextLine();
		
		System.out.println("↓ 비밀번호를 입력해주세요 ↓");
		String pwd = Util.sc.nextLine();
		

		// DB연결
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 해당 아이디에 맞는 패스워드 DB에서 조회하기 SELECT = executeQuery();
		String sql = "SELECT STU_PWD, STU_NO FROM STUDENT WHERE STU_ID = ? AND STU_QUIT_YN = 'N'";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String dbPwd = rs.getString(1);
				if (dbPwd.equals(pwd)) {
					// 로그인 성공
					System.out.println("로그인 성공!!!");
					Util.infono = rs.getInt(2);
					Util.info = "Student";
					stuMenu.showMenu();
				}
			} else {
				System.out.println("로그인 실패!");
				System.out.println("탈퇴된 계정이거나 비밀번호 오류입니다.");
				try_login();
			}
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
		System.out.println("+++++ 로그인 하기 +++++");
		new EnterKey();
		System.out.println("↓ 아이디를 입력해주세요 ↓");
		String id = Util.sc.nextLine();
		
		System.out.println("↓ 비밀번호를 입력해주세요 ↓");
		String pwd = Util.sc.nextLine();

		// DB연결
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 해당 아이디에 맞는 패스워드 DB에서 조회하기 SELECT = executeQuery();
		String sql = "SELECT P_PWD, P_NO FROM PROF WHERE P_ID = ? AND P_QUIT_YN = 'N'";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String dbPwd = rs.getString(1);
				if (dbPwd.equals(pwd)) {
					// 로그인 성공
					System.out.println("로그인 성공!!!");
					Util.infono = rs.getInt(2);
					Util.info = "Prof";
					ProfScreen.profScreenStart();
				}
			} else {
				System.out.println("로그인 실패!");
				System.out.println("탈퇴된 계정이거나 비밀번호 오류입니다.");
				try_login();
			}
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
		System.out.println("+++++ 로그인 하기 +++++");
		new EnterKey();
		System.out.println("↓ 아이디를 입력해주세요 ↓");
		String id = Util.sc.nextLine();
		
		System.out.println("↓ 비밀번호를 입력해주세요 ↓");
		String pwd = Util.sc.nextLine();

		// DB연결
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 해당 아이디에 맞는 패스워드 DB에서 조회하기 SELECT = executeQuery();
		String sql = "SELECT AD_PWD, AD_NO FROM ADMIN WHERE AD_ID = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String dbPwd = rs.getString(1);
				if (dbPwd.equals(pwd)) {
					// 로그인 성공
					System.out.println("로그인 성공!!!");
					Util.infono = rs.getInt(2);
					Util.info = "Admin";
					new AdminScreen().adminscreen();
				}
			} else {
				System.out.println("로그인 실패!");
				try_login();
			}
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
