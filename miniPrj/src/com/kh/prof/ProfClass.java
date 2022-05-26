package com.kh.prof;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.lecture.LectureOracle;
import com.kh.lecture.RegistrationLecture;
import com.kh.lecture.SignUp;
import com.kh.util.Util;
import com.swy.db.OracleDB;

public class ProfClass {

	
	public static void ClassList() {
				System.out.println(" ===== 전체 강의 목록 보기 =====");
				int input = Util.scInt();
				
		
		
		switch(input){
			case 1 : 
				System.out.println("   1. 전체 신청 리스트 조회     ");
				SignUp.showAllSignupList(); break;
			case 2 : 
				System.out.println("   2. 학생별 강의 신청 내역 조회  ");
				String std_name = Util.sc.nextLine();
				SignUp.showStdSignUp(std_name); break;
			case 3 : 
				System.out.println("   3. 강의별 신청 내역 조회     ");
				String c_name = Util.sc.nextLine();
				SignUp.showLectureSingUp(c_name); break;
			default : System.out.println("잘못 선택하셨습니다."); break;
			
		}
		
	}
	
	
	
	
	public static void ClassAdd() {
		System.out.println("===== 강의 추가 하기 =====");
		//객체 생성  후 호출
       new RegistrationLecture().RegistrationClass(); 
	}

	
	
	
	
	
}//class
	
	
	
	

