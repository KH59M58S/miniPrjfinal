package com.kh.prof;

import java.util.Scanner;

import com.kh.util.Util;


public class ProfScreen {

	public void profscreen() {
			System.out.println("==== 교수 화면 메인 ====");
			System.out.println("1. 출석");
			System.out.println("2. 학과 공지");
			System.out.println("3. 강의 관련");
			System.out.println("4. 뒤로가기");
			int input = Util.scInt();
	
		switch (input){
		case 1: System.out.println("출석"); break;
		case 2: System.out.println("학과공지"); break;
		case 3: System.out.println("강의관련"); break;
		case 4: System.out.println("뒤로가기"); break;
		default : System.out.println("잘못선택하셨습니다.");
	
		}
	
	
	}
	

	

}
