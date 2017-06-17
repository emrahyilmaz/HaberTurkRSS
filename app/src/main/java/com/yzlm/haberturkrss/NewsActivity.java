package com.yzlm.haberturkrss;



import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Gravity;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements NewsResultListener{


    ArrayList<News> dataset;
    NewsAdapter adapter;
     RecyclerView rvNews;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Bundle extras = getIntent().getExtras();
        String value = extras.getString("category");
        //textView = (TextView)findViewById(R.id.textView);
        //textView.setText(value);

        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);

        getSupportActionBar().setCustomView(R.layout.activity_news);

        TextView actionbar_title = (TextView)getSupportActionBar().getCustomView().findViewById(R.id.actionBar_centered);
        actionbar_title.setText(value);
        //setTitle(value);


        rvNews = (RecyclerView) findViewById(R.id.rvNews);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvNews.setLayoutManager(linearLayoutManager);
        rvNews.setHasFixedSize(true);
        dataset = new ArrayList<>();
        adapter = new NewsAdapter(dataset, getApplicationContext());
        rvNews.setAdapter(adapter);
        value= value.toLowerCase();
        value=changeTurtoEng(value);
        if(!(value.equals("ekonomi")||value.equals("spor")||value.equals("magazin")))
        {
            value="kategori/"+value;
        }

        new NewsGenerator((NewsResultListener) this).execute(value);


    }
    public String changeTurtoEng(String data)
    {
        for (char  c : data.toCharArray())
        {
            switch (c)
            {
                case 'ş':
                case 'Ş':
                    data = data.replace(c, 's');
                    break;
                case 'ç':
                case 'Ç':
                    data = data.replace(c, 'c');
                    break;
                case 'ı':
                case 'İ':
                    data = data.replace(c, 'i');
                    break;
                case 'ğ':
                case 'Ğ':
                    data = data.replace(c, 'g');
                    break;
                case 'ü':
                case 'Ü':
                    data = data.replace(c, 'u');
                    break;
                case 'ö':
                case 'Ö':
                    data = data.replace(c, 'o');
                    break;
                case ' ':
                    data = data.replace(c, '-');
                    break;
            }
        }
        return data;
    }
    @Override
    public void onSuccess(List<News> news) {
        for(News item : news)
        {
            dataset.add(item);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFail(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

}
