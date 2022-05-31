package petsanct.services;

public class Cat {

    String id;
    String name;

    boolean outdoorCat;

    String keeperId;

    public Cat(String id, String name, boolean outdoorCat, String keeperId) {
        this.id = id;
        this.name = name;
        this.outdoorCat = outdoorCat;
        this.keeperId = keeperId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeeperId() {
        return keeperId;
    }

    public void setKeeperId(String keeperId) {
        this.keeperId = keeperId;
    }

    public boolean isOutdoorCat() {
        return outdoorCat;
    }

    public void setOutdoorCat(boolean outdoorCat) {
        this.outdoorCat = outdoorCat;
    }
}
