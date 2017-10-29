package com.djonique.birdays.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

public class ProgressDialogHelper {

    private static final String ILLEGAL_ARGUMENT_EXCEPTION = "IllegalArgumentException";

    private Context context;
    private ProgressDialog progressDialog;

    public ProgressDialogHelper(Context context) {
        this.context = context;
        progressDialog = new ProgressDialog(context);
    }

    /**
     * Starts progress dialog
     */
    public void startProgressDialog(String message) {
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    /**
     * Stops progress dialog
     */
    public void dismissProgressDialog() {
        if (progressDialog != null) {
            try {
                progressDialog.dismiss();
            } catch (IllegalArgumentException e) {
                Toast.makeText(context, ILLEGAL_ARGUMENT_EXCEPTION, Toast.LENGTH_SHORT).show();
            }
        }
    }
}