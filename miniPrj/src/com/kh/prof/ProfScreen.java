package com.kh.prof;

import java.util.Scanner;

import com.kh.util.Util;


public class ProfScreen {

	public static void main(String[] args) {
		
	
			System.out.println("==== 교수 화면 메인 ====");
			System.out.println("1. 출석");
			System.out.println("2. 학과 공지");
			System.out.println("3. 강의 관련");
			System.out.println("4. 뒤로가기");
			int input = Util.scInt();
	
		switch (input){
//		case 1: new ProfAtt(); break;
		case 2: new ProfAnnounce().board(); break;
//		case 3: new ProfClass().class(); break;
		case 4: System.out.println("뒤로가기"); break;
		default : System.out.println("잘못선택하셨습니다.");
	
		}
	
	
	}
	
}
	

