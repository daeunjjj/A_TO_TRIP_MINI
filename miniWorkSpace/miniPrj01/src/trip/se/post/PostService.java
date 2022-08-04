package trip.se.post;

import static trip.min.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;


public class PostService {

	// 게시글 작성
	public int write(PostVo vo) {
		
		if(vo.getTitle().length() < 1) {
			return -1;
		}
		
		if(vo.getContent().length() < 1) {
			return -2;
		}
		
		int result = 0;
		
		try {
			Connection conn = getConnection();
			result = new PostDao().write(vo, conn);
			
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

	// 게시판 조회
	public List<PostVo> showList() {
		
		Connection conn = null;
		List<PostVo> postVoList =null;
		
		try {
			conn = getConnection();
			postVoList = new PostDao().showList(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(conn);
		}
		
		return postVoList;
		
	}// showList
	
	// 게시글 상세조회
	public PostVo showPostDetail(String num) {
		
		Connection conn = null;
		PostVo vo = null;
		
		try { 
			conn = getConnection();
			vo = new PostDao().showPostDetail(conn, num);
			commit(conn);
			
		} catch (Exception e) {
			System.out.println("[ERROR!!!]");
			e.printStackTrace();
		}
		return vo;
		
	}// showPostDetail
	
	public int editPost(PostVo vo) {
		
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
			result = new PostDao().editPost(vo, conn);
			
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
	
	public int deletePost(String postNo) {
		
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = new PostDao().deletePost(postNo, conn);
			
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
	
	public int likePost(String num) {
		
		Connection conn = null;
		int result = 0;
		
		try {
			conn = getConnection();
			result = new PostDao().likePost(num, conn);
			
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
		
	}//likePost
	
	
	
	
	
	
	
	
	
}//class
