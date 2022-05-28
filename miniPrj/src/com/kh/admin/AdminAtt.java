package com.kh.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import com.kh.db.OracleDB;
import com.kh.util.Util;

public class AdminAtt {

	public void AdminClassAtt() {

		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println();
		System.out.print("과목을 입력하세요 : ");
		String c_name = Util.sc.nextLine();
		System.out.println();

		// 3. 해당 강의에 맞는 출석부 조회하기

		try {
			String sql = "SELECT * FROM C_" + Util.classNameToNo(c_name) + "_ATT "; // 바꾸기
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			while (rs.next()) {
				
				Timestamp a_time = rs.getTimestamp(1);
				String a_check = rs.getString(2);
				int stu_no = rs.getInt(3);
				
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

}
