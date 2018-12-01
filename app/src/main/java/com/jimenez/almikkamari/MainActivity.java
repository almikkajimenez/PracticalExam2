
package com.jimenez.almikkamari;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText firstname, lastname, examone, examtwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        examone = findViewById(R.id.examone);
        examtwo = findViewById(R.id.examtwo);
    }

    public void next(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void display(View v) {
        String fname = firstname.getText().toString();
        String lname = lastname.getText().toString();
        String examo = examone.getText().toString();
        String examt = examtwo.getText().toString();
        SharedPreferences sp = getSharedPreferences("Data1", MODE_PRIVATE);
        SharedPreferences.Editor writer = sp.edit();
        writer.putString("fname", fname);
        writer.putString("lname", lname);
        writer.putString("examone", examo);
        writer.putString("examtwo", examt);
        writer.commit();
        Toast.makeText(this, "Data saved...", Toast.LENGTH_LONG).show();

        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(folder, "external.txt");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(fname.getBytes());
            fos.write(lname.getBytes());
            fos.write(examo.getBytes());
            fos.write(examt.getBytes());
            Toast.makeText(this, "Data saved in sd card", Toast.LENGTH_LONG).show();
            fos.close();
        } catch (Exception e) {
            Toast.makeText(this, "Error writing on sd card", Toast.LENGTH_LONG).show();
        }

        try {
            fos = openFileOutput("Data2.txt", MODE_PRIVATE);
            fos.write(fname.getBytes());
            fos.write(lname.getBytes());
            fos.write(examo.getBytes());
            fos.write(examt.getBytes());
        } catch (Exception e) {
            Log.d("Error: ","Error writing to file..");
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Saved in "+getFilesDir(), Toast.LENGTH_LONG).show();

        
    }
}
