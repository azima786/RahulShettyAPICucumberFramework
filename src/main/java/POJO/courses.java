package POJO;

import java.util.List;

public class courses {


    public List<webAutomation> getWebAutomation() {
        return WebAutomation;
    }

    public void setWebAutomation(List<webAutomation> webAutomation) {
        this.WebAutomation = webAutomation;
    }

    public List<mobile> getMobile() {
        return Mobile;
    }

    public void setMobile(List<mobile> mobile) {
        this.Mobile = mobile;
    }

    public List<api> getApi() {
        return Api;
    }

    public void setApi(List<api> Api) {
        this.Api = Api;
    }

    private List<webAutomation> WebAutomation;
    private List<mobile> Mobile;
    private List<api> Api;

}
