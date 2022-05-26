package com.kh.lecture;

import com.kh.util.Util;

public class LSignUpUI {

	static FindLecture fl = new FindLecture();

	// 수강신청 페이지
	public static void LSingUpUIStart(int std_no) {	
		
		System.out.println();
		System.out.println("++++++++++ 수강신청 ++++++++++");
		SignUp.showAllLectureList();
		
		while (true) {
			System.out.println("++++++++++++++++++++++++++++");
			System.out.println("1. 수강신청");
			System.out.println("2. 강의명별 검색");
			System.out.println("3. 교수명별 검색");
			System.out.println("0. 이전 단계");
			System.out.println("++++++++++++++++++++++++++++");
			
			
			System.out.print("입력창 : ");
			int chose = Util.scInt();
			if (chose == 0) {
				System.out.println("이전 페이지...");
				break;
			} else if (chose == 1) {
				while (true) {
					System.out.print("신청할 강의번호 입력(이전 단계 = 0) : ");
					int c_no = Util.scInt();

					if (c_no == 0) {
						System.out.println("이전 페이지...");
						break;
					}
					if (SignUp.insertSignUp(c_no, std_no)) {
						System.out.println("신청완료!!!");
					} else {
						System.out.println("잘못 입력하였습니다.");
					}
				}
			} else if (chose == 2) {
				System.out.println("강의별 검색입니다.");
				new FindLecture().findbyNameClass();
			} else if (chose == 3) {
				System.out.println("교수별 검색입니다.");
				new FindLecture().findbyProfClass();
			}
		}
	}

	//장바구니 기능
	public static void userSingUpList(int std_no) {
		System.out.println("++++++++++++ 내가 신청한 강의 +++++++++++++");
		SignUp.showStdSignUp(std_no);
	}

	
	//테스트용 메인메서드 나중에 없앰
	public static void main(String[] args) {
		LSingUpUIStart(1);
	}

}
