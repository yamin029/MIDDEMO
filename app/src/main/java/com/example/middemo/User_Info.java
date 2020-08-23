package com.example.middemo;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class User_Info extends AppCompatActivity {


    ImageView uImage;
    ImageView bguImage;
    TextView uTvUserName;
    TextView utvBOD;
    TextView utvAddress;
    TextView utvEducation;
    TextView utvSkills;
    TextView utvExperiences;
    Button uShareButton;
    RelativeLayout uPortfolio;


    ImageView showrLImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__info);
        uImage = findViewById(R.id.ivProfile);
        bguImage = findViewById(R.id.ivbdImage);
        utvBOD = findViewById(R.id.tvBOD);
        uTvUserName = findViewById(R.id.tvName);
        utvAddress = findViewById(R.id.tvAddress);
        utvEducation = findViewById(R.id.tvEducation);
        utvExperiences = findViewById(R.id.tvExperiences);
        uShareButton = findViewById(R.id.btnShare);
        uPortfolio = findViewById(R.id.rlPortfolio);
        showrLImage = findViewById(R.id.showrLImage);

        utvSkills = findViewById(R.id.tvSkills);
        try {
            DatabaseHandler objectDatabaseHandler = new DatabaseHandler(this);
            ArrayList<ModelClass> objectModelClassList = objectDatabaseHandler.getUserInfo();

            uImage.setImageBitmap(objectModelClassList.get(0).getmImage());
            bguImage.setImageBitmap(objectModelClassList.get(0).getmImage());
            uTvUserName.setText(objectModelClassList.get(0).getmFirstName() +" "+ objectModelClassList.get(0).getmLastName());
            utvBOD.setText(objectModelClassList.get(0).getmDOB());
            utvAddress.setText(objectModelClassList.get(0).getmAddress());
            String education = objectModelClassList.get(0).getmEducation();
            String educations[] = education.split(",");
            education = "";
            for(int i = 0; i< educations.length; i++){

                education += (i+1)+"-"+educations[i]+"\n";

            }
            utvEducation.setText(education);

            String skill = objectModelClassList.get(0).getmSkills();
            String skills[] = skill.split(",");
            skill = "";
            for(int i = 0; i< skills.length; i++){

                skill += (i+1)+"-"+skills[i]+"\n";

            }
            utvSkills.setText(skill);

            String experience = objectModelClassList.get(0).getmExperience();
            String experiences[] = experience.split(",");
            experience = "";
            for(int i = 0; i< experiences.length; i++){

                experience += (i+1)+"-"+experiences[i]+"\n";

            }
            utvExperiences.setText(experience);
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        uShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(User_Info.this, "shareClicked", Toast.LENGTH_SHORT).show();

                Bitmap bitmap = Bitmap.createBitmap(uPortfolio.getWidth(),uPortfolio.getHeight(),Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                uPortfolio.draw(canvas);
                showrLImage.setImageBitmap(bitmap);
                sharePalette(bitmap);



            }
        });


    }

    private void sharePalette(Bitmap bitmap) {
        String bitmapPath = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "palette", "share palette");
        Uri bitmapUri = Uri.parse(bitmapPath);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/png");
        intent.putExtra(Intent.EXTRA_STREAM, bitmapUri);
        startActivity(Intent.createChooser(intent, "Share"));
    }


}