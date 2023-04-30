package com.mysite.springjpa.buy;

import com.mysite.springjpa.buy.Buy;

import java.util.List;

public interface BuyService {


    public Buy insertBuy(String userid, Buy buy);

    public List<Buy> listBuy();

    public Buy getBuy(int num);

    public void updateBuy(int num, String prodname, String groupname, int price, int amount);

    public void deleteBuy(int num);


}
