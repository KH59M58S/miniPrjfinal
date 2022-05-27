package com.kh.prof;

import java.util.Scanner;

import com.kh.stu.Login;
import com.kh.util.Util;


public class ProfScreen {

	
	public static void profScreenStart() {
		while(true) {
			System.out.println("==== 교수 화면 메인 ====");
			System.out.println("1. 출석");
			System.out.println("2. 학과 공지");
			System.out.println("3. 강의 관련");
			System.out.println("4. 뒤로가기");
			int input = Util.scInt();
	
		switch (input){
		case 1:
			System.out.println("번호를 선택 하세요.");
			System.out.println("1. 과목별 출석부 조회");
			System.out.println("2. 학생별 출석부 조회");
			int a = Util.scInt();
			
			if(a == 1) {
				new ProfAtt().classAttendence();
			} else if(a == 2 ) {
				new ProfAtt().stuAttendence();
			} else {
				System.out.println("잘못 누르셨습니다.");
			} break;
			
		case 2: new ProfAnnounce().board(); break;
		case 3: new ProfClass().ClassList(); break;
		case 4: new Login().try_login(); break;//System.out.println("뒤로가기"); break;
		default : System.out.println("잘못선택하셨습니다.");
	
		}
	}
	
	}
	
}
	

