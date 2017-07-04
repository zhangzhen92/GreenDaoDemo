package com.example.lzc.greendaodemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.lzc.greendaodemo.adapter.ListViewAdapter;
import com.example.lzc.greendaodemo.dao.User;
import com.example.lzc.greendaodemo.dao.userdao.UserDao;

import java.util.ArrayList;
import java.util.List;




/**
 * 类描述：测试类
 * 创建人：zz
 * 创建时间：2017/2/14 10:53
 */
public class MainActivity extends Activity implements View.OnClickListener{
    private Button buttonAdd;
    private Button buttonDelete;
    private Button buttonUpdate;
    private Button buttonSelect;
    private ListView listViewId;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化UI
     */
    private void initView() {
        buttonAdd = ((Button) findViewById(R.id.button_add));
        buttonAdd.setOnClickListener(this);
        buttonDelete = ((Button) findViewById(R.id.button_delete));
        buttonDelete.setOnClickListener(this);
        buttonUpdate = ((Button) findViewById(R.id.button_update));
        buttonUpdate.setOnClickListener(this);
        buttonSelect = ((Button) findViewById(R.id.button_select));
        buttonSelect.setOnClickListener(this);
        listViewId = ((ListView) findViewById(R.id.listview_id));
        adapter = new ListViewAdapter(new ArrayList<User>(),getApplicationContext());
        listViewId.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        UserDao userDao = MyApplication.getInstance().getmDaoSession().getUserDao();
        switch (v.getId()){
            case R.id.button_add:
                for (int i = 0; i < 10; i++) {
                    User user = new User();
                    user.setName("张"+i);
                    user.setPassWord("123456");
                    userDao.insert(user);
                }
                select(userDao);
                break;
            case R.id.button_delete:
               // userDao.deleteAll();      删除所有的
                //删除所有名字为张2的
                List<User> userDeletes = userDao.queryBuilder().where(UserDao.Properties.Name.eq("张2")).build().list();
                userDao.deleteInTx(userDeletes);
                select(userDao);
                break;
            case R.id.button_update:
                //将张4的名字修改为张5
                List<User> searchUser = userDao.queryBuilder().where(UserDao.Properties.Name.eq("张4")).build().list();
                for (int i = 0; i < searchUser.size(); i++) {
                    User user = searchUser.get(i);
                    user.setName("张5");
                    userDao.update(user);
                }
                 select(userDao);
                break;
            case R.id.button_select:
                select(userDao);
                break;

        }
    }

    /**
     * 查询数据库中的数据
     * @param userDao
     */
    private void select(UserDao userDao) {
        adapter.clear();
        List<User> users = userDao.loadAll();
        adapter.addAll(users);
    }
}
