package com.kh.prof;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.swy.db.OracleDB;

public class ProfAnnounce {

	//교수 공지사항 선택 시 뜨는 화면
	public void board() {
		System.out.println("==== 학과 공지 ====");
		System.out.println("1. 공지사항 목록");
		System.out.println("2. 공지사항 작성");
		System.out.println("3. 공지사항 삭제");
		System.out.println("4. 공지사항 수정");
		System.out.println("5. 뒤로가기");
	}
	
	//1. 공지사항 목록
	
	public void boardlist() {
	//DB얻기
	Connection conn = OracleDB.getConnection();
	// 공지사항테이블 목록 보여주기
	String sql = "SELECT PRO_NO, PRO_TITLE, PRO_CONTENT, PRO_DATE FROM (SELECT * FROM  WHERE ROWNUM = ?";
	try {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, 1);
		pstmt.setLong(1, 2);
		pstmt.setLong(1, 3);
		pstmt.setLong(1, 4);
		pstmt.setLong(1, 5);
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	
	// 서브쿼리 + 로우넘 이용허시면 됩니다.
	// 좀 어려울 수 있어요 ㅊ ㅓ음엔 ㅎㅎ ㄴ
	//라고 슨거겠죠 ..?ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ맞아요 네 한번 해보시고, 어려움 있으시면 다시 말씀해주세요 ~~~ㄱ
	//화면 공유 종료하겠습니다 ~ ! 갑사합니다
	
	}
	
	//2. 공지사항 작성
	
	//3. 공지사항 삭제
	
	//4. 공지사항 수정
	
	//5. 뒤로가기
	
	
}
