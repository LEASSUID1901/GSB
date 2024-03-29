package fr.euroforma.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class FraisForfait extends AppCompatActivity {
     EditText Quantite;
     Spinner typeForfait;
     Button BtnAjouter;
    EditText Text_date;
    Calendar calendrier = Calendar.getInstance(); //on declare une classe d'un calendrier qui existe deja
    int jj=calendrier.get(Calendar.DAY_OF_MONTH);
    int mm=calendrier.get(Calendar.MONTH);
    int aaaa=calendrier.get(Calendar.YEAR);
    DatePickerDialog picker;
    TextView Msomme;
    String [] ValeurForfait;
     BDDHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frais_forfait);
        Quantite=findViewById(R.id.Quantite);
        typeForfait=findViewById(R.id.typeForfait);
        BtnAjouter=findViewById(R.id.BtnAjouter);
        Text_date=findViewById(R.id.Text_date);
        Msomme=findViewById(R.id.txtsomme);
        ValeurForfait=getResources().getStringArray(R.array.ValeurForfait);
        database=new BDDHelper(this);
        database.open();
        Quantite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
           Integer q1 =Integer.parseInt(String.valueOf("0"+Quantite.getText()));
           String f = typeForfait.getSelectedItem().toString();
           int posForfait= typeForfait.getSelectedItemPosition();
           Float s1 = q1* Float.parseFloat(ValeurForfait[posForfait]);
           Msomme.setText(s1.toString());
            }
        });

        }
        public void setBtnAjouter(View v) {
        affiche(Quantite.getText().toString()+" "+typeForfait.getSelectedItem().toString());
            if (Quantite.getText().toString().trim().length() == 0 || typeForfait.getSelectedItem().toString().length() == 0
                    || Text_date.getText().toString().trim().length()==0) {
                //teste si le champ quantite est renseigné ou si le champ type n'est pas vide
                // et qu'on a selectionne l'une des 4 possibilités et si la date est renseignée
                affiche("Erreur! Champ vide");
                return;
            } else if (Text_date.getText().toString().trim().length()>10 || Text_date.getText().toString().trim().length()<8 ) {
                //test sur la validité du champ date
                affiche("Erreur! Date invalide");
                return;

            }else if(Integer.parseInt(Quantite.getText().toString())<1) {
                affiche("Erreur quantite invalide ");
                return;

            }else{
        String tf1= typeForfait.getSelectedItem().toString();
        Integer q1 = Integer.parseInt(Quantite.getText().toString());
        String d1 =Text_date.getText().toString();
        Float m1 = Float.parseFloat(Msomme.getText().toString());


        if(database.insertData(tf1,q1,d1,m1,tf1)){
            affiche("Valeur ajoutée avec succès.Montant="+m1);
            return;
        }                            ;

    }}
    public void retourMenu(View view){
        Intent intent=new Intent(FraisForfait.this,MainActivity.class);
        startActivity(intent);
    }
    public void affiche(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    public void ShowCal(View vv) {
        picker = new DatePickerDialog(FraisForfait.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Text_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        //date qu'on recupere une fois qu'on a selectionne
                    }
                }, aaaa, mm, jj);//date qui s'affiche sur le calendrier
        picker.show();


}}
