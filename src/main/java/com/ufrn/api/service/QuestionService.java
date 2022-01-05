package com.ufrn.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrn.api.entities.Log;
import com.ufrn.api.entities.Question;
import com.ufrn.api.repository.QuestionRepository;
import com.ufrn.dtos.QuestionDTO;
import com.ufrn.dtos.ReturnDTO;

@Service
@Transactional
public class QuestionService {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	SessionFactory sessionFactory;

	public List<QuestionDTO> getQuestions() {
		List<Question> questions = questionRepository.findAll();
		
		List<QuestionDTO> questionsDTO = questions.stream().map(QuestionDTO::new).collect(Collectors.toList());
		
		return questionsDTO;
	}
	
	public List<QuestionDTO> getQuestionsByAnnotation(String annotation) {
		List<Question> questions = questionRepository.findQuestionByAnnotation("%@"+annotation+"%");
		
		List<QuestionDTO> questionsDTO = questions.stream().map(QuestionDTO::new).collect(Collectors.toList());
		
		return questionsDTO;
	}

	public List<ReturnDTO> getQuestionsByException(String exception) {
		List<Object[]> result = questionRepository.findQuestionByException(exception);
		
		List<ReturnDTO> returnDTO  = new ArrayList<ReturnDTO>();
		
		Session session = sessionFactory.openSession();
        session.beginTransaction();
		
		for (Object[] r : result) {
			List<String> idsList = Arrays.asList(r[1].toString().split(", "));
			idsList = idsList.stream().map( id -> (id != null) ? "https://stackoverflow.com/questions/"+id : null ).collect(Collectors.toList());
			returnDTO.add(new ReturnDTO(r[0].toString(), idsList.size(), idsList));
			session.save(new Log(Calendar.getInstance(), r[1].toString(), idsList.size(), r[0].toString(), exception));
		}
        
        session.getTransaction().commit();
        session.close();
		
		return returnDTO;
	}

	
	
}
