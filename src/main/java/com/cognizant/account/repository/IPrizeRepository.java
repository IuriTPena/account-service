package com.cognizant.account.repository;

import com.cognizant.account.domain.Prize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrizeRepository extends JpaRepository<Prize, Long> {
}
