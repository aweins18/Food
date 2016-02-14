package teatime.food;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


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

    Client client = new Client();

//    Runnable refreshRunnable = new Runnable() {
//        public void run() {
//            try {
//                client.refresh();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//    };

    Runnable refreshRunnable = new Runnable() {
        public void run() {
            try {
                client.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    };


    // UI references.
    private EditText mUserView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    public boolean oneOfUs = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(refreshRunnable, 0, 30, TimeUnit.SECONDS);
        try{client.connectToServer();System.out.println("Past connect");}catch(IOException e){System.out.println(e);};

        final Button buttonSI = (Button) findViewById(R.id.sign_in_button);
        final Button buttonR = (Button) findViewById(R.id.register_button);
        final Button submit_button = (Button) findViewById(R.id.submit_button);

        final EditText user = (EditText) findViewById(R.id.user);
        final EditText pass = (EditText) findViewById(R.id.password);
        final EditText pass2 = (EditText) findViewById(R.id.password2);
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
                oneOfUs = true;
            }
        });

        buttonR.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                user.setVisibility(View.VISIBLE);
                user.setHint("Username");
                pass.setVisibility(View.VISIBLE);
                pass.setHint("Password");
                pass2.setVisibility(View.VISIBLE);
                pass2.setHint("Confirm Password");
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

        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
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
        EditText user = (EditText) findViewById(R.id.user);
        EditText pass = (EditText) findViewById(R.id.password);
        EditText pass2 = (EditText) findViewById(R.id.password2);
        EditText first = (EditText) findViewById(R.id.firstname);
        EditText last = (EditText) findViewById(R.id.lastname);

        if(oneOfUs){
            //Sign In
            boolean log = false;
            try{
                log = client.login();
            }catch(IOException e){
                System.out.print(e);
            };
            if(log){
                try{client.setUp();}catch(IOException e){System.out.print(e);};
            }


        } else {
            //Register
            if(!pass.getText().toString().equals("") && !user.getText().toString().equals("") && !first.getText().toString().equals("") && !last.getText().toString().equals("")) {
                if(pass.getText().toString().equals(pass2.getText().toString())) {
                    try {
                        if (!client.signup(user.getText().toString(), pass.getText().toString(), first.getText().toString(), last.getText().toString()))
                            showSimplePopUp("Commas ' , ' are not allowed");
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                } else showSimplePopUp("Passwords don't match, please try again");
            } else showSimplePopUp("Please fill in all fields");
        }
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


    private void showSimplePopUp(String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                })
                .show();
        }


}

