package com.triutari.alit.contactlist.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

@Database(entities = {ContactEntity.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "contact-database";
    private static ContactDatabase INSTANCE;
    public abstract ContactDao contactDao();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

        }
    };

    public static ContactDatabase getContactDatabase(Context context){
        if (INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context, ContactDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
