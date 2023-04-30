package com.mysite.springjpa.buy;

import com.mysite.springjpa.user.User;
import com.mysite.springjpa.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyServiceImpl implements BuyService {
    private BuyRepository buyRepository;
    private UserRepository userRepository;

    public BuyServiceImpl(BuyRepository buyRepository, UserRepository userRepository) {
        super();
        this.buyRepository = buyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Buy insertBuy(String userid, Buy buy) {
        User user = userRepository.findById(userid).orElse(null);
        buy.setUser(user);
        return buyRepository.save(buy);
    }

    @Override
    public List<Buy> listBuy() {
        return (List<Buy>) buyRepository.findAll();
    }

    @Override
    public Buy getBuy(int num) {
        return buyRepository.findById(num).orElse(null);
    }


    public void updateBuy(int num, String prodname, String groupname, int price, int amount) {
        buyRepository.updateBuy(num, prodname, groupname, price, amount);
    }

    @Override
    public void deleteBuy(int num) {
        buyRepository.deleteById(num);
    }

}
