package com.techwiz.smartstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.techwiz.smartstudy.model.User;
import com.techwiz.smartstudy.sql.DatabaseHelper;
import com.techwiz.smartstudy.validations.InputValidation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = MainActivity.this;
    TextView login, signup;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewVariables();
        initListeners();
        initObjects();

    }

    private void initViewVariables() {
        login = (TextView) findViewById(R.id.openLogin);
        signup = (TextView) findViewById(R.id.openRegister);
    }

    private void initListeners() {
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.openLogin:
                //Display login activity
                startActivity(new Intent(activity, LoginActivity.class));
                break;
            case R.id.openRegister:
                //Display register activity
                startActivity(new Intent(activity, RegisterActivity.class));
                break;
        }
    }
}