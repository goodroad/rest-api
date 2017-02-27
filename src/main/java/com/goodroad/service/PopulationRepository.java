package com.goodroad.service;

import com.goodroad.model.Population;
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
public interface PopulationRepository extends PagingAndSortingRepository<Population, Long>, JpaRepository<Population, Long>,JpaSpecificationExecutor<Population> {

//    @RestResource(path = "group2")
//    List<Population> findByGroup2StartingWith(@Param("p") String name);

//    @RestResource(path = "group2")
//    Page findByGroup2StartingWith(@Param("p") String name, Pageable p);


    @Override
    @Query(value = "select p.id, p.code ,p.name, ifnull(round(base.cnt),0) population   \n" +
        "from (\n" +
        "select AA.name ,count(AA.name) cnt\n" +
        "from (SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(a.address, ' ', -2),' ',1) name from report a ) AA\n" +
        "group by AA.name) base\n" +
        "right outer join population p\n" +
        "on base.name  = p.name" , nativeQuery = true)
    List<Population> findAll();


    @Query(value = "select p.id, p.code ,p.name, ifnull(round(base.cnt),0) population   \n" +
            "from (\n" +
            "select AA.name ,count(AA.name) cnt\n" +
            "from (SELECT SUBSTRING_INDEX(a.address, ' ', 2) name from report a where a.write_date like %?1% ) AA\n" +
            "group by AA.name \n" +
            "union all \n" +
            "    select '전국' name , count(*) cnt\n" +
            "    from report \n" +
            "    where write_date like %?1% \n" +
            ") base\n" +
            "right outer join population p\n" +
            "on SUBSTRING_INDEX(base.name, ' ', -1)  = p.name", nativeQuery = true)
    List<Population> findAllByWriteDate(@Param("p")String writeDate);

    @Query(value = "select p.id, p.code ,p.name, ifnull(round(base.cnt),0) population   \n" +
            "from (\n" +
            "select AA.name ,count(AA.name) cnt\n" +
            "from (SELECT SUBSTRING_INDEX(a.address, ' ', 2) name from report a where a.write_date like %?1%  and group1=?2) AA\n" +
            "group by AA.name \n" +
            "union all \n" +
            "    select '전국' name , count(*) cnt\n" +
            "    from report \n" +
            "    where write_date like %?1% \n" +
            "    and  group1=?2" +
            ") base\n" +
            "right outer join population p\n" +
            "on SUBSTRING_INDEX(base.name, ' ', -1)  = p.name", nativeQuery = true)
    List<Population> findAllByWriteDateAndGroup1(String writeDate, String group1);

    @Query(value = "select p.id, p.code ,p.name, ifnull(round(base.cnt),0) population   \n" +
            "from (\n" +
            "select AA.name ,count(AA.name) cnt\n" +
            "from (SELECT SUBSTRING_INDEX(a.address, ' ', 2) name from report a where a.write_date like %?1% and group2=?2 ) AA\n" +
            "group by AA.name \n" +
            "union all \n" +
            "    select '전국' name , count(*) cnt\n" +
            "    from report \n" +
            "    where write_date like %?1% \n" +
            "    and  group2=?2" +
            ") base\n" +
            "right outer join population p\n" +
            "on SUBSTRING_INDEX(base.name, ' ', -1)  = p.name", nativeQuery = true)
    List<Population> findAllByWriteDateAndGroup2(String writeDate, String group2);

}
