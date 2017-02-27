package com.goodroad.service;

import com.goodroad.model.MonthCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by rigel on 2/20/17.
 */
public interface MonCountRepository extends CrudRepository<MonthCount,Long>{

    @Query(value = "" +
        "select id, substring(write_date,1,6) mon ,count(write_date) cnt \n" +
        "from report\n" +
        "where substring(write_date,1,2) = '20'\n" +
        "group by substring(write_date,1,6)", nativeQuery = true)
    public List<MonthCount> selectLineAll();


    @Query(value = "select id, concat(substring(write_date,1,6),group1) mon, count(group1) cnt \n" +
            "from report\n" +
            "where write_date like %?1%\n" +
            "and group1 in ('포유류','양서류','조류','파충류')\n" +
            "group by group1 ,substring(write_date,1,6)\n" +
            "order by group1 ,substring(write_date,1,6)\n", nativeQuery = true)
    List<MonthCount> selectLine4Group(String year);

}
