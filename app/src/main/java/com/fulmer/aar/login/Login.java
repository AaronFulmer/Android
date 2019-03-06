package com.fulmer.aar.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.content.Context;


public class Login extends AppCompatActivity {

    public static DatabaseHelper sqLiteDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sqLiteDB = new DatabaseHelper(this);
        sqLiteDB.addDefaultUserData();
        final Button button = findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                final TextView usernameTextBox = findViewById(R.id.usernameText);
                final TextView passwordTextBox = findViewById(R.id.passwordText);
                String username = usernameTextBox.getText().toString();
                String password = passwordTextBox.getText().toString();



                Cursor cursor = sqLiteDB.getName(username, password);
                if(cursor.getCount() > 0) {
                    Main.username = username;
                    Main.password = password;
                    Intent intent = new Intent(v.getContext(), Main.class);
                    startActivity(intent);
                }

                /*
                if(username.equals("admin") && password.equals("password")){
                    Intent intent = new Intent(v.getContext(), Main.class);
                    startActivity(intent);
                }
                */
                else if (username.isEmpty() || password.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setMessage("ERROR: Please make sure both fields have been filled").setTitle("ERROR");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked OK button
                        }
                    });
                    builder.create().show();
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    // Add the buttons
                    builder.setMessage("ERROR: Username or Password incorrect").setTitle("Error");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked OK button
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }
        });
    }
}
