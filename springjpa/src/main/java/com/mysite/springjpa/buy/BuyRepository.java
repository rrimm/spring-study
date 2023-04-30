package com.mysite.springjpa.buy;

import com.mysite.springjpa.buy.Buy;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyRepository extends JpaRepository<Buy, Integer> {

//    Buy insertBuy(String userid, Buy buy);

//    List<Buy> findByPrice(int num);


    @Modifying
    @Transactional
    @Query(value = "update buy set prodname = :prodname, groupname = :groupname, price = :price, amount = :amount where num = :num", nativeQuery = true)
    void updateBuy(int num, String prodname, String groupname, int price, int amount);
}
