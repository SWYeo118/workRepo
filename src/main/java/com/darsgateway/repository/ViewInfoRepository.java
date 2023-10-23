package com.darsgateway.repository;

import com.darsgateway.entity.StTntCallsDailyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewInfoRepository extends JpaRepository<StTntCallsDailyEntity, Long> {

}
