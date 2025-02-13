package model;

public class Website {
    // fields
    private String name;
    
    private String url;
    
    /* 
    REQUIRES: name and url length are greater than 0
    EFFECTS: sets up a new website with name and url
    */
    public Website(String name, String url) {
        

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
}
