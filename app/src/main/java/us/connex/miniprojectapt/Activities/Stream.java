package us.connex.miniprojectapt.Activities;

/**
 * Created by dranderson on 10/22/17.
 */

public class Stream {
    private String cover;
    private String name;
    Stream(String name, String cover) {
        this.cover = cover;
        this.name = name;
    }
    public String getCover() {
        return cover;
    }
    public String getName() {
        return name;
    }
}
