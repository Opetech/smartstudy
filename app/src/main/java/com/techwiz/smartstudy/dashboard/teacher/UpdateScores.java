package com.techwiz.smartstudy.dashboard.teacher;

import androidx.appcompat.app.AppCompatActivity;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpdateScores extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final AppCompatActivity activity = UpdateScores.this;
    DatabaseHelper databaseHelper;
    TeacherService teacherService;
    SimpleDateFormat dateFormat;
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
        // Tests Drop down elements
        tests = new ArrayList<String>();
        tests.add("Item 1");
        tests.add("Item 2");
        tests.add("Item 3");

        testsId = new ArrayList<>();
        testsId.add(1);
        testsId.add(2);
        testsId.add(3);

        // User Drop down elements
        users = new ArrayList<String>();
        users.add("User 1");
        users.add("User 2");
        users.add("User 3");

        usersId = new ArrayList<>();
        usersId.add(1);
        usersId.add(2);
        usersId.add(3);

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

    private void updateScore() {
        teacherService = new TeacherService(activity);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ScoreDetails scoreDetails = new ScoreDetails(selectedTest, selectedUser, scoreDescription.getText().toString(), dateFormat.format(new Date()), Integer.parseInt(score.getText().toString()));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String from;
        if (parent.getSelectedView().getId() == R.id.testsDropdown) {
            selectedTest = testsId.get(parent.getSelectedItemPosition());
        } else if(parent.getSelectedView().getId() == R.id.usersDropdown) {
            selectedUser = usersId.get(parent.getSelectedItemPosition());
        }

        Log.println(Log.INFO, "Selected: ", "" + parent.getSelectedView().getId());
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + view.getId(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}