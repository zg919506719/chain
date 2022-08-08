package com.eth.wallet.database.repository;

import android.app.Application;

import com.eth.wallet.database.AppDatabase;
import com.eth.wallet.database.PersonDao;
import com.eth.wallet.database.bean.Person;

import java.util.List;

import androidx.lifecycle.LiveData;

public class PersonRepository {
    private PersonDao personDao;
    private LiveData<List<Person>> mPersons;

    public PersonRepository(Application application) {
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        this.personDao = appDatabase.personDao();
        this.mPersons = personDao.getAllPeople();
    }

    public LiveData<List<Person>> getAllPeople(){
        return mPersons;
    }

    public void insert(Person person){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                personDao.insertAll(person);
            }
        });
    }
}

