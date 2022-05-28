package com.kh.screen;

import com.kh.stu.IDsearch;
import com.kh.stu.Login;
import com.kh.stu.PWDsearch;
import com.kh.stu.SignUp;
import com.kh.util.Util;

public class Screen {

	// 메인화면 가는 메뉴
	public static void mainMenu_show() {
		boolean flag = true;
		while (flag) {
			System.out.println();
			System.out.println("#   #  #  #      #   #  #   #  ###  #    #");
			System.out.println("# #    #  #      #   #  ##  #   #   #    #");
			System.out.println("##     ####      #   #  # # #   #   #    #");
			System.out.println("# #    #  #      #   #  #  ##   #    #  #");
			System.out.println("#  #   #  #      #####  #   #  ###    #");
			System.out.println("+++++++++++++++ 메인 화면 +++++++++++++++");
			System.out.println("1. 회원가입 2. 로그인 3. 아이디 찾기 ");
			System.out.println("4. 비밀번호 찾기 5. 종료하기");
			System.out.print("입력 : ");
			int input = Util.scInt();

			switch (input) {
			case 1:
				new SignUp().join();
				break;
			case 2:
				Login.try_login();
				break;
			case 3:
				IDsearch.try_IDFind();
				break;
			case 4:
				PWDsearch.try_PWDFind();
				break;
			case 5:
				System.out.println("잘가세요");
				System.exit(0);
			}
		}
	}
}
