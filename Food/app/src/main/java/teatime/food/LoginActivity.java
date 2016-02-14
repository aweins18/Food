package teatime.food;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.app.Activity;

import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };

    // UI references.
    private EditText mUserView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button buttonSI = (Button) findViewById(R.id.sign_in_button);
        final Button buttonR = (Button) findViewById(R.id.register_button);
        final Button submit_button = (Button) findViewById(R.id.submit_button);

        final EditText user = (EditText) findViewById(R.id.user);
        final EditText pass = (EditText) findViewById(R.id.password);
        final EditText first = (EditText) findViewById(R.id.firstname);
        final EditText last = (EditText) findViewById(R.id.lastname);

        buttonSI.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                user.setVisibility(View.VISIBLE);
                user.setHint("Username");
                pass.setVisibility(View.VISIBLE);
                pass.setHint("Password");
                submit_button.setText("Sign In");
                submit_button.setVisibility(View.VISIBLE);
                buttonSI.setVisibility(View.INVISIBLE);
                buttonR.setVisibility(View.INVISIBLE);
            }
        });

        buttonR.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                user.setVisibility(View.VISIBLE);
                user.setHint("Username");
                pass.setVisibility(View.VISIBLE);
                pass.setHint("Password");
                first.setVisibility(View.VISIBLE);
                first.setHint("First Name");
                last.setVisibility(View.VISIBLE);
                last.setHint("Last Name");
                submit_button.setText("Register");
                submit_button.setVisibility(View.VISIBLE);
                buttonSI.setVisibility(View.INVISIBLE);
                buttonR.setVisibility(View.INVISIBLE);
            }
        });

        submit_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        
    }


    /**
     @Override public void onStart() {
     super.onStart();

     // ATTENTION: This was auto-generated to implement the App Indexing API.
     // See https://g.co/AppIndexing/AndroidStudio for more information.
     client.connect();
     Action viewAction = Action.newAction(
     Action.TYPE_VIEW, // TODO: choose an action type.
     "Login Page", // TODO: Define a title for the content shown.
     // TODO: If you have web page content that matches this app activity's content,
     // make sure this auto-generated web page URL is correct.
     // Otherwise, set the URL to null.
     Uri.parse("http://host/path"),
     // TODO: Make sure this auto-generated app deep link URI is correct.
     Uri.parse("android-app://teatime.food/http/host/path")
     );
     AppIndex.AppIndexApi.start(client, viewAction);
     }

     @Override public void onStop() {
     super.onStop();

     // ATTENTION: This was auto-generated to implement the App Indexing API.
     // See https://g.co/AppIndexing/AndroidStudio for more information.
     Action viewAction = Action.newAction(
     Action.TYPE_VIEW, // TODO: choose an action type.
     "Login Page", // TODO: Define a title for the content shown.
     // TODO: If you have web page content that matches this app activity's content,
     // make sure this auto-generated web page URL is correct.
     // Otherwise, set the URL to null.
     Uri.parse("http://host/path"),
     // TODO: Make sure this auto-generated app deep link URI is correct.
     Uri.parse("android-app://teatime.food/http/host/path")
     );
     AppIndex.AppIndexApi.end(client, viewAction);
     client.disconnect();
     }
     */
}

