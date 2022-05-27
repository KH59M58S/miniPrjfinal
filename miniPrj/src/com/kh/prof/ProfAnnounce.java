package com.kh.prof;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.kh.admin.Main;
import com.kh.db.OracleDB;
import com.kh.stu.stuMenu;
import com.kh.util.Util;

//질문1: pad했는데 왜 크기가 다른지(새로생성한것만그럼..)>크기떄문그런듯, 질문2: 글번호 삭제 후 다시 생성했을 때 그 뒷번호로됨.>시퀀스 원래그럼..
//표시되는 크기 제한설정>세모, 작성시 교수번호 자동입력>ok, 뒤로가기 구현>ok

public class ProfAnnounce {

	public static int input;
	public static int pno;
	
	//교수 학사공지 목록
	public void board() {
		while(true) {
			
			System.out.println("==== 학사 공지 ====");
			System.out.println("1. 공지사항 목록");
			System.out.println("2. 공지사항 작성");
			System.out.println("3. 공지사항 삭제");
			System.out.println("4. 공지사항 수정");
			System.out.println("5. 뒤로가기");
			
			input = Util.scInt();
			
			switch (input) {
			case 1 : boardlist();break;
			case 2 : boardwrite();break;
			case 3 : boarddelete();break;
			case 4 : boardupdate();break;
			case 5 : new ProfScreen().profScreenStart();break;
			default : System.out.println("잘못선택하셨습니다...");return;
			}
		}
    
	}
	
	
	//학생 학사공지 목록
	public void boardStu() {
		while(true) {
			System.out.println("==== 학사 공지 ====");
			System.out.println("1. 공지사항 목록");
			System.out.println("2. 뒤로가기");
			
			int abc = Util.scInt();
			switch(abc) {
			case 1 : boardlist();break; 
			case 2 : new stuMenu().showMenu();break;
			default : System.out.println("잘못 선택하셨습니다...");return;
			}
			
		}
		
		
		
	}
	
	
	//1. 공지사항 목록 >> ******실행 OK
	public void boardlist() {
	//1-1 공지사항테이블 목록 보여주기 ******실행 OK
		//DB얻기
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		String sql = "SELECT LPAD(ROWNUM,5,' ') 순서, SUBSTR(RPAD(PRO_TITLE,40,' '),1,40) 글제목, SUBSTR(RPAD(PRO_DATE,8,' '),1) 작성일 "
				+ "FROM (SELECT ROWNUM RN, P.* FROM PROFBOARD P ORDER BY PRO_DATE) WHERE ROWNUM <= ?  ";		
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, 10);
		
		rs = pstmt.executeQuery();
		
