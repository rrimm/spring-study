package com.mysite.springbootboard;

import com.mysite.springbootboard.answer.Answer;
import com.mysite.springbootboard.answer.AnswerRepository;
import com.mysite.springbootboard.question.Question;
import com.mysite.springbootboard.question.QuestionRepository;
import com.mysite.springbootboard.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SpringBootBoardApplicationTests {

    //junit: 테스트 코드를 작성하고 작성한 테스트 코드를 실행하기 위한 테스트 프레임워크
    @Autowired
    private QuestionService questionService;

    @Test
    void testJpa() {
        for (int i = 1; i <= 300; i++) {
            String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "내용무";
            this.questionService.create(subject, content);
        }

    }

}
