package model;

import org.json.JSONObject;

import persistence.Writable;

// A website contains a name and URL. Represents a Website or an app that you can store passwords for.
public class Website implements Writable {
    // fields
    private String name;
    private String url;
    
    /* 
    REQUIRES: name and url length are greater than 0
    EFFECTS: sets up a new website with name and url
    */
    public Website(String name, String url) {
        this.name = name;
        this.url = url;
    }

    // getters
    public String getName() {
        return name;
    }
    
    public String getUrl() {
        return url;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // taken from JSONSERIALIZATIONDEMO
    //EFFECTS: turns object into JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("nameWebsite", name);
        json.put("url", url);
        return json;
    }
}
