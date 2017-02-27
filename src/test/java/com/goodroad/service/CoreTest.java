package com.goodroad.service;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dubu on 2017-02-25.
 */
public class CoreTest {

    @Test
    public void testCompare() throws Exception {

        List<Float> list  = new ArrayList();

        list.add(10.0f);
        list.add(20.0f);
        list.add(30.0f);
        list.add(0.0f);
        list.add(2.0f);


        Collections.sort(list, new Comparator<Float>() {
            @Override
            public int compare(Float o1, Float o2) {

//                return Integer.parseInt(o1.toString()) >Integer.parseInt( o2.toString()) ?1 :0;
                return  o1.compareTo(o2);
            }



        });


        System.out.println(list);





//        List<float>

    }

}
