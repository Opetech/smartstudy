package com.techwiz.smartstudy.dashboard.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.techwiz.smartstudy.MainActivity;
import com.techwiz.smartstudy.R;
import com.techwiz.smartstudy.helper.SharedPreferenceHelper;

public class StudentDashboard extends AppCompatActivity {
    private final AppCompatActivity activity = StudentDashboard.this;
    private LinearLayoutCompat viewMarks, viewAcademicProgress, viewStudyResources, viewRevisions;
    private TextView logout, welcomeMessage;
    private SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        initializeViewVariables();
        updateUserInfo();
        initListeners();
    }

    private void initializeViewVariables() {
        logout = (TextView) findViewById(R.id.logout);
        welcomeMessage = (TextView) findViewById(R.id.welcomeMessage);
        viewMarks = findViewById(R.id.viewMarks);
        viewAcademicProgress = findViewById(R.id.viewAcademicProgress);
        viewStudyResources = findViewById(R.id.viewStudyResources);
        viewRevisions = findViewById(R.id.viewRevisions);
    }

    private void initListeners() {
        viewMarks.setOnClickListener(view -> {
            startActivity(new Intent(activity, TestScores.class));
        });
        viewAcademicProgress.setOnClickListener(view -> {
            startActivity(new Intent(activity, AcademicStatus.class));
        });
        viewStudyResources.setOnClickListener(view -> {
            startActivity(new Intent(activity, StudyResource.class));
        });
//        viewRevisions.setOnClickListener(view -> {
//            startActivity(new Intent(activity, StudyResource.class));
//        });
        logout.setOnClickListener(view -> {
            sharedPreferenceHelper.updateUserLogout();
            startActivity(new Intent(activity, MainActivity.class));
            finish();
        });
    }

    private void updateUserInfo() {
        sharedPreferenceHelper = new SharedPreferenceHelper(activity);
        welcomeMessage.setText("Welcome, " + sharedPreferenceHelper.userFullName());
    }

}