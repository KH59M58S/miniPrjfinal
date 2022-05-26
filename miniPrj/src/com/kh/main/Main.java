package com.kh.main;

import com.kh.stu.IDsearch;
import com.kh.stu.Login;
import com.kh.stu.PWDsearch;
import com.kh.stu.SignUp;
import com.kh.util.Util;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("==== 메인 화면 ====");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 아이디 찾기");
		System.out.println("4. 비밀번호 찾기");
		System.out.println("5. 종료하기");
		int input = Util.scInt();
		
		switch(input) {
		case 1 : new SignUp().join();
			break;
		case 2 : Login.try_login();
			break;
		case 3 : IDsearch.try_IDFind();
			break;
		case 4: PWDsearch.try_PWDFind();
			break;
		case 5 :
			break;
		}
		
	}

}
