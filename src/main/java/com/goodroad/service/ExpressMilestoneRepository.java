package com.goodroad.service;

import com.goodroad.model.ExpressMilestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by rigel on 2/20/17.
 */
public interface ExpressMilestoneRepository extends PagingAndSortingRepository<ExpressMilestone, Long>, JpaRepository<ExpressMilestone, Long>,JpaSpecificationExecutor<ExpressMilestone> {

    List<ExpressMilestone> findByRoadType(ExpressMilestone.RoadType roadType);

}
