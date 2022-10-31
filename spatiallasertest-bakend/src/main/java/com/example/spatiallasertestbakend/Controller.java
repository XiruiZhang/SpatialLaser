package com.example.spatiallasertestbakend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@CrossOrigin(origins="*")
//@CrossOrigin()
@RestController
@RequestMapping(value = "")
public class Controller {

    private TableAService tableAService;
    private TableBService tableBService;

    private final String URL = "https://api.placekey.io/v1/placekey";

    private final RestTemplate restTemplate;

    private List<TableA> allA;

    private List<TableB> allB;

    private List<TableB> newB = new ArrayList<>();

    public Controller(TableAService tableAService, TableBService tableBService, RestTemplate restTemplate){
        this.tableAService = tableAService;
        this.tableBService = tableBService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    @RequestMapping("/getA")
    public List<TableA> getAllA(){
        allA = tableAService.getAllAddresses();
        return allA;
    }

    @GetMapping
    @RequestMapping("/getB")
    public List<TableB> getAllB(){
        allB = tableBService.getAllAddresses();
        return allB;
    }

    @GetMapping
    @RequestMapping("/encodedA")
    public Set<String> getA(){

        HashMap<String, TableA> encodedA = new HashMap();

        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", "wlgL94i2TITm4DX91G5HLHYbGbMrXmm2");
        headers.setContentType(MediaType.APPLICATION_JSON);

        for (TableA ta: allA) {

            HashMap<String, HashMap<String, String>> request = new HashMap<>();
            HashMap<String, String> map = new HashMap<>();

            map.put("street_address", ta.address);
            map.put("city", ta.city);
            map.put("region", ta.state);
            map.put("postal_code", null);
            map.put("iso_country_code", "US");

            request.put("query", map);

            //RestTemplate restTemplate = new RestTemplate();

            //HttpEntity<MultiValueMap<String, MultiValueMap<String,String>>> entity = new HttpEntity<MultiValueMap<String, MultiValueMap<String, String>>>(request, headers);
            HttpEntity<HashMap<String, HashMap<String, String>>> entity = new HttpEntity<>(request, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(URL, entity, String.class);
            encodedA.put(response.getBody(), ta);
        }
        return encodedA.keySet();
    }

    @GetMapping
    @RequestMapping("/encodedB")
    public Set<String> getB(){

//        HashMap<String, TableA> encodedA = new HashMap();
        HashMap<String, TableB> encodedB = new HashMap();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("apikey", "wlgL94i2TITm4DX91G5HLHYbGbMrXmm2");
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        for (TableA ta: allA) {
//
//            HashMap<String, HashMap<String, String>> request = new HashMap<>();
//            HashMap<String, String> map = new HashMap<>();
//
//            map.put("street_address", ta.address);
//            map.put("city", ta.city);
//            map.put("region", ta.state);
//            map.put("postal_code", null);
//            map.put("iso_country_code", "US");
//
//            request.put("query", map);
//
//            //RestTemplate restTemplate = new RestTemplate();
//
//            //HttpEntity<MultiValueMap<String, MultiValueMap<String,String>>> entity = new HttpEntity<MultiValueMap<String, MultiValueMap<String, String>>>(request, headers);
//            HttpEntity<HashMap<String, HashMap<String, String>>> entity = new HttpEntity<>(request, headers);
//            ResponseEntity<String> response = restTemplate.postForEntity(URL, entity, String.class);
//            encodedA.put(String.valueOf(response), ta);
//        }

        for(TableB tb: allB){

            HashMap<String, HashMap<String, String>> request = new HashMap<>();
            HashMap<String, String> map = new HashMap<>();

            HttpHeaders headers = new HttpHeaders();
            headers.set("apikey", "wlgL94i2TITm4DX91G5HLHYbGbMrXmm2");
            headers.setContentType(MediaType.APPLICATION_JSON);

            map.put("street_address", tb.address);
            map.put("city", tb.city);
            map.put("region", tb.state);
            map.put("postal_code", null);
            map.put("iso_country_code", "US");

            request.put("query", map);

            //RestTemplate restTemplate = new RestTemplate();

            //HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
            //HttpEntity<MultiValueMap<String, MultiValueMap<String,String>>> entity = new HttpEntity<MultiValueMap<String, MultiValueMap<String, String>>>(request, headers);
            HttpEntity<HashMap<String, HashMap<String, String>>> entity = new HttpEntity<>(request, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(URL, entity, String.class);
            encodedB.put(response.getBody(), tb);
        }
        return encodedB.keySet();
    }

    @GetMapping
    @RequestMapping("/update")
    public List<Object> getNewB(){
        /*
         * allA
         * allB
         * foreach to iterate through allA and allB
         * use their address city state to get the encoded
         * save encoded in encodedA encodedB
         * if encodedA not contain encodedB, add to newB
         * */

        /*
         * when more than 1 invalid addresses key
         * how to compare if same address
         * if invalid address then no need to compare ifcontains
         * string of response.body and parse, compare the street_address and city
         * */

        allA = tableAService.getAllAddresses();
        allB = tableBService.getAllAddresses();

        HashMap<String, TableA> encodedA = new HashMap();
        HashMap<String, TableB> encodedB = new HashMap();
        List<Object> result = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", "wlgL94i2TITm4DX91G5HLHYbGbMrXmm2");
        headers.setContentType(MediaType.APPLICATION_JSON);

        for (TableA ta: allA) {

            HashMap<String, HashMap<String, String>> request = new HashMap<>();
            HashMap<String, String> map = new HashMap<>();

            map.put("street_address", ta.address);
            map.put("city", ta.city);
            map.put("region", ta.state);
            map.put("postal_code", null);
            map.put("iso_country_code", "US");

            request.put("query", map);


            //RestTemplate restTemplate = new RestTemplate();

            //HttpEntity<MultiValueMap<String, MultiValueMap<String,String>>> entity = new HttpEntity<MultiValueMap<String, MultiValueMap<String, String>>>(request, headers);
            HttpEntity<HashMap<String, HashMap<String, String>>> entity = new HttpEntity<>(request, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(URL, entity, String.class);
            encodedA.put(response.getBody(), ta);
        }

        for(TableB tb: allB){

            HashMap<String, HashMap<String, String>> request = new HashMap<>();
            HashMap<String, String> map = new HashMap<>();

            map.put("street_address", tb.address);
            map.put("city", tb.city);
            map.put("region", tb.state);
            map.put("postal_code", null);
            map.put("iso_country_code", "US");

            request.put("query", map);

            //RestTemplate restTemplate = new RestTemplate();

            //HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
            //HttpEntity<MultiValueMap<String, MultiValueMap<String,String>>> entity = new HttpEntity<MultiValueMap<String, MultiValueMap<String, String>>>(request, headers);
            HttpEntity<HashMap<String, HashMap<String, String>>> entity = new HttpEntity<>(request, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(URL, entity, String.class);
            encodedB.put(response.getBody(), tb);
        }

        for(String sb: encodedB.keySet()){
            if(!encodedA.keySet().contains(sb)){
                result.add(encodedB.get(sb));
            }
        }

//        for(TableA ta : encodedA.values()){
//            result.add(ta);
//        }

//        for(TableB tb: encodedB.values()){
//            result.add(tb);
//        }

        return result;
    }


}

