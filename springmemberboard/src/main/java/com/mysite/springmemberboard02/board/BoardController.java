package com.mysite.springmemberboard02.board;

import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	
private BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Value("${spring.servlet.multipart.location}")
    String uploadDir;
	
	@RequestMapping("/boardlist.bo") 
	public String getBoardlist(Model model, @RequestParam(value="page", required=false, defaultValue="1") int page) { 
		int limit=10; // 페이지당 글의 갯수
		
		int listcount=boardService.getListCount();
		List<BoardVO> boardlist = boardService.getBoardList(page, limit);
		
		//총 페이지 수
   		int maxpage=(int)((double)listcount/limit+0.95); //0.95를 더해서 올림 처리
   		//현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
   		int startpage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   		//현재 페이지에 보여줄 마지막 페이지 수(10, 20, 30 등...)
   		int endpage = maxpage;
   		
   		if (endpage>startpage+10-1) 
   			endpage=startpage+10-1;
   		
		model.addAttribute("page", page);
		model.addAttribute("listcount", listcount);
		model.addAttribute("boardlist", boardlist);
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("limit", limit);
		
		return "board/qna_board_list";
	}
	
	@RequestMapping("/boardwriteform.bo") 
	public String boardInsertForm() {
		
		return "board/qna_board_write";
	}
	
	@RequestMapping("/boardwrite.bo") 
	public String boardInsert(BoardVO vo) throws Exception {
		MultipartFile mf = vo.getUploadfile();
		if (!mf.isEmpty()) {  // 첨부 파일이 존재할 때
			String uploadPath = uploadDir;
			
			String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") + "_" + mf.getOriginalFilename();       
	        if(mf.getSize() != 0) {                
	        	mf.transferTo(new File(uploadPath + "/" + storedFileName)); // 예외처리 기능 필요함.
	        }
			vo.setOrg_file(mf.getOriginalFilename());
			vo.setUp_file(storedFileName);	
		}
		boolean res = boardService.boardInsert(vo);
	
		return "redirect:/boardlist.bo";
	} 
	
	@RequestMapping("/boarddetail.bo") 
	public String getDetail(@RequestParam(value="num", required=true) int num, Model model) {
		BoardVO vo = boardService.getDetail(num);
		
		model.addAttribute("vo", vo);
		
		return "board/qna_board_view";
	}
	
	@RequestMapping("/filedownload.bo")
    public ResponseEntity<UrlResource> fileDownload(@RequestParam("of") String originfilename, 
    		@RequestParam("of2") String storedfilename) throws Exception{
		UrlResource resource = new UrlResource("file:" + uploadDir + "/" + storedfilename);
        String encodedFileName = UriUtils.encode(originfilename, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition).body(resource);
	}
	
	@RequestMapping("/boardreplyform.bo") 
	public String boardReplyForm(@RequestParam(value="num", required=true) int num, Model model) {
		BoardVO vo = boardService.boardReplyForm(num);
		
		model.addAttribute("vo", vo);
		
		return "board/qna_board_reply";
	}
	
	@RequestMapping("/boardreply.bo") 
	public String boardReply(BoardVO vo) {
		int res = boardService.boardReply(vo);
		
		return "redirect:/boardlist.bo";
	}
	
	
	@RequestMapping("/boardmodifyform.bo") 
	public String getModifyForm(@RequestParam(value="num", required=true) int num, Model model) {
		BoardVO vo = boardService.boardModifyForm(num);
		
		model.addAttribute("vo", vo);
		
		return "board/qna_board_modify";
	}
	
	@RequestMapping("/boardmodify.bo") 
	public String boardModify(BoardVO vo, HttpServletResponse response) throws Exception {
		boolean res = boardService.boardModify(vo);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		if (res == true)
		{
			writer.write("<script>alert('수정 성공!!');"
					+ "location.href='./boarddetail.bo?num=" + vo.getNum() + "';</script>");
		}
		else
		{
			writer.write("<script>alert('수정 실패(권한없음)!!');"
					+ "location.href='./boardlist.bo';</script>");
		}
		return null;

//		if (res == true)
//		{
//			return "redirect:/boarddetail.bo?num=" + vo.getNum();
//		}
//		else
//		{
//			return "redirect:/boardlist.bo";
//		}
	}
	
	@RequestMapping("/boarddelete.bo") 
	public String boardDelete(@RequestParam(value="num", required=true) int num, HttpSession session, HttpServletResponse response) throws Exception {
		String id = (String)session.getAttribute("id");
		boolean res = boardService.boardDelete(num, id);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		if (res == true)
		{
			writer.write("<script>alert('삭제 성공!!');"
					+ "location.href='./boardlist.bo';</script>");
		}
		else
		{
			writer.write("<script>alert('삭제 실패(권한없음)!!');"
					+ "location.href='./boardlist.bo';</script>");
		}
		return null;

//		return "redirect:/boardlist.bo";
	}

}
