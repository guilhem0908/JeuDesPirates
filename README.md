# README

## Description du projet

Ce projet est un jeu de cartes (en console) reposant sur une architecture **MVC** (Modèle‑Vue‑Contrôleur). Il comporte :

- Un **Modèle** (`GameModel`, `Player`, `Card` et ses sous-classes, `Draw`, etc.) qui gère la logique de jeu.  
- Un **Contrôleur** (`GameController`) qui orchestre le déroulement de la partie.  
- Une **Vue** (interface `IView` et classe d’affichage console `ConsoleView`) qui gère l’interaction avec l’utilisateur.  

La classe **`Main`** est le point d’entrée de l’application : c’est depuis cette classe que vous lancez le programme.

## Importation et exécution sous Eclipse

1. **Importer le projet**  
   - Dans Eclipse, sélectionnez `File` > `Import...` > `Existing Projects into Workspace`.  
   - Choisissez le dossier racine du projet puis validez.  

2. **Exécuter la classe Main**  
   - Dans l’Explorateur de projets, repérez la classe `Main` (souvent dans un package `default` ou `app`, selon votre structure).  
   - Faites un clic droit sur `Main` puis sélectionnez `Run As` > `Java Application`.  
   - Le jeu démarre alors en console.  

## Fonctionnement du jeu

- Au lancement, le jeu vous demande les noms des deux joueurs.  
- Chaque joueur dispose d’une main de cartes, et le tour se déroule ainsi :  
  1. Le joueur courant pioche une carte.  
  2. Le joueur choisit une carte de sa main à jouer (attaque, popularité, ou spéciale).  
  3. Les effets de la carte s’appliquent.  
  4. Le tour passe à l’autre joueur.  
- La partie s’achève lorsqu’un joueur atteint **5** points de popularité ou tombe à **0** en points de vie.  
