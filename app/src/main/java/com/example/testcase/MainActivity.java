package com.example.testcase;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static int VIDEO_REQUEST=101;
    private Uri videoUri= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt=findViewById(R.id.navigate);
        bt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                Intent mapIntent= new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0.0,0.0"));
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

            }
        });
    }

    public void captureVideo(View view) {
        Intent videoIntent= new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if(videoIntent.resolveActivity(getPackageManager())!= null){
            startActivityForResult(videoIntent, VIDEO_REQUEST);
        }
    }

    public void playVideo(View view) {
        Intent playIntent= new Intent(this,VideoPlayActivity.class);
        playIntent.putExtra(" videoUri", videoUri.toString());
        startActivity(playIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data); //edited
        if (requestCode == VIDEO_REQUEST && resultCode == RESULT_OK) {
            videoUri = data.getData();
        }
    }
}
