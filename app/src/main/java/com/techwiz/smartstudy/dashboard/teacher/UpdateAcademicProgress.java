package com.techwiz.smartstudy.dashboard.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.techwiz.smartstudy.R;
import com.techwiz.smartstudy.model.Test;
import com.techwiz.smartstudy.services.TeacherService;
import com.techwiz.smartstudy.validations.InputValidation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpdateAcademicProgress extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final AppCompatActivity activity = UpdateAcademicProgress.this;
    TeacherService teacherService;
    List<String> tests;
    List<Integer> testsId;
    Spinner testsDropdown;
    Button submitAcademicProgress;
    int testStatus = 1;
    int selectedTestId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_academic_progress);
        initViewVariables();
        initObjects();
        initListeners();
        createSelectDropdownMenu();
    }

    private void initViewVariables() {
        testsDropdown = findViewById(R.id.testsDropdown);
        submitAcademicProgress = findViewById(R.id.submitAcademicProgress);
    }

    private void initObjects() {
        teacherService = new TeacherService(activity);
    }

    private void initListeners() {
        testsDropdown.setOnItemSelectedListener(this);
        submitAcademicProgress.setOnClickListener(view -> {
            updateScore();
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        // Check which test status button was clicked
        switch (view.getId()) {
            case R.id.testIsTaken:
                if (checked)
                    testStatus = 1;
                break;
            case R.id.testNotTaken:
                if (checked)
                    testStatus = 0;
                break;
        }
    }

    private void createSelectDropdownMenu() {
        //Fetch available tests
        tests = teacherService.getAllTests();
        //Assign ids for tests, to get selected test id from dropdown
        testsId = new ArrayList<>();
        for (int i = 1; i <= tests.size(); i++) {
            testsId.add(i);
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tests);

        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        testsDropdown.setAdapter(dataAdapter1);
    }

    private void updateScore() {
        try {
            Test test = new Test();
            test.setIsTaken(testStatus);
            teacherService.updateAcademicProgress(selectedTestId, test);
            Toast.makeText(activity, "Progress Update Successful", Toast.LENGTH_LONG).show();
            startActivity(new Intent(activity, TeacherDashboard.class));
        } catch (Exception e) {
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedTestId = testsId.get(parent.getSelectedItemPosition());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}