package com.techwiz.smartstudy.dashboard.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.techwiz.smartstudy.R;
import com.techwiz.smartstudy.model.Test;
import com.techwiz.smartstudy.services.TeacherService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpdateAcademicProgress extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{
    private final AppCompatActivity activity = UpdateAcademicProgress.this;
    TeacherService teacherService;
    List<String> tests;
    List<Integer> testsId;
    Spinner testsDropdown;
    Button submitAcademicProgress;
    int testStatus;
    int selectedTestId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_academic_progress);
        initViewVariables();
        initListeners();
        createSelectDropdownMenu();
    }

    private void initViewVariables() {
        testsDropdown = findViewById(R.id.testsDropdown);
        submitAcademicProgress = findViewById(R.id.submitAcademicProgress);
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
        // Tests Drop down elements
        tests = new ArrayList<String>();
        tests.add("Item 1");
        tests.add("Item 2");
        tests.add("Item 3");

        testsId = new ArrayList<>();
        testsId.add(1);
        testsId.add(2);
        testsId.add(3);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tests);

        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        testsDropdown.setAdapter(dataAdapter1);
    }

    private void updateScore() {
        teacherService = new TeacherService(activity);
        Test test = new Test();
        test.setIsTaken(testStatus);
        teacherService.updateAcademicProgress(selectedTestId, test);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedTestId = testsId.get(parent.getSelectedItemPosition());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}