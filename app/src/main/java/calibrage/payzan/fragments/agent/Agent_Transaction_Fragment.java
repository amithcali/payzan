package calibrage.payzan.fragments.agent;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import calibrage.payzan.R;

import static calibrage.payzan.fragments.agent.Agent_Home_Fragment.TAG;


public class Agent_Transaction_Fragment extends Fragment {

    private  EditText fromEdit,toEdit;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private DatePickerDialog.OnDateSetListener ontoDateSetListener;
    RecyclerView recycleragenttran;
    Context context;
    ArrayList<String> listTran = new ArrayList<>(Arrays.asList("11-11-17", "11-11-17", "11-11-17", "11-11-17", "11-11-17", "11-11-17", "11-11-17","11-11-17"));

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


        fromEdit=(EditText)view.findViewById(R.id.fromEdit);
        toEdit=(EditText)view.findViewById(R.id.toEdit);
        fromEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(),android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,onDateSetListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
              month=month+1;
                Log.d(TAG,"onDateset:dd/mm/yyy:"+day+"/"+month+"/"+year);
                String date=day+"/"+month+"/"+year;
                fromEdit.setText(date);

            }
        };


        toEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(),android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,ontoDateSetListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        ontoDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month=month+1;
                Log.d(TAG,"onDateset:dd/mm/yyy:"+day+"/"+month+"/"+year);
                String date=day+"/"+month+"/"+year;
                toEdit.setText(date);
            }
        };

        return view;
    }

}
