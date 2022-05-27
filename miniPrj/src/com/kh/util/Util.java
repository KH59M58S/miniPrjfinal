package com.kh.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.kh.db.OracleDB;

public class Util {
	
	public static Scanner sc = new Scanner(System.in);
	public static String info = null;
	public static int infono = 0;
	
	
	public static int scInt() {
		return Integer.parseInt(sc.nextLine());
	}
	
	//학생번호를 학생 이름으로 반환
	public static String stdNoToName(int std_no) {
		String result = null;
		
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT STU_NAME FROM STUDENT WHERE STU_NO = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, std_no);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getString(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//학생이름을 학생 번호로 반환
	public static int stdNameToNo(String std_name) {
		int result = 0;
		
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT STU_NO FROM STUDENT WHERE STU_NAME = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, std_name);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//교수이름을 교수 번호로 반환
	public static String profNoToName(int prof_no) {
		String result = null;
		
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT P_NAME FROM PROF WHERE P_NO = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prof_no);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getString(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//교수번호를 교수 이름으로 변환
	public static int profNameToNo(String prof_name) {
		int result = 0;
		
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT P_NO FROM PROF WHERE P_NAME = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, prof_name);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//강의명을 강의번호로 변환
	public static int classNameToNo(String c_name) {
		int result = 0;
		
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT C_NO FROM CLASS WHERE C_NAME = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_name);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public static int deptNameToNo(String d_name) {
		int result = 0;
		
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT D_NO FROM DEPT WHERE D_NAME = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, d_name);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static int profNotoDeptNo(int p_no) {
		int result = 0;
		
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT D_NO FROM PROF WHERE P_NO = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_no);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 부서 개수
	public static int countDept() {
		Connection conn = OracleDB.getConnection();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0;
		String sqlSelect = "SELECT COUNT(DEPT_NO) FROM DEPT";
		
		try {
			pstmt = conn.prepareStatement(sqlSelect);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
		
	}
	
}
		   


