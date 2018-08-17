package com.example.fxb.realobs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by fxb on 2017/8/24.
 */
public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.MsgViewHolder> {

    private Context context;
    private List<com.example.fxb.realobs.MsgBean> dataList;


    public MsgAdapter(Context context, List<com.example.fxb.realobs.MsgBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public MsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MsgViewHolder(LayoutInflater.from(context).inflate(R.layout.msg_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MsgViewHolder holder, int position) {
        holder.content.setText(dataList.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class MsgViewHolder extends RecyclerView.ViewHolder {

        private TextView content;

        public MsgViewHolder(View itemView) {

            super(itemView);
            content = (TextView) itemView.findViewById(R.id.msg_content);


        }
    }
}
