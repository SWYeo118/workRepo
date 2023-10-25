package com.darsgateway.repository;

import com.darsgateway.entity.TntDarsCallsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TntDarsCallsRepository extends JpaRepository<TntDarsCallsEntity, Long> {

}
