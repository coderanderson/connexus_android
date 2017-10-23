package us.connex.miniprojectapt;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;

/**
 * Created by dranderson on 10/21/17.
 */

public class StreamImageAdapter extends BaseAdapter {
    private Context mContext;
    private Stream[] streams;

    public StreamImageAdapter(Context c, Stream[] streams) {
        mContext = c;
        this.streams = streams;
    }

    public int getCount() {
        return streams.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(270, 270));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }
        Picasso.with(mContext).load(streams[position].getCover()).into(imageView);


        return imageView;
    }

    public Stream[] getStreams() {
        return streams;
    }


    private String[] mThumbURLs = {
            "http://dora.missouri.edu/wp-content/uploads/2012/11/guinea-pig-tan.jpg",
            "https://media.mnn.com/assets/images/2016/07/guineapig-eating-basil.jpg.838x0_q80.jpg",
            "https://media.mnn.com/assets/images/2016/07/guineapig-eating-basil.jpg.838x0_q80.jpg",
            "https://media.mnn.com/assets/images/2016/07/guineapig-eating-basil.jpg.838x0_q80.jpg",
            "https://media.mnn.com/assets/images/2016/07/guineapig-eating-basil.jpg.838x0_q80.jpg",
            "https://media.mnn.com/assets/images/2016/07/guineapig-eating-basil.jpg.838x0_q80.jpg",
            "https://media.mnn.com/assets/images/2016/07/guineapig-eating-basil.jpg.838x0_q80.jpg",
            "https://media.mnn.com/assets/images/2016/07/guineapig-eating-basil.jpg.838x0_q80.jpg",
            "https://media.mnn.com/assets/images/2016/07/guineapig-eating-basil.jpg.838x0_q80.jpg",
            "https://media.mnn.com/assets/images/2016/07/guineapig-eating-basil.jpg.838x0_q80.jpg",
            "https://media.mnn.com/assets/images/2016/07/guineapig-eating-basil.jpg.838x0_q80.jpg",
            "https://media.mnn.com/assets/images/2016/07/guineapig-eating-basil.jpg.838x0_q80.jpg",
            "https://media.mnn.com/assets/images/2016/07/guineapig-eating-basil.jpg.838x0_q80.jpg",
            "https://media.mnn.com/assets/images/2016/07/guineapig-eating-basil.jpg.838x0_q80.jpg",
            "https://media.mnn.com/assets/images/2016/07/guineapig-eating-basil.jpg.838x0_q80.jpg",
            "https://media.mnn.com/assets/images/2016/07/guineapig-eating-basil.jpg.838x0_q80.jpg"
    };
}
