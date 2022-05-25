package com.kh.stu;

import com.kh.util.Util;
import com.swy.db.*;

public class Login {

	public void start() {
		
		System.out.println("==== 로그인 하기 ====");
		System.out.print("아이디 : ");
		String id = Util.sc.nextLine().trim();
		System.out.print("비밀번호 : ");
		String pwd = Util.sc.nextLine().trim();
		
		String sql = "SELECT STU_PWD FROM STUDENT WHERE STU_ID = ? AND STU_PWD= ?";
		
	}
	
	
}
