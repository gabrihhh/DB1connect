package br.com.fiap.db1connect.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.db1connect.model.Post

@Database(
    entities = [Post::class],
    version = 1
)
abstract class PostDb : RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object {
        private lateinit var instance: PostDb
        fun getDataBase(context: Context): PostDb {
            if (!::instance.isInitialized) {
                instance = Room.databaseBuilder(
                    context,
                    PostDb::class.java,
                    "post_db"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}
