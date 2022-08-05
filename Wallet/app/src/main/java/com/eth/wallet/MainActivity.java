package com.eth.wallet;

import android.os.Bundle;
import android.security.identity.PersonalizationData;
import android.view.View;
import android.widget.Toast;

import com.eth.wallet.databinding.ActivityMainBinding;
import com.eth.wallet.database.AppDatabase;
import com.eth.wallet.database.bean.Person;
import com.eth.wallet.database.PersonDao;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.room.Room;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private PersonDao personDao;

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setOnClick(new ClickAction());

        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "wallet-data").build();

        personDao = appDatabase.personDao();



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
            mDisposable.add(personDao.insertAll(person).andThen(personDao.getAll())
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<Person>>() {
                        @Override
                        public void accept(List<Person> people) throws Exception {
                            for (Person person :
                                    people) {
                                Timber.d(person.toString());
                            }

                            Toast.makeText(MainActivity.this,people.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Timber.e(throwable);
                            Toast.makeText(MainActivity.this,throwable.toString(),Toast.LENGTH_SHORT).show();

                        }
                    }));
        }


        public void show(View view) {

        }


    }

}