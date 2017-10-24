package calibrage.payzan.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import calibrage.payzan.R;
import calibrage.payzan.activities.HomeActivity;
import calibrage.payzan.utils.CommonConstants;
import calibrage.payzan.utils.SharedPrefsData;


public class UserProfileHome extends Fragment {
    private Button btn_logOut;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_profile_home, container, false);
        btn_logOut = (Button) v.findViewById(R.id.btn_sign_out);
        btn_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefsData.getInstance(getActivity()).ClearData(getActivity());
                Toast.makeText(getActivity(), "DATA Cleared", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), HomeActivity.class);
                startActivity(i);
            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event

}
