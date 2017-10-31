package calibrage.payzan.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import calibrage.payzan.R;
import calibrage.payzan.controls.BaseFragment;
import calibrage.payzan.controls.CommonEditText;
import calibrage.payzan.interfaces.DrawableClickListener;
import calibrage.payzan.utils.NCBTextInputLayout;

/**
 * Created by Calibrage11 on 10/20/2017.
 */

public class SendMoneyToWallet extends BaseFragment {
    public static final String TAG = SendMoneyToWallet.class.getSimpleName();
    private View rootView;
    private Context context;
    private NCBTextInputLayout mobileNumberTXT,amountTXT,commentTXT;
    private CommonEditText mobileEdt,amount,commentEdt;
    static final int PICK_CONTACT = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_send_money,container,false);
        context = this.getActivity();
        setViews();
        initViews();
        return rootView;
    }

    private void initViews() {
        mobileEdt.setDrawableClickListener(new DrawableClickListener() {


            public void onClick(DrawablePosition target) {
                switch (target) {
                    case RIGHT:
                        //Do something here
                        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                        startActivityForResult(intent, PICK_CONTACT);

                        break;

                    default:
                        break;
                }
            }

        });

        mobileEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    mobileNumberTXT.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    amountTXT.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void setViews() {

        mobileNumberTXT = (NCBTextInputLayout)rootView.findViewById(R.id.mobileNumberTXT);
        amountTXT = (NCBTextInputLayout)rootView.findViewById(R.id.amountTXT);
        commentTXT = (NCBTextInputLayout)rootView.findViewById(R.id.commentTXT);
        mobileEdt = (CommonEditText) rootView.findViewById(R.id.mobileEdt);
        amount = (CommonEditText) rootView.findViewById(R.id.amount);
        commentEdt = (CommonEditText) rootView.findViewById(R.id.commentEdt);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (PICK_CONTACT):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = context.getContentResolver().query(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String hasPhone = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        mobileEdt.setText(hasPhone.replaceAll("\\s",""));
                    }

                }
                break;
        }
    }
}
