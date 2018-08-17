package com.example.fxb.realobs;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ShowFragment extends Fragment implements View.OnClickListener {
    private Button btnSearch;
    private Button btnAdd;
    private Button btnDelete;
    private Button btnChange;
    private TextView txtShow;
    private EditText user;
    private EditText pass;
    private EditText level;
    private Handler handler=new Handler();
    private String ss;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_show, null);
        txtShow = (TextView) view.findViewById(R.id.show);

        user = (EditText) view.findViewById(R.id.username);
        pass = (EditText) view.findViewById(R.id.password);
        level = (EditText) view.findViewById(R.id.level);

        btnAdd = (Button) view.findViewById(R.id.adduser);
        btnDelete = (Button) view.findViewById(R.id.delete);
        btnChange = (Button) view.findViewById(R.id.change);
        btnSearch = (Button) view.findViewById(R.id.search);

        btnSearch.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnChange.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.adduser:
                new Thread(new MyThread2(user.getText().toString(),pass.getText().toString(),level.getText().toString(), com.example.fxb.realobs.Url.urlRegist)).start();
                break;
            case R.id.delete:
                new Thread(new MyThread2(user.getText().toString(),"","", com.example.fxb.realobs.Url.urlDelete)).start();
                break;
            case R.id.change:
                new Thread(new MyThread2(user.getText().toString(),pass.getText().toString(),"", com.example.fxb.realobs.Url.urlChange)).start();
                break;
            case R.id.search:
                //创建子线程
                new Thread(new MyThread1("a", "123", com.example.fxb.realobs.Url.urlSearch)).start();
                break;
        }
    }

    //子线程接受数据，主线程修改数据
    public class MyThread1 implements Runnable {
        private String url;
        private String userN;
        private String passW;
        @Override
        public void run() {
            //GET方式获取数据
            ss = com.example.fxb.realobs.WebService.executeHttpGet(userN,passW,url);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    txtShow.setText(ss);
                }
            });
        }
        //往线程里传参
        public MyThread1(String userN,String passW,String url)
        {
            this.userN=userN;
            this.passW=passW;
            this.url=url;
        }

    }

    //子线程接受数据，主线程修改数据
    public class MyThread2 implements Runnable {
        private String url;
        private String userN;
        private String passW;
        private String level;
        @Override
        public void run() {
            //GET方式获取数据
            ss = com.example.fxb.realobs.WebService.executeHttpGet(userN,passW,level,url);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    txtShow.setText(ss);
                }
            });
        }

        public MyThread2(String userN,String passW,String level,String url)
        {
            this.userN=userN;
            this.passW=passW;
            this.level=level;
            this.url=url;
        }

    }
}
