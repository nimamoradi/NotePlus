package com.nimamoradi.NotePlus.Users;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.backtory.androidsdk.HttpStatusCode;
import com.backtory.androidsdk.internal.BacktoryCallBack;
import com.backtory.androidsdk.model.BacktoryResponse;
import com.backtory.androidsdk.model.BacktoryUser;
import com.nimamoradi.NotePlus.R;

import static android.content.ContentValues.TAG;

/**
 * Created by Nima on 11/25/2016.
 *
 * control sign up process in background asynchronously
 */

public class SignUp extends AsyncTask<String,Void,String> {
    private Context context;
    //responses
    public static final String ResponseIsSucessfull="Register Success";
    public static final String InvalidUserName="Bad username";
    public static final String NoNetwork="Registeration failed";

    public SignUp(Context context) {
        this.context = context;
    }
/**fisrt params is name, family name , user name , email, password,phone Number*/
    @Override
    protected String doInBackground(String... params) {
        // First create a user and fill his/her data
        BacktoryUser newUser = new BacktoryUser.
                Builder().
                setFirstName(params[0]).
                setLastName(params[1]).
                setUsername(params[2]).
                setEmail(params[3]).
                setPassword(params[4]).
                setPhoneNumber(params[5])
                .build();
        final String[] respond = new String[1];
// Registering user to backtory (in background)
   //     newUser.register();
        newUser.registerInBackground(new BacktoryCallBack<BacktoryUser>() {

            // Register operation done (fail or success), handling it:
            @Override
            public void onResponse(BacktoryResponse<BacktoryUser> response) {
                // Checking result of operation
                if (response.isSuccessful()) {
                    // Successful
                    Log.d(TAG, "Register Success: new username is " + response.body().getUsername());
                    respond[0] =ResponseIsSucessfull;
                } else if (response.code() == HttpStatusCode.Conflict.code()) {
                    // Username is invalid
                    Log.d(TAG, "Bad username: a user with this username already exist");
                  respond[0] =ResponseIsSucessfull;
                } else {
                    // General failure
                    Log.d(TAG, "Registeration failed, for network or some other reasons"+response.message());
                    respond[0] =NoNetwork;
                }
            }
        });


        return respond[0];
}

    @Override
    protected void onPostExecute(String respond) {

        super.onPostExecute(respond);
        if(respond!=null)
        switch(respond){
            case ResponseIsSucessfull:
                Toast.makeText(getContext(), respond, Toast.LENGTH_SHORT).show();
                break;
            case InvalidUserName:
                Toast.makeText(getContext(), respond, Toast.LENGTH_SHORT).show();
                break;
            case NoNetwork:
                Toast.makeText(getContext(), respond, Toast.LENGTH_SHORT).show();
                break;

        }
        else   Toast.makeText(getContext(), R.string.internetInavilable, Toast.LENGTH_SHORT).show();


    }

    private Context getContext() {
        return context;
    }
}
