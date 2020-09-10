package com.example.learderboard;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learderboard.services.ServiceBuilder;
import com.example.learderboard.services.SubmitService;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity implements SubmitDialogFragment.SubmitDialogListener {
    EditText mFirstName,mLastName,mEmail,mLink;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        submit=findViewById(R.id.submit);
        mFirstName=findViewById(R.id.firtsname);
        mLastName=findViewById(R.id.lastname);
        mEmail=findViewById(R.id.email);
        mLink=findViewById(R.id.gitlink);


        //Objects.requireNonNull(getSupportActionBar()).hide();
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = getSupportActionBar().getCustomView().findViewById(R.id.toolbar_title);
        textView.setText("Google Africa Developer Scholarship");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an instance of the dialog fragment and show it
                DialogFragment dialog = new SubmitDialogFragment();
                dialog.show(getSupportFragmentManager(), "SubmitDialogFragment");
        }
        });

    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        dialog.dismiss();
        String firtsname=Objects.requireNonNull(mFirstName.getText()).toString().trim();
        String lastname=Objects.requireNonNull(mLastName.getText()).toString().trim();
        String email = Objects.requireNonNull(mEmail.getText()).toString().trim();
        String gitlink=Objects.requireNonNull(mLink.getText()).toString().trim();
        if (!email.isEmpty() && !firtsname.isEmpty() && !lastname.isEmpty() && gitlink != null){
            SubmitService submitService= ServiceBuilder.buildService(SubmitService.class);
            Call<Void> submitRequest=submitService.creatOwner(email,firtsname,lastname,gitlink);
            submitRequest.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    //Toast.makeText(SubmissionActivity.this, "succes"+response.code(), Toast.LENGTH_SHORT).show();
                    SuccesDialog succesDialog=new SuccesDialog();
                    succesDialog.show(getSupportFragmentManager(),"SuccesDialog");
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    //Toast.makeText(SubmissionActivity.this, "error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                    ErrorDialog errorDialog=new ErrorDialog();
                    errorDialog.show(getSupportFragmentManager(),"ErrorDialog");

                }
            });


        }
        else {
            Toast.makeText(this, "Fill all the form", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.dismiss();

    }
}