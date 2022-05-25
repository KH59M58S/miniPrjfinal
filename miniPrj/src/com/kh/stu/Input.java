package com.kh.stu;

import java.util.Scanner;

public class Input {
	
	public static Scanner sc = new Scanner(System.in);
	
	public static int scInt() {
		int n = sc.nextInt(); //엔터키 제거
		sc.nextLine();
		return n;
	}
	
}
