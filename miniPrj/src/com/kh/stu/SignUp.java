package com.kh.stu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kh.util.Util;
import com.swy.db.OracleDB;

public class SignUp {
	
	/*<회원가입>
	 * 1. 아이디, 비밀번호, 이름, 학과, 전화번호 입력받기
	 * 2. 비밀번호 확인(한번 더 입력받는 부분)
	 * 3. 아이디 중복 검사 -> 중복시 출력문 출력
	 * 4. 비밀 번호 유효성 검사
	 * 5. 비밀 번호 * 로 표시(유틸에서 작업)
	 * 6. 디비에 저장
	 */

	public boolean join() {
		//1. 입력받기
		System.out.println("==== 회원가입 ====");
		System.out.print("아이디 : ");
		String id = Util.sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = Util.sc.nextLine();
		System.out.print("비밀번호 확인 : ");
		String pwd1 = Util.sc.nextLine();
		System.out.print("이름 : ");
		String name = Util.sc.nextLine();
		System.out.print("전공 : ");
		String major = Util.sc.nextLine();
		System.out.print("전화번호 : ");
		String phone = Util.sc.nextLine();
		System.out.print("주소 : ");
		String addr = Util.sc.nextLine();
		
		//2. 아이디 중복 검사
		//6. 디비 접속, 디비에서 현 아이디와 일치하는 아이디 조회, 성공여부 결과 안내
		Connection conn = new OracleDB().getConnection();
		try {
			String sql = "SELECT STU_ID FROM STUDENT WHERE ID = ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  id);
			ResultSet rs = pstmt.executeQuery();
					
			if (rs.next()) {
				System.out.println("아이디가 중복입니다. 다시 만들어주세요.");
				return false;
			}
			
			String sqlInsert 
			= "INSERT INTO STU_ID(NO,ID,PWD,NAME,MAJOR,PHONE,ADDR)"
					+ "VALUES(?,?,?,?,?,?,?)";
			PreparedStatement pstmt2 = conn.prepareStatement(sqlInsert);
			pstmt2.setInt(1, 1);
			pstmt2.setString(2, id);
			pstmt2.setString(3, pwd);
			pstmt2.setString(4, name);
			pstmt2.setString(5, major);
			pstmt2.setString(6, phone);
			pstmt2.setString(7, addr);
			int result = pstmt2.executeUpdate();
			
			
			if (result == 1) {
				return true;
			}
			
			
		} catch (SQLException e) {  
			e.printStackTrace(); //에러확인
		}
		
		//3. 비밀번호 유효성 검사
		//비밀번호 4글자 이상, 특수문자 1개 이상 포함
		if(pwd.length() < 5 ) {
			System.out.println("4글자 이상으로 만들어주세요!");
			return false;
		}
		else if(pwd.contains("!") || pwd.contains("^")|| pwd.contains("*") || pwd.contains(".") || pwd.contains("#")) {
			//특수문자 1개 이상 포함
			System.out.print("특수문자 !, ^, *, . , # 중에 1개 이상 포함해주세요!");
			return false;
		}
		
		//4.비밀번호 확인
		//pwd와 pwd1이 틀리면 다시 입력
		if (pwd != pwd1) {
			System.out.println("비밀번호가 동일하지  않습니다. 다시 입력해주세요!");
			return false;
		}
		
		//코드 오류. 다시 입력
		return false;
	}

}
