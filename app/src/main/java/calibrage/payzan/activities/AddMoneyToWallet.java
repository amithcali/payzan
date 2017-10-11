package calibrage.payzan.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Calibrage11 on 9/25/2017.
 */

public class AddMoneyToWallet extends AppCompatActivity {

    private EditText enterMoneyEdt,enterpromocodeEdt;
    private Button submit;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money_to_wallet);
     //   setupToolbar();
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
        enterMoneyEdt = (EditText)findViewById(R.id.amount);
        enterpromocodeEdt = (EditText)findViewById(R.id.promocode);
        submit = (Button)findViewById(R.id.submit);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    void setupToolbar() {
       toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Add Money to Wallet");
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
