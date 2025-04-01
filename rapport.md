# Lab02 IBeacons
> Häffner Edwin, Dunant Guillaume, Junod Arthur

## Réponses aux questions
### 1.1.1
> Est-ce que toutes les balises à proximité sont présentes dans toutes les annonces de la librairie ? Que faut-il mettre en place pour permettre de « lisser » les annonces et ne pas perdre momentanément certaines balises ?

En général, nous pouvons voir toutes les balises présentes mais elles sont parfois perdues pendant un court laps de temps. Pour palier à ce problème, nous avons rajouté une valeur dans `PersistentBeacon` qui nous permet de garder en mémoire le temps de la dernière mise à jour que nous avons reçues pour chaques balises. Nous utilisons cette valeur afin de calculer un delta entre elle et le temps actuel afin de savoir si la balise lié doit être supprimé de la liste affichée. Le temps de vie de l'affichage des balises est choisis de manière arbitraire pour le moment (5 secondes).

### 1.1.2
> Nous souhaitons effectuer un positionnement en arrière-plan, à quel moment faut-il démarrer et éteindre le monitoring des balises ? Sans le mettre en place, que faudrait-il faire pour pouvoir continuer le monitoring alors que l’activité n’est plus active ?


### 1.1.3
> On souhaite trier la liste des balises détectées de la plus proche à la plus éloignée, quelles sont les valeurs présentes dans les annonces reçues qui nous permettraient de le faire ? Comment sont-elles calculées et quelle est leur fiabilité ?


### 2.1.1
> Comment pouvons-nous déterminer notre position ? Est-ce uniquement basé sur notion de proximité étudiée dans la question 1.1.3, selon vous est-ce que d’autres paramètres peuvent être pertinents ?


### 2.1.2
> Les iBeacons sont conçus pour permettre du positionnement en intérieur. D’après l’expérience que vous avez acquise sur cette technologie dans ce laboratoire, quels sont les cas d’utilisation pour lesquels les iBeacons sont pleinement adaptés (minimum deux) ? Est-ce que vous voyez des limitations qui rendraient difficile leur utilisation pour certaines applications ?


