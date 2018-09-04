package com.triutari.alit.contactlist;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.triutari.alit.contactlist.Database.ContactDatabase;
import com.triutari.alit.contactlist.Database.ContactEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddData extends AppCompatActivity {

    private static final String TAG = "Add Data";

    RadioGroup checkedRadioButton = null;

    @BindView(R.id.evNama)
    EditText tambahNama;
    @BindView(R.id.evNumber)
    EditText tambahNomer;
    @BindView(R.id.rbSim1)
    RadioButton radioSim1;
    @BindView(R.id.rbSim2)
    RadioButton radioSim2;
    @BindView(R.id.imageView)
    ImageView imageProfile;
    @BindView(R.id.btnSimpan)
    Button btnSimpan;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    ContactDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        ButterKnife.bind(this);

//        final ContactDatabase db = Room.databaseBuilder(getApplicationContext(), ContactDatabase.class, "contact-database")
//                .fallbackToDestructiveMigration()
//                .build();

        db = ContactDatabase.getContactDatabase(getApplicationContext());

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Data disimpan");

                String nama = tambahNama.getText().toString();
                String nomer = tambahNomer.getText().toString();
                Boolean radio = ValueRadioButton(radioGroup);

                db.contactDao().insert(new ContactEntity(nama, nomer, radio));
                startActivity(new Intent(AddData.this, MainActivity.class));
            }
        });
    }

    Boolean ValueRadioButton(RadioGroup rg){
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        View radioButton = radioGroup.findViewById(radioButtonId);
        int idx = rg.indexOfChild(radioButton);
        RadioButton r = (RadioButton) rg.getChildAt(idx);
        String selectedtext = r.getText().toString();
        return (selectedtext.equals(getResources().getString(R.string.rb1)));
    }
}
