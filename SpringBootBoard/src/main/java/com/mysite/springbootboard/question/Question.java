package com.mysite.springbootboard.question;

import com.mysite.springbootboard.answer.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 200)
    private String subject;
    @Column(columnDefinition = "TEXT")  // columnDefinition: 컬럼의 속성을 정의할 때 사용, "TEXT": 글자수를 제한할 수 없을 때 사용
    private String content;
    private LocalDateTime createDate;
    // mappedBy = “question” : Answer엔티티에 있는 question을 의미
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) // CascadeType.REMOVE: 질문을 삭제하면 그에 달린 답변들도 모두 함께 삭제
    private List<Answer> answerList;
}
