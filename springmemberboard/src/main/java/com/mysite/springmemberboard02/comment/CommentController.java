package com.mysite.springmemberboard02.comment;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
public class CommentController {
	
	CommentService mCommentService;
    
    public CommentController(CommentService mCommentService) {
    	this.mCommentService = mCommentService;
    }
    
    // produces : Response의 Content-Type을 제어하기 위해 사용
    @RequestMapping(value="/comment_list.bo", produces="application/json;charset=UTF-8") //댓글 리스트
    private List<CommentVO> mCommentServiceList(@RequestParam int bno) throws Exception{
    	List<CommentVO> comment_list = mCommentService.commentListService(bno);
        
        return comment_list;
    }
    
    @RequestMapping(value="/comment_insert.bo", produces="application/json;charset=UTF-8") //댓글 작성 
    private int mCommentServiceInsert(CommentVO comment, HttpSession session) throws Exception{
    	System.out.println("AAA");
        comment.setWriter((String)session.getAttribute("id"));  
        
        return mCommentService.commentInsertService(comment);
    }
    
//    @RequestMapping(value="/comment_update.bo", produces="application/json;charset=UTF-8") //댓글 수정  
//    private int mCommentServiceUpdateProc(@RequestParam int cno, @RequestParam String content) throws Exception{
//        
//        CommentVO comment = new CommentVO();
//        comment.setCno(cno);
//        comment.setContent(content);
//        
//        return mCommentService.commentUpdateService(comment);
//    }
    @RequestMapping(value="/comment_update.bo", produces="application/json;charset=UTF-8") //댓글 수정  
    private int mCommentServiceUpdateProc(CommentVO comment) throws Exception{
        
        return mCommentService.commentUpdateService(comment);
    }
    
    @RequestMapping(value="/comment_delete.bo", produces="application/json;charset=UTF-8") //댓글 삭제  
    private int mCommentServiceDelete(@RequestParam(value="cno") int cno) throws Exception{
        
        return mCommentService.commentDeleteService(cno);
    }

}
