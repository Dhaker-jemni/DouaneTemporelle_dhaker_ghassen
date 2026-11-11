package com.example.douanetemporelle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {
    private String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        Intent intent = getIntent();
        nom = intent.getStringExtra(Cles.cle(Cles.MATRICULE, "NOM_VOYAGEUR"));
        int naissance = intent.getIntExtra(Cles.cle(Cles.MATRICULE, "ANNEE_NAISSANCE"), 0);
        int destination = intent.getIntExtra(Cles.cle(Cles.MATRICULE, "ANNEE_DESTINATION"), 0);
        String motif = intent.getStringExtra(Cles.cle(Cles.MATRICULE, "MOTIF"));

        int ageTemporel = destination - naissance;

        TextView tvNom = findViewById(R.id.tv_nom_dj);
        TextView tvAge = findViewById(R.id.tv_age_dj);
        TextView tvAnnees = findViewById(R.id.tv_annees_dj);
        TextView tvMotif = findViewById(R.id.tv_motif_dj);
        Button btnApprouver = findViewById(R.id.btn_approuver_dj);
        Button btnRefuser = findViewById(R.id.btn_refuser_dj);

        tvNom.setText(getString(R.string.label_nom, nom));
        tvAge.setText(getString(R.string.label_age, ageTemporel));
        tvAnnees.setText(getString(R.string.label_annees, naissance, destination));
        tvMotif.setText(getString(R.string.label_motif, motif));

        boolean modification = getString(R.string.motif_modification).equals(motif);
        if (modification && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false);

            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle(getString(R.string.titre_alerte));
            alertDialog.setMessage(getString(R.string.alerte_paradoxe));
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.bouton_ok),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }

        btnApprouver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                envoyerVerdict(true);
            }
        });

        btnRefuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                envoyerVerdict(false);
            }
        });
    }

    private void envoyerVerdict(boolean approuve) {
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(Cles.cle(Cles.MATRICULE, "VERDICT"), approuve);
        intent.putExtra(Cles.cle(Cles.MATRICULE, "NOM_VOYAGEUR"), nom);
        startActivity(intent);
    }
    private boolean estPremier(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}