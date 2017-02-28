package com.goodroad.model;

import org.springframework.data.rest.core.config.Projection;

/**
 * Created by dubu on 2017-02-27.
 */
@Projection(name = "simpleReport", types = { Report.class })
public interface SimpleReport {

    String getLat();
    String getLng();

}
