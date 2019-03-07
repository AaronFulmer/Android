package com.fulmer.aar.login;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends AppCompatActivity {

    public static DatabaseHelper sqLiteDB = Login.sqLiteDB;
    public static String username;
    public static String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor cursor = sqLiteDB.getName(username, password);
        cursor.moveToFirst();
        int column = cursor.getColumnIndex(sqLiteDB.getEmployeeFirstName());
        String name = cursor.getString(column);
        //byte[] barray = cursor.getBlob(column);
        //String name = "";
        //for(int a = 0; a < barray.length; a++){
        //    name += barray[a];
        //}
        final TextView label = findViewById(R.id.textView2);
        label.setText("Hello, " + name);

        final Button button = findViewById(R.id.button);
        final Button button1 = findViewById(R.id.btnProceed);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }

        });

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), checkOutDvd.class);
                startActivity(intent);
            }
        });

    }
}
