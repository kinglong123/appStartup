package com.nd.hy.ele.startb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivityScheme extends AppCompatActivity {
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((TextView)findViewById(R.id.tv_2)).setText("scheme方式");

        mTextView =   ((TextView)findViewById(R.id.tv_3));
        getAppData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    private void getAppData() {

        Intent intent=getIntent();

        //String data_str = intent.getStringExtra("data");
        Bundle bundle=intent.getExtras();
        if(bundle !=null) {
//            String data_str = bundle.getString("type");
//            mTextView.setText(bundle.toString());
            String type = bundle.getString("type");
            String ticket = bundle.getString("ticket");
            mTextView.setText("type:"+type+"---ticket:"+ticket);
//            Log.i("tag", data_str);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
