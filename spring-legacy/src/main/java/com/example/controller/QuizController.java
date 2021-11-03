package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.example.domain.SolveHistoryVO;
import com.example.service.QuizService;
import com.example.util.JsonUtils;
import com.google.gson.Gson;

@Controller
@RequestMapping("/quiz/*")
public class QuizController {

	private Gson gson = new Gson();
	
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
	public String submit(int bunchNum, HttpServletRequest request, HttpSession session) {
		
		// 사용자가 제출한 정답 리스트
		List<String> clientAnswerList = new ArrayList<String>();
		
		Enumeration<String> names = request.getParameterNames();
		
		while(names.hasMoreElements()) {	
			String name = names.nextElement();
			String value = request.getParameter(name);
			
			if(name.startsWith("reply")) {
				clientAnswerList.add(value);
			}	
			
		} //while
		
		System.out.println(clientAnswerList);
		System.out.println(bunchNum);
		
		// 실제 정답 리스트
		List<String> realAnswerList = quizService.getAnswerListByBunchNum(bunchNum);
		
		int questionCount = realAnswerList.size(); // 총 문제 수
		int correct = 0; // 정답 맞춘 개수
		
		// 맞힌 문제 리스트
		List<Integer> correctList = new ArrayList<Integer>();
		
		// 틀린 문제 리스트
		List<Integer> incorrectList = new ArrayList<Integer>(); 
		
		for(int i = 0; i < questionCount; ++i) {
			if(clientAnswerList.get(i).equals(realAnswerList.get(i))) {
				correctList.add(i+1);
				correct++;
			}else {
				incorrectList.add(i+1);
			}
		}

		System.out.println("정답 개수 : " + correct + "/" + questionCount );
		System.out.println("정답 : " + correctList);
		System.out.println("오답 : " + incorrectList);
		
		// 점수
		double point = Math.round((double)correct / questionCount * 1000) / 10.0;
		System.out.println(point);
		
		// List -> strJson 직렬화
		String ClientAnswerList = gson.toJson(clientAnswerList);
		String jsonCorrectList = gson.toJson(correctList);
		String jsonIncorrectList = gson.toJson(incorrectList);
		
		SolveHistoryVO solveHistoryVO = new SolveHistoryVO();
		
		
		
		return "redirect:/quiz/result";
	} // submit
	
	@GetMapping("result")
	public String result() {
		
		return "quiz/quizResult";
	}

}
