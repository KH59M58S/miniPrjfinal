package com.kh.stu;

import com.kh.admin.Admin;
import com.kh.admin.SysBoard;
import com.kh.lecture.LSignUpUI;
import com.kh.prof.ProfAnnounce;
import com.kh.util.Util;

public class stuMenu {

	public static void showMenu() {
		System.out.println("++++++++++ 로그인 화면 메인 ++++++++++");
		System.out.println("1. 시스템 공지");	//학생기준 화면 보는거라 1. 시스템공지 2.상세보기 3.돌아가기
		System.out.println("2. 학사 공지");	//학생기준 화면 보는거라 1. 학사 공지 2.상세보기 3.돌아가기
		System.out.println("3. 수강 관련");	//학생기준 화면 보는거라 1. 강의목록 2. 수강신청 3.돌아가기
		System.out.println("4. 마이페이지");	//학생기준 화면 보는거라 1. 수강내역 2.자기정보보기 3.돌아가기
		System.out.println("5. 로그아웃");

		int num = Util.scInt();

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
			Login.stu_Login();
			break;
		default:
			throw new IllegalArgumentException("잘못된 값 : " + num);
		}

	}

	public void showSysNotice() {
		System.out.println("시스템 공지");
		new SysBoard().showAllSysBoard();
	}

	public void showCNotice() {
		System.out.println("학사 공지");
		new ProfAnnounce().board();
	}

	public void showClass() {
		System.out.println("강의 목록");
		LSignUpUI.LSingUpUIStart(Util.infono);
	}

	public void showMyPage() {
		System.out.println("마이 페이지");
		// 나중에
	}
	
}
