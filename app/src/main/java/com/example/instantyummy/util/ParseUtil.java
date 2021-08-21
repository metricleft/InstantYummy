package com.example.instantyummy.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.instantyummy.R;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ParseUtil {
    private static final String TAG = "ParseUtil";

    // Returns the user-friendly error message that accompanies a ParseException.
    public static String parseExceptionToErrorText(ParseException e) {
        // fullErrorMessage in the format of: "com.parse.ParseRequest$ParseRequestException: [error message]."
        // sometimes, the error may be "com.parse.ParseRequest$ParseRequestException: java.lang.IllegalArgumentException: [error message]."
        // The function extracts and capitalizes the first letter of the user-friendly part of the error message,
        // which always begins 2 characters after the last colon.
        String fullErrorMessage = e.toString();
        int indexOfColon = fullErrorMessage.lastIndexOf(":");
        return fullErrorMessage.substring(indexOfColon+2, indexOfColon+3).toUpperCase()
                .concat(fullErrorMessage.substring(indexOfColon + 3));
    }

    public static void save(ParseObject object, Context context, String TAG, String successMessage, String errorMessage) {
        object.saveInBackground(e -> {
            if (e != null) {
                if (errorMessage != null) {
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                    Log.e(TAG, errorMessage, e);
                }
                else {
                    Toast.makeText(context, context.getString(R.string.error_default_message), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, context.getString(R.string.error_default_message), e);
                }
            }
            else {
                if (successMessage != null) {
                    Toast.makeText(context, successMessage, Toast.LENGTH_SHORT).show();
                    Log.i(TAG, successMessage);
                }
            }
        });
    }

}
