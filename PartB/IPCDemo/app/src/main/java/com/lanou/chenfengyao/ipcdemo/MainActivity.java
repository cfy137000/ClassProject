package com.lanou.chenfengyao.ipcdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.lanou.chenfengyao.ipcdemo.file.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button byFileBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        byFileBtn = (Button) findViewById(R.id.by_file_btn);
        byFileBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.by_file_btn:
                try {
                    byFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    /**
     * 通过文件的形式
     */
    private void byFile() throws IOException {
        User user = new User(1,"张三",true);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("cache.text"));
        out.writeObject(user);
        out.close();
    }
}
