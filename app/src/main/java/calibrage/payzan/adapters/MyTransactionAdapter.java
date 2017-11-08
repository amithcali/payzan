package calibrage.payzan.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import calibrage.payzan.R;
import calibrage.payzan.model.TransactionResponseModel;
import calibrage.payzan.utils.PayZanEnums;

/**
 * Created by Calibrage11 on 10/20/2017.
 */

public class MyTransactionAdapter extends RecyclerView.Adapter<MyTransactionAdapter.MyHolder> {
    private List<TransactionResponseModel.ListResult> listResults;
    private Context context;

    public MyTransactionAdapter(Context context, List<TransactionResponseModel.ListResult> listResults){
        this.context = context;
        this.listResults = listResults;

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
        if(listResults.get(holder.getAdapterPosition()).getTransactionType().equalsIgnoreCase(PayZanEnums.TransactionsEnum.Deposit.TID())){

        }else if(listResults.get(holder.getAdapterPosition()).getTransactionType().equalsIgnoreCase(PayZanEnums.TransactionsEnum.Transfer.TID())){

        }else if(listResults.get(holder.getAdapterPosition()).getTransactionType().equalsIgnoreCase(PayZanEnums.TransactionsEnum.WithDrawal.TID())){

        }
        holder.imageACK.setImageResource(R.drawable.ic_up_arrow);
        holder.amountTxt.setText(String.valueOf(listResults.get(holder.getAdapterPosition()).getAmount()));
        holder.dateTxt.setText(listResults.get(holder.getAdapterPosition()).getCreated());
        holder.paymentModeTxt.setText(listResults.get(holder.getAdapterPosition()).getReasonType());
       // holder.fromTxt.setText(listResults.get(holder.getAdapterPosition()).);

    }

    @Override
    public int getItemCount() {
        return listResults.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        private ImageView imageACK;
        private TextView paymentModeTxt,fromTxt,amountTxt,dateTxt;
        public MyHolder(View itemView) {
            super(itemView);
            imageACK = (ImageView)itemView.findViewById(R.id.imageACK);
            paymentModeTxt = (TextView) itemView.findViewById(R.id.paymentModeTxt);
            fromTxt = (TextView) itemView.findViewById(R.id.fromTxt);
            amountTxt = (TextView) itemView.findViewById(R.id.amountTxt);
            dateTxt = (TextView) itemView.findViewById(R.id.dateTxt);

        }

    }
}
