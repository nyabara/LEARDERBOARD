package com.example.learderboard;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.fragment.app.DialogFragment;

public class SubmitDialogFragment extends DialogFragment implements View.OnClickListener {
    public interface SubmitDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    SubmitDialogListener listener;

    // Override the Fragment.onAttach() method to instantiate the SubmitDialogListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the SubmitDialogListener so we can send events to the host
            listener = (SubmitDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(getActivity().toString()
                    + " must implement SubmitDialogListener");
        }
    }

    private AlertDialog.Builder mBuilder;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_submit_fragment, null);

        view.findViewById(R.id.close_negative).setOnClickListener(this);
        view.findViewById(R.id.btn_positive).setOnClickListener(this);


        mBuilder = new AlertDialog.Builder(getActivity());
        mBuilder.setView(view);
        return mBuilder.create();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_positive:
                listener.onDialogPositiveClick(SubmitDialogFragment.this);
                break;
            case R.id.close_negative:
                listener.onDialogNegativeClick(SubmitDialogFragment.this);
                break;
            default:
                break;
        }
    }
}
