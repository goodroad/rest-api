package com.goni.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by dubu on 2017-01-24.
 */
@Entity
public class Dept {

    @Id
    long deptNo;

    public long getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(long deptNo) {
        this.deptNo = deptNo;
    }
}
