# Douane Temporelle 

Mini-projet (DS 1) de Programmation Mobile, 3ème BI, FSEGT
Enseignant : Nidhal Jelassi

Application Android (Java + XML) qui simule un poste de douane pour les voyageurs du temps

## Groupe

- Mohammed Dhaker Jemni  (initiales : dj)(a01)
- Ghassen Sakhri (a07)


## Fonctionnement

L'application contient trois activités reliés par des Intents explicites :

| Écran | Classe | Rôle |
|---|---|---|
| 1 | `MainActivity` | Formulaire d'enregistrement : nom, année de naissance, année de destination, motif du voyage (Tourisme, Modification du passé, Fuite) |
| 2 | `VerificationActivity` | Passeport temporel : affiche le nom, l'âge temporel (destination − naissance) et le motif. Deux boutons : Approuver / Refuser |
| 3 | `ResultActivity` | Verdict : fond vert si approuvé, rouge si refusé, avec un message personnalisé. Le bouton « Nouveau voyageur » revient au formulaire en fermant les écrans précédents |

### Règle du paradoxe

Si le motif est **« Modification du passé »** et que la différence entre l'année de destination et l'année de naissance est un **nombre premier**, le bouton **Approuver** est désactivé dès l'ouverture de l'écran 2, et une alerte (`AlertDialog`) est affichée

## Structure du projet


app/src/main/
├── AndroidManifest.xml
├── java/com/example/douanetemporelle/
│   ├── Cles.java                  
│   ├── MainActivity.java          
│   ├── VerificationActivity.java  
│   └── ResultActivity.java        
└── res/
    ├── layout/    activity_main.xml, activity_verification.xml, activity_result.xml
    ├── values/    strings.xml, colors.xml
    └── drawable/  fond_billet.xml, passport.png


## Technologies

- Java
- XML 
- Android Studio
- Git / GitHub
