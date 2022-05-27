package com.kh.stu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kh.db.OracleDB;
import com.kh.util.Util;

public class SignUp {

	/*
	 * <회원가입> 1. 아이디, 비밀번호, 이름, 학과, 전화번호 입력받기 2. 비밀번호 확인(한번 더 입력받는 부분) 3. 아이디 중복 검사
	 * -> 중복시 출력문 출력 4. 비밀 번호 유효성 검사 5. 비밀 번호 * 로 표시(유틸에서 작업) 6. 디비에 저장
	 */
	
	public boolean join() {
		// 1. 입력받기
		System.out.println("+++++++++++++++ 회원 가입 +++++++++++++++");
		System.out.print("이름 : ");
		String name = Util.sc.nextLine();
		System.out.print("아이디 : ");
		String id = Util.sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = Util.sc.nextLine();
		System.out.print("비밀번호 확인 : ");
		String pwd1 = Util.sc.nextLine();
		if (pwdCheck(pwd1)) {
			return false;
		}
		// 4.비밀번호 확인 - 맞아도 틀려도 메인으로 자꾸 돌아감.ㅠㅜ 
		//pwd와 pwd1이 틀리면 다시 입력
		/*if	(pwd != pwd1) {
		System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
		return false;
			}*/
		System.out.print("전공 : ");
		String major = Util.sc.nextLine();
		System.out.print("전화번호 : ");
		String phone = Util.sc.nextLine();
		System.out.print("주소 : ");
		String addr = Util.sc.nextLine();

		// 6. 디비 접속, 디비에서 현 아이디와 일치하는 아이디 조회, 성공여부 결과 안내
		// 2. 아이디 중복 검사
		Connection conn = OracleDB.getConnection();
		try {
			String sql = "SELECT STU_ID FROM STUDENT WHERE STU_ID = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("아이디가 중복입니다. 다시 만들어주세요.");
				return false;
			}

			String sqlInsert = "INSERT INTO STUDENT(STU_NO, STU_NAME, STU_ID, STU_PWD, D_NO, STU_PHONE, STU_ADDR)"
					+ "VALUES(STUDENT_NO.NEXTVAL,?,?,?,?,?,?)";
			PreparedStatement pstmt2 = conn.prepareStatement(sqlInsert);
			pstmt2.setString(1, name);
			pstmt2.setString(2, id);
			pstmt2.setString(3, pwd);
			pstmt2.setInt(4, Util.deptNameToNo(major));
			pstmt2.setString(5, phone);
			pstmt2.setString(6, addr);
			int result = pstmt2.executeUpdate();

			if (result == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace(); // 에러확인
		}

		return false;
	}
	
	// 3. 비밀번호 유효성 검사
	// 비밀번호 5글자 이상, 특수문자 1개 이상 포함
	public static boolean pwdCheck(String s) {
		if (s.length() < 5) {
			System.out.println("5글자 이상으로 만들어주세요!");
			return true;
		} else {
			if (!(s.contains("!") || s.contains("^") || s.contains("*") || s.contains(".") || s.contains("#"))) {
				// 특수문자 1개 이상 포함
				System.out.print("특수문자 !, ^, *, . , # 중에 1개 이상 포함해주세요!");
				return true;

			}
		}
		return false;
	}				
}

	