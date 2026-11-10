package com.example.douanetemporelle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private EditText etNom, etNaissance, etDestination;
    private RadioGroup rgMotif;
    private Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNom = findViewById(R.id.et_nom_dj);
        etNaissance = findViewById(R.id.et_naissance_dj);
        etDestination = findViewById(R.id.et_destination_dj);
        rgMotif = findViewById(R.id.rg_motif_dj);
        btnInspecter = findViewById(R.id.btn_inspecter_dj);


        btnInspecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inspecter();
            }
        });
    }

    private void inspecter() {
        // 1) قراءة البيانات من الحقول
        String nom = etNom.getText().toString().trim();
        String sNaissance = etNaissance.getText().toString().trim();
        String sDestination = etDestination.getText().toString().trim();


        if (nom.isEmpty()) {
            afficherAlerte(getString(R.string.erreur_nom));
            return;
        }
        if (sNaissance.isEmpty() || sDestination.isEmpty()) {
            afficherAlerte(getString(R.string.erreur_annee));
            return;
        }


        int naissance = Integer.parseInt(sNaissance);
        int destination = Integer.parseInt(sDestination);


        if (destination <= naissance) {
            afficherAlerte(getString(R.string.erreur_ordre));
            return;
        }

        int idChoisi = rgMotif.getCheckedRadioButtonId();
        if (idChoisi == -1) {
            afficherAlerte(getString(R.string.erreur_motif));
            return;
        }
        RadioButton rbChoisi = findViewById(idChoisi);
        String motif = rbChoisi.getText().toString();

        Intent intent = new Intent(MainActivity.this, VerificationActivity.class);
        intent.putExtra(Cles.cle(Cles.MATRICULE, "NOM_VOYAGEUR"), nom);
        intent.putExtra(Cles.cle(Cles.MATRICULE, "ANNEE_NAISSANCE"), naissance);
        intent.putExtra(Cles.cle(Cles.MATRICULE, "ANNEE_DESTINATION"), destination);
        intent.putExtra(Cles.cle(Cles.MATRICULE, "MOTIF"), motif);
        startActivity(intent);
    }

    private void afficherAlerte(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getString(R.string.titre_alerte));
        alertDialog.setMessage(message);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.bouton_ok),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}