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


public class Agent_Commission_Fragment extends Fragment {

RecyclerView agentcommission;
Context context;
    ArrayList<String> listComm = new ArrayList<>(Arrays.asList("Airtel", "Airtel", "Airtel", "Airtel", "Airtel", "Airtel", "Airtel"));

    public Agent_Commission_Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_agent_commission, container, false);
        agentcommission=(RecyclerView)view.findViewById(R.id.agentcommission);
        AgentCommissionAdapter agentCommissionAdapter=new AgentCommissionAdapter(Agent_Commission_Fragment.this,listComm);
        agentcommission.setAdapter(agentCommissionAdapter);
        agentcommission.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        return view;
    }


    }



