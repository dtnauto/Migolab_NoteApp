package com.example.migolab_noteapp.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.migolab_noteapp.database.dao.NoteDao
import com.example.migolab_noteapp.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object{
        @Volatile //bien phia sau se nam trong bo nho va khi biến có bất kì sự thay đổi ở bất kì luồng nào thì đều có thể thấy sự thay đổi đó
        private var instance: NoteDatabase?= null
        fun getInstance(context: Context): NoteDatabase{

            if (instance==null){
                synchronized(this){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        "NoteDatabase"
                    )
                        .allowMainThreadQueries()
//                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance!! //khởi tao doi tuong
        }
    }
}