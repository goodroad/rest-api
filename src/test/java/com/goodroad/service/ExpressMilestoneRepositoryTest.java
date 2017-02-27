package com.goodroad.service;

import com.goodroad.model.Ex;
import com.goodroad.model.ExpressMilestone;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by rigel on 2/23/17.
 */
@RunWith(SpringRunner.class)
//@Transactional
@SpringBootTest
public class ExpressMilestoneRepositoryTest {

    @Autowired ExpressMilestoneRepository expressMilestoneRepository;
    @Autowired ExRepository exRepository;


    @Test
    public void testLngLatByName() throws Exception {

//        ExpressMilestone expressMilestone = expressMilestoneRepository.findOne(53L);

        List<ExpressMilestone> all = expressMilestoneRepository.findAll();

        for (int i = 0; i < all.size(); i++) {
            ExpressMilestone expressMilestone = all.get(i);

            Map item = searchAddressNameByKeyword(expressMilestone);
            if (item != null) {

                expressMilestone.lat = Float.valueOf((String) item.get("latitude"));
                expressMilestone.lng = Float.valueOf((String) item.get("longitude"));
                expressMilestoneRepository.save(expressMilestone);

            }else{
                System.err.println(expressMilestone.keyword);
            }

        }


    }


    private Map searchAddressNameByKeyword(ExpressMilestone expressMilestone) {

        String apikey = "6c8b5962143b5a1331b52b5403663307";
        String apiurl = "https://apis.daum.net/local/v1/search/keyword.json?apikey=%s&query=%s";
        String keyword = expressMilestone.keyword;


        try {
            keyword = URLEncoder.encode(keyword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = String.format(apiurl, apikey, keyword);
        URI uri = URI.create(url);
        RestTemplate restTemplate = new RestTemplate();

        Map map = restTemplate.getForObject(uri, Map.class);
        Map channel = (Map) map.get("channel");

        ArrayList<Map> item = (ArrayList<Map>) channel.get("item");
//        Map channel = (Map) map.get("channel");

        if (item.size() == 0) {
            return null;

        }

        System.out.println(item);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String fullName = "";
//        return fullName;
        return item.get(0);
    }


    /**
     * '197.88','36.3661','128.239'
     '189.24','36.3678','128.141'
     '170.29','36.4425','127.962'
     '154.06','36.4556','127.787'
     '148.08','36.4567','127.721'
     '137.26','36.4868','127.609'
     '126.56','36.5298','127.507'
     '118.46','36.5648','127.431'
     '114.96','36.5336','127.43'
     '103.88','36.4396','127.42'
     '99.8','36.4047','127.419'
     '96.19','36.4113','127.381'
     '91.58','36.3891','127.34'
     '85.45','36.4247','127.291'
     '72.89','36.4949','127.195'
     '65.89','36.4915','127.122'
     '63.02','36.4856','127.092'
     '60.35','36.4855','127.062'
     '56.56','36.495','127.024'
     '47.42','36.5651','126.976'
     '36.89','36.5932','126.866'
     '24.38','36.6772','126.775'
     '12.7','36.7619','126.705'
     '4.04','36.8235','126.647'
     '0','36.8926','126.707'


     * @throws Exception
     */
    @Test
    public void testEstimateLatLngDJ() throws Exception {
//
//        ExpressMilestone e1 =  new ExpressMilestone(197.88f, 36.3661f, 128.239f);
//        ExpressMilestone e2 =  new ExpressMilestone(189.24f, 36.3678f, 128.141f);
//        ExpressMilestone e3 =  new ExpressMilestone(170.24f, 36.4425f, 127.962f);
//        ExpressMilestone e4 =  new ExpressMilestone(154.06f, 36.4556f, 127.787f);
//        ExpressMilestone e5 =  new ExpressMilestone(148.08f, 36.4567f, 127.721f);
//        ExpressMilestone e6 =  new ExpressMilestone(137.26f, 36.4868f, 127.609f);
//        ExpressMilestone e7 =  new ExpressMilestone(126.56f, 36.5298f, 127.507f);
//        ExpressMilestone e8 =  new ExpressMilestone(118.46f, 36.5648f, 127.431f);
//        ExpressMilestone e9 =  new ExpressMilestone(114.96f, 36.5336f, 127.43f);
//        ExpressMilestone e10 =  new ExpressMilestone(103.88f, 36.4396f, 127.42f);
//        ExpressMilestone e11 =  new ExpressMilestone(99.8f, 36.4047f, 127.419f);
//        ExpressMilestone e12 =  new ExpressMilestone(96.19f, 36.4113f, 127.381f);
//        ExpressMilestone e13 =  new ExpressMilestone(91.58f, 36.3891f, 127.34f);
//        ExpressMilestone e14 =  new ExpressMilestone(85.45f, 36.4247f, 127.291f);
//        ExpressMilestone e15 =  new ExpressMilestone(72.89f, 36.4949f, 127.195f);
//        ExpressMilestone e16 =  new ExpressMilestone(65.89f, 36.4915f, 127.122f);
//        ExpressMilestone e17 =  new ExpressMilestone(63.02f, 36.4856f, 127.092f);
//        ExpressMilestone e18 =  new ExpressMilestone(60.35f, 36.4855f, 127.062f);
//        ExpressMilestone e19 =  new ExpressMilestone(56.56f, 36.495f, 127.024f);
//        ExpressMilestone e20 =  new ExpressMilestone(47.42f, 36.5651f, 126.976f);
//        ExpressMilestone e21 =  new ExpressMilestone(36.89f, 36.5932f, 126.866f);
//        ExpressMilestone e22 =  new ExpressMilestone(24.38f, 36.6772f, 126.775f);
//        ExpressMilestone e23 =  new ExpressMilestone(12.7f, 36.7619f, 126.705f);
//        ExpressMilestone e24 =  new ExpressMilestone(4.04f, 36.8235f, 126.647f);
//        ExpressMilestone e25 =  new ExpressMilestone(0f, 36.8926f, 126.707f);
//        List<ExpressMilestone> miList = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20
//            , e21, e22, e23, e24, e25);

        // 당진 -> 대전
        List<ExpressMilestone> miList = expressMilestoneRepository.findByRoadType(ExpressMilestone.RoadType.DJ);
//        List<Ex> exList = exRepository.findByLineAndWay("당진대전선", "대전");
        List<Ex> exList = exRepository.findByLine("당진대전선");
        for (int i = 0; i < exList.size(); i++) {
            Ex ex = exList.get(i);

            if ((ex.way != "대전")) {
                ex.milestone = 92- ex.milestone;
                ex.way = "대전";
            }

            setEst(miList, ex);

            exRepository.save(ex);
//            System.out.println(ex.lng);
//            System.out.println(ex.lat);

        }


//        List<Float> floats = Arrays.asList(0.1f, 2.2f, 2.4f, 2.6f, 2.7f, 3.2f, 3.3f, 3.7f, 3.8f, 4f, 4.2f, 4.3f, 4.4f, 4.5f, 5f, 5.2f, 5.6f, 5.9f, 6.3f, 7.2f, 7.3f, 7.4f, 7.5f, 7.6f, 7.7f, 7.8f, 7.9f, 8f, 8.1f, 8.3f, 8.4f, 8.5f, 8.6f, 8.8f, 8.9f, 9f, 10.1f, 10.3f, 10.5f, 10.8f, 11f, 11.2f, 11.3f, 11.4f, 11.6f, 11.8f, 11.9f, 12f, 12.1f, 12.4f, 12.8f, 12.9f, 13f, 13.1f, 13.2f, 13.3f, 13.4f, 13.5f, 13.6f, 13.7f, 13.8f, 13.9f, 14f, 14.1f, 14.4f, 14.7f, 14.9f, 15f, 15.1f, 15.2f, 15.4f, 15.5f, 15.7f, 15.8f, 15.9f, 16.5f, 16.6f, 16.9f, 17.3f, 17.4f, 17.8f, 17.9f, 18.1f, 18.7f, 18.9f, 19.1f, 19.3f, 19.4f, 19.5f, 19.7f, 20f, 20.1f, 20.2f, 20.3f, 20.6f, 20.8f, 21.1f, 21.2f, 21.9f, 22.1f, 22.2f, 22.3f, 22.6f, 22.8f, 23.5f, 23.6f, 24.3f, 24.4f, 24.5f, 24.6f, 25.1f, 25.3f, 25.4f, 25.6f, 26f, 26.1f, 26.3f, 26.5f, 26.9f, 27.3f, 27.6f, 29.1f, 29.5f, 29.6f, 29.7f, 30f, 30.1f, 30.9f, 31.1f, 31.3f, 31.4f, 31.7f, 31.8f, 31.9f, 32.1f, 32.5f, 32.6f, 32.8f, 33f, 33.2f, 33.3f, 33.4f, 33.8f, 34f, 34.3f, 34.4f, 34.5f, 34.6f, 34.7f, 34.8f, 34.9f, 35f, 35.1f, 35.2f, 35.3f, 35.4f, 35.5f, 35.9f, 36.1f, 36.9f, 37f, 37.3f, 37.4f, 37.6f, 37.9f, 38.1f, 38.3f, 38.5f, 38.6f, 38.7f, 39f, 39.2f, 39.7f, 39.8f, 39.9f, 40.1f, 40.8f, 41.4f, 41.5f, 41.6f, 41.7f, 41.8f, 42.1f, 42.3f, 42.5f, 42.6f, 42.7f, 42.8f, 43f, 43.2f, 43.9f, 44f, 44.2f, 45.5f, 45.7f, 47.1f, 47.2f, 47.3f, 47.4f, 47.5f, 47.7f, 48.2f, 48.3f, 48.4f, 48.5f, 48.6f, 49f, 49.5f, 49.7f, 49.8f, 50f, 50.1f, 50.2f, 50.3f, 50.5f, 51f, 51.2f, 51.3f, 52.1f, 52.2f, 52.3f, 52.4f, 52.7f, 52.9f, 53f, 53.2f, 53.3f, 53.4f, 53.6f, 53.7f, 54.7f, 56.5f, 56.7f, 56.9f, 57.1f, 57.2f, 57.3f, 57.4f, 58f, 58.4f, 58.5f, 58.6f, 58.7f, 58.8f, 58.9f, 59.4f, 59.7f, 59.8f, 60.1f, 60.2f, 60.3f, 60.5f, 60.7f, 60.9f, 61.1f, 61.5f, 61.6f, 61.8f, 61.9f, 62f, 62.1f, 62.3f, 62.6f, 62.9f, 63f, 63.1f, 63.3f, 63.5f, 63.6f, 63.8f, 63.9f, 64.3f, 64.4f, 64.6f, 64.9f, 65f, 65.1f, 65.2f, 65.3f, 65.4f, 65.5f, 65.6f, 65.7f, 65.8f, 65.9f, 66f, 66.1f, 66.2f, 66.8f, 67.9f, 68f, 68.2f, 68.4f, 68.5f, 68.6f, 68.8f, 69.1f, 69.2f, 69.3f, 69.4f, 69.5f, 69.6f, 69.7f, 69.8f, 69.9f, 70f, 70.1f, 70.4f, 70.5f, 71f, 71.1f, 71.2f, 71.3f, 71.5f, 71.7f, 71.8f, 71.9f, 72f, 72.1f, 72.2f, 72.3f, 72.4f, 72.5f, 72.7f, 72.8f, 72.9f, 73.1f, 73.6f, 74.2f, 74.4f, 74.5f, 74.9f, 75.3f, 75.4f, 75.8f, 76.2f, 76.3f, 76.4f, 76.5f, 76.6f, 76.7f, 76.8f, 76.9f, 77f, 77.1f, 77.3f, 77.4f, 77.5f, 77.6f, 77.7f, 78f, 78.3f, 78.4f, 78.5f, 78.6f, 78.7f, 79.2f, 79.4f, 79.6f, 80.1f, 80.3f, 80.4f, 80.6f, 80.7f, 80.9f, 81.1f, 81.4f, 81.7f, 81.9f, 82f, 82.1f, 82.4f, 82.5f, 82.8f, 83f, 83.1f, 83.2f, 83.5f, 84.5f, 84.9f, 85.1f, 85.2f, 85.4f, 85.5f, 85.8f, 85.9f, 86f, 86.5f, 86.6f, 86.9f, 87f, 87.1f, 87.5f, 87.6f, 87.8f, 88f, 88.3f, 88.4f, 88.9f, 90.5f, 90.7f, 91.4f, 91.5f, 91.58f, 91.6f, 91.7f);


        Ex ex = new Ex();
        ex.milestone = 5.2f;


    }


    @Test
    public void testEstimateLatLngJ() throws Exception {
//


        // 부산 -> 추천  0 - 386
        List<ExpressMilestone> miList = expressMilestoneRepository.findByRoadType(ExpressMilestone.RoadType.J);
        List<Ex> exList = exRepository.findByLine("중앙선");
        for (int i = 0; i < exList.size(); i++) {
            Ex ex = exList.get(i);

            if ((ex.way != "춘천")) {
                ex.milestone = 387 - ex.milestone;
                ex.way = "춘천";
            }

            setEst(miList, ex);

            exRepository.save(ex);

        }


    }

    @Test
    public void testEstimateLatLngJB() throws Exception {
//

        // 남이 + 246.37

        // 통영->하남   0 - 363.6
        List<ExpressMilestone> miList = expressMilestoneRepository.findByRoadType(ExpressMilestone.RoadType.JB);
        List<Ex> exList = exRepository.findByLine("중부선");
        for (int i = 0; i < exList.size(); i++) {
            Ex ex = exList.get(i);

            if ((ex.way != "하남")) {
                ex.milestone = 364 - ex.milestone;
                ex.way = "하남";
            }

            setEst(miList, ex);

            exRepository.save(ex);

        }


    }
//
//
//    @Test
//    public void testEstimateLatLngYD() throws Exception {
//
//        // 서창 to 강릉  0 -  235
//        int length =  235;
//        List<ExpressMilestone> miList = expressMilestoneRepository.findByRoadType(ExpressMilestone.RoadType.YD);
//        List<Ex> exList = exRepository.findByLine("영동선");
//        for (int i = 0; i < exList.size(); i++) {
//            Ex ex = exList.get(i);
//            if ((ex.way != "강릉")) {
//                ex.milestone = length - ex.milestone;
//                ex.way = "강릉";
//            }
//            setEst(miList, ex);
//            exRepository.save(ex);
//        }
//    }

    @Test
    public void testEstimateLatLngKB() throws Exception {


        Map map_YD = ImmutableMap.of("name", "영동선", "length", 235, "from", "서창", "to", "강릉","type",ExpressMilestone.RoadType.YD);
        Map map_KB = ImmutableMap.of("name", "경부선", "length", 416, "from", "부산", "to", "서울","type",ExpressMilestone.RoadType.JB);
        Map map_WEST = ImmutableMap.of("name", "서해안선", "length", 341, "from", "목포", "to", "서울","type",ExpressMilestone.RoadType.WEST);

        //당진영덕고속도로
       Map map_CS= ImmutableMap.of("name", "청주상주선", "length", 81, "from", "청주", "to", "상주","type",ExpressMilestone.RoadType.CS);

        // 순천논산
        Map map_H= ImmutableMap.of("name", "호남선", "length", 198, "from", "순천", "to", "천안","type",ExpressMilestone.RoadType.H);
        Map map_JI= ImmutableMap.of("name", "중부내륙선", "length", 303, "from", "내서", "to", "양평","type",ExpressMilestone.RoadType.JI);
        Map map_SJ= ImmutableMap.of("name", "서천공주선", "length", 63, "from", "서천", "to", "공주","type",ExpressMilestone.RoadType.SK);
        Map map_HB= ImmutableMap.of("name", "호남지선", "length", 55, "from", "논산", "to", "회덕","type",ExpressMilestone.RoadType.HB);
        Map map_SW= ImmutableMap.of("name", "순천완주선", "length", 118, "from", "순천", "to", "완주","type",ExpressMilestone.RoadType.SW);
        Map map_EAST= ImmutableMap.of("name", "동해선", "length", 87, "from", "동해", "to", "속초","type",ExpressMilestone.RoadType.EAST);
        Map map_KD= ImmutableMap.of("name", "광주대구선", "length", 181 , "from", "광주", "to", "대구","type",ExpressMilestone.RoadType.KD);


//        List<Map> mapList = Arrays.asList(map_KB, map_WEST, map_CS, map_H, map_JI, map_SJ, map_HB, map_SW, map_EAST, map_KD);
//        List<Map> mapList = Arrays.asList(   map_SW, map_EAST, map_KD);
        List<Map> mapList = Arrays.asList(   map_YD, map_KB);

        for (int ii = 0; ii < mapList.size(); ii++) {
            Map map = mapList.get(ii);

//        Map map =  map_YD;

            int length = (int) map.get("length");
            List<ExpressMilestone> miList = expressMilestoneRepository.findByRoadType((ExpressMilestone.RoadType) map.get("type"));
            List<Ex> exList = exRepository.findByLine(String.valueOf(map.get("name")));
            for (int i = 0; i < exList.size(); i++) {
                Ex ex = exList.get(i);
                if ((ex.way.equals(String.valueOf(map.get("to"))))) {
                    ex.milestone = length - ex.milestone;
                    ex.way = String.valueOf(map.get("to"));
                }
                setEst(miList, ex);
                exRepository.save(ex);
            }
        }
    }


    private void setEst(List<ExpressMilestone> miList, Ex ex) {

        Collections.sort(miList, new Comparator<ExpressMilestone>() {
            @Override
            public int compare(ExpressMilestone o1, ExpressMilestone o2) {

                return o1.getMiles().compareTo(o2.getMiles());
//                return o1.getMiles() > o2.getMiles() ? 1: 0f;
//                if (o1.getMiles() < o2.getMiles()) {
//                    return -1;
//                } else {
//                    return 0;
//                }
            }
        });

        for (int i = 0; i < miList.size(); i++) {
            ExpressMilestone e = miList.get(i);
            if (e.miles > ex.milestone) {

                if(i == 0){
                    System.err.println("ERROR");
                }
                ExpressMilestone e0 = miList.get(i - 1);

                float lat0 = e0.lat;
                float lng0 = e0.lng;
                float mi0 = e0.miles;

                float lat1 = e.lat;
                float lng1 = e.lng;
                float mi1 = e.miles;

                float dLat = (ex.milestone - mi0) / (mi1 - mi0) * (lat1 - lat0);
                float dLng = (ex.milestone - mi0) / (mi1 - mi0) * (lng1 - lng0);

                float eLat = e0.lat + dLat;
                float eLng = e0.lng + dLng;

                System.out.println(ex.milestone);
                System.out.println(i);
                System.out.println(lat1);
                System.out.println(lng1);


                System.out.println(new DecimalFormat("#.####").format(eLat));
                System.out.println(new DecimalFormat("#.###").format(eLng));

                ex.lat = Float.parseFloat(new DecimalFormat("#.####").format(eLat));
                ex.lng = Float.parseFloat(new DecimalFormat("#.###").format(eLng));

                return;
            }

        }
    }

}