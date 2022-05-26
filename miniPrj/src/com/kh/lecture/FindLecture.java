package com.kh.lecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.kh.util.Util;
import com.swy.db.OracleDB;

public class FindLecture {

	public void findAllClass() {
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT C.C_NO, C_NAME, C.C_TIME, C.C_ROOM, P.P_NAME " + "FROM CLASS C " + "INNER JOIN PROF P "
				+ "ON (C.P_NO = P.P_NO) ";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println("강의번호:" + rs.getString(1) + "  강의명:" + rs.getString(2) + "  시간:" + rs.getString(3)
						+ "  강의실: " + rs.getString(4) + "  교수명:" + rs.getString(5));
			}

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


	public void findbyProfClass() {

		System.out.println("교수명 : ");
		String p_name = Util.sc.nextLine();

		Connection conn = OracleDB.getConnection();

		String sql = "SELECT C.C_NAME,C.C_TIME,C.C_ROOM,P.P_NAME " + " FROM CLASS C" + " JOIN PROF P ON C.P_NO = P.P_NO"
				+ " WHERE P.P_NAME = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p_name);
			rs = pstmt.executeQuery();
			System.out.println("이름 \t\t 시간 \t\t\t 교수명 \t\t 강의실");
			while (rs.next()) {
				String cname = rs.getString(1);
				String time = rs.getString(2);
				String room = rs.getString(3);
				String pname = rs.getString(4);

				System.out.println(cname + "\t\t" + time + "\t\t\t" + pname + "\t\t" + room);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}
	}

	public void findbyRoomClass() {

		System.out.println("강의실명 : ");
		String c_room = Util.sc.nextLine();

		Connection conn = OracleDB.getConnection();

		String sql = "SELECT C.C_NAME,C.C_TIME,C.C_ROOM,P.P_NAME " + " FROM CLASS C" + " JOIN PROF P ON C.P_NO = P.P_NO"
				+ " WHERE C.C_ROOM = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_room);
			rs = pstmt.executeQuery();
			System.out.println("이름 \t\t 시간 \t\t\t 교수명 \t\t 강의실");
			while (rs.next()) {

				String cname = rs.getString(1);
				String time = rs.getString(2);
				String room = rs.getString(3);
				String pname = rs.getString(4);

				System.out.println(cname + "\t\t" + time + "\t\t\t" + pname + "\t\t" + room);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}

	}
	
	public void findbyNameClass() {

		System.out.println("강의명 : ");
		String c_name = Util.sc.nextLine();

		Connection conn = OracleDB.getConnection();

		String sql = "SELECT C.C_NAME,C.C_TIME,C.C_ROOM,P.P_NAME " + " FROM CLASS C" + " JOIN PROF P ON C.P_NO = P.P_NO"
				+ " WHERE C.C_NAME = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_name);
			rs = pstmt.executeQuery();
			System.out.println("이름 \t\t 시간 \t\t\t 교수명 \t\t 강의실");
			while (rs.next()) {
				String cname = rs.getString(1);
				String time = rs.getString(2);
				String room = rs.getString(3);
				String pname = rs.getString(4);


				System.out.println(cname + "\t\t" + time + "\t\t\t" + pname + "\t\t" + room);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}

	
	}
}
