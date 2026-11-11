package com.example.douanetemporelle;

// hedhi classe sghira tbni el mo3tiyet (les clés) mta3 el Intent
public class Cles {
    public static final String MATRICULE = "7281";
    // hedhi fonction static, tbni el clé
    public static String cle(String matricule, String nom)
    {
        return matricule + "_" + nom;
    }
}
