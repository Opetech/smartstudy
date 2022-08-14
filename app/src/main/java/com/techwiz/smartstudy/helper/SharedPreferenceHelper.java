package com.techwiz.smartstudy.helper;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class SharedPreferenceHelper {

    private final static String USER_PREFERENCE_NAME = "UserLoginInfo";
    SharedPreferences sharedPreferences;

    public SharedPreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(USER_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }


    public boolean userIsLoggedIn() {
        Boolean userIsLoggedIn = sharedPreferences.getBoolean("userIsLoggedIn", false);
        return userIsLoggedIn;
    }

    public String userFullName() {
        String firstname = sharedPreferences.getString("firstname", "");
        String lastname = sharedPreferences.getString("lastname", "");

        return firstname + " " + lastname;
    }

    public void updateUserLogin(String firstname, String lastname, String category) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("userIsLoggedIn", true);
        editor.putString("firstname", firstname);
        editor.putString("lastname", lastname);
        editor.putString("category", category);
        editor.apply();
    }

    public void updateUserLogout() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("userIsLoggedIn", false);
        editor.apply();
    }

    public String getUserCategory() {
        return sharedPreferences.getString("category", "");
    }
}
