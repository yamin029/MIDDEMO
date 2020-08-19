package com.example.middemo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class User_Info extends AppCompatActivity {

    ImageView uImage;
    TextView uTvUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__info);
        uImage = findViewById(R.id.uImage);
        uTvUserName = findViewById(R.id.tvUserName);
        try {
            DatabaseHandler objectDatabaseHandler = new DatabaseHandler(this);
            ArrayList<ModelClass> objectModelClassList = objectDatabaseHandler.getUserInfo();

            uImage.setImageBitmap(objectModelClassList.get(0).getmImage());
            uTvUserName.setText(objectModelClassList.get(0).getmFirstName() +" "+ objectModelClassList.get(0).getmLastName());
            //Toast.makeText(this,objectModelClassList.get(0).getmFirstName() , Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}