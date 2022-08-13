package com.techwiz.smartstudy.dashboard.teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.techwiz.smartstudy.R;

public class TeacherDashboard extends AppCompatActivity {
    private final AppCompatActivity activity = TeacherDashboard.this;
    private TextView username, welcomeMessage;
    private LinearLayoutCompat updateMarks, updateAcademicProgress, addStudyResource, addRevision;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        initializeViewVariables();
        updateUserInfo();
        initListeners();
    }

    private void initializeViewVariables() {
        username = (TextView) findViewById(R.id.username);
        welcomeMessage = (TextView) findViewById(R.id.welcomeMessage);
        updateMarks = findViewById(R.id.updateMarks);
        updateAcademicProgress = findViewById(R.id.updateAcademicProgress);
        addStudyResource = findViewById(R.id.addStudyResource);
        addRevision = findViewById(R.id.addRevision);
    }

    private void initListeners() {
        updateMarks.setOnClickListener(view -> {
             startActivity(new Intent(activity, UpdateScores.class));
        });
        updateAcademicProgress.setOnClickListener(view -> {
            startActivity(new Intent(activity, UpdateAcademicProgress.class));
        });
        addStudyResource.setOnClickListener(view -> {
            startActivity(new Intent(activity, UpdateAcademicResource.class));
        });
        addRevision.setOnClickListener(view -> {
            startActivity(new Intent(activity, AddRevisionActivity.class));
        });
    }


    private void updateUserInfo() {
        username.setText("Hi, " + getIntent().getStringExtra("firstname"));
        welcomeMessage.setText("Welcome, " + getIntent().getStringExtra("firstname") + " " + getIntent().getStringExtra("lastname"));
    }
}