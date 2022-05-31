package petsanct.services;

public class Dog {

    String id;
    String name;

    boolean shedding;

    String keeperId;

    public Dog() {
    }

    public Dog(String id, String name, boolean shedding, String keeperId) {
        this.id = id;
        this.name = name;
        this.shedding = shedding;
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

    public boolean isShedding() {
        return shedding;
    }

    public void setShedding(boolean shedding) {
        this.shedding = shedding;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", shedding=" + shedding +
                ", keeperId='" + keeperId + '\'' +
                '}';
    }
}

