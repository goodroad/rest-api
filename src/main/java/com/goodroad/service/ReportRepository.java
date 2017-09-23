package com.goodroad.service;

import com.goodroad.model.Report;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Created by dubu on 2017-01-23.
 */
@CacheConfig(cacheNames = "report")
public interface ReportRepository extends PagingAndSortingRepository<Report, Long>, JpaRepository<Report, Long>,JpaSpecificationExecutor<Report> {

    @RestResource(path = "group2")
    Page findByGroup2StartingWith(@Param("p") String name, Pageable p);

    @RestResource(path = "group1")
    Page findByGroup1StartingWith(@Param("p") String name, Pageable p);

    @RestResource(path = "group1year")
    List<Report> findByGroup1StartingWithAndWriteDateStartingWith(@Param("p") String name, @Param("year") String year );

    @RestResource(path = "writeDate")
    Page findByWriteDateStartingWith(@Param("p") String name, Pageable p);

    @Cacheable(value = "year")
    @RestResource(path = "year")
    List<Report> findByWriteDateStartingWith(@Param("p") String name);

    @RestResource(path = "address")
    Page findByAddressContaining(@Param("p") String name, Pageable p);


    @RestResource(path = "addressyear")
    Page findByAddressContainingAndWriteDateStartingWith(@Param("p") String name, @Param("year") String year, Pageable p);




//    @RestResource(path = "auth")
//    Page findByAddressStartingWith(@Param("p") String name, Pageable p);

    @RestResource(path = "writerType")
    Page findByWriterType(@Param("p")  Report.WriterType name, Pageable p);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @CacheEvict(value = "year",allEntries = true)
    void delete(Long aLong);

    @Override
    @CacheEvict(value = "year",allEntries = true)
    Report save(Report s);


    @Query(value = "select group1, count(group1)\n" +
            "from report\n" +
            "where group1 in ('포유류','조류','파충류','양서류')\n" +
            "group by group1\n" +
            "union \n" +
            "select '기타' group1, count(group1)\n" +
            "from report\n" +
            "where group1 not in ('포유류','조류','파충류','양서류')\n",nativeQuery = true)
    List findByGroup2Rank();


    @Query(value = "select group1, count(group1)\n" +
            "from report\n" +
            "where write_date like %?1% \n" +
            "and group1 in ('포유류','조류','파충류','양서류')\n" +
            "group by group1\n" +
            "union \n" +
            "select '기타' group1, count(group1)\n" +
            "from report\n" +
            "where write_date like %?1% \n" +
            "and group1 not in ('포유류','조류','파충류','양서류')\n",nativeQuery = true)
    List findByYearGroup2Rank(String year);

    List<Report> findByAddressIsNull();

}