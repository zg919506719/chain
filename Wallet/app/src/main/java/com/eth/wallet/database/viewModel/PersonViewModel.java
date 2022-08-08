package com.eth.wallet.database.viewModel;

import android.app.Application;

import com.eth.wallet.database.bean.Person;
import com.eth.wallet.database.repository.PersonRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

public class PersonViewModel extends AndroidViewModel {

    private final LiveData<List<Person>> allPeople;
    private PersonRepository personRepository;

    public PersonViewModel(@NonNull Application application) {
        super(application);
        personRepository=new PersonRepository(application);
        allPeople = personRepository.getAllPeople();
    }

    public LiveData<List<Person>> getAllPeople(){
        return allPeople;
    }

    public void insertPerson(Person person){
        personRepository.insert(person);
    }
}
