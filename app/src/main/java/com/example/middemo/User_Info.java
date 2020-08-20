package com.example.middemo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class User_Info extends AppCompatActivity {

    ImageView uImage;
    ImageView bguImage;
    TextView uTvUserName;
    TextView utvBOD;
    TextView utvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__info);
        uImage = findViewById(R.id.ivProfile);
        bguImage = findViewById(R.id.ivbdImage);
        utvBOD = findViewById(R.id.tvBOD);
        uTvUserName = findViewById(R.id.tvName);
        utvAddress = findViewById(R.id.tvAddress);
        try {
            DatabaseHandler objectDatabaseHandler = new DatabaseHandler(this);
            ArrayList<ModelClass> objectModelClassList = objectDatabaseHandler.getUserInfo();

            uImage.setImageBitmap(objectModelClassList.get(0).getmImage());
            bguImage.setImageBitmap(objectModelClassList.get(0).getmImage());
            uTvUserName.setText(objectModelClassList.get(0).getmFirstName() +" "+ objectModelClassList.get(0).getmLastName());
            utvBOD.setText(objectModelClassList.get(0).getmDOB());
            utvAddress.setText(objectModelClassList.get(0).getmAddress());
            //Toast.makeText(this,objectModelClassList.get(0).getmFirstName() , Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}