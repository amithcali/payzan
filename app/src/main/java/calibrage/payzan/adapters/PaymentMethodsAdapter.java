package calibrage.payzan.adapters;

import android.content.Context;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import calibrage.payzan.R;
import calibrage.payzan.interfaces.PaymentMethodClickListiner;
import calibrage.payzan.interfaces.RechargeClickListiner;

/**
 * Created by Calibrage19 on 13-10-2017.
 */

public class PaymentMethodsAdapter extends RecyclerView.Adapter<PaymentMethodsAdapter.MyHolder> {
    private ArrayList<Pair<Integer, String>> itemsPairArrayList;
    private Context context;
    private PaymentMethodClickListiner methodClickListiner;

    public PaymentMethodsAdapter(ArrayList<Pair<Integer, String>> itemsPairArrayList, Context context) {
        this.itemsPairArrayList = itemsPairArrayList;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_new_recharge, null);
        PaymentMethodsAdapter.MyHolder mh = new PaymentMethodsAdapter.MyHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        holder.imageView.setImageResource(itemsPairArrayList.get(holder.getAdapterPosition()).first);
        holder.textView.setText(itemsPairArrayList.get(holder.getAdapterPosition()).second);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               methodClickListiner.onPaymentMethodClickListiner(holder.getPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsPairArrayList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public MyHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }
    public void setOnAdapterListener(PaymentMethodClickListiner onAdapterListener) {
        this.methodClickListiner =  onAdapterListener;
    }
}
