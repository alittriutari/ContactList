package com.triutari.alit.contactlist.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

//nama tabel
@Entity (tableName = "tb_contact")
public class ContactEntity {

    //nama kolom
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_contact")
    public int mId;

    @ColumnInfo(name = "name")
    public String mName;

    @ColumnInfo(name = "number")
    public String mNumber;

    @ColumnInfo(name = "sim")
    public boolean mSim;

    public ContactEntity(){

    }

    public ContactEntity(String Name, String Number, boolean Sim) {
        this.mName = Name;
        this.mNumber = Number;
        this.mSim = Sim;
    }

    public ContactEntity(int Id, String Name, String Number, boolean Sim) {
        this.mName = Name;
        this.mNumber = Number;
        this.mSim = Sim;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public void setmSim(boolean mSim) {
        this.mSim = mSim;
    }

    public boolean ismSim() {
        return mSim;
    }

}
