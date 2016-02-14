package teatime.food;

public class User {

    private int id;
    private String name;
    private String time;
    private String location;
    private boolean food;


    //get/set id
    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }


    //get/set name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //get/set time
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    //get/set location
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }


    //get/set food
    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }



}
