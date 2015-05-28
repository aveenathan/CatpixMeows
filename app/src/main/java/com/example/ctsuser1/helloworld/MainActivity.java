package com.example.ctsuser1.helloworld;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;

import java.io.IOException;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer mp = new MediaPlayer();
        ImageButton b = (ImageButton) findViewById(R.id.button1);

        // disable ScrollView in portrait mode
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            ScrollView scrollView = (ScrollView)findViewById(R.id.ScrollView01);
            scrollView.setEnabled(false);};

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (mp.isPlaying()) {
                    mp.stop();
                }

                try {
                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("CatFile.mp3");
                    mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

