package us.connex.miniprojectapt.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import us.connex.miniprojectapt.Activities.ImageWithGps;
import us.connex.miniprojectapt.R;

/**
 * Created by dranderson on 10/24/17.
 */

public class ImageGpsAdaptor extends BaseAdapter {
    private Context mContext;
    private int clickTimes;
    private List<ImageWithGps> images;
    private LayoutInflater inflater;

    public ImageGpsAdaptor(Context c, List<ImageWithGps> images, int clickTimes) {
        mContext = c;
        this.images = images;
        this.clickTimes = clickTimes;
    }

    public int getCount() {
        return (images.size() - 16 * clickTimes < 16 ? images.size() - 16 * clickTimes : 16);
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView = convertView;
        if(gridView == null) {
            inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridView = inflater.inflate(R.layout.image_gps_module, null);
            gridView.setLayoutParams(new GridView.LayoutParams(270, 300));
            gridView.setPadding(0, 0, 0, 0);
        }
        ImageView image = (ImageView) gridView.findViewById(R.id.imageView);
        TextView text = (TextView) gridView.findViewById(R.id.textView);
        TextView text2 = (TextView) gridView.findViewById(R.id.textView2);

        Picasso.with(mContext).load(images.get(16 * clickTimes + position).getURL()).resize(50,50).into(image);
        text.setText(images.get(16 * clickTimes + position).getStreamName());
        text2.setText(images.get(16 * clickTimes + position).getDistance());

        return gridView;
    }
}
