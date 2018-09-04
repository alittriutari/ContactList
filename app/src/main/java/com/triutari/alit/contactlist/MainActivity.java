package com.triutari.alit.contactlist;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.triutari.alit.contactlist.Database.ContactDao;
import com.triutari.alit.contactlist.Database.ContactDatabase;
import com.triutari.alit.contactlist.Database.ContactEntity;
import com.triutari.alit.contactlist.Database.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

    ContactAdapter contactAdapter;
    RecyclerView recyclerView;
    private List<ContactEntity> contactList = new ArrayList<>();
    ContactDao mDao;
    ContactEntity entity;
    ContactDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddData();
            }
        });

        SetupList();
    }

    void SetupList(){

//        final ContactDatabase db = Room.databaseBuilder(getApplicationContext(), ContactDatabase.class, "contact-database")
//                .fallbackToDestructiveMigration()
//                .build();
        db = ContactDatabase.getContactDatabase(getApplicationContext());
        List<ContactEntity> contactList = db.contactDao().getAllContact();

        //cari Id Dari RecylerView menggunakan findViewById agar dari XMl dan java terhubung
        recyclerView = findViewById(R.id.recyclerview);

        //setLayoutManager dari RecylerView.
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        //setting adapter dan set ke recyclerview
        contactAdapter = new ContactAdapter(contactList);
        recyclerView.setAdapter(contactAdapter);
        contactAdapter.notifyDataSetChanged(); //pemberitahuan bahwa ada data yang masuk

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        DeleteData();
                    }

                }));
    }

    void AddData(){
        startActivity(new Intent(MainActivity.this, AddData.class));
    }


    void DeleteData(){
//        final ContactDatabase db = Room.databaseBuilder(getApplicationContext(), ContactDatabase.class, "contact-database")
//                .fallbackToDestructiveMigration()
//                .build();
        db = ContactDatabase.getContactDatabase(getApplicationContext());
        db.contactDao().delete(entity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
