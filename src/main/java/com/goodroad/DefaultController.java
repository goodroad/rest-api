package com.goodroad;

import com.goodroad.model.MonthCount;
import com.goodroad.model.Population;
import com.goodroad.model.Report;
import com.goodroad.service.MonCountRepository;
import com.goodroad.service.PopulationRepository;
import com.goodroad.service.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class DefaultController {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private PopulationRepository populationRepository;


    @Autowired
    private MonCountRepository monCountRepository;


    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @RequestMapping("/dubu")
    public Report dubu(){

        Report one = reportRepository.findOne(1L);
        return one;
    }

    @RequestMapping("earth")
    public List<Population> findAll(){
        return  populationRepository.findAll();
    }

    @RequestMapping("earth2")
    public List<Population> findAllByWriteDate(@RequestParam("p") String writeDate){
        return  populationRepository.findAllByWriteDate(writeDate);
    }

    @RequestMapping("earth3")
    public List<Population> findAllByWriteDateAndGroup1(@RequestParam("p") String writeDate, @RequestParam("group1")String group1){
        return  populationRepository.findAllByWriteDateAndGroup1(writeDate,group1);
    }

    @RequestMapping("earth4")
    public List<Population> findAllByWriteDateAndGroup2(@RequestParam("p") String writeDate, @RequestParam("group2")String group2){
        return  populationRepository.findAllByWriteDateAndGroup2(writeDate,group2);
    }


    @RequestMapping("line4group")
    public Map<String, List<String>> findLine4group(@RequestParam("year") String year){

        List<MonthCount> list = monCountRepository.selectLine4Group(year);

        List<String> monList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
        Map<String, List<String>> rsMap =  new HashMap<>();
        rsMap.put("x", monList);

        for (int i = 0; i < list.size(); i++) {
            MonthCount monthCount = list.get(i);


//            String year = monthCount.mon.substring(0, 4);
            Integer mon= Integer.valueOf(monthCount.mon.substring(4, 6));
            String group1 = monthCount.mon.substring(6);

            List<String> cntList = rsMap.get(group1);
            if(cntList == null){
//                ArrayList arrayList = new ArrayList();
//                List initList = Arrays.asList("0","0","0","0","0","0","0","0","0","0","0","0") ;
                List initList = new ArrayList();
                for (int j = 0; j < 12; j++) {
                    initList.add("0");
                }
                initList.set( mon-1, monthCount.cnt);
                rsMap.put(group1,initList);

            }else{
                cntList.set( mon-1, monthCount.cnt);
            }

        }



        return rsMap;
    }

    @RequestMapping("line")
    public Map<String, List<String>> findLineAll(){

        List<MonthCount> list = monCountRepository.selectLineAll();

        List<String> monList = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
//        Map<String, List<String>> rsMap = ImmutableMap.of("x", monList);
        Map<String, List<String>> rsMap =  new HashMap<>();
        rsMap.put("x", monList);


        for (int i = 0; i < list.size(); i++) {
            MonthCount monthCount = list.get(i);

            String year = monthCount.mon.substring(0, 4);
            Integer mon= Integer.valueOf(monthCount.mon.substring(4, 6));
            List<String> cntList = rsMap.get(year);
            if(cntList == null){
//                ArrayList arrayList = new ArrayList();
//                List initList = Arrays.asList("0","0","0","0","0","0","0","0","0","0","0","0") ;
                List initList = new ArrayList();
                for (int j = 0; j < 12; j++) {
                    initList.add("0");
                }
                initList.set( mon-1, monthCount.cnt);
                rsMap.put(year,initList);

            }else{
                cntList.set( mon-1, monthCount.cnt);
            }


        }

        return rsMap;

    }





//    @RequestMapping("group1")
//    public List<Map> findByGroup2Rank() {
//       List<Map> list =  reportRepository.findByGroup2Rank();
//       return list;
//    }


    @RequestMapping("group1")
    public List<Map> findByYearGroup2Rank(@RequestParam("year") String year) {
        List<Map> list =  reportRepository.findByYearGroup2Rank(year);
        return list;
    }


}
