package com.example.fxb.realobs;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
/**
 * 消息Frag
 */
public class MsgFragment extends Fragment {
    private View msg_frag;
    private RecyclerView msg_recv;
    private List<MsgBean> dataList = new ArrayList<>();
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        msg_frag = inflater.inflate(R.layout.fragment_msg, container, false);
        innitView();
        showData();
        context=msg_frag.getContext();
        return msg_frag;
    }

    private void innitView() {
        msg_recv = (RecyclerView) msg_frag.findViewById(R.id.msg_recv);
        msg_recv.setLayoutManager(new LinearLayoutManager(context));
    }

    private void showData() {
        MsgBean msgBean=new MsgBean();

        for (int i=1;i<6;i++){
            msgBean.setContent("10月"+i+"号,2号点沉降异常");
            dataList.add(msgBean);
        }
        MsgAdapter adapter=new MsgAdapter(msg_frag.getContext(),dataList);
        msg_recv.setAdapter(adapter);
    }


}
