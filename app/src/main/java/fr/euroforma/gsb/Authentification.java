package fr.euroforma.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class Authentification extends AppCompatActivity {
    //Button bouttonVerification;
    LinearLayout layout;
    EditText codevisiteur;
    EditText email;
    private static final String SHARED_PREF_USER_INFO = "SHARED_PREF_USER_INFO";
    private static final String CODE_VISITEUR="CODE_VISITEUR";
    private static final String EMAIL="EMAIL";

EditText verification;
    Random r = new Random();
    int min = 10000;
    int max = 99999;
    Integer codeAleatoire = r.nextInt((max - min) + 1) + min;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
        this.getSharedPreferences("SHARED_PREF_USER_INFO",MODE_PRIVATE).edit().clear().commit();

        layout=findViewById(R.id.authentification_layout);
        codevisiteur = findViewById(R.id.CodeVisiteur);
        verification=findViewById(R.id.CodeVerification);
        email = findViewById(R.id.email);
    }
    void afficheMessage(String msg) { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show(); }
   public void verification(View vv) {
       if (codeAleatoire.toString().equals(verification.getText().toString())) {
           afficheMessage("le code est bon ");
           SharedPreferences preferences = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE);
           getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE)
                   .edit()
                   .putString(CODE_VISITEUR,codevisiteur.getText().toString())
                   .putString(EMAIL,email.getText().toString())
                   .apply();
           String CodeVisiteurEnregistre = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(CODE_VISITEUR, null);
           String EmailEnregistre = getSharedPreferences(SHARED_PREF_USER_INFO, MODE_PRIVATE).getString(EMAIL, null);
           afficheMessage("code visiteur enregistré :"+ CodeVisiteurEnregistre+"email enregistré:"+ EmailEnregistre);
           Intent intent= new Intent(Authentification.this, MainActivity.class);
           startActivity(intent);
       }
       else{
           afficheMessage("le code n est pas valide");
       }
   }


    void afficheCode(String msg) { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show(); }

    public void ClickEnvoyer(View vv) {


        afficheCode(codeAleatoire.toString());
      layout.setVisibility(View.VISIBLE);

        
        }


    }




