package com.techwiz.smartstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.techwiz.smartstudy.dashboard.ParentDashboard;
import com.techwiz.smartstudy.dashboard.student.StudentDashboard;
import com.techwiz.smartstudy.dashboard.teacher.TeacherDashboard;
import com.techwiz.smartstudy.helper.SharedPreferenceHelper;
import com.techwiz.smartstudy.model.User;
import com.techwiz.smartstudy.sql.DatabaseHelper;
import com.techwiz.smartstudy.validations.InputValidation;

public class LoginActivity extends AppCompatActivity {
    private final AppCompatActivity activity = LoginActivity.this;
    EditText email, password;
    TextView login, register;
    DatabaseHelper databaseHelper;
    InputValidation inputValidation;
    User user;
    SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViewVariables();
        initObjects();

        login.setOnClickListener(view -> {
            handleLoginAction();
        });
        register.setOnClickListener(view -> {
            startActivity(new Intent(activity, RegisterActivity.class));
        });
    }

    private void initViewVariables() {
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (TextView) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        sharedPreferenceHelper = new SharedPreferenceHelper(activity);
        user = new User();
    }

    private void handleLoginAction() {
        if (inputValidation.isEmpty(email)) {
            email.setError("Email cannot be empty!");
        } else if (inputValidation.isEmpty(password)) {
            password.setError("Password cannot be empty");
        } else {
            try {
                Object user = databaseHelper.checkUser(email.getText().toString(), password.getText().toString());
                if (user instanceof User) {
                    User userDetails = (User) user;
                    Intent intent;

                    switch (userDetails.getCategory()) {
                        case "student":
                            intent = new Intent(activity, StudentDashboard.class);
                            break;
                        case "teacher":
                            intent = new Intent(activity, TeacherDashboard.class);
                            break;
                        case "parent":
                            intent = new Intent(activity, ParentDashboard.class);
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + userDetails.getCategory());
                    }

                    sharedPreferenceHelper.updateUserLogin(userDetails.getFirstname(), userDetails.getLastname(), userDetails.getCategory());
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Login Details", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}