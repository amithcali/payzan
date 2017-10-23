package calibrage.payzan.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import calibrage.payzan.R;

/**
 * Created by Calibrage11 on 10/20/2017.
 */

public class MyTransactionAdapter extends RecyclerView.Adapter<MyTransactionAdapter.MyHolder> {

    public MyTransactionAdapter(Context context){

    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_my_transactions, null);
        MyTransactionAdapter.MyHolder mh = new MyTransactionAdapter.MyHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

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
