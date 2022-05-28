package com.kh.lecture;

import com.kh.stu.stuMenu;
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
			System.out.println("1. 수강신청  2. 강의명별 검색  3. 교수명별 검색");
			System.out.println("4. 수강신청 취소  0. 이전 단계");
			System.out.println("++++++++++++++++++++++++++++");

			System.out.print("입력창 : ");
			int chose = 0;
			try {
				chose = Util.scInt();
			} catch (Exception e) {
				System.out.println("잘못 입력하셨습니다.");
				LSingUpUIStart(Util.infono);
			}
			if (chose == 0) {
				System.out.println("이전 페이지...");
				stuMenu.showMenu();
			} else if (chose == 1) {
				while (true) {
					System.out.print("신청할 강의번호 입력(이전 단계 = 0) : ");
					int c_no = -1;
					try {
						c_no = Util.scInt();
					} catch (Exception e) {
						System.out.println("잘못 입력하셨습니다.");
						continue;
					}

					if (c_no == 0) {
						System.out.println("이전 페이지...");
						LSingUpUIStart(Util.infono);
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

			} else if (chose == 4) {
				while (true) {
					SignUp.showStdSignUp(Util.infono);
					System.out.print("취소할 강의번호 입력(이전 단계 = 0) : ");
					int c_no;
					try {
						c_no = Util.scInt();
					} catch (Exception e) {
						System.out.println("잘못 입력하셨습니다.");
						continue;
					}

					if (c_no == 0) {
						System.out.println("이전 페이지...");
						LSingUpUIStart(Util.infono);
					}
					int res = SignUp.deleteSignUp(c_no);

					if (res >= 1) {
						System.out.println("취소완료!!!");
					} else if (res == 0) {
						System.out.println("취소실패...");
					} else {
						System.out.println("잘못 입력하였습니다.");
					}
				}
			}
		}
	}

	// 장바구니 기능
	public static void userSingUpList(int std_no) {
		System.out.println("++++++++++++ 내가 신청한 강의 +++++++++++++");
		SignUp.showStdSignUp(std_no);
	}

	// 테스트용 메인메서드 나중에 없앰
	public static void main(String[] args) {
		LSingUpUIStart(1);
	}

}
