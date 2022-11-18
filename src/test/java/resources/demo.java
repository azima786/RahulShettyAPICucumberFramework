package resources;

public class demo {

    public String getAddPlaceAPI() {
        return addPlaceAPI;
    }

    public String getGetPlaceAPI() {
        return getPlaceAPI;
    }

    public String getDeletePlaceAPI() {
        return deletePlaceAPI;
    }

    String addPlaceAPI = "/maps/api/place/add/json";
    String getPlaceAPI = "/maps/api/place/get/json";
    String deletePlaceAPI = "/maps/api/place/delete/json";

}
