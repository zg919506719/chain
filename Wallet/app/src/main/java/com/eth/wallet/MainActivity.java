package com.eth.wallet;

import android.os.Bundle;
import android.security.identity.PersonalizationData;
import android.view.View;
import android.widget.Toast;

import com.eth.wallet.database.viewModel.PersonViewModel;
import com.eth.wallet.databinding.ActivityMainBinding;
import com.eth.wallet.database.AppDatabase;
import com.eth.wallet.database.bean.Person;
import com.eth.wallet.database.PersonDao;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private PersonViewModel personViewModel ;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setOnClick(new ClickAction());

        final MainAdapter adapter = new MainAdapter(new MainAdapter.PeopleDiff());
        mainBinding.listview.setAdapter(adapter);
        mainBinding.listview.setLayoutManager(new LinearLayoutManager(this));

        personViewModel = new ViewModelProvider(this).get(PersonViewModel.class);
        personViewModel.getAllPeople().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(List<Person> people) {
                adapter.submitList(people);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposable.clear();
    }

    public class ClickAction {
        public void add(View view) {
            Person person = new Person();
            person.setAge("asd");
            Timber.v(person.hashCode()+"");
            person.setName(person.hashCode()+"");
            personViewModel.insertPerson(person);
        }


        public void show(View view) {
            Toast.makeText(MainActivity.this, "+"+personViewModel.getAllPeople().getValue().size(), Toast.LENGTH_SHORT).show();
        }


    }

}