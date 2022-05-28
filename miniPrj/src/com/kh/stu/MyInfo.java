package com.kh.stu;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.prof.ProfScreen;
import com.kh.screen.Screen;
import com.kh.util.Util;
import com.kh.admin.AdminScreen;
import com.kh.db.OracleDB;

public class MyInfo {

	// DB연결
	public static Connection conn = OracleDB.getConnection();
	public static PreparedStatement pstmt = null;
	public static ResultSet rs = null;
	
	public static int yn_Num;

	public static void ShowInfo() {

		System.out.println("나의 정보");
		System.out.println("=====================");
		System.out.print("현재 계정은 : ");
		System.out.println(Util.info + " 계정입니다.");
		System.out.println("=====================");
		System.out.print("나의 이름은 : ");
		System.out.println(Util.stdNoToName(Util.infono));
		System.out.println("=====================");
		System.out.print("나의 회원번호는 ");
		System.out.println(Util.infono + " 입니다.");
		System.out.println("=====================");
		System.out.print("나의 가입날짜는 ");
		stu_showMyEnroll(); // 학생 가입날짜 출력
		System.out.println("=====================");
		System.out.println("1. 뒤로가기  3. 탈퇴하기");
		System.out.println("+++++++++++++++++++++");

		int answer = Util.scInt();
		if (answer == 1) {
			System.out.println("+++++++++++++++++++++++++++++++++++");
			System.out.println("뒤로 이동하겠습니다.");
			System.out.println("+++++++++++++++++++++++++++++++++++");
			new stuMenu().showMenu();
		} else if (answer == 3) {
			System.out.println("+++++++++++++++++++++++++++++++++++");
			System.out.println("탈퇴 진행하겠습니다.");
			System.out.println("탈퇴 하시겠습니까?");
			System.out.println("1. 탈퇴한다. 2. 돌아간다.");
			int answer2 = Util.scInt();
			if (answer2 == 1) {
				stu_QuitYN();
			} else if (answer2 == 2) {
				ShowInfo();
			}
		}

	}
	
	public static void ShowProfInfo() {

		System.out.println("나의 정보");
		System.out.println("=====================");
		System.out.print("현재 계정은 : ");
		System.out.println(Util.info + " 계정입니다.");
		System.out.println("=====================");
		System.out.print("나의 이름은 : ");
		System.out.println(Util.profNoToName(Util.infono));
		System.out.println("=====================");
		System.out.print("나의 회원번호는 ");
		System.out.println(Util.infono + " 입니다.");
		System.out.println("=====================");
		System.out.print("나의 가입날짜는 ");
		stu_showMyEnroll(); // 학생 가입날짜 출력
		System.out.println("=====================");
		System.out.println("1. 뒤로가기  3. 탈퇴하기");
		System.out.println("+++++++++++++++++++++");

		int answer = Util.scInt();
		if (answer == 1) {
			System.out.println("+++++++++++++++++++++++++++++++++++");
			System.out.println("뒤로 이동하겠습니다.");
			System.out.println("+++++++++++++++++++++++++++++++++++");
			new ProfScreen().profScreenStart();
		} else if (answer == 3) {
			System.out.println("+++++++++++++++++++++++++++++++++++");
			System.out.println("탈퇴 진행하겠습니다.");
			System.out.println("탈퇴 하시겠습니까?");
			System.out.println("1. 탈퇴한다. 2. 돌아간다.");
			int answer2 = Util.scInt();
			if (answer2 == 1) {
				prof_QuitYN();
			} else if (answer2 == 2) {
				ShowInfo();
			}
		}

	}

