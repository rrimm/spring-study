package com.mysite.springjpa.buy;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class BuyController {
    private BuyServiceImpl buyServiceImpl;

    public BuyController(BuyServiceImpl buyServiceImpl) {
        super();
        this.buyServiceImpl = buyServiceImpl;
    }

    @RequestMapping("/insertBuy")
    public void insertBuy(@RequestParam(value = "userid") String userid, Buy buy) {
        buyServiceImpl.insertBuy(userid, buy);
    }

    @RequestMapping("/listBuy")    // 객체 전체 반환
    public List<Buy> listBuy() {
        return buyServiceImpl.listBuy();
    }

    @RequestMapping("/getBuy")
    public Buy getBuy(int num) {
        return buyServiceImpl.getBuy(num);
    }

    @RequestMapping("/updateBuy")
    public void updateBuy(int num, String prodname, String groupname, int price, int amount) {
        buyServiceImpl.updateBuy(num, prodname, groupname, price, amount);
    }

    @RequestMapping("/deleteBuy")
    public void deleteBuy(int num) {
        buyServiceImpl.deleteBuy(num);
    }


}
