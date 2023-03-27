package fr.euroforma.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


public class FraisHorsForfait extends AppCompatActivity {
    EditText Libelle;
    EditText Montant;
    DatePickerDialog picker;
    EditText maDate;
    Calendar calendrier = Calendar.getInstance(); //on declare une classe d'un calendrier qui existe deja
    int jj=calendrier.get(Calendar.DAY_OF_MONTH);
    int mm=calendrier.get(Calendar.MONTH);
    int aaaa=calendrier.get(Calendar.YEAR);
    BDDHelper database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frais_hors_forfait);
        Libelle = findViewById(R.id.libelle);
        Montant = findViewById(R.id.montant);
       maDate= findViewById(R.id.maDate);


        database=new BDDHelper(this);
        database.open();

    }



    public void afficheFHF(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public void setBtnAjouterFHF(View v) {
        if (Montant.getText().toString().trim().length() == 0 || Libelle.getText().toString().length() == 0
                || maDate.getText().toString().trim().length()==0) {
            //teste si le champ quantite est renseigné ou si le champ type n'est pas vide
            // et qu'on a selectionne l'une des 4 possibilités et si la date est renseignée
            afficheFHF("Erreur! Champ vide");
            return;
        } else if (maDate.getText().toString().trim().length()>10 || maDate.getText().toString().trim().length()<8 ) {
            //test sur la validité du champ date
            afficheFHF("Erreur! Date invalide");
            return;



        }else{

        String tf1=Libelle.getText().toString();
        String date=maDate.getText().toString();
        Float m1 = Float.parseFloat(Montant.getText().toString());
        Integer Q1=0;
        afficheFHF(Libelle.getText().toString()+" "+Montant.getText().toString());
        if(database.insertData(tf1,Q1,date,m1,tf1)){
            afficheFHF("Valeur ajoutée avec succès.Montant="+m1);
            return;
    }}}
    public void ShowCal (View v) {
        picker = new DatePickerDialog(FraisHorsForfait.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        maDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        //date qu'on recupere une fois qu'on a selectionne
                    }
                }, aaaa, mm, jj);//date qui s'affiche sur le calendrier
        picker.show();

    }}


