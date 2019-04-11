package com.example.keshar.registerloginauthenticationfirebase;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

public  class ValidationCheck {
    public static boolean isValidForEmail(String value, EditText editText){
        boolean isValid=false;

        if(!TextUtils.isEmpty(value) &&  Patterns.EMAIL_ADDRESS.matcher(value).matches())
            isValid=true;
        else {
            isValid=false;
            editText.setError("Required...");
        }
        return isValid;

    }
    public static boolean isValidForPassword(String value, EditText editText){
        boolean isValid=false;

        if(!TextUtils.isEmpty(value))
        {
            if(value.length()>6){
                isValid=true;
            }else {
                isValid=false;
                editText.setError("Password Should be Atleast 6 Character");
            }
        }

        else {
            isValid=false;
            editText.setError("Required...");
        }
        return isValid;

    }

}
