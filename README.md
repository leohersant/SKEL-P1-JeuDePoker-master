# TP 1 - Jeu de Poker (Fiche de réponses)

  - Auteur : Léonardo, Hernandez (`HERL16027606`)
  - Date de remise : 24/02/2021
  - Estimation du temps de travail passé sur le projet : 50 heures.

## Réponses aux questions  
 
### Question 1: Évolution du code légataire

* Il faut créer une classe arbitre pour que:
<ol>
<li>l'arbitre puisse analyser les mains de poker de chaque joueur, même si plus de 2 joueurs.</li>
<li>l'arbitre puisse déterminer la carte la plus haute dans chaque main de poker de chaque joueur.</li>
<li>l'arbitre puisse attribuer des points à chaque joueur et dire qui a gagné.</li>
</ol>

* Il faut créer une classe Joueur qui contiendra le score de chaque joueur, la main de poker, la combinaison du poker, la carte la plus haute de sa main de poker.
* Il faut créer une class MainDePoker qui contient 5 cartes
* Il faut créer une class Carte avec une valeur avec un couleur.	
à la fin on peut créer un liste de joueurs avec leur propre main de poker et le passer à un objet arbitre.	

### Question 2: Analyse des défauts du code légataire

Voici groso-modo les problématiques vues dans la classe Main

#### Le principe de responsabilité unique
La fonction <em>str2Array(String s)</em> a trop de responsabilité. Elle parse une chaîne de caractère pour séparer les cartes, mais aussi envoie des messages d'erreur pour la validation.

#### Principe d'inversion de dépendance
La fonction <em>str2Array(String s)</em> est immobile, elle utilise une variable statique (result) de la classe main ce qui ne permet pas de déplacer la fonction à cause de cette dépendance, donc la classe Main a besoin absoluement de cette fonction pour travailler.

#### Le principe Open/Close
* La fonction <em>comp(String p1, String p2)</em> est limité à deux comparaisons et difficilement extensible pour 3 joueurs.
* La fonction <em>findComb</em>, peut être éclatée en petites fonctions qui pourraient être dans une seule classe. On peut créer une classe, par exemple PokerCombination, qui serait extensible (que l'on ajoute des combinaisons) au lieu de modifier findcomb.

#### Obsession du primitive (encapsulation)
La fonction maxVal() est supposé de rétourner la carte la plus haute, mais elle retroune un character pour réprésenter la valeur de la carte.


### Question 3: Justification des choix de conception

1) Pourquoi les choix de ces concepts?

 Il y a deux principaux acteurs lors de cette conception: les joueurs et l'arbitre. La conception s'est basée sur l'idée que chaque joueur détient un combinaison de poker après lui avoir distribué des cartes. De cette façon, une combinaison de poker est une main de poker et une main de poker contient plusieures cartes. Dans mon programme il y a donc 5 classes: Player, Referee, PokerCombination, CardHand et Card. Comme chaque joueur détient un combinaison, ces dernières peuvent êtres analysés par un arbitre qui attribut de points, detecte le ganant et le perdants et annonces qui a gagné. Chaque classe a donc une responsabilité unique, sont extensibles et indépendantes les unes les autres. 

2) Comment ma conception actuelle respecte le principe Solid:

* Chaque classe joue un seul rôle (responsabilité unique). Par exemple, <em>PokerCombination</em> ne fait que detecter les combinations possibles du poker.
* Facilement extensibles, si l'on decide omettre/ajouter des nouvelles combinaisons de poker. Aussi, la classe <em>CardHand</em> peut être extensible pour un autre jeu de cartes (principe <em>open/close</em>). Dans mon projet <em>PorkerCombination</em> étand <em>CardHand</em>.
* Principe de substitution de Liskov: les objets de classe <em>cardHand</em> (de la super classe) peuvent être remplacés par les objets de la classe <em>PokerCombination</em> (la classe fille). 
* Principe d'inversion de dépendance: dans mon projet aucune classe de haut niveau dépends des classes de bas niveau.
* Séparation des interfaces: les classes ne sont pas oubligés d'avoir de méthodes qu'elle n'utilise pas. Ex: <em>Player</em> et <em>PokerPlayer</em>. PokerPlayer utilise une interface pour ne pas emcombrer <em>Player</em>.

3) Faiblesses

La classe <em>PokerReferee</em> devrait être une extension de une classe <em>Referee</em>, car elle est trop spécialisée. Aussi on devrait lui faire implementer pour lui dire, voici ce qu'un <em>PokerReferee</em> doit faire. Je devrais peut-être separer les methodes d'affichage qui se trouvent dans <em>PokerReferee</em>.


### Question 4: Évolution du code objet
Comment l'ajout des parties gagnées, du multi-joueur a pu être fait dans votre projet en respectant le principe de conception O de SOLID?

Ajouter le Score a été possible, car une fois que le referee détecte un joueur gagnant, on peut lui attribuer un point. Comme les classes PokerReferee, PokerCombination, PokerPlayer et Player jouent un seul rôle et sont bien seéparées elles peuvent s'échanger de messages facilement.


## Auto-évaluation (optionnelle)

Vous êtes libre de faire l'exercice de vous auto-évaluer en remplissant la grille d'évaluation du projet ci-dessous.

| Élement         | Critère d'évaluation                       | Note  |
| :---:           | :---                                       | :---: |
|  _Questions_    | (#1) Évolution du code légataire           | /5    |
|                 | (#2) Analyse des défauts du code légataire | /10   |
|                 | (#3) Justification des choix de conception | /15   |
|                 | (#4) Évolution du code objet               | /5    |
|  _Modèles_      | Justesse & Pertinence de la conception     | /15   |
|                 | Cohérence inter-modèles                    | /5    |
|                 | Respect des principes de conception        | /15   |
|  _Code_         | Qualité du code Java et du dépôt Git       | /10   |
|                 | Cohérence du code avec les modèles         | /10   | 
|                 | Qualité des tests                          | /10   | 
| **Note Finale** | Questions + Modèles + Code                 | /100  | 

_Cette auto-évaluation permet au correcteur de vous donner une rétro-action plus personnalisée en pointant les critères sur lesquels vous vous sur-évaluez et ceux sur lesquels au contraire vous vous sous-évaluez._
