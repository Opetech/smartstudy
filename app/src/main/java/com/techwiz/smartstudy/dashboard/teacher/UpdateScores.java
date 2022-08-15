package com.techwiz.smartstudy.dashboard.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.techwiz.smartstudy.R;
import com.techwiz.smartstudy.model.ScoreDetails;
import com.techwiz.smartstudy.services.TeacherService;
import com.techwiz.smartstudy.sql.DatabaseHelper;
import com.techwiz.smartstudy.validations.InputValidation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpdateScores extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final AppCompatActivity activity = UpdateScores.this;
    TeacherService teacherService;
    SimpleDateFormat dateFormat;
    InputValidation inputValidation;
    List<String> tests;
    List<Integer> testsId;
    List<String> users;
    List<Integer> usersId;

    Spinner testsDropdown;
    Spinner usersDropdown;
    EditText score, scoreDescription;
    Button submitScore;
    int selectedTest;
    int selectedUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_scores);
        initViewVariables();
        initListeners();
        createSelectDropdownMenu();
    }

    private void createSelectDropdownMenu() {
        teacherService = new TeacherService(activity);
        //Fetch available tests
        tests = teacherService.getAllTests();
        //Assign ids for tests, to get selected test id from dropdown
        testsId = new ArrayList<>();
        for (int i = 1; i <= tests.size(); i++) {
            testsId.add(i);
        }

        //Fetch all users
        users = teacherService.getAllStudents();
        //Assign ids for users, to get selected test id from dropdown
        usersId = new ArrayList<>();
        for (int i = 1; i <= tests.size(); i++) {
            usersId.add(i);
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tests);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);

        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        testsDropdown.setAdapter(dataAdapter1);
        usersDropdown.setAdapter(dataAdapter2);
    }

    private void initViewVariables() {
        testsDropdown = findViewById(R.id.testsDropdown);
        usersDropdown = findViewById(R.id.usersDropdown);
        score = findViewById(R.id.score);
        scoreDescription = findViewById(R.id.scoreDescription);
        submitScore = findViewById(R.id.submitScore);
    }

    private void initListeners() {
        testsDropdown.setOnItemSelectedListener(this);
        usersDropdown.setOnItemSelectedListener(this);
        submitScore.setOnClickListener(view -> {
            updateScore();
        });
    }

    private void initObjects() {
        teacherService = new TeacherService(activity);
        inputValidation = new InputValidation(activity);
    }

    private void updateScore() {
        try {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            int scoreInput = score.getText().toString().isEmpty() ? -1 : Integer.parseInt(score.getText().toString());
            if (scoreInput > 100 || scoreInput < 0) {
                score.setError("Score must be between 0 and 100");
            } else {
                ScoreDetails scoreDetails = new ScoreDetails(selectedTest, selectedUser, scoreDescription.getText().toString(), dateFormat.format(new Date()), Integer.parseInt(score.getText().toString()));
                teacherService.updateScore(scoreDetails);
                Toast.makeText(activity, "Test Score Updated Successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(activity, TeacherDashboard.class));
                finish();
            }
        } catch (Exception e) {
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent == testsDropdown) {
            selectedTest = testsId.get(parent.getSelectedItemPosition());
        } else if (parent == usersDropdown) {
            selectedUser = usersId.get(parent.getSelectedItemPosition());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}