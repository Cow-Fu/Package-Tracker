package rocks.cow.Tracker.TrackingInfo;

import java.util.ArrayList;

public class TrackingInfo {

    private ArrayList<String> dateTime = new ArrayList<>();
    private ArrayList<String> status = new ArrayList<>();
    private ArrayList<String> location = new ArrayList<>();

    public ArrayList<String> getTimes() {
        return dateTime;
    }

    public void setTimes(ArrayList<String> dateTime) {
        this.dateTime = dateTime;
    }

    public void addTime(String time) {
        dateTime.add(time);
    }

    public void addTime(int index, String time) {
        dateTime.add(index, time);
    }

    public ArrayList<String> getStatus() {
        return status;
    }

    public void setStatus(ArrayList<String> status) {
        this.status = status;
    }

    public void addStatus(String status) {
        this.status.add(status);
    }

    public void addStatus(int index, String status) {
        this.status.add(index, status);
    }

    public ArrayList<String> getLocations() {
        return location;
    }

    public void setLocations(ArrayList<String> location) {
        this.location = location;
    }

    public void addLocation(String location) {
        this.location.add(location);
    }

    public void addLocation(int index, String location) {
        this.location.add(index, location);
    }
}
