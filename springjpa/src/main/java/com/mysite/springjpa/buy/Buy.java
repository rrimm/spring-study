package com.mysite.springjpa.buy;

import com.mysite.springjpa.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Buy {
    @Id
    @Column(name = "num")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 id값 설정
    private int id;
    @ManyToOne(fetch = FetchType.EAGER) // 다대일
    @JoinColumn(name = "userid")    // 외래키. 실제 테이블 생성시에 userid란 이름으로 컬럼이 만들어짐
    private User user;
    private String prodname;
    private String groupname;
    private int price;
    private int amount;
}
