package com.nimamoradi.NotePlus.Users;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.nimamoradi.NotePlus.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //


    }

    public void register(View view) {
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);
        EditText phone = (EditText) findViewById(R.id.PhoneNumber);
        EditText name = (EditText) findViewById(R.id.firstname);
        EditText familyname = (EditText) findViewById(R.id.familyname);
        EditText username = (EditText) findViewById(R.id.UserName);
        SignUp signUp = new SignUp(this);
        String[] edit = {
                name.getText().toString(),
                familyname.getText().toString(),
                username.getText().toString(),
                email.getText().toString(),
                password.getText().toString(),
                phone.getText().toString()};
        //progress dialog
        FragmentManager fm = getFragmentManager();
       dialog dialogFragment = new dialog ();
        dialogFragment.show(fm,"Wait");
 signUp.execute(edit);
dialogFragment.finsh();


    }

    public void resetPasswordActivity(View view) {
        startActivity(new Intent(this,ResetPassword.class));



    }


    private class dialog extends DialogFragment {


      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
          View rootView = inflater.inflate(R.layout.progress, container, false);
          getDialog().setTitle("wait");
          return rootView;
      }
      public  void finsh(){dismiss();}


  }
}
