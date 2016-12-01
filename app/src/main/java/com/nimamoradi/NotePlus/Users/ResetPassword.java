package com.nimamoradi.NotePlus.Users;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.backtory.androidsdk.HttpStatusCode;
import com.backtory.androidsdk.internal.BacktoryCallBack;
import com.backtory.androidsdk.model.BacktoryResponse;
import com.backtory.androidsdk.model.BacktoryUser;
import com.nimamoradi.NotePlus.R;

public class ResetPassword extends AppCompatActivity {

    private static final String TAG = "NotePlus.Users";
    // Getting old and new password
    String oldPassword = "OLD-PASSWORD";
    String newPassword = "NEW-PASSWORD";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);




    }
private String UserEmail;
    private String code;
    public void restPasss(View view) {
        EasyMail mail = new EasyMail(getString(R.string.emailAdd), getString(R.string.pass));
        String[] directionsToSend = {UserEmail};
        mail.setTo(directionsToSend);
        mail.setFrom(getString(R.string.emailAdd));
        mail.setSubject("Password change Reqest ");
        mail.setBody(getString(R.string.PasswordRestReqest).replace("?",code));
    }
    private void restPassword(){// Requesting change password to backtory
        BacktoryUser.getCurrentUser().changePasswordInBackground(oldPassword, newPassword,
                new BacktoryCallBack<Void>() {

                    // Operation done (success or fail), handling it:
                    @Override
                    public void onResponse(BacktoryResponse<Void> response) {
                        if (response.isSuccessful()) {
                            // Password changed successfully
                            Log.d(TAG, "Your password successfully changed");
                        } else if (response.code() == HttpStatusCode.Forbidden.code()) {
                            // value "OLD-PASSWORD" is not your real old password
                            Log.d(TAG, "Old password was incorrect");
                        } else {
                            // Operation generally failed, maybe internet connection issue
//                            Log.d("request failed");
                        }
                    }
                });
    }
}
