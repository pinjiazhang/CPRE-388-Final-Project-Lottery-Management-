package com.example.latest_lottery.Analysis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.example.latest_lottery.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

/**
 * Latest Analyzer to analyze the latest lottery appearance based on frequency
 *
 * @author  Pinjia Zhang
 * @version 1.0
 * @release   12/16/2021
 */

public class Latest_Analysis extends AppCompatActivity {
    BarChart barchart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_latest_analysis);

        barchart=findViewById(R.id.bar_chart);
        ArrayList<BarEntry> arrayList=new ArrayList<>();
        arrayList.add(new BarEntry(1,2));
        arrayList.add(new BarEntry(3,30));
        arrayList.add(new BarEntry(30,30));
        BarDataSet barDataSet=new BarDataSet(arrayList,"visitor");

        BarData b=new BarData(barDataSet);
        barchart.setFitBars(true);
        barchart.setData(b);
        barchart.getDescription().setText("Grand Lotto Distribution in 50 count");

    }
}