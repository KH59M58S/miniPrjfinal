package com.kh.stu;

import com.kh.admin.SysBoard;
import com.kh.lecture.LSignUpUI;
import com.kh.prof.ProfAnnounce;
import com.kh.screen.Screen;
import com.kh.util.Attenment;
import com.kh.util.Util;

public class stuMenu {

	public static void showMenu() {
		System.out.println();
		System.out.println("+++++++++++++++ 로그인 화면 메인 +++++++++++++++");
		System.out.println("1. 시스템 공지 2. 학사 공지 3. 수강 관련"); // 학생기준 화면 보는거라 1. 시스템공지 2.상세보기 3.돌아가기
		System.out.println("4. 마이페이지 5. 출석체크 6. 로그아웃"); // 학생기준 화면 보는거라 1. 학사 공지 2.상세보기 3.돌아가기
		
		int num = -1;
		
		try {
			num = Util.scInt();
		} catch (Exception w) {
			System.out.println("잘못 입력하셨습니다...");
			showMenu();
		}
		switch (num) {
		case 1:
			new stuMenu().showSysNotice();
			break;
		case 2:
			new stuMenu().showCNotice();
			break;
		case 3:
			new stuMenu().showClass();
			break;
		case 4:
			new stuMenu().showMyPage();
			break;
		case 5:
			Attenment.stuAttin();
			showMenu();
			break;
		case 6:
			Screen.mainMenu_show();
			break;
		default:
			throw new IllegalArgumentException("잘못된 값 : " + num);
		}

	}

	public void showSysNotice() {
		System.out.println();
		System.out.println("+++++++++++++++ 시스템 공지 +++++++++++++++");
		new SysBoard().showAllSysBoard();
	}

	public void showCNotice() {
		System.out.println();
		new ProfAnnounce().boardStu();
	}

	public void showClass() {
		System.out.println();
		System.out.println("+++++++++++++++ 강의 목록 +++++++++++++++");
		LSignUpUI.LSingUpUIStart(Util.infono);
	}

	public void showMyPage() {
		System.out.println();
		System.out.println("+++++++++++++++ 마이 페이지 +++++++++++++++");
		MyInfo.ShowInfo();
	}

}
