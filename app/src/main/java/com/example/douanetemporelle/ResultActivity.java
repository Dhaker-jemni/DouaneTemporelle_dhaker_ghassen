package com.example.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

// hedhi el écran 3 : el verdict finale
public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
// 1) ne5dhou el verdict w el esm mel Intent
        Intent intent = getIntent();
        boolean approuve = intent.getBooleanExtra(Cles.cle(Cles.MATRICULE, "VERDICT"), false);
        String nom = intent.getStringExtra(Cles.cle(Cles.MATRICULE, "NOM_VOYAGEUR"));

        // 2) nrabtou el éléments bel XML
        View racine = findViewById(R.id.layout_racine_dj);
        TextView tvMessage = findViewById(R.id.tv_message_dj);
        Button btnNouveau = findViewById(R.id.btn_nouveau_dj);

// 3) nbaddlou lawn el fond w el message 7asb el verdict
        if (approuve) {
            racine.setBackgroundColor(Color.GREEN);
            tvMessage.setText(getString(R.string.msg_approuve, nom));
        } else {
            racine.setBackgroundColor(Color.RED);
            tvMessage.setText(getString(R.string.msg_refuse, nom));
        }

// 4) bouton "Nouveau voyageur" : yraj3na lel écran loula
        // w nsakrou kol  écrans elli 9bal (CLEAR_TASK yfasa5 eli fl  pile(li warrithelna fl cours mr ))
        btnNouveau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent retour = new Intent(ResultActivity.this, MainActivity.class);
                retour.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(retour);
                finish();
            }
        });
    }
}