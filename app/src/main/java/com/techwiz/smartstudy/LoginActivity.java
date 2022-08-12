package com.techwiz.smartstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.techwiz.smartstudy.model.User;
import com.techwiz.smartstudy.sql.DatabaseHelper;
import com.techwiz.smartstudy.validations.InputValidation;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = LoginActivity.this;
    EditText email, password;
    TextView login;
    DatabaseHelper databaseHelper;
    InputValidation inputValidation;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViewVariables();
        initObjects();
        initListeners();
    }

    private void initViewVariables() {
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (TextView) findViewById(R.id.login);
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }

    private void initListeners() {
        login.setOnClickListener(this);
    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                startActivity(new Intent(activity, RegisterActivity.class));
                break;
            case R.id.login:
                handleLoginAction();
                break;
        }
    }

    private void handleLoginAction() {
            if (!!inputValidation.isEmpty(email)) {
                email.setError("Email cannot be empty!");
            } else if (!inputValidation.isEmpty(password)) {
                password.setError("Password cannot be empty");
            } else {
                try {
                    Object user = databaseHelper.checkUser(email.getText().toString(), password.getText().toString());
                    if (user instanceof User) {
                        User userDetails = (User) user;
                        Intent intent = new Intent(this, DashboardActivity.class);
                        intent.putExtra("firstname", userDetails.getFirstname());
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Login Details", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
}