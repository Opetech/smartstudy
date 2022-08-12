package com.techwiz.smartstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.techwiz.smartstudy.model.User;
import com.techwiz.smartstudy.sql.DatabaseHelper;
import com.techwiz.smartstudy.validations.InputValidation;

public class RegisterActivity extends AppCompatActivity{
    private final AppCompatActivity activity = RegisterActivity.this;
    EditText email, firstname, lastname, contact, password;
    String category;
    TextView signupSubmitBtn;
    DatabaseHelper databaseHelper;
    InputValidation inputValidation;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViewVariables();
        initObjects();
        signupSubmitBtn.setOnClickListener( view -> {
            handleRegisterAction();
        });
    }

    private void initViewVariables() {
        email = (EditText) findViewById(R.id.emailAddress);
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        contact = (EditText) findViewById(R.id.userPhone);
        password = (EditText) findViewById(R.id.password);
        signupSubmitBtn = (TextView) findViewById(R.id.register);
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        databaseHelper.getWritableDatabase();
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which user category button was clicked
        switch (view.getId()) {
            case R.id.student:
                if (checked)
                    category = "student";
                break;
            case R.id.parent:
                if (checked)
                    category = "parent";
                break;
            case R.id.teacher:
                if (checked)
                    category = "teacher";
                break;
        }
    }

    private void handleRegisterAction() {
        if (inputValidation.isEmpty(firstname)) {
            firstname.setError("Firstname is required");
        } else if (inputValidation.isEmpty(lastname)) {
            lastname.setError("Lastname is required");
        } else if (!inputValidation.isEmail(email)) {
            email.setError("Enter valid email!");
        } else if (inputValidation.isEmpty(contact)) {
            contact.setError("Phone number is required");
        } else if (inputValidation.isEmpty(password)) {
            password.setError("Password is required");
        } else {
            if (databaseHelper.checkEmailExist(email.getText().toString())) {
                Toast.makeText(activity, "Email Already exist", Toast.LENGTH_LONG).show();
            } else {
                try {
                    user = new User(firstname.getText().toString(), lastname.getText().toString(), contact.getText().toString(), email.getText().toString(), password.getText().toString(), category);
                    databaseHelper.addUser(user);
                    Toast.makeText(activity, "Information saved successfully", Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(1000);
                        startActivity(new Intent(this, LoginActivity.class));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}