package com.kh.prof;


import com.kh.lecture.RegistrationLecture;
import com.kh.lecture.SignUp;
import com.kh.util.Util;

public class ProfClass {

	
	public static void ClassList() {
				System.out.println();
				System.out.println();
				System.out.println("+++++++++++++++ 전체 강의 목록 보기 +++++++++++++++");
				System.out.println("1. 전체 신청 리스트 조회    2. 학생별 강의 신청 내역 조회   ");
				System.out.println("3. 강의별 신청 내역 조회    4. 뒤로가기");
				System.out.print(">>원하시는 메뉴의 번호를 선택하세요 : ");
				
				int input = Util.scInt();
				
				
		switch(input){
			case 1 : 
				SignUp.showAllSignupList(); 
				ClassList();
				break;
				
			case 2 : 
				System.out.print("학생 이름 입력 : ");
				String input1 = Util.sc.nextLine();
				SignUp.showStdSignUp(Util.stdNameToNo(input1)); 
				ClassList();
				break;
			case 3 : 
				System.out.print("강의 이름 입력 : ");
				String c_name = Util.sc.nextLine();
				SignUp.showLectureSingUp(c_name); 
				break;
			case 4 : ProfScreen.profScreenStart();break;
			default : System.out.println("잘못 선택하셨습니다."); break;
			
		}
		
	}
	
	
	
	
	public static void ClassAdd() {
		System.out.println();
		System.out.println("+++++++++++++++ 강의 추가 하기 +++++++++++++++");
		//객체 생성  후 호출
       new RegistrationLecture().RegistrationClass(Util.infono); 
	}

	
	
	
	
}//class
	
	
	
	

