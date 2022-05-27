package com.kh.admin;

import com.kh.stu.MyInfo;
import com.kh.stu.stuMenu;
import com.kh.util.Util;


public class AdminScreen {

	public void adminscreen() {
		int input = 0;
		System.out.println("1. 강의 관련");
		System.out.println("2. 시스템 공지사항");
		System.out.println("3. 계정관리");
		System.out.println("4. 로그아웃");
		try {
			input = Util.scInt();
		} catch (Exception e) {
			System.out.println("숫자를 넣어주세요");
			adminscreen();
		}
		switch (input) {
		case 1:
			AdminAttScreen();
			break;
		case 2:
			new SysBoard().showAllSysBoard();
			break;
		case 3:
			MyInfo.selectnum();
		case 4:
			Util.infono = 0;
			Util.info = "";
			break;
		}

	}

	public void showSysBoardMenu() {

		System.out.println("글 번호 선택");
		if (Util.info.equals("Admin")) {
			System.out.println("글 쓰기 : -2");
		}
		System.out.println("뒤로가기 : -1");
		System.out.print("입력 :::  ");
		int input = 0;
		try {
			input = Util.scInt();
		} catch (Exception e) {
			System.out.println("숫자를 넣어주세요");
			new SysBoard().showAllSysBoard();
		}

		if (Util.info.equals("Admin")) {
			if (input == -1) {
				new AdminScreen().adminscreen();
			} else if (input == -2) {
				new SysBoard().writeSysBoard();
			} else {
				new SysBoard().showSysBoard(input);
			}
		} else if (Util.info.equals("Student")) {
			if ( input == -1) {
				stuMenu.showMenu();
			} else {
				new SysBoard().showSysBoard(input);
			}
		}

	}
	
	public void AdminAttScreen() {
		System.out.println("===== 출석 선택 =====");
		System.out.println("1. 강의별 출석 보기");
		System.out.println("2. 강의별 학생 보기");
		System.out.println("3. 강의의 해당 일의 출석 학생");
		int input = Util.scInt();
		switch (input) {
		case 1: new AdminAtt().AdminClassAtt();
			break;
		case 2:
			break;
		case 3:
			break;
		}
	}

}
