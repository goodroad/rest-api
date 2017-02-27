package com.goodroad.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by dubu on 2017-01-23.
 */
@Entity
public class Report implements Serializable{

   enum group {
      pu(1,"포유류"),bird(2,"조류"),;

       private final int id;
       private final String name;

       group(int id, String name) {
          this.id = id ;
          this.name = name;
       }
   }
   enum Status{
       CLOSE, OPEN
   }

   public enum WriterType{
       GOODROAD ,DATA_GO_KR ,ME_GO_KR_DAEGU ,EX_CO_KR ,KNPS_OR_KR ,WL_GNU_AC_KR
   }
//office pos write_date hour min group1 group2 cnt spot  env_left env_right lng lat etc
    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    public String road;
    @JsonIgnore
    public String pos;
    public String writeDate;
    @JsonIgnore
    public String hour;
    @JsonIgnore
    public String min;
    public String group1;
    public String group2;
    @JsonIgnore
    public int cnt;
    @JsonIgnore
    public String spot;
    @JsonIgnore
    public String env1;
    @JsonIgnore
    public String env2;

    @Column(nullable = false)
    public float lng;

    @Column(nullable = false)
    public float lat;

    public String etc;
    public String file;
    public Status status;
    public WriterType writerType;

    public String address;

    @CreatedDate
    public String createdDate;

    @LastModifiedBy
    public String modifiedDate;

}
