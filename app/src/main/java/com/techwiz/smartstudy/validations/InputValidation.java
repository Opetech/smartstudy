package com.techwiz.smartstudy.validations;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class InputValidation {
    private Context context;

    /**
     * @param context
     */
    public InputValidation(Context context) {
        this.context = context;
    }

    /**
     * Check if input field is empty
     *
     * @param text
     * @return
     */
    public boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        hideKeyboardFrom(text);
        return TextUtils.isEmpty(str);
    }

    /**
     * Validate if input value is valid email
     *
     * @param text
     * @return
     */
    public boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        hideKeyboardFrom(text);
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public boolean isValidUrl(EditText text) {
        CharSequence url = text.getText().toString();
        return !TextUtils.isEmpty(url) && Patterns.WEB_URL.matcher(url).matches();
    }

    /**
     * method to Hide keyboard
     *
     * @param view
     */
    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
