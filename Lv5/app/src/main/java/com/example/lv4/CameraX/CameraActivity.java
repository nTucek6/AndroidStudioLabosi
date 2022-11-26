package com.example.lv4.CameraX;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lv4.R;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CameraActivity extends AppCompatActivity {
    private PreviewView previewView;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    private TextView textView;
    private Button takePicture;
    ImageCapture imageCapture;
    private Executor executor = Executors.newSingleThreadExecutor();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        previewView = findViewById(R.id.previewView);
        textView = findViewById(R.id.orientation);
        takePicture = findViewById(R.id.captureImg);

        cameraProviderFuture = ProcessCameraProvider.getInstance(this);

        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                    startCameraX(cameraProvider);

                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, getExecutor());

        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capturePhoto();
            }
        });
    }
    private Executor getExecutor()
    {
        return ContextCompat.getMainExecutor(this);
    }

    private void startCameraX(@NonNull ProcessCameraProvider cameraProvider) {
        cameraProvider.unbindAll();

        //Camera selector
        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        //create preview view
        Preview preview = new Preview.Builder().build();

        //set preview view
        preview.setSurfaceProvider(previewView.createSurfaceProvider());

        //image capture use case
        imageCapture = new ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build();

        cameraProvider.bindToLifecycle((LifecycleOwner)this,cameraSelector,preview, imageCapture);

    }

    private void capturePhoto()
    {
        long timestamp = System.currentTimeMillis();//String.valueOf(date.getTime());
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME,timestamp);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE , "image/jpeg"); //, "/jpeg"

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P)
        {
            contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX"); //
        }

        File fileDirectory = CameraActivity.this.getFilesDir();
        if(!fileDirectory.exists())
        {
            fileDirectory.mkdirs();
        }

        String Image = String.valueOf(timestamp)+".jpg";
        File saveImage = new File(fileDirectory, Image);

        imageCapture.takePicture(
                new ImageCapture.OutputFileOptions.Builder(
                       // saveImage
                        getContentResolver(),
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        contentValues
                ).build(),
                getExecutor(),
                new ImageCapture.OnImageSavedCallback()
                {
                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                        Toast.makeText(CameraActivity.this, "Photo saved", Toast.LENGTH_SHORT).show();

                        //Intent i = new Intent();
                       // i.putExtra("ImageLocation",ImageId);
                        //setResult(78,i);
                        CameraActivity.this.finish();
                    }
                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        Toast.makeText(CameraActivity.this, "Error saving photo: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}