	// 학생 가입 날짜 출력
	public static void stu_showMyEnroll() {
		String sql = "SELECT STU_ENROLL_DATE FROM STUDENT WHERE STU_NO = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Util.infono);
			rs = pstmt.executeQuery();
			rs.next();
			String result = rs.getString(1);
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 교수 가입 날짜 출력
	public static void prof_showMyEnroll() {
		String sql = "SELECT P_ENROLL_DATE FROM PROF WHERE P_NO = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Util.infono);
			rs = pstmt.executeQuery();
			rs.next();
			String result = rs.getString(1);
			System.out.println(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 학생 탈퇴 여부 Y로 바꾸기
	public static void stu_QuitYN() {
		String sql = "UPDATE STUDENT SET STU_QUIT_YN = 'Y' WHERE STU_NO =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Util.infono);
			pstmt.executeUpdate();
			System.out.println("+++++++++++++++++++++++++++++++++++");
			System.out.println("학생 계정 탈퇴 여부 변경 되었습니다. ");
			System.out.println("종료 하겠습니다.");
			System.out.println("+++++++++++++++++++++++++++++++++++");
			Screen.mainMenu_show();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 교수 탈퇴 여부 Y로 바꾸기
	public static void prof_QuitYN() {
		String sql = "UPDATE PROF SET P_QUIT_YN = 'Y' WHERE P_NO =?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Util.infono);
			pstmt.executeUpdate();
			System.out.println("+++++++++++++++++++++++++++++++++++");
			System.out.println("교수 계정 탈퇴 여부 변경 되었습니다. ");
			System.out.println("종료 하겠습니다.");
			System.out.println("+++++++++++++++++++++++++++++++++++");
			Screen.mainMenu_show();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void stu_QuitYN_Search() {
		System.out.println("탈퇴된 학생 계정 리스트를 불러 오는중...");
		System.out.println("==============================");
		System.out.print(" 번호 |  이름  |  아이디  |  가입날짜  | 탈퇴여부");
		System.out.println();
		String sql = "SELECT STU_NO, STU_NAME, STU_ID, STU_ENROLL_DATE, STU_QUIT_YN FROM STUDENT WHERE STU_QUIT_YN = 'Y'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int no= rs.getInt("STU_NO");
				String name= rs.getString("STU_NAME");
				String id= rs.getString("STU_ID");
				Date date= rs.getDate("STU_ENROLL_DATE");
				String yn =rs.getString("STU_QUIT_YN");
				
				System.out.print("  " + no + "    " + name + "    " + id + "   " + date + "   " + yn);
				System.out.println();
			}
			//번호 클릭후 여부 변경 메소드
			System.out.println("+++++++++++++++++++++++++++++++++++");
			System.out.println("탈퇴 여부 바꿀 계정이 있으신가요?? (있을시 해당 번호 입력, 없을시 -1)");
			yn_Num= Util.scInt();
			stuChangeYn(yn_Num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void prof_QuitYN_Search() {
		System.out.println("탈퇴된 교수 계정 리스트를 불러 오는중...");
		System.out.println("==============================");
		System.out.println(" 번호 |  이름  |  아이디  |  가입날짜  | 탈퇴여부");
		String sql = "SELECT P_NO, P_NAME, P_ID, P_ENROLL_DATE, P_QUIT_YN FROM PROF WHERE P_QUIT_YN = 'Y'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int no= rs.getInt("P_NO");
				String name= rs.getString("P_NAME");
				String id= rs.getString("P_ID");
				Date date= rs.getDate("P_ENROLL_DATE");
				String yn =rs.getString("P_QUIT_YN");
				
				System.out.print("  " + no + "    " + name + "    " + id + "   " + date + "   " + yn);
				System.out.println();
			}
			//번호 클릭후 여부 변경 메소드
			System.out.println("+++++++++++++++++++++++++++++++++++++++");
			System.out.println("탈퇴 여부 바꿀 계정이 있으신가요?? (있을시 해당 번호 입력, 없을시 -1)");
			yn_Num= Util.scInt();
			profChangeYn(yn_Num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void stuChangeYn(int Num) {
		if(Num==-1) {
			selectnum();
		}
		String sql = "UPDATE STUDENT SET STU_QUIT_YN = 'N' WHERE STU_NO= ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Num);
			pstmt.executeUpdate();
			rs.next();
			System.out.println("+++++++++++++++++++++++++++++++++++++++");
			System.out.println("학생 계정 탈퇴 여부 변경 되었습니다. ");
			System.out.println("종료 하겠습니다.");
			//번호 클릭후 여부 변경 메소드
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void profChangeYn(int Num) {
		if(Num==-1) {
			selectnum();
		}
		String sql = "UPDATE PROF SET P_QUIT_YN = 'N' WHERE P_NO= ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Num);
			pstmt.executeUpdate();
			rs.next();
			System.out.println("+++++++++++++++++++++++++++++++++++");
			System.out.println("교수 계정 탈퇴 여부 변경 되었습니다. ");
			System.out.println("종료 하겠습니다.");
			//번호 클릭후 여부 변경 메소드
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void selectnum() {
		System.out.println("+++++++++++++++ 계정 관리 +++++++++++++++");
		System.out.println("1. 학생 계정 관리");
		System.out.println("2. 교수 계정 관리");
		System.out.println("0. 뒤로가기");
		
		int num = Util.scInt();
		
		if(num ==1) {
			stu_QuitYN_Search();
		} else if(num ==2) {
			prof_QuitYN_Search();
		}else {
			new AdminScreen().adminscreen();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
