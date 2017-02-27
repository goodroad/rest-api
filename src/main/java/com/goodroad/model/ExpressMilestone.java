package com.goodroad.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by rigel on 2/23/17.
 */
@Entity
public class ExpressMilestone implements Serializable {

    public ExpressMilestone() {
    }

    public ExpressMilestone(float miles, float lat, float lng) {

        this.miles =miles;
        this.lat = lat;
        this.lng = lng;
    }

    public enum RoadType{
        J("중앙선")
        ,JB("중부선")
        ,DJ("당진대전선")
        ,YD("영동선")
        ,KB("경부선")
        ,WEST("서해안선")
        ,CS("청주상주선")
        ,H("호남선")
        ,JI("중부내륙선")
        , SK("서천공주선")
        ,HB("호남지선")
        ,SW("순천완주선")
        ,EAST("동해선")
        ,KD("광주대구선");

        private final String name;

        RoadType(String name) {
            this.name = name;

        }
    }

    @Id
    @GeneratedValue
    private Long id;

    public String num, name, engName, keyword;
    public float distance,  miles;

    public float lng;
    public float lat;

    public RoadType roadType;


    public Float getMiles() {
        return miles;
    }
}
