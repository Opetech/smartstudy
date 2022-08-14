package com.techwiz.smartstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.techwiz.smartstudy.dashboard.ParentDashboard;
import com.techwiz.smartstudy.dashboard.student.StudentDashboard;
import com.techwiz.smartstudy.dashboard.teacher.TeacherDashboard;
import com.techwiz.smartstudy.helper.SharedPreferenceHelper;
import com.techwiz.smartstudy.model.User;
import com.techwiz.smartstudy.sql.DatabaseHelper;
import com.techwiz.smartstudy.validations.InputValidation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = MainActivity.this;
    TextView login, signup;
    DatabaseHelper databaseHelper;
    SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        initViewVariables();
        initListeners();
        initObjects();
        if (sharedPreferenceHelper.userIsLoggedIn()) {
            Intent intent;
            String userCategory = sharedPreferenceHelper.getUserCategory();
            switch (userCategory) {
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
                    throw new IllegalStateException("Unexpected value: " + userCategory);
            }
            startActivity(intent);
            finish();
        }

        super.onCreate(savedInstanceState);
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
        sharedPreferenceHelper = new SharedPreferenceHelper(activity);
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