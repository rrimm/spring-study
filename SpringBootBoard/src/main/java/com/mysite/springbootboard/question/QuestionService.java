package com.mysite.springbootboard.question;

import com.mysite.springbootboard.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Page<Question> getList(int page) {
        // Sort.Order : 정렬을 위해 사용하는 객체
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));   // 첫번째 정렬 기준
        sorts.add(Sort.Order.asc("subject"));   // 두번째 정렬 기준
        // PageRequest.of(page, Sort.by(sorts)) : page는 조회할 페이지 번호, 10은 페이지당 글 개수, Sort.by(sorts)은 정렬 기준
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.questionRepository.findAll(pageable);
    }

    public Question getQuestion(Integer id) {
        // Optional: null이 올 수 있는 값을 감싸는 Wrapper 클래스. 참조하더라도 NPE(Null Point Exception)이 발생하지 않도록 한다.
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) { // isPresent(): question이 null이 아닌지 검사
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void create(String subject, String content) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
    }
}
