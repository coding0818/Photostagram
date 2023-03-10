package kr.co.photostagram;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.photostagram.controller.MemberController;
import kr.co.photostagram.vo.MemberVO;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@AutoConfigureMockMvc
@SpringBootTest
class PhotostagramApplicationTests {

	void contextLoads() {

		System.out.println("hello test!");
	}

	@Autowired
	private MockMvc mvc;

	@Autowired
	private MemberController controller;

	@Test
	public void testTerms() throws Exception {

		MemberVO vo = MemberVO.builder()
								.username("abcd")
								.name("홍길동")
								.password("1234")
								.email("abcd@gmail.com").build();

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(vo);

		System.out.println("json : " + json);

//		mvc.perform(post("/member/testTerms")).content()

		//Map<String, Integer> resultMap = controller.terms(vo, null);

		//log.info("resultMap : " + resultMap);
	}

}
