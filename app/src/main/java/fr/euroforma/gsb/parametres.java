package fr.euroforma.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class parametres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
    }
 public void retourMenu(View view){
    Intent intent=new Intent(parametres.this,MainActivity.class);
    startActivity(intent); }}