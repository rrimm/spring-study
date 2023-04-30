package com.mysite.springjpa.user;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// 자동으로 빈으로 등록됨

@Repository
public interface UserRepository extends JpaRepository<User, String> {
//    @Modifying  // insert, update, delete시 사용
//    @Transactional  // 트랜잭션 단위로 실행
//    @Query(value = "insert into user(userid, addr, birth, height, mobile, name) values(:id, :addr, :birth, :height, :mobile, :name)", nativeQuery = true)
//    void insertUser2(String id, String addr, int birth, int height, String mobile, String name);

    @Query(value = "select * from user where addr = :addr", nativeQuery = true)
    List<User> getUserAddr(String addr);

    List<User> findByAddr(String addr);

    List<User> findByAddrAndName(String addr, String name);

    @Modifying
    @Transactional
    @Query(value = "update user set addr = :addr, mobile = :mobile where userid = :id", nativeQuery = true)
    void updateUser(String id, String addr, String mobile);

    List<User> findAllByOrderByIdAsc();

    List<User> findAllByOrderByName();

    List<User> findAllByOrderByNameDesc();

    //UserInfoMapping: 추출하고자 하는 컬럼값을 읽는 인터페이스
    List<UserInfoMapping> findAllByAddr(String addr);   // 아이디, 이름, 키만 추출

}
