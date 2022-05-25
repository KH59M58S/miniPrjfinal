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
	
	public void findbyProfClass() {
		
		System.out.println("교수명 : ");
		String p_name = Util.sc.nextLine();
		
		Connection conn = LectureOracle.getConnection();
		
		String sql ="SELECT C.C_NAME,C.C_TIME,C.C_ROOM,P.P_NAME "
				+ " FROM CLASS C"
				+ " JOIN PROF P ON C.P_NO = P.P_NO"
				+ " WHERE P.P_NAME = ?";
		
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p_name);
			rs = pstmt.executeQuery();
			System.out.println("이름 \t\t 시간 \t\t\t 교수명 \t\t 강의실");
			while (rs.next()) {
				
				String cname = rs.getString(1);
				Date time = rs.getDate(2);
				String room = rs.getString(3);
				String pname = rs.getString(4);
				DateFormat df = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분");
				String d = null;
				
				d = df.format(time);
				
				System.out.println(cname + "\t\t" + d + "\t\t\t" + pname + "\t\t" + room);
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
	
	public void findbyTimeClass() {

		System.out.println("ex) xxxx년 xx월 xx일 xx시 xx분");
		System.out.println("시간 : ");
		String c_time = Util.sc.nextLine();
		
		DateFormat df = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분");
		Date date = null;
		try {
			date = df.parse(c_time);
		} catch (ParseException e1) {
			System.out.println("형식에 맞게 입력해주세요");
			findbyTimeClass();
		}
		long dt = date.getTime();
		Timestamp ts = new Timestamp(dt);
		
		
		Connection conn = LectureOracle.getConnection();
		
		String sql ="SELECT C.C_NAME,C.C_TIME,C.C_ROOM,P.P_NAME "
				+ " FROM CLASS C"
				+ " JOIN PROF P ON C.P_NO = P.P_NO"
				+ " WHERE C.C_TIME = ?";
		
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setTimestamp(1, ts);
			rs = pstmt.executeQuery();
			System.out.println("이름 \t\t 시간 \t\t\t 교수명 \t\t 강의실");
			while (rs.next()) {
				
				String cname = rs.getString(1);
				Date time = rs.getDate(2);
				String room = rs.getString(3);
				String pname = rs.getString(4);
				String d = null;
				
				d = df.format(time);
				
				System.out.println(cname + "\t\t" + d + "\t\t\t" + pname + "\t\t" + room);
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
		
		Connection conn = LectureOracle.getConnection();
		
		String sql ="SELECT C.C_NAME,C.C_TIME,C.C_ROOM,P.P_NAME "
				+ " FROM CLASS C"
				+ " JOIN PROF P ON C.P_NO = P.P_NO"
				+ " WHERE C.C_ROOM = ?";
		
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_room);
			rs = pstmt.executeQuery();
			System.out.println("이름 \t\t 시간 \t\t\t 교수명 \t\t 강의실");
			while (rs.next()) {
				
				String cname = rs.getString(1);
				Date time = rs.getDate(2);
				String room = rs.getString(3);
				String pname = rs.getString(4);
				DateFormat df = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분");
				String d = null;
				
				d = df.format(time);
				
				System.out.println(cname + "\t\t" + d + "\t\t\t" + pname + "\t\t" + room);
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
