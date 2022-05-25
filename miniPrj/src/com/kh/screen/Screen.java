package com.kh.screen;

import com.kh.util.Util;

public class Screen {
	
	public void Main() {
		System.out.println("==== 메인 화면 ====");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 아이디 찾기");
		System.out.println("4. 비밀번호 찾기");
		System.out.println("5. 종료하기");
		int input = Util.scInt();
		
		
	}
	
	public void join() {
		System.out.println("==== 회원 가입 ====");
		System.out.print("이름 : ");
		System.out.print("아이디 : ");
		System.out.print("비밀번호 : ");
		System.out.print("전화번호 : ");
		System.out.print("주소 : ");
		System.out.println("학과 선택,,,,");
	}
	
	public void login() {
		System.out.println("==== 로그인 하기 ====");
		System.out.print("아이디 : ");
		System.out.print("비밀번호 : ");
	}
	
	public void findId() {
		System.out.println("==== 아이디 찾기 ====");
		System.out.println("이름 : ");
		System.out.println("전화번호 : ");
	}
	
	public void findPwd() {
		System.out.println("==== 비밀번호 찾기 ====");
		System.out.print("아이디 : ");
		System.out.print("이름 : ");
		System.out.print("전화번호 : ");
	}
	
	public void loginMenu() {
		System.out.println("==== 로그인 화면 메인 ====");
		System.out.println("1. 시스템 공지");
		System.out.println("2. 학사 공지");
		System.out.println("3. 수강 관련");
		System.out.println("4. 마이페이지");
	}
	
	public void  sysAnnounce() {
		System.out.println("==== 시스템 공지 ====");
	}
	
	public void  profAnnounce() {
		System.out.println("==== 학사 공지 ====");
	}
	
	public void  aboutClass() {
		System.out.println("==== 수강 관련 ====");
		System.out.println("1. 전체 목록");
	}
	
	public void myPage() {
		System.out.println("==== 마이 페이지 ====");
		System.out.println("1. 수강 신청 내역");
		// 나의 정보?
	}
	
	public void adminMenu() {
		System.out.println("==== 관리자 화면 메인 ====");
		System.out.println("1. 출석");
		System.out.println("2. 시스템 공지 사항 보기");
		// sysAnnounce 사용하면 될듯?
		System.out.println("3. 로그아웃");
	}
	
	public void adminAtt() {
		System.out.println("==== 관리자 출석 ====");
		System.out.println("1. 과목 선택");
	}
	
	
	
	public void profMenu() {
		System.out.println("==== 교수 화면 메인 ====");
		System.out.println("1. 출석");
		System.out.println("2. 학과 공지");
		// profAnnounce ?
		System.out.println("3. 강의 관련");
		System.out.println("4. 뒤로가기");
	}
	
	public void profAtt() {
		System.out.println("==== 교수 출석 ====");
		System.out.println("1. 과목 선택");
	}
	
	public void profClass() {
		System.out.println("==== 강의 관련 ====");
		System.out.println("1. 전체목록");
		System.out.println("2. 강의 추가");
		System.out.println("3. 뒤로가기");
	}
	
	
	
	
}
