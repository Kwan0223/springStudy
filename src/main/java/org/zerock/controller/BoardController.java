package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
@Log4j
public class BoardController {
	
	private final BoardService service;
	
	@GetMapping("/list")
	 public void list(Model model) {
		
		log.info("list...............");
		
		model.addAttribute("list", service.getList());
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		
		log.info("board :" + board);
		
		Long bno = service.register(board);
		
		log.info("BNO : " + bno);
		rttr.addFlashAttribute("result", bno); //잠깐 저장되어 전송!! 세션쪽에서 한번사용되고 지움
		//rttr.addAttribute(rttr); //브라우저 링크에 붙어서  "redirect:/board/list 뒤에 붙음
		
		
		return "redirect:/board/list";
	}
	@PostMapping("/modify")  //수정 후 다시 이동해야하니까 Redirect를 사용 !
	public String modify(BoardVO board , RedirectAttributes rttr) {
		
		int count = service.modify(board);
		
		if(count ==1) {
			rttr.addFlashAttribute("result", "success");
		}
					
		return "redirect:/board/list";
	}
	@PostMapping("/remove")  //삭제 후 다시 이동해야하니까 Redirect를 사용 !
	public String remove(@RequestParam("bno") Long bno , RedirectAttributes rttr) {
		
		int count = service.remove(bno);
		
		if(count ==1) {
			rttr.addFlashAttribute("result", "success");
		}
					
		return "redirect:/board/list";
	}
}
