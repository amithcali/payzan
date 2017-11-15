package calibrage.payzan.fragments.agent;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import calibrage.payzan.R;


public class Agent_Transaction_Fragment extends Fragment {

    RecyclerView recycleragenttran;
    Context context;
    ArrayList<String> listTran = new ArrayList<>(Arrays.asList("Mobile", "Electricity", "Events", "Sport", "DTH", "Internet", "LandLine","Water"));

    public Agent_Transaction_Fragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_agent_transaction,container,false);

        recycleragenttran=(RecyclerView)view.findViewById(R.id.recycleragenttran);
        AgentTransactionAdapter agentTransactionAdapter=new AgentTransactionAdapter(Agent_Transaction_Fragment.this,listTran);
        recycleragenttran.setAdapter(agentTransactionAdapter);
        recycleragenttran.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));

        return view;
    }

}
