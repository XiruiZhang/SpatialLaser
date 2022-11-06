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

    //two hashset of invalid addresses to keep track of invalid addresses in table A and table B
    private HashSet<TableA> invalid = new HashSet<>();
    private HashSet<TableA> invalidA = new HashSet<>();

    public Controller(TableAService tableAService, TableBService tableBService, RestTemplate restTemplate){
        this.tableAService = tableAService;
        this.tableBService = tableBService;
        this.restTemplate = restTemplate;
    }

    /*
    * return a list of table A addresses
    * */
    @GetMapping
    @RequestMapping("/getA")
    public List<TableA> getAllA(){
        allA = tableAService.getAllAddresses();
        return allA;
    }

    /*
    * return a list of table B addresses
    * */
    @GetMapping
    @RequestMapping("/getB")
    public List<TableB> getAllB(){
        allB = tableBService.getAllAddresses();
        return allB;
    }

    /*
    * return a set of string of place key of addresses in table A
    * */
    @GetMapping
    @RequestMapping("/encodedA")
    public Set<String> getA(){

        //key is the placekey, value is the address object
        HashMap<String, TableA> encodedA = new HashMap();

        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", "wlgL94i2TITm4DX91G5HLHYbGbMrXmm2");
        headers.setContentType(MediaType.APPLICATION_JSON);

        for (TableA ta: allA) {
            getEncodedA(ta, encodedA, headers);
        }

        return encodedA.keySet();
    }

    /*
    * implementation of get place key
    * */
    public void getEncodedA(TableA ta, HashMap<String, TableA> encodedA, HttpHeaders headers){
        HashMap<String, HashMap<String, String>> request = new HashMap<>();
        HashMap<String, String> map = new HashMap<>();

        map.put("street_address", ta.address);
        map.put("city", ta.city);
        map.put("region", ta.state);
        map.put("postal_code", null);
        map.put("iso_country_code", "US");

        request.put("query", map);

        HttpEntity<HashMap<String, HashMap<String, String>>> entity = new HttpEntity<>(request, headers);
        ResponseEntity<Response> response = restTemplate.postForEntity(URL, entity, Response.class);

        /*
        * if addresses == null => invalid address
        * add to both invalid and invalidA
        * */
        if(response.getBody().getPlacekey() == null){
            invalid.add(ta);
            invalidA.add(ta);
        }else{
            encodedA.put(response.getBody().getPlacekey(), ta);
        }
    }

    /*
     * return a set of string of place key of addresses in table B
     * */
    @GetMapping
    @RequestMapping("/encodedB")
    public Set<String> getB(){

        HashMap<String, TableB> encodedB = new HashMap();

        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", "wlgL94i2TITm4DX91G5HLHYbGbMrXmm2");
        headers.setContentType(MediaType.APPLICATION_JSON);

        for(TableB tb: allB){
            getEncodedB(tb, encodedB, headers);
        }

        return encodedB.keySet();
    }

    /*
     * implementation of get place key
     * */
    public void getEncodedB(TableB tb, HashMap<String, TableB> encodedB, HttpHeaders headers){
        HashMap<String, HashMap<String, String>> request = new HashMap<>();
        HashMap<String, String> map = new HashMap<>();


        map.put("street_address", tb.address);
        map.put("city", tb.city);
        map.put("region", tb.state);
        map.put("postal_code", null);
        map.put("iso_country_code", "US");

        request.put("query", map);

        HttpEntity<HashMap<String, HashMap<String, String>>> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Response> response = restTemplate.postForEntity(URL, entity, Response.class);

        /*
         * if placekey is null => invalid address
         * add to invalid, duplicate value removed "automatically" with overriden hashCode and equals
         * otherwise add to the encoded hashmap
         * */
        if(response.getBody().getPlacekey() == null){
            TableA tempTA = new TableA();
            tempTA.address = tb.address;
            tempTA.city = tb.city;
            tempTA.state = tb.state;
            invalid.add(tempTA);
        }else{
            encodedB.put(response.getBody().getPlacekey(), tb);
        }
    }

    /*
    * for testing purpose
    * get all the invalid addresses
    * */
    @GetMapping
    @RequestMapping("/getInvalid")
    public HashSet<TableA> getInvalid(){
        return invalid;
    }
    /*
    * for testing purpose
    * get invalid addresses in table A
    * */
    @GetMapping
    @RequestMapping("/getInvalidA")
    public HashSet<TableA> getInvalidA(){
        return invalidA;
    }


    @GetMapping
    @RequestMapping("/getNewB")
    public List<Object> getNewB(){
        /*
         * allA
         * allB
         * foreach to iterate through allA and allB
         * use their address city state to get the encoded
         * save encoded in encodedA encodedB
         * if encodedA not contain encodedB, add to newB
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
            getEncodedA(ta, encodedA, headers);
        }

        for(TableB tb: allB){
            getEncodedB(tb, encodedB, headers);
        }

        for(String sb: encodedB.keySet()){
            //remove duplicate placekey
            if(!encodedA.keySet().contains(sb)){
                result.add(encodedB.get(sb));
            }
        }

        //add invalid address if it was in table B
        for(TableA o : invalidA){
            if(!invalid.contains(o)){
                result.add(o);
            }
        }

        return result;
    }

}

