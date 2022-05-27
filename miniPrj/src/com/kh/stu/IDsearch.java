package com.kh.stu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.db.OracleDB;
import com.kh.util.Util;

public class IDsearch {

	public static void try_IDFind() {
		System.out.println("==== 아이디 찾으려는 계정을 선택해주세요. ====");
		System.out.println("1. 학생  2. 교수");
		int num = Util.scInt();
		if (num == 1) {
			stu_IDFind();
		} else if (num == 2) {
			prof_IDFind();
		} else {
			System.out.println("잘못 입력 되었습니다.!! ");
			System.out.println("메인화면으로 이동합니다.");
		}

	}

	public static void stu_IDFind() {
		System.out.println("==== 아이디 찾기 ====");
		System.out.print("이름 : ");
		String name = Util.sc.nextLine().trim();
		System.out.print("전화번호 ex)010-0000-0000 : ");
		String phone = Util.sc.nextLine().trim();

		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 해당 아이디에 맞는 패스워드 DB에서 조회하기 SELECT = executeQuery();
		String sql = "SELECT STU_ID FROM STUDENT WHERE STU_NAME = ? AND STU_PHONE = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String foundID = rs.getString(1);
				if (foundID == null) {
					System.out.println("그런 아이디는 존재 하지 않습니다...");
				} else
					System.out.println(name + "님의 아이디는 " + foundID);
			}else {
				System.out.println("잘못된 정보를 적었습니다.");
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

	public static void prof_IDFind() {
		System.out.println("==== 아이디 찾기 ====");
		System.out.print("이름 : ");
		String name = Util.sc.nextLine().trim();
		System.out.print("전화번호 ex)010-0000-0000 : ");
		String phone = Util.sc.nextLine().trim();

		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 해당 아이디에 맞는 패스워드 DB에서 조회하기 SELECT = executeQuery();
		String sql = "SELECT P_ID FROM PROF WHERE P_NAME = ? AND P_PHONE = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String foundID = rs.getString(1);
				if (foundID == null) {
					System.out.println("그런 아이디는 존재 하지 않습니다...");
				} else
					System.out.println(name + "님의 아이디는 " + foundID);
			}else {
				System.out.println("잘못된 정보를 적었습니다.");
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
