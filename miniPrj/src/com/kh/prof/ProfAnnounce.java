package com.kh.prof;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.util.Util;
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
		
        int input = Util.scInt();
        
    switch (input) {
    case 1 : boardlist();break;
    case 2 : boardwrite();break;
    case 3 : boarddelete();break;
    case 4 : boardupdate();break;
    case 5 : System.out.println("뒤로가기");break;
    default : System.out.println("잘못선택하셨습니다...");
    }
    
	}
	
	//DB얻기
	Connection conn = OracleDB.getConnection();
	
	//1. 공지사항 목록 >> ******실행 OK
	public void boardlist() {
	// 공지사항테이블 목록 보여주기 ******실행 OK
		// 서브쿼리 + 로우넘 이용허시면 됩니다.
	String sql = "SELECT PRO_NO 글번호, PRO_TITLE 글제목, PRO_DATE 작성일 "
			+ "FROM (SELECT ROWNUM RN, P.* FROM PROFBOARD P) WHERE ROWNUM <= ?";
	try {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, 10);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			System.out.print(rs.getInt("글번호") +". " + rs.getString("글제목") + " (" + rs.getDate("작성일")+")" +"\n");
//			System.out.print(rs.getString("글제목"));
//			System.out.println(rs.getDate("작성일"));
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	// 공지사항 선택해서 읽기 ******실행 OK
	System.out.println(">>원하는 글의 번호 선택하세요");
	int pno = Util.scInt();
	
	String sql2 ="SELECT PRO_NO 글번호, PRO_TITLE 글제목, PRO_CONTENT 내용 FROM (SELECT ROWNUM RN, P.* FROM PROFBOARD P)\r\n"
			+ "WHERE PRO_NO = ?";
	try {
		PreparedStatement pstmt2 = conn.prepareStatement(sql2);
		pstmt2.setInt(1, pno);
		
		ResultSet rs = pstmt2.executeQuery();
		
		while(rs.next()) {
			System.out.print(rs.getInt("글번호"));
			System.out.print(rs.getString("글제목"));
			System.out.print(rs.getString("내용"));
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	

	}
	
	//2. 공지사항 작성*********실행OK
    
    public void boardwrite() {
    //글작성 얻기
    System.out.println("====공지사항 작성 ====");
    System.out.println("글제목 : ");
    String title = Util.sc.nextLine();
    System.out.println("내  용 : ");
    String content = Util.sc.nextLine();
    System.out.println("교수번호 : ");
    int pno = Util.scInt();
    System.out.println("학과번호 : ");
    int dno = Util.scInt();
        
    //DB얻어놓음    
    //DB에 작성하기
        String sql = "INSERT INTO PROFBOARD(PRO_NO , PRO_TITLE, PRO_CONTENT, P_NO, D_NO) "
        		+ "VALUES(PROFBOARD_NO.NEXTVAL,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setInt(3, pno);
            pstmt.setInt(4, dno);
            
            int result = pstmt.executeUpdate();
            
            if(result == 1) {
                System.out.println("공지작성 완료 !!!");
            }else System.out.println("공지작성 실패 ...");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }
	
	
	//3. 공지사항 삭제
    public void boarddelete() {
    //DB얻어놓음    
        System.out.println("====공지사항 삭제====");
    //글목록 보여주기
        boardlist();
    //글 선택(PRO_NO로 선택)
        System.out.println("삭제하실 글번호를 입력하세요.");
        int input = Util.scInt();
        
    //삭제쿼리날리기
        String sql = "DELETE FROM PROFBOARD WHERE PRO_NO = ?";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, input);
            
            int result = pstmt.executeUpdate();
            
            if(result == 1) {
                System.out.println("공지삭제 완료 !!!");
            }else System.out.println("공지삭제 실패 ...");
        } catch (SQLException e) {
        	System.out.println("공지삭제 실패 ...");
            e.printStackTrace();
        }
        
    }
    
	//4. 공지사항 수정
    public void boardupdate() {
    //DB얻어놓음
        System.out.println("====공지사항 수정 ====");
    //글목록 보여주기
        boardlist();
    //글 선택(PRO_NO로 선택)
        System.out.println("수정하려는 글번호를 입력하세요.");
        int input = Util.scInt();
    //수정내용 얻기
        System.out.println("수정 내용을 입력하세요.");
        String upcon = Util.sc.nextLine();
        
    //수정내용 쿼리 날리기
        String sql = "UPDATE PROFBOARD SET PRO_CONTENT = ? WHERE PRO_NO = ?";
        PreparedStatement ptst;
        try {
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, upcon);
            ptst.setInt(2, input);
            
            int rs = ptst.executeUpdate();
            
            if(rs ==1) {
                System.out.println("공지사항 수정완료 !!!");
            }else System.out.println("공지사항 수정실패 ...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }
	//5. 뒤로가기
	
	
}
