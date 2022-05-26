package com.kh.admin;

import com.kh.util.Util;

public class AdminScreen {
	
	public void adminscreen() {
		System.out.println("1. 출석");
		System.out.println("2. 시스템 공지사항");
		System.out.println("3. 로그아웃");
		int input = 0;
		try {
			input = Util.scInt();
		} catch(Exception e) {
			System.out.println("숫자를 넣어주세요");
			adminscreen();
		}
		switch(input) {
		case 1: 
			break;
		case 2: new SysBoard().showAllSysBoard();
			break;
		case 3:
			break;
		}
	}
	
	public void showSysBoardMenu() {
		System.out.println("글 번호 선택");
		System.out.println("뒤로가기 : -1");
		int input = 0;
		try {
			input = Util.scInt();
		} catch(Exception e) {
			System.out.println("숫자를 넣어주세요");
			new SysBoard().showAllSysBoard();
		}
		
		if (input == -1) {
			new AdminScreen().adminscreen();
		} else {
			new SysBoard().showSysBoard(input);
		}
		
	}
	
}
