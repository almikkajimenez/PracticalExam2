
package com.jimenez.almikkamari;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity
{
    EditText firstname, lastname, examone, examtwo;
    String fname, lname, eone, etwo;
    TextView ave;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        examone = findViewById(R.id.examone);
        examtwo = findViewById(R.id.examtwo);
        ave = findViewById(R.id.ave);
    }

    public void display(View v)
    {
        fname = firstname.getText().toString();
        lname = lastname.getText().toString();
        eone = examone.getText().toString();
        etwo = examtwo.getText().toString();

        double average = Integer.parseInt(eone) + Integer.parseInt(etwo);
        average = average / 2;

        String stringAverage = Double.toString(average);

        File folder = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(folder, "SDcard.txt");

        String first = fname;
        String last = lname;

        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(file);
            fos.write(first.getBytes());
            fos.write(last.getBytes());
            fos.write(stringAverage.getBytes());
            Toast.makeText(this, "Your credentials are saved in your SD card!", Toast.LENGTH_SHORT);

        }
        catch (FileNotFoundException e)
        {
            Toast.makeText(this, "Error writing in SD card", Toast.LENGTH_LONG);
        }
        catch (IOException e)
        {
            Toast.makeText(this, "IO Exception", Toast.LENGTH_LONG);

        }

        SharedPreferences preference = getSharedPreferences("Data1", MODE_PRIVATE);
        String aver = preference.getString("stringAve",null);
        ave.setText(stringAverage);

}

}
