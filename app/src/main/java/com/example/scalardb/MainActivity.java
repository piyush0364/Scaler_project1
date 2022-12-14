package com.example.scalardb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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


        Log.d("Number of Entries: ", ""+db.getCount());
    }
}