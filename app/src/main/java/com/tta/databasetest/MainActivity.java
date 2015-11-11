package com.tta.databasetest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    ArrayList<String> names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        File nameFile = new File("names.txt");
        Scanner fileScanner= null;
        final PrintWriter fileWriter;
        try
        {
            fileScanner = new Scanner(nameFile);
            fileWriter = new PrintWriter(nameFile);

        }
        catch(FileNotFoundException s)
        {
            System.out.println("File does Not Exist Please Try Again: ");
        }

        while(fileScanner.hasNext())
        {
            names.add(fileScanner.next());
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button addButton = (Button) findViewById(R.id.add);
        Button remButton = (Button) findViewById(R.id.remove);
        Button viewButton = (Button) findViewById(R.id.view);
        final EditText txtAdd = (EditText) findViewById(R.id.add_text);
        final EditText txtRem = (EditText) findViewById(R.id.remove_text);
        //builder right here
        addButton.setOnClickListener(new OnClickListener()
        {
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Add");
                builder.setMessage("Add \"" + txtAdd.getText() + "\"?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        fileWriter.print(txtAdd.getText());
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog dialogAdd = builder.create();
                dialogAdd.show();
            }
        });

        remButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this); //makes the builder that formats the alert dialog
                //builder.setTitle("Add");
                builder.setMessage("Remove \"" + txtRem.getText() + "\"?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() //
                {
                    public void onClick(DialogInterface dialog, int id) {
                        System.out.println("YES");
                    }
                });

                builder.setNegativeButton("No", null);
                AlertDialog dialogRem = builder.create();
                dialogRem.show();
            }
        });
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
