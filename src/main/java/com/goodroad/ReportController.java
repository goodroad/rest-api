package com.goodroad;

import com.goodroad.model.Report;
import com.goodroad.service.ReportRepository;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;

/**
 * Created by rigel on 2/20/17.
 */
@RepositoryRestController
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/reports")
    public
    @ResponseBody
    ResponseEntity saveReport(@RequestBody Report report) {

        String yyyyMMdd = DateTime.now().toString(DateTimeFormat.forPattern("yyyyMMddHHmmss"));
        report.address = findAddress(report);
        report.modifiedDate = yyyyMMdd;
        report.writerType = Report.WriterType.GOODROAD;

        if(report.createdDate == null){
            report.createdDate =  yyyyMMdd;
        }

//        System.out.println("address : " + s.address);
        Report save = reportRepository.save(report);

        return ResponseEntity.ok(report);

    }

    private String findAddress(Report report) {
        String lng = String.valueOf(report.lng);
        String lat = String.valueOf(report.lat);
        String url = String.format("https://apis.daum.net/local/geo/coord2addr?apikey=6c8b5962143b5a1331b52b5403663307&longitude=%s&latitude=%s&inputCoordSystem=WGS84&output=json", lng, lat);
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

}
