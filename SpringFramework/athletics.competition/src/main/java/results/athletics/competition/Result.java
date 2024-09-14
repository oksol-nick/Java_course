package results.athletics.competition;

public class Result {
    private String name;
    private String gender;
    private int distance;
    private int time;

    public Result(String name, String gender, int distance, int time) {
        this.name = name;
        this.gender = gender;
        this.distance = distance;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public float getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Result{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", distance=" + distance +
                ", time=" + time +
                '}';
    }
}
