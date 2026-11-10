package com.example.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        Intent intent = getIntent();
        boolean approuve = intent.getBooleanExtra("VERDICT", false);
        String nom = intent.getStringExtra("NOM_VOYAGEUR");

        View racine = findViewById(R.id.layout_racine_dj);
        TextView tvMessage = findViewById(R.id.tv_message_dj);
        Button btnNouveau = findViewById(R.id.btn_nouveau_dj);


        if (approuve) {
            racine.setBackgroundColor(Color.GREEN);
            tvMessage.setText(getString(R.string.msg_approuve, nom));
        } else {
            racine.setBackgroundColor(Color.RED);
            tvMessage.setText(getString(R.string.msg_refuse, nom));
        }


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