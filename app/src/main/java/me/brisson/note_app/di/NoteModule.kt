package me.brisson.note_app.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.brisson.note_app.data.data_source.local.LocalNoteDataSource
import me.brisson.note_app.data.data_source.local.NoteDao
import me.brisson.note_app.data.data_source.local.NoteDatabase
import me.brisson.note_app.data.repository.NoteRepositoryImpl
import me.brisson.note_app.domain.data_source.NoteDataSource
import me.brisson.note_app.domain.repository.NoteRepository
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {

    @Qualifier
    annotation class NoteLocalDataSource

    @Qualifier
    annotation class NoteRemoteDataSource

    @Provides
    @Singleton
    fun providesNoteDatabase(@ApplicationContext app: Context) : NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            "note_database"
        ).build()
    }


    @Provides
    @Singleton
    fun providesNoteDao(db: NoteDatabase) : NoteDao {
        return db.noteDao()
    }

    @Provides
    @Singleton
    @NoteLocalDataSource
    fun providesLocalNoteDataSource(noteDao: NoteDao) : NoteDataSource {
        return LocalNoteDataSource(noteDao)
    }

    @Provides
    @Singleton
    fun providesNoteRepository(@NoteLocalDataSource localDataSource: NoteDataSource) : NoteRepository {
        return NoteRepositoryImpl(localDataSource)
    }
}

