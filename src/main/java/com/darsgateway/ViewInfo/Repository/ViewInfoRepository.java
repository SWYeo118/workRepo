package com.darsgateway.ViewInfo.Repository;

import com.darsgateway.ViewInfo.Entity.ViewInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewInfoRepository extends JpaRepository<ViewInfoEntity, Long> {

}
