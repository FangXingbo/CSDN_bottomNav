package com.example.fxb.realobs;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * 任务Frag
 */
public class TaskFragment extends Fragment implements View.OnClickListener {
    private Button btn;
    private TextView tv;
    private View view;
    private String info;
    private static Handler handler=new Handler();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_task, null);
        btn=(Button) view.findViewById(R.id.button);
        tv=(TextView) view.findViewById(R.id.text) ;
        btn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        //创建子线程
        new Thread(new MyThread("a", "123", com.example.fxb.realobs.Url.urlLogin)).start();
    }

    //子线程接受数据，主线程修改数据
    public class MyThread implements Runnable {
        private String url;
        private String userN;
        private String passW;
        @Override
        public void run() {
            //GET方式获取数据
            info = com.example.fxb.realobs.WebService.executeHttpGet(userN,passW,url);
            System.out.println("*****"+info);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    tv.setText(info);

                }
            });
        }
        //往线程里传参
        public MyThread(String userN,String passW,String url)
        {
            this.userN=userN;
            this.passW=passW;
            this.url=url;
        }

    }
}
