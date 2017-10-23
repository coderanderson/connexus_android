package us.connex.miniprojectapt;

import java.util.*;

/**
 * Created by dranderson on 10/22/17.
 */

public class fetch_all_streams {
    private List<Stream> streams = new ArrayList<Stream>();
    public void request_streams() {
        streams.add(new Stream("pig1", "http://dora.missouri.edu/wp-content/uploads/2012/11/guinea-pig-tan.jpg"));
        streams.add(new Stream("pig2", "https://media.mnn.com/assets/images/2016/07/guineapig-eating-basil.jpg.838x0_q80.jpg"));
    }
    public Stream[] getStreams() {
        Stream[] fetchedStreams = new Stream[streams.size()];
        for(int i = 0; i < fetchedStreams.length; i++) {
            fetchedStreams[i] = streams.get(i);
        }
        return fetchedStreams;
    }
}
