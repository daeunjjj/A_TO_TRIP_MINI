package trip.se.qna;

import static trip.min.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;


public class QnaService {
	// 문의글 작성
	public int qnaWrite(QnaVo vo) {
		
		if(vo.getTitle().length() < 1) {
			return -1;
		}
		
		if(vo.getContent().length() < 1) {
			return -2;
		}
		
		int result = 0;
		
		try {
			Connection conn = getConnection();
			result = new QnaDao().qnaWrite(vo, conn);
			
			if(result == 1) {
				commit(conn);
			}else {
				rollBack(conn);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	// 문의글 조회
	public List<QnaVo> qnaShowList() {
		
		Connection conn = null;
		List<QnaVo> qnaVoList =null;
		
		try {
			conn = getConnection();
			qnaVoList = new QnaDao().qnaShowList(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return qnaVoList;
		
	}// showList
	
	// 문의글 상세조회
	public QnaVo showQnaDetail(String num) {
		
		Connection conn = null;
		QnaVo vo = null;
		
		try { 
			conn = getConnection();
			vo = new QnaDao().showQnaDetail(conn, num);
			commit(conn);
			
		} catch (Exception e) {
			System.out.println("[ERROR!!!]");
			e.printStackTrace();
		}
		return vo;
		
	}// showPostDetail
	
	// 문의글 수정
	public int editQna(QnaVo vo) {
		
		if(vo.getTitle().length() < 1) {
			// 제목이 비어있음
			return -1;
		}
		
		if(vo.getContent().length() < 1){
			// 내용이 비어있음
			return -2;
		}
		
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = new QnaDao().editQna(vo, conn);
			
			if(result == 1) {
				commit(conn);
			}else {
				rollBack(conn);
			}
			
		} catch (Exception e) {
			rollBack(conn);
		}finally {
			close(conn);
		}
		
		return result;
		
	}//editPost
	
	//문의글 삭제
	public int deleteQna(String postNo) {
		
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = new QnaDao().deleteQna(postNo, conn);
			
			if(result == 1) {
				commit(conn);
			}else {
				rollBack(conn);
			}
			
		} catch (Exception e) {
			rollBack(conn);
		}finally {
			close(conn);
		}
		
		return result;
		
	}// deletePost
}