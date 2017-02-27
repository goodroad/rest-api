package com.goodroad.service;

import com.goodroad.model.Report;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Created by rigel on 2/10/17.
 */

@RunWith(SpringRunner.class)
//@Transactional
@SpringBootTest

public class ReportRepositoryTest {

    @Autowired ReportRepository reportRepository;

    @Test
    public void testFillAddreassByLatLng() throws Exception {

//        List<Report> all = reportRepository.findAll();
        List<Report> all = reportRepository.findByAddressIsNull();
        for (int i = 0; i < all.size(); i++) {
//        for (int i = 0; i < 3; i++) {
            Report report = all.get(i);
            if(report.address == null){
                String adress = findAddress(report);
                report.address = adress;
                System.out.printf("addr  " + adress);
                reportRepository.save(report);
            }

        }


//        all.stream().forEach(re -> System.out.println(re.group2));


    }

    @Test
    public void testFindByGroup1StartingWith() throws Exception {


    }

    private String findAddress(Report report) {
//        String lng = String.valueOf(127.10863694633468);
//        String lat = String.valueOf(37.40209529907863);
        String lng = String.valueOf(report.lng);
        String lat = String.valueOf(report.lat);
        String url = String.format("https://apis.daum.net/local/geo/coord2addr?apikey=6c8b5962143b5a1331b52b5403663307&longitude=%s&latitude=%s&inputCoordSystem=WGS84&output=json",lng,lat);
        URI uri = URI.create(url);
        RestTemplate restTemplate = new RestTemplate();
        Map map = restTemplate.getForObject(uri, Map.class);
        String fullName = (String) map.get("fullName");
//        System.out.println(fullName);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return fullName;
    }

    public void testFindByWriteDateStartingWith() throws Exception {

        // https://apis.daum.net/local/geo/coord2addr?apikey=6c8b5962143b5a1331b52b5403663307&longitude=127.10863694633468&latitude=37.40209529907863&inputCoordSystem=WGS84&output=json
    }


    @Test
    public void testFileGroup1() throws Exception {


    }


}