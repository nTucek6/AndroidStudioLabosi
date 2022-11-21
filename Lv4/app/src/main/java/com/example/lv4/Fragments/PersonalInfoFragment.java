package com.example.lv4.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.unusedapprestrictions.IUnusedAppRestrictionsBackportService;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lv4.CameraX.CameraActivity;
import com.example.lv4.CreateNewRecordActivity;
import com.example.lv4.FragmentDataClass.PersonalInfoData;
import com.example.lv4.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class PersonalInfoFragment extends Fragment {

    private static final String[] CAMERA_PERMISSION = new String[]{Manifest.permission.CAMERA};
    private static final int CAMERA_REQUEST_CODE = 10;

    public TextInputEditText TextBoxInsertName;
    public TextInputEditText TextBoxInsertSurname;
    public TextInputEditText TextBoxInsertDate;
    public ImageView addImage,previewImage;
    public Button uploadImage;

    private PersonalInfoData personalInfoData;

    private View rootView;
    String sImage = null;

    ActivityResultLauncher<Intent> uploadImageFromGallery = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if(result.getResultCode() == getActivity().RESULT_OK)
                    {
                        Intent data = result.getData();

                        if(data != null)
                        {
                            Uri selectedImageUri = data.getData();
                            try{
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),selectedImageUri);
                                previewImage.setImageBitmap(bitmap);
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                                byte[] bytes = stream.toByteArray();
                                sImage = Base64.encodeToString(bytes,Base64.DEFAULT);
                            }catch(IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
    );

    public PersonalInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_personal_info, container, false);
        return rootView;
    }

   @Override
   public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
       super.onViewCreated(view, savedInstanceState);

       TextBoxInsertName = view.findViewById(R.id.textBoxInputName);
       TextBoxInsertSurname = view.findViewById(R.id.textBoxInputSurname);
       TextBoxInsertDate = view.findViewById(R.id.textBoxInputDate);
       addImage = view.findViewById(R.id.profileImageView);
       uploadImage = view.findViewById(R.id.uploadImage);
       previewImage = view.findViewById(R.id.previewImage);

       addImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (hasCameraPermission()) {
                   enableCamera();
               } else {
                   requestPermission();
               }
           }
       });

       uploadImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
               {
                   ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},100);
               }
               else
               {
                   selectImage();
               }
           }
       });
   }

   public PersonalInfoData SendData()
   {
       try
       {
           String name = TextBoxInsertName.getText().toString();
           String surname = TextBoxInsertSurname.getText().toString();
           String birthDate = TextBoxInsertDate.getText().toString();

           personalInfoData = new PersonalInfoData(name,surname,birthDate,sImage);

           if(personalInfoData.CheckEmpty())
           {
               return personalInfoData;
           }
           else
           {
               return null;
           }
       }catch (Exception e)
       {
           return null;
       }
   }


    private boolean hasCameraPermission()
    {
        return ContextCompat.checkSelfPermission(
                getActivity(),
                Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(
                getActivity(),
                CAMERA_PERMISSION,
                CAMERA_REQUEST_CODE
        );
    }

    private void enableCamera() {
        Intent intent = new Intent(getActivity(), CameraActivity.class);
        startActivity(intent);
    }

    private void selectImage()
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        uploadImageFromGallery.launch(intent);
    }
}
