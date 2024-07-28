package com.smart.wallpaperchanger;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button btn;
    Boolean running=false;
    WallpaperManager wpm;
    int curr=0;
    int[] arr = new int[]{R.drawable.img1, R.drawable.img2 ,R.drawable.img3 , R.drawable.img4, R.drawable.img5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!running){
                    new Timer().schedule(new mytimer(),0,30000);
                    running=true;
                }
            }
        });
    }

    private class mytimer extends TimerTask {
        @Override
        public void run() {
            try {
                wpm = WallpaperManager.getInstance(getBaseContext());
                Drawable drawable = getResources().getDrawable(arr[curr]);
                curr = (curr+1)%arr.length;
                Bitmap wallpaper = ((BitmapDrawable)drawable).getBitmap();
                wpm.setBitmap(wallpaper);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}