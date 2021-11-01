package com.example.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.domain.MemberVO;


@RunWith(SpringJUnit4ClassRunner.class)  // @Component 계열 애노테이션
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberMapperTests {

	@Autowired
	private MemberMapper memberMapper;
	
	@Test
	public void testInsert() {
		
		assertNotNull(memberMapper);
		
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId("sss");
		
		String hashedPw= BCrypt.hashpw("1234", BCrypt.gensalt());
		memberVO.setPasswd(hashedPw);

		memberVO.setName("호빵맨");
		memberVO.setRegDate(new Date());
		
		memberMapper.insert(memberVO);
	}
	
	@Test
	public void testGetMemberAndProfilepic() {
		MemberVO memberVO = memberMapper.getMemberAndProfilepic("aaa");
		
		assertNotNull(memberVO);
		
		System.out.println(memberVO);
	}
	
}






