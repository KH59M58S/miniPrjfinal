package com.kh.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.kh.db.OracleDB;
import com.kh.util.Util;

public class AdminAtt {

	public void AdminClassAtt() {

		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("과목을 입력하세요.");
		String c_name = Util.sc.nextLine();

		// 3. 해당 강의에 맞는 출석부 조회하기

		try {
			String sql = "SELECT * FROM C_" + Util.classNameToNo(c_name) + "_ATT "; // 바꾸기
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int a_no = rs.getInt(1);
				Timestamp a_time = rs.getTimestamp(2);
				String a_check = rs.getString(3);
				int stu_no = rs.getInt(4);

				System.out.print("출석번호 : " + a_no + " | ");
				System.out.print("출석체크 시간 : " + a_time + " | ");
				System.out.print("출석여부 : " + a_check + " | ");
				System.out.println("학생번호 : " + stu_no + " | ");
			}
//			System.out.println("출석 번호 : " + rs.getString(1) + "\t" + "출석 체크 시간 : " + rs.getDate(2) + "\t"
//					+ "출석 여부 : "+ rs.getByte(3)+ "학생 번호 : "+rs.getLong(4)
//					);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn == null)
				OracleDB.close(conn);
			if (pstmt == null)
				OracleDB.close(pstmt);
			if (rs == null)
				OracleDB.close(rs);

		}

	}

	public void stuInClass() {

		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("과목을 입력하세요.");
		String c_name = Util.sc.nextLine();

		try {
			String sql = 
					"SELECT S.STU_NAME "
					+ " FROM C_" + Util.classNameToNo(c_name) + "_ATT  C "
							+ " INNER JOIN STUDENT S"
							+ " ON S.STU_NO = C.STU_NO"; 
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				String stuname = rs.getString(1);
				System.out.println("강의명 : " + c_name);
				System.out.print("학생명 : " );
				System.out.print(stuname + " ");
			}


		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("없는 강의 입니다.");
			stuInClass();
		} finally {
			if (conn == null)
				OracleDB.close(conn);
			if (pstmt == null)
				OracleDB.close(pstmt);
			if (rs == null)
				OracleDB.close(rs);

		}

	}
	
	public static void main(String[] args) {
		new AdminAtt().stuInClass();
	}
	


}
