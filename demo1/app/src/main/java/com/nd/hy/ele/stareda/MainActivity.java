package com.nd.hy.ele.stareda;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         *而且APP B中的intent-filter里面没有这行代码:不需要<category android:name="android.intent.category.DEFAULT"/>
         * 但是要指定action
         */
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                ComponentName componentName = new ComponentName("com.nd.hy.ele.startb","com.nd.hy.ele.startb.MainActivityExplicit");
                intent.setComponent(componentName);
//                intent.putExtra("type", "type+显式启动方式传值");
//                startActivity(intent);



                Bundle bundle = new Bundle();
                bundle.putString("type", "type+显式启动方式传值");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        /**
         * 而且APP B中的intent-filter这种方式一定要加上  <category android:name="android.intent.category.DEFAULT"/>
         *
         * android:exported这个属性的默认值依赖于这个activity是否声明了intent-filter,如果没有，android:exported则为false；否则为默认为true.
         */
        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.qidong3");
                Bundle bundle = new Bundle();
                bundle.putString("type", "type+隐式启动方式传值");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        findViewById(R.id.tv3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager packageManager = getPackageManager();

                 Intent intent = packageManager.getLaunchIntentForPackage("com.nd.hy.ele.startb");
//                ArrayList<String> S =new ArrayList<String>();
//
//                 intent.putExtra("type", S);

                Bundle bundle = new Bundle();
                bundle.putString("type", "type+包名启动");
                intent.putExtras(bundle);
                 startActivity(intent);


//                Bundle bundle = new Bundle();
//                bundle.putDouble("type",11);
//                intent.putExtras(bundle);
//                startActivity(intent);

            }
        });
        findViewById(R.id.tv5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String uri = "example://com.nd.hy.ele.startb/ActivityDemo5";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));

                    Bundle bundle = new Bundle();
                    bundle.putString("type", "type+scheme启动方式传值");
                    intent.putExtras(bundle);

                    startActivity(intent);

//                Bundle bundle = new Bundle();
//                bundle.putDouble("type",11);
//                intent.putExtras(bundle);
//                startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });



    }


}
