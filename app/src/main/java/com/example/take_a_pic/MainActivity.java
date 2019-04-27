package com.example.take_a_pic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    public static final int REQUEST_CAPTURE_PHOTO = 120;
    ImageView iPhoto;
    Button bCamera;
    Button bMap;
    TextView sMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bCamera = findViewById(R.id.bCamera);
        iPhoto = findViewById(R.id.iPhoto);
        bMap = findViewById(R.id.bMap);
        sMap = findViewById(R.id.sMap);

        bCamera.setOnClickListener(this);
        bMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bCamera:
                takePhoto();
                break;

            case R.id.bMap:
                String search = sMap.getText().toString();
                Uri location = Uri.parse("geo:0,0?q=" + search );
                lookMap(location);
                break;
                
        }

    }

    private void takePhoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, REQUEST_CAPTURE_PHOTO);
        }
    }

    private void lookMap(Uri geoLocation){
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, geoLocation);
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CAPTURE_PHOTO  && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            iPhoto.setImageBitmap(bitmap);
        }
    }

}
