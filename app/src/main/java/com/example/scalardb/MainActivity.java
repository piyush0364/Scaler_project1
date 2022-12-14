package com.example.scalardb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.scalardb.data.MyDbHandler;
import com.example.scalardb.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDbHandler db = new MyDbHandler(MainActivity.this);

//        Contact anmol = new Contact();
//        anmol.setName("anmol");
//        anmol.setStart_time("1:00");
//        anmol.setEnd_time("4:00");
//
//        db.addContact(anmol);
//
//        Contact aviral = new Contact();
//        aviral.setName("Aviral");
//        aviral.setStart_time("4:00");
//        aviral.setEnd_time("6:00");
//
//        db.addContact(aviral);
//
//        Contact aqsa = new Contact();
//        aqsa.setName("Aqsa");
//        aqsa.setStart_time("6:00");
//        aqsa.setEnd_time("8:00");
//
//        db.addContact(aqsa);


        ArrayList<String> contacts = new ArrayList<>();
        listView = findViewById(R.id.listView);
        //Getting all entries
        List<Contact> allContacts = db.getAllContacts();
        for (Contact contact: allContacts){
            Log.d("list", "Id: "+ contact.getId() + "\n" +
                    "Name: "+ contact.getName() + "\n" +
                    "start: "+ contact.getStart_time() + "\n" +
                    "end: "+ contact.getEnd_time());
            contacts.add(contact.getId() + "\n" + contact.getName() + "\n" + "Start Time: " + contact.getStart_time() + "\n" + "End Time: " + contact.getEnd_time());
//            db.deleteContactById(contact.getId());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                String item = (String) listView.getItemAtPosition(position);
                String s = "" + item.charAt(0);
                int a = Character.getNumericValue(item.charAt(0));

                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you want to delete?")
                        .setTitle("Delete Meeting");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        db.deleteContactById(a);
                        contacts.clear();
                        arrayAdapter.notifyDataSetChanged();
                        List<Contact> allContacts = db.getAllContacts();
                        for (Contact contact: allContacts){
                            contacts.add(contact.getId() + "\n" + contact.getName() + "\n" + "Start Time: " + contact.getStart_time() + "\n" + "End Time: " + contact.getEnd_time());
                        }
//                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
                        listView.setAdapter(arrayAdapter);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        Log.d("Number of Entries: ", ""+db.getCount());
    }
}