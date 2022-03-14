package org.zerock.mapper;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testGetList() {
		log.info("----------------");
		boardMapper.getList();
		
	}
	
	@Test
	public void testInsert() {  // 그냥 글이 생성되었다 할떄 이렇게!!
		 BoardVO vo = new BoardVO();
		 vo.setTitle("Test 테스트");
		 vo.setContent("Content 테스트");
		 vo.setWriter("tester");
		 
		 boardMapper.insert(vo);
		 log.info("-------------------");
		 log.info("after insert " + vo.getBno());
	}
	@Test
	public void testInsertSelectKey(){  // 몇번 글이 생성되었습니다 안내할떈 이렇게 !!
		 BoardVO vo = new BoardVO();
		 vo.setTitle("AAATest 테스트");
		 vo.setContent("AAAContent 테스트");
		 vo.setWriter("AAAtester");
		 
		 boardMapper.insertSelectKey(vo); 
		 log.info("-------------------");
		 log.info("after insert selectkey " + vo.getBno());
	}
	@Test
	public void testRead() {
		BoardVO vo = boardMapper.read(4L); //25번쨰 long 타입 
		log.info(vo);
	}
	@Test 
	public void testDelete(){
		int count = boardMapper.delete(4L);
		
		log.info("count : " + count);
	}

	@Test
	public void testUpdate() {
		BoardVO vo = new BoardVO();
		
		vo.setBno(1L);
		vo.setTitle("Update Title");
		vo.setContent("Update Content");
		vo.setWriter("user00");
		
		log.info("count :" + boardMapper.update(vo));
	}
	@Test
	public void testPaging() {
		//1 10
		Criteria cri = new Criteria();
		
		List<BoardVO> list  =boardMapper.getListWithPaging(cri);
		
		list.forEach(b -> log.info(b));
		
	}
}
