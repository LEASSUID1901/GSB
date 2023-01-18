package fr.euroforma.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        securite ();
    }

    public void clickMenu(View view) {
       Class mc = null;
        switch (view.getId()) {
            case R.id.btnFraisForfait:
                afficheMenu("notes forfait");
                mc= FraisForfait.class;
                break;
            case R.id.btnFraisHorsForfait:
                afficheMenu("notes frais hors forfait");
                mc=FraisHorsForfait.class;

                break;
            case R.id.btnParametres:
                afficheMenu("mes informations");
                mc= parametres.class;

                break;
            case R.id.btnSyntheseDesFrais:
                afficheMenu("synthese de mes frais");
                mc=Synthese.class;

                break;
            case R.id.btnAnthentification:
                afficheMenu("Authentification");
                mc=Authentification.class;

        }
        Intent menuIntent= new Intent(MainActivity.this,mc);
        startActivity(menuIntent);
    }
    public void afficheMenu (String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
//affiche sur quel boutton on clique//


public void securite(){
        String cvisiteur=getSharedPreferences("SHARED_PREF_USER_INFO", MODE_PRIVATE).getString("CODE_VISITEUR","zero");
        afficheMenu(cvisiteur);
        if(cvisiteur.equals("zero")){
            Intent intent= new Intent(MainActivity.this, Authentification.class);
            startActivity(intent);
        }

        }
};
