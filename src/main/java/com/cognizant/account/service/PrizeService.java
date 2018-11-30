package com.cognizant.account.service;

import com.cognizant.account.domain.Prize;
import com.cognizant.account.repository.IPrizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrizeService implements IPrizeService {

    @Autowired
    IPrizeRepository prizeRepository;

    @Override
    public Prize createPrize(Prize prize) {
        return prizeRepository.save(prize);
    }
}
