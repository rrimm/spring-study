package com.mysite.springjpa.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id // 기본키 설정(필수)
    @Column(name = "userid", length = 8)    // 컬럼을 맵핑할 때 사용. name, nullable=false, length 설정에 사용
    private String id;
    @Column(length = 10)
    private String name;
    private int birth;
    @Column(length = 2)
    private String addr;
    @Column(length = 13)
    private String mobile;
    private int height;
}
