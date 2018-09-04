package com.triutari.alit.contactlist.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM tb_contact")
    List<ContactEntity> getAllContact();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ContactEntity contactEntity);

    @Update
    void update(ContactEntity contactEntity);

    @Delete
    void delete(ContactEntity contactEntity);
}
