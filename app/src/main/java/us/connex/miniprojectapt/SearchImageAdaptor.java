package us.connex.miniprojectapt;

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

/**
 * Created by dranderson on 10/24/17.
 */


public class SearchImageAdaptor extends BaseAdapter {
    private List<Stream> streams;
    private Context mContext;
    private LayoutInflater inflater;
    private int clickTimes;

    public SearchImageAdaptor(Context c, List<Stream> streams, int clickTimes) {
        mContext = c;
        this.streams = streams;
        this.clickTimes = clickTimes;
    }

    public int getCount() {
        return (streams.size() - 8 * clickTimes < 8 ? streams.size() - 8 * clickTimes : 8);
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
            gridView = inflater.inflate(R.layout.stream_module, null);
            gridView.setLayoutParams(new GridView.LayoutParams(270, 270));
            gridView.setPadding(0, 0, 0, 0);
        }
        ImageView image = (ImageView) gridView.findViewById(R.id.imageView);
        TextView text = (TextView) gridView.findViewById(R.id.textView);

        //image.setImageResource(images.get(i));
        Picasso.with(mContext).load(streams.get(position).getCover()).into(image);
        text.setText(streams.get(position).getName());

        return gridView;
    }

    public List<Stream> getStreams() {
        return streams;
    }



}
