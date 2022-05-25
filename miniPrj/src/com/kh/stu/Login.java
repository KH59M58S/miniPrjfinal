package com.kh.stu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.kh.util.Util;
import com.swy.db.OracleDB;


public class Login {

	public static int stu_Login() {
		
		System.out.println("==== 로그인 하기 ====");
		System.out.print("아이디 : ");
		String id = Util.sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = Util.sc.nextLine();
		
		
		//DB연결
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//해당 아이디에 맞는 패스워드 DB에서 조회하기 SELECT  =  executeQuery();
		String sql = "SELECT STU_PWD FROM STUDENT WHERE STU_ID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String dbPwd = rs.getString(1);
				if(dbPwd.equals(pwd)) {
					//로그인 성공
					System.out.println("로그인 성공!!!");
					return 1;
				}else {
					System.out.println("비밀번호를 잘못 입력하였습니다!");
					return 0;	
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("!! SQL 예외 발생 !!");	
			return -1;
		}finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
		
		System.out.println("로그인 실패 입니다...");
		return -2;
		
		
	}
	
}
