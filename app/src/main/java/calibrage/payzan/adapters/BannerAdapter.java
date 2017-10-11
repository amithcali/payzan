package calibrage.payzan.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import calibrage.payzan.R;

/**
 * Created by Calibrage11 on 10/7/2017.
 */

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.MyHolder> {
    private Context context;

    public BannerAdapter(Context context){
        this.context = context;

    }
    @Override
    public BannerAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_banner, null);
        BannerAdapter.MyHolder mh = new BannerAdapter.MyHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(BannerAdapter.MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        public MyHolder(View itemView) {
            super(itemView);
        }
    }
}
