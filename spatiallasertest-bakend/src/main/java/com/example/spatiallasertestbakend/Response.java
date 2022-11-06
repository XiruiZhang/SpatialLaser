package com.example.spatiallasertestbakend;

public class Response {

    private String query_id;

    public String getQuery_id() {
        return query_id;
    }

    public void setQuery_id(String query_id) {
        this.query_id = query_id;
    }

    public String getPlacekey() {
        return placekey;
    }

    public void setPlacekey(String placekey) {
        this.placekey = placekey;
    }

    private String placekey;
}
