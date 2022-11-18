package resources;

import appTest.googleMaps;
import appTest.location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public googleMaps addPlacePayload(String name, String language, String address){
        googleMaps p = new googleMaps();
        p.setAccuracy(50);
        p.setName(name);
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("http://google.com");
        p.setLanguage(language);
        p.setAddress(address);

        List<String> myList = new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);

        location l = new location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);
        return p;
    }

    public String deletePlacePayload(String placeId) {
        return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
    }
}
