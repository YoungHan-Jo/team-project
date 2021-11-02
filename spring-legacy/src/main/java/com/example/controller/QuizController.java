package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Criteria;
import com.example.domain.BunchVO;
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
	public void write(BunchVO bunchVO, String[] questions, String[] numOnes, String[] numTwos, String[] numThrees,
			String[] numFours, String[] answers, HttpSession session) {
		System.out.println("bunchVO : " + bunchVO);
		System.out.println(Arrays.toString(questions));
		System.out.println(Arrays.toString(numOnes));
		System.out.println(Arrays.toString(numTwos));
		System.out.println(Arrays.toString(numThrees));
		System.out.println(Arrays.toString(numFours));
		System.out.println(Arrays.toString(answers));
		System.out.println(questions.length);

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

		System.out.println(bunchVO);
		System.out.println(bunchVO.getQuizList());
		
		quizService.addBunchAndQuizList(bunchVO);
		

	}

}
