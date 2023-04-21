package com.mysite.springbootboard.answer;

import com.mysite.springbootboard.answer.Answer;
import com.mysite.springbootboard.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

}
