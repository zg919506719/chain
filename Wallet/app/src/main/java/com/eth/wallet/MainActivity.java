package com.eth.wallet;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.eth.wallet.bean.Task;
import com.eth.wallet.bean.TaskStatus;
import com.eth.wallet.databinding.ActivityMainBinding;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.setOnClick(new ClickAction());
        XXPermissions.with(this)
                // 申请单个权限
                .permission(Permission.WRITE_EXTERNAL_STORAGE)
                .permission(Permission.READ_EXTERNAL_STORAGE)
                // 申请多个权限
                .permission(Permission.Group.STORAGE)
                // 设置权限请求拦截器（局部设置）
                //.interceptor(new PermissionInterceptor())
                // 设置不触发错误检测机制（局部设置）
                //.unchecked()
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        if (all) {
                            Timber.d("获取权限成功");
                        } else {
                            Timber.d("获取部分权限成功，但部分权限未正常授予");
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean never) {
                        if (never) {
                            Toast.makeText(MainActivity.this, "被永久拒绝授权，请手动授予", Toast.LENGTH_SHORT).show();
                            // 如果是被永久拒绝就跳转到应用权限系统设置页面
                            XXPermissions.startPermissionActivity(MainActivity.this, permissions);
                        } else {
                            Timber.d("获取录音和日历权限失败");
                        }
                    }
                });

        executorService = Executors.newFixedThreadPool(2);
    }



    private Realm initRealm() {
        String realmName = "Wallet";
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().name(realmName).build();

        return Realm.getInstance(realmConfiguration);
    }

    // all modifications to a realm must happen inside of a write block
    private void addTask(Realm instanceRealm) {
        Task new_task = new Task("New Task");
        instanceRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm transactionRealm) {
                transactionRealm.insertOrUpdate(new_task);
            }
        });
    }

    private void showTask(Realm instanceRealm) {
        instanceRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Task> Tasks = realm.where(Task.class).findAll();
                // you can also filter a collection
                RealmResults<Task> TasksThatBeginWithN = Tasks.where().beginsWith("name", "N").findAll();
                RealmResults<Task> openTasks = Tasks.where().equalTo("status", TaskStatus.Open.name()).findAll();
                String name = Tasks.get(0).getName();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,name, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    public class ClickAction {
        public void add(View view) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    addTask(initRealm());
                }
            });

        }


        public void show(View view) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {

                    showTask(initRealm());
                }
            });
        }


    }

}