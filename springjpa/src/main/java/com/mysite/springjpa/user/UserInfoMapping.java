package com.mysite.springjpa.user;

// User 테이블에서 원하는 컬럼만 get 메서드로 정의
public interface UserInfoMapping {
    String getId();

    String getName();

    int getHeight();
}
