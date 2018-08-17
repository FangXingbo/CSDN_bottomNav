package com.example.fxb.realobs;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import static com.example.fxb.realobs.R.id.show1;


/**
 * 设置frag
 */
public class NoticeFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Button btnLine;
    private Button btnBar;
    private LineChart mChart;
    private BarChart barChart;
    private TextView show;
    private Handler handler = new Handler();
    String as;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_set, null);
        show = (TextView) view.findViewById(show1);

       // btnLine = (Button) view.findViewById(R.id.btn_Line);
        //btnLine.setOnClickListener(this);

        btnBar = (Button) view.findViewById(R.id.btn_Bar);
        btnBar.setOnClickListener(this);

        //柱状图
        barChart = (BarChart) view.findViewById(R.id.barChart);
        barChart.setNoDataTextDescription("双击更新后点此处");

        //折线图
        mChart = (LineChart) view.findViewById(R.id.mChart);
        mChart.setDescription("");
        mChart.setNoDataTextDescription("双击更新后点此处");
        mChart.setHighlightPerDragEnabled(true);
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.setPinchZoom(true);
        mChart.setBackgroundColor(Color.LTGRAY);
//        LineData data = new LineData();
//        data.setValueTextColor(Color.WHITE);
//        mChart.setData(data);
//        Legend l = mChart.getLegend();
//        l.setForm(Legend.LegendForm.LINE);
//        l.setTextColor(Color.WHITE);
        XAxis xl = mChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(true);
        xl.setDrawAxisLine(true);
        //xl.setAvoidFirstLastClipping(true);

        YAxis yl = mChart.getAxisLeft();
        yl.setTextColor(Color.WHITE);
       // yl.setAxisMaxValue(40.00f);//设置Y轴最大值
        yl.setDrawGridLines(true);

        YAxis yl2 = mChart.getAxisRight();
        yl2.setEnabled(false);
         //新增
        mChart.animateX(750);
        return view;
    }

    @Override
    public void onClick(View view) {
        //改动
        new Thread(new MyThread1("a", "123",Url.urlSearch)).start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        String aa[] = show.getText().toString().split(",;,");
        String atime[] = aa[0].split(",");
        String adata[] = aa[1].split(",");
        float a1[] = new float[adata.length];
        for (int i = 0; i < adata.length; i++) {
            a1[i] = Float.parseFloat(adata[i]);
        }

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        new Thread(new MyThread2(1,atime, a1)).start();
        addBardata(atime,a1);
//        switch (view.getId()) {
//            case R.id.btn_Line:


//                String aa[] = show.getText().toString().split(",;,");
//                String atime[] = aa[0].split(",");
//                String adata[] = aa[1].split(",");
//                float a1[] = new float[adata.length];
//                for (int i = 0; i < adata.length; i++) {
//                    a1[i] = Float.parseFloat(adata[i]);
//                }


//                new Thread(new MyThread2(1,atime, a1)).start();
//                addBardata(atime,a1);
//                break;
//            case R.id.btn_Bar:
////                new Thread(new MyThread2(1,atime, a1)).start();
////                addBardata(atime,a1);
//                break;
//        }

    }

    //子线程接受数据，主线程修改数据
    public class MyThread2 implements Runnable {
        private float[] y;
        private String[] x;
        private  int a;
        @Override
        public void run() {
           // mChart.removeAllViews();
            //mChart.getLineData().clearValues();
//            for (int i = 0; i < y.length; i++) {
//                addEntry(x[i], y[i]);
//                try {
//                    Thread.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            mChart.setData( generateDataLine(a,x,y));
        }

        //往线程里传参
        public MyThread2(int a,String[] x, float[] y) {
            this.a=a;
            this.x = x;
            this.y = y;

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        new Thread(new MyThread1("a", "123", com.example.fxb.realobs.Url.urlSearch)).start();
    }

    //添加柱状图数据
    public void addBardata(String[] x,float[] y)
    {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for(int i=0;i<y.length;i++)
        {
           barEntries.add(new BarEntry(y[i], i));
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "变形监测数据");
        BarData theData = new BarData(x, barDataSet);
        XAxis xl = barChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.setData(theData);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
    }

    //动态添加曲线图Entry方法，暂未使用
    private void addEntry(String x, float y) {

        LineData data = mChart.getData();
        if (data != null) {
            LineDataSet set = data.getDataSetByIndex(0);
            if (set == null) {
                set = createSet();
                data.addDataSet(set);

            }
            data.addXValue(x);//设置X轴值
            //Y轴的值
            data.addEntry(new Entry(y, set.getEntryCount()), 0);

            mChart.notifyDataSetChanged();

            mChart.setVisibleXRange(6, 10);

            mChart.moveViewToX(data.getXValCount() - 7);
        }
    }
     //暂未使用
    private LineDataSet createSet() {
        LineDataSet set = new LineDataSet(null, "SPL Db");
        set.setDrawCubic(true);
        set.setCubicIntensity(0.2f);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(ColorTemplate.getHoloBlue());
        set.setCircleColor(ColorTemplate.getHoloBlue());
        set.setLineWidth(2f);
        set.setCircleSize(4f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244, 117, 177));
        set.setValueTextColor(Color.WHITE);
        set.setValueTextSize(10f);
        return set;
    }


    //子线程接受数据，主线程修改数据
    public class MyThread1 implements Runnable {
        private String url;
        private String userN;
        private String passW;

        @Override
        public void run() {
            //GET方式获取数据
            as = com.example.fxb.realobs.WebService.executeHttpGet(userN, passW, url);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    show.setText(as);
                }
            });
        }

        //往线程里传参
        public MyThread1(String userN, String passW, String url) {
            this.userN = userN;
            this.passW = passW;
            this.url = url;
        }

    }
    //新增
    private LineData generateDataLine(int cnt,String[] x,float[] y) {

        ArrayList<Entry> e1 = new ArrayList<Entry>();


        for (int i = 0; i < y.length; i++) {
            e1.add(new Entry(y[i], i));
        }


        LineDataSet d1 = new LineDataSet(e1, "变形监测数据" + cnt + ", (1)mm");
            /*   d1.setLineWidth(2.5f);
            d1.setCircleSize(4.5f);*/

        d1.setLineWidth(2.5f);
        d1.setCircleSize(5.5f);//折线的圆点大小
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(true);

        ArrayList<Entry> e2 = new ArrayList<Entry>();

        for (int i = 0; i < y.length; i++) {
            e2.add(new Entry(e1.get(i).getVal() - 30, i));
        }

        ArrayList<LineDataSet> sets = new ArrayList<LineDataSet>();
        sets.add(d1);

        //这个图标坐标显示对应的坐标是按照顺序对应的，第一个y对应第一个x,这么来的
//        ArrayList<String> m = new ArrayList<String>();
//        m.add("9:00:37");
//        m.add("9:30:28");
//        m.add("10:00");
//        m.add("10:30");
//        m.add("May");
//        m.add("Jun");
//        m.add("12:00:59");
//        m.add("Aug");
//        m.add("Sep");
//        m.add("Okt");
//        m.add("Nov");
//        m.add("Dec");
        LineData cd = new LineData(x, sets);
        return cd;
    }
}
