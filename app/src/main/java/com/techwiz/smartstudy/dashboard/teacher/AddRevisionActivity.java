package com.techwiz.smartstudy.dashboard.teacher;

import androidx.appcompat.app.AppCompatActivity;

import com.techwiz.smartstudy.R;
import com.techwiz.smartstudy.model.Resources;
import com.techwiz.smartstudy.model.Revision;
import com.techwiz.smartstudy.services.TeacherService;
import com.techwiz.smartstudy.validations.InputValidation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRevisionActivity extends AppCompatActivity {
    private final AppCompatActivity activity = AddRevisionActivity.this;
    TeacherService teacherService;
    InputValidation inputValidation;
    EditText revisionName, revisionDate, revisionTime;
    Button submitRevision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_revision);
        initViewVariables();
        submitRevision.setOnClickListener(view -> {
            addRevision();
        });
    }
    
    private void initViewVariables() {
        revisionName = findViewById(R.id.revisionName);
        revisionDate = findViewById(R.id.revisionDate);
        revisionTime = findViewById(R.id.revisionTime);
    }

    private void addRevision() {
        try {
            teacherService = new TeacherService(activity);
            if (inputValidation.isEmpty(revisionName)) {
                revisionName.setError("Name cannot be empty");
            } else if (inputValidation.isEmpty(revisionDate)) {
                revisionDate.setError("Date cannot be empty");
            } else if (inputValidation.isEmpty(revisionTime)) {
                revisionTime.setError("Time cannot be empty");
            } else {
                Revision revision = new Revision(revisionName.getText().toString(), revisionDate.getText().toString(), revisionTime.getText().toString());
                teacherService.addRevisionClasses(revision);
                Toast.makeText(activity, "Resource Added Successfully", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(activity, "Something went wrong", Toast.LENGTH_LONG).show();
        }

    }
}