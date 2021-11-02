package com.example.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.BunchVO;
import com.example.domain.Criteria;
import com.example.domain.PageDTO;
import com.example.domain.QuizVO;
import com.example.service.QuizService;

@Controller
@RequestMapping("/quiz/*")
public class QuizController {

	private QuizService quizService;

	public QuizController(QuizService quizService) {
		super();
		this.quizService = quizService;
	}

	@GetMapping("/list")
	public String list(Criteria cri, Model model) {

		System.out.println("list() 호출됨 ... ");

		List<BunchVO> bunchList = quizService.getBunchesByCri(cri);

		int totalCount = bunchList.size();

		System.out.println(bunchList);

		PageDTO pageDTO = new PageDTO(cri, totalCount);

		model.addAttribute("bunchList", bunchList);
		model.addAttribute("pageMaker", pageDTO);

		return "quiz/quizList";
	}

	@GetMapping("/write")
	public String writeForm() {

		System.out.println("writeForm() 호출됨 ... ");

		return "quiz/quizWrite";
	}

	@PostMapping("/write")
	public String write(BunchVO bunchVO, String[] questions, String[] numOnes, String[] numTwos, String[] numThrees,
			String[] numFours, String[] answers, HttpSession session, RedirectAttributes rttr) {

		System.out.println("write() 호출됨... ");

		int bunchNum = quizService.getNextBunchNum();
		bunchVO.setNum(bunchNum);

		List<QuizVO> quizList = new ArrayList<QuizVO>();

		for (int i = 0; i < questions.length; ++i) {
			QuizVO quizVO = new QuizVO();
			quizVO.setBunchNum(bunchNum);
			quizVO.setQuestionNum(i + 1);
			quizVO.setQuestion(questions[i]);
			quizVO.setNumOne(numOnes[i]);
			quizVO.setNumTwo(numTwos[i]);
			quizVO.setNumThree(numThrees[i]);
			quizVO.setNumFour(numFours[i]);
			quizVO.setAnswer(answers[i]);

			quizList.add(quizVO);
		}

		String id = (String) session.getAttribute("id");
		bunchVO.setMemberId(id);
		bunchVO.setRegDate(new Date());
		bunchVO.setQuizList(quizList);

		quizService.addBunchAndQuizList(bunchVO);
		
		rttr.addAttribute("bunchNum", bunchVO.getNum());
		
		return "redirect:/quiz/content";

	} // write

	@GetMapping("content")
	public String content(int bunchNum, Model model) {

		BunchVO bunchVO = quizService.getBunchAndQuizList(bunchNum);

		System.out.println("bunchVO : " + bunchVO);
		model.addAttribute("bunch", bunchVO);
		model.addAttribute("quizList", bunchVO.getQuizList());
		
		return "quiz/quizContent";

	}
	
	@PostMapping("submit")
	public String submit(HttpServletRequest request) {
		
		List<String> clientReplyList = new ArrayList<String>();
		Enumeration<String> names = request.getParameterNames();
		
		while(names.hasMoreElements()) {	
			String name = names.nextElement();
			String value = request.getParameter(name);
			
			if(name.startsWith("reply")) {
				clientReplyList.add(value);
			}	
			
		} //while
		
		System.out.println(clientReplyList);
		
		
		return "redirect:/quiz/result";
	} // submit
	
	@GetMapping("result")
	public String result() {
		
		return "quiz/quizResult";
	}

}
