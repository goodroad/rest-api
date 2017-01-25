package com.goni.service;

import com.goni.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;
/**
 * Created by dubu on 2017-01-23.
 */
public interface ReportRepository extends Repository<Report, Long>, JpaRepository<Report, Long>,JpaSpecificationExecutor<Report> {
}
