package fr.euroforma.gsb;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Synthese extends AppCompatActivity {
    private Cursor cursor;
    private SimpleCursorAdapter dataAdapter;
   private  BDDHelper db = new BDDHelper(this);
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synthese);

    //    ListAdapter adapter = new SimpleAdapter(Synthese.this, details_frais, R.layout.details_frais, new String[]{"DATE", "MONTANT", "LIBELLE", "DATESAISIE"}, new int[]{R.id.Libelle, R.id.montant, R.id.DateFrais, R.id.Datesaisie});
    //    lv.setAdapter(adapter);

        cursor = db.viewData();
        db.open();
        displayListView();

    }
    private void displayListView() {

        Cursor cursor = db.viewData();

        // Les colonnes que l’on veut lier
        String[] columns = new String[]{
                BDDHelper.LIBELLE,
                BDDHelper.MONTANT,
                BDDHelper.DATEFRAIS,
                BDDHelper.DATESAISIE,
                BDDHelper.QUANTITE,
                BDDHelper.ID
        };

        // Les éléments defnis dans le XML auxquels les données sont liées
        int[] to = new int[]{
                R.id.Libelle,
                R.id.montant,
                R.id.DateFrais,
                R.id.Datesaisie,
                R.id.Quantite,
                R.id.IdFrais,

        };
        //On crée l'adaptateur à l'aide du curseur pointant sur les données souhaitées :git
        // ainsi que les informations de mise en page
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.details_frais,
                cursor,
                columns,
                to,
                0);

        ListView lv = (ListView) findViewById(R.id.frais_list);
        // Attribuer l’adapter au ListView
        lv.setAdapter(dataAdapter);

        //Pour que l'id du frais s'affiche quand on clique dessus, et pour supprimer le frais
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // On obtient le curseur, positionne sur la ligne correspondante dans le jeu de résultats
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                //On obtient l'id du frais en cliquant sur le frais
                String myId = cursor.getString(cursor.getColumnIndexOrThrow("ID"));
                Toast.makeText(getApplicationContext(), myId, Toast.LENGTH_SHORT).show();
                db.deleteData (parseInt (myId));
            }
        });
    }



    public void retourMenu (View view){
        Intent retourIntent = new Intent(Synthese.this, MainActivity.class);
        startActivity(retourIntent);
    }

}




