package com.kh.prof;

import java.util.Scanner;

import com.kh.stu.Login;
import com.kh.stu.MyInfo;
import com.kh.stu.stuMenu;
import com.kh.util.Util;

public class ProfScreen {

	public static void profScreenStart() {
		while (true) {
			System.out.println("\n==== 교수 화면 메인 ====");
			System.out.println("1. 출석            ");
			System.out.println("2. 학사 공지        ");
			System.out.println("3. 강의 관련        ");
			System.out.println("4. 마이페이지        ");
			System.out.println("5. 로그아웃          ");
			int input = Util.scInt();

			switch (input) {
			case 1:
				new ProfAtt().classAttendence();
				break;
			case 2:
				new ProfAnnounce().board();
				break; // 교수전용목록으로 보임
			case 3:
				System.out.println("\n==== 번호를 선택하세요. ====");
				System.out.println(" 1. 전체 강의 목록       ");
				System.out.println(" 2. 강의 추가 하기       ");
				System.out.println(" 3. 뒤로가기            ");
				
				int a = Util.scInt();
				
				if(a == 1) {
					new ProfClass().ClassList();
				} else if(a == 2) {
					new ProfClass().ClassAdd();
				} else if(a == 3){
					new ProfScreen();
				} else {
					System.out.println("잘못 선택하셨습니다 ...");
				}
				break;
			case 4:
				new MyInfo().ShowProfInfo();
				break;
			case 5:
				new Login().try_login();
				break;// 로그인하는 메소드로 이동
			default:
				System.out.println("잘못 선택하셨습니다 ...");

			}
		}

	}

	public static void main(String[] args) {
		profScreenStart();
	}
	
}
