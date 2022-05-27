package com.kh.admin;

import com.kh.screen.EnterKey;
import com.kh.stu.stuMenu;
import com.kh.util.Util;

public class AdminScreen {

	public void adminscreen() {
		int input = 0;
		System.out.println("1. 출석");
		System.out.println("2. 시스템 공지사항");
		System.out.println("3. 로그아웃");
		new EnterKey();
		try {
			input = Util.scInt();
		} catch (Exception e) {
			System.out.println("숫자를 넣어주세요");
			adminscreen();
		}
		switch (input) {
		case 1:
			break;
		case 2:
			new SysBoard().showAllSysBoard();
			break;
		case 3:
			Util.infono = 0;
			Util.info = "";
			break;
		}

	}

	public void showSysBoardMenu() {
		System.out.println("글 번호 선택");
		if (Util.info.equals("admin")) {
			System.out.println("글 쓰기 : -2");
		}
		System.out.println("뒤로가기 : -1");
		int input = 0;
		try {
			input = Util.scInt();
		} catch (Exception e) {
			System.out.println("숫자를 넣어주세요");
			new SysBoard().showAllSysBoard();
		}

		System.out.print("입력 :::  ");

		if (Util.info.equals("admin")) {
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

}
