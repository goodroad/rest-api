package com.goodroad.service;

import com.goodroad.model.Ex;
import com.goodroad.model.ExpressMilestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by rigel on 2/20/17.
 */
public interface ExRepository extends PagingAndSortingRepository<Ex, Long>, JpaRepository<Ex, Long>,JpaSpecificationExecutor<Ex> {


    List<Ex> findByLineAndWay(String line, String way);

    List<Ex> findByLine(String line);
}
