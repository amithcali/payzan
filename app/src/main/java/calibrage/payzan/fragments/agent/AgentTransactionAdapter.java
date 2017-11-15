package calibrage.payzan.fragments.agent;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import calibrage.payzan.R;

/**
 * Created by Calibrage25 on 11/15/2017.
 */

public class AgentTransactionAdapter extends RecyclerView.Adapter<AgentTransactionAdapter.MyViewHolder> {

Agent_Transaction_Fragment context;
ArrayList<String>listTran;
    public AgentTransactionAdapter(Agent_Transaction_Fragment context, ArrayList<String> listTran) {
        this.context=context;
        this.listTran=listTran;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.agent_transaction_adapter,null);
        MyViewHolder mvh=new MyViewHolder(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
     holder.textView.setText(listTran.get(position).toString());
    }



    @Override
    public int getItemCount() {
        return listTran.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.agenttxt1);
        }
    }
}
