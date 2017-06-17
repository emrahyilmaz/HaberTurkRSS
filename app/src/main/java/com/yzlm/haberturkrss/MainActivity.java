package com.yzlm.haberturkrss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String[] categories =
            {"Ekonomi", "Spor","Siyaset","Tatil","İş Yaşam","Astroloji","Sağlık","Dünya","Gündem","Kültür-Sanat","Sinema","Teknoloji","Otomobil","Magazin","Kadın"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);

        getSupportActionBar().setCustomView(R.layout.activity_news);

        TextView actionbar_title = (TextView)getSupportActionBar().getCustomView().findViewById(R.id.actionBar_centered);
        actionbar_title.setText("İSMEK HABER");

        final ListView list=(ListView) findViewById(R.id.listView1);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, categories);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String value = list.getAdapter().getItem(position).toString();

                Intent intent = new Intent(MainActivity.this,NewsActivity.class);
                intent.putExtra("category",value);
                startActivity(intent);
            }
        });

    }
}
