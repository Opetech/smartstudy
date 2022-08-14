package com.techwiz.smartstudy.dashboard.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.techwiz.smartstudy.LoginActivity;
import com.techwiz.smartstudy.R;
import com.techwiz.smartstudy.model.Resources;
import com.techwiz.smartstudy.services.TeacherService;
import com.techwiz.smartstudy.validations.InputValidation;

public class UpdateAcademicResource extends AppCompatActivity {
    private final AppCompatActivity activity = UpdateAcademicResource.this;
    TeacherService teacherService;
    InputValidation inputValidation;

    EditText resourceTitle, resourceLink;
    Button submitResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_academic_resource);
        initViewVariables();
        submitResource.setOnClickListener(view -> {
            addResource();
        });
    }

    private void initViewVariables() {
        resourceTitle = findViewById(R.id.resourceTitle);
        resourceLink = findViewById(R.id.resourceLink);
    }

    private void addResource() {
        try {
            teacherService = new TeacherService(activity);
            if (inputValidation.isEmpty(resourceTitle)) {
                resourceTitle.setError("Title cannot be empty");
            } else if (inputValidation.isEmpty(resourceLink)) {
                resourceLink.setError("Link cannot be empty");
            } else {
                Resources academicResources = new Resources(resourceTitle.getText().toString(), resourceLink.getText().toString());
                teacherService.addStudyResources(academicResources);
                Toast.makeText(activity, "Resource Added Successfully", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(activity, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }


}