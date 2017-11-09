package calibrage.payzan.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import calibrage.payzan.R;
import calibrage.payzan.controls.CommonEditText;
import calibrage.payzan.utils.NCBTextInputLayout;

/**
 * Created by Calibrage11 on 11/8/2017.
 */

public class UpdatePasswordActivity extends AppCompatActivity {
    private CommonEditText oldPsdEdt,newPsdEdt,confirmPsdEdt;
    private NCBTextInputLayout oldPsdTIL,newPsdTIL,confirmPsdTIL;
    private Button saveBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        setView();
        initView();
    }

    private void initView() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidateUi()){

                }

            }
        });

    }

    private void setView() {
        oldPsdEdt = (CommonEditText)findViewById(R.id.oldPsdEdt);
        newPsdEdt = (CommonEditText)findViewById(R.id.newPsdEdt);
        confirmPsdEdt = (CommonEditText)findViewById(R.id.confirmPsdEdt);
        oldPsdTIL = (NCBTextInputLayout)findViewById(R.id.oldPsdTIL);
        newPsdTIL = (NCBTextInputLayout)findViewById(R.id.newPsdTIL);
        confirmPsdTIL = (NCBTextInputLayout)findViewById(R.id.confirmPsdTIL);
        saveBtn = (Button) findViewById(R.id.saveBtn);
    }

    private boolean isValidateUi(){
        return true;
    }
}