		System.out.println("\n 순서 " + "|" +"  작성일  "  + "|" + "       제  목       ");
		System.out.println("===================================");
		while(rs.next()) {
			int proNo = rs.getInt("순서");//글번호
			String proDate = rs.getString("작성일");
			String proTitle = rs.getString("글제목");
			
			System.out.print("  " + proNo + "  ");
			System.out.print("|");
			System.out.print(proDate);
			System.out.print("|");
			System.out.print(proTitle);
			System.out.println("\n-----------------------------------");
		}
		System.out.println("===================================");
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	//1-2 공지사항 선택해서 읽기 ******실행 OK
	System.out.println(">>원하는 글의 번호 선택하세요");
	pno = Util.scInt();
	ResultSet rs2 = null;
	String sql2 = "SELECT PRO_NO 글번호, RPAD(PRO_CONTENT,40,' ') 글내용, RPAD(PRO_DATE,8,',') 작성일 "
			+ "FROM (SELECT ROWNUM RN, P.* FROM PROFBOARD P) WHERE RN = ?";	
	try {
		PreparedStatement pstmt2 = conn.prepareStatement(sql2);
		pstmt2.setInt(1, pno);
		
		rs2 = pstmt2.executeQuery();
		
		while(rs2.next()) {
			System.out.println("\n 글번호 " + "|" +"  작성일  " + "|" + "      내 용         " );
			System.out.println("-----------------------------------");
			System.out.print("   "+rs2.getInt("글번호")+"  ");
			System.out.print("|");
			System.out.print(rs2.getString("작성일"));
			System.out.print("|");
			System.out.print(rs2.getString("글내용"));
			System.out.println("\n===================================\n");
			
		}
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	}//finally {
//		OracleDB.close(conn);OracleDB.close(pstmt);OracleDB.close(rs);OracleDB.close(rs2);
//	}
	

	}
	
	//2. 공지사항 작성*********실행OK
    
    public void boardwrite() {
    
    //접근제한
    	if(Util.info.equals("Student")) {
    		System.out.println("!!! 교수 유저만 접근가능합니다 !!!");
    		return;
    	}
	//DB얻기
	Connection conn = OracleDB.getConnection();	
    //글작성 얻기
    System.out.println("====공지사항 작성 ====");
    System.out.println("글제목 : ");
    String title = Util.sc.nextLine();
    System.out.println("내  용 : ");
    String content = Util.sc.nextLine();
    
        
    //DB얻어놓음    
    //DB에 작성하기
        String sql = "INSERT INTO PROFBOARD(PRO_NO , PRO_TITLE, PRO_CONTENT, P_NO, D_NO) "
        		+ "VALUES(PROFBOARD_NO.NEXTVAL,?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setInt(3, Util.infono);
            pstmt.setInt(4, Util.profNotoDeptNo(Util.infono));
            
            int result = pstmt.executeUpdate();
            
            if(result == 1) {
                System.out.println("공지작성 완료 !!!");
            }else System.out.println("공지작성 실패 ...");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	OracleDB.close(conn);OracleDB.close(pstmt);
        }
        
        
    }
	
	
	//3. 공지사항 삭제*********실행OK
   
    public void boarddelete() {
    
    //접근제한
    	if(Util.info.equals("Student")) {
    		System.out.println("!!! 교수 유저만 접근가능합니다 !!!");
    		return;
    	}
    //DB얻어놓음 
	Connection conn = OracleDB.getConnection();	
	
        System.out.println("====공지사항 삭제====");
    //글목록 보여주기
        boardlist();
    //글 선택(PRO_NO로 선택)
        System.out.println("\n>>삭제하실 글번호를 입력하세요.");
        int input = Util.scInt();
        
    //삭제쿼리날리기
        String sql = "DELETE FROM PROFBOARD WHERE PRO_NO = ?";
        PreparedStatement pstmt = null;
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
        }finally {
        	OracleDB.close(conn);OracleDB.close(pstmt);
        }
        
    }
    
	//4. 공지사항 수정
    public void boardupdate() {
    
	//접근제한
    	if(Util.info.equals("Student")) {
    		System.out.println("!!! 교수 유저만 접근가능합니다 !!!");
    		return;
    	}
    //DB얻기
	Connection conn = OracleDB.getConnection();	
        System.out.println("====공지사항 수정 ====");
    //글목록 보여주기
        boardlist();
   
    //수정내용 얻기
        System.out.println("수정하려는 글번호 선택하세요");
        int pnoUp = Util.scInt();
        System.out.println("\n"+"수정 내용을 입력하세요.");
        System.out.print(">>>");
        String upcont = Util.sc.nextLine();
     
        
    //수정내용 쿼리 날리기
        String sql = "UPDATE PROFBOARD SET  PRO_CONTENT = ? WHERE PRO_NO = ?";
        PreparedStatement ptst = null;
        try {
        	
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, upcont);
            ptst.setInt(2, pnoUp);
            int rs = ptst.executeUpdate();
            if(rs == 1) {
                System.out.println("공지사항 수정완료 !!!");
            }
            else {
            	System.out.println("공지사항 수정실패 ...");
            }
         
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        	OracleDB.close(conn); OracleDB.close(ptst);
        }
        
    
        
        
    }
	//5. 뒤로가기
	
    public void back() {
    	return;
    }
	
    
   
    
}
