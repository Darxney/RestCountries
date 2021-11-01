package lv.andris.rest.countries.web.controllers.CountryController_classes;

public class Message {

    private String response;

    public Message( ) {
    }

    public Message(String s) {
        this.response = s;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}