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
    
    // VS Code generated
    // EFFECTS: generates hashcode for Website
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        return result;
    }

    // VS Code generated 
    // EFFECTS: checks if object equals website
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Website other = (Website) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (url == null) {
            if (other.url != null) {
                return false;
            }
        } else if (!url.equals(other.url)) {
            return false;
        }
        return true;
    }

    
}
