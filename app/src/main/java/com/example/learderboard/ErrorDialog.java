package com.example.learderboard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.DialogFragment;

public class ErrorDialog extends DialogFragment {
    private AlertDialog.Builder mBuilder;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.error_dialog, null);

        mBuilder = new AlertDialog.Builder(getActivity());
        mBuilder.setView(view);
        return mBuilder.create();
    }

}
