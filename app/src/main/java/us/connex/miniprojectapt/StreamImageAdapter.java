package us.connex.miniprojectapt;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dranderson on 10/21/17.
 */

public class StreamImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<Stream> streams;

    public StreamImageAdapter(Context c, List<Stream> streams) {
        mContext = c;
        this.streams = streams;
    }

    public int getCount() {
        return streams.size();
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
        Picasso.with(mContext).load(streams.get(position).getCover()).into(imageView);

        return imageView;
    }

    public List<Stream> getStreams() {
        return streams;
    }

}
