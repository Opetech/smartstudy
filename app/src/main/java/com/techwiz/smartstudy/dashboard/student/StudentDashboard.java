package com.techwiz.smartstudy.dashboard.student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.techwiz.smartstudy.R;

public class StudentDashboard extends AppCompatActivity {
    private TextView username, welcomeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        initializeViewVariables();
        updateUserInfo();
    }

    private void initializeViewVariables() {
        username = (TextView) findViewById(R.id.username);
        welcomeMessage = (TextView) findViewById(R.id.welcomeMessage);
    }

    private void updateUserInfo() {
        username.setText(getIntent().getStringExtra("firstname"));
        welcomeMessage.setText("Welcome, " + getIntent().getStringExtra("firstname") + " " + getIntent().getStringExtra("lastname"));
    }

}