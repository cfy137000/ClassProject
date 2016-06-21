package com.example.chenfengyao.slidemenulistview.waytwo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.chenfengyao.slidemenulistview.R;

/**
 * Created by ChenFengYao on 15/12/10.
 */
public class TwoActivity extends Activity{
    private ListView listView;
    private TwoAdapter twoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        listView = (ListView) findViewById(R.id.lv_two);
        twoAdapter = new TwoAdapter(this);
        listView.setAdapter(twoAdapter);
    }
}
