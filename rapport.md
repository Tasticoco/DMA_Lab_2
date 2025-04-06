# Lab02 IBeacons
> Häffner Edwin, Dunant Guillaume, Junod Arthur

## Implémentation



## Réponses aux questions
### 1.1.1
> Est-ce que toutes les balises à proximité sont présentes dans toutes les annonces de la librairie ? Que faut-il mettre en place pour permettre de « lisser » les annonces et ne pas perdre momentanément certaines balises ?

En général, nous pouvons voir toutes les balises présentes mais elles sont parfois perdues pendant un court laps de temps. Pour palier à ce problème, nous avons rajouté une valeur dans `PersistentBeacon` qui nous permet de garder en mémoire le temps de la dernière mise à jour que nous avons reçues pour chaques balises. Nous utilisons cette valeur afin de calculer un delta entre elle et le temps actuel afin de savoir si la balise liée doit être supprimé de la liste affichée. Le temps de vie de l'affichage des balises est choisi de manière arbitraire pour le moment (5 secondes).

### 1.1.2
> Nous souhaitons effectuer un positionnement en arrière-plan, à quel moment faut-il démarrer et éteindre le monitoring des balises ? Sans le mettre en place, que faudrait-il faire pour pouvoir continuer le monitoring alors que l’activité n’est plus active ?

Pour pouvoir continuer le monitoring alors que l'activité n'est plus active, il faut, selon la documentation de AltBeacon, créer une classe qui extend la classe `application` d'Android et override la fonction `onCreate` de celle-ci. 

```kotlin
class MyApplication:  Application() {
    override fun onCreate() {
        super.onCreate()

        val parser = BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24")
        val region = Region("Labo region", parser, null, null, null)
        BeaconManager.getInstanceForApplication(this).startMonitoring(region)
        BeaconManager.getInstanceForApplication(this).addMonitorNotifier(this)
    
    }
}
```
[Source de ce code](https://altbeacon.github.io/android-beacon-library/background_launching.html)

Il faut aussi rajouter cette application custom dans l'android manifest pour qu'elle puisse se lancer. `<application android:name="ch.heigvd.iict.dma.labo2.Application"`

Après pour pouvoir arrêter ce monitoring en arrière plan, on pourrait mettre en place un bouton dans l'application qui pourrait mettre à jour une valeur dans les SharedPreference de l'application qui indique si on effectue ou non ce monitoring de l'application dans le `onCreate`.


### 1.1.3
> On souhaite trier la liste des balises détectées de la plus proche à la plus éloignée, quelles sont les valeurs présentes dans les annonces reçues qui nous permettraient de le faire ? Comment sont-elles calculées et quelle est leur fiabilité ?

Dans la librairie que nous utilisons pour gérer les beacons, il y a l'attribut `distance` qui fournit la distance en mètre entre le beacon et le récépteur. Cette distance est calculée à partir de la puissance d'émission `txPower` et de l'indicateur de la force du signal reçu `rssi`. 

Pour la librairie *AltBeacon*, celle-ci prend les mesures efféctuées dans les 20 dernières secondes, ignore le 10% des mesures les plus forte et le 10% des mesures les plus faibles afin d'éviter les valeurs abbérantes, pour finalement faire une moyenne des mesures restantes. Cette méthode permet d'éliminer au maximum le bruit présent sur la mesure des distances mais implique que quand un appareil bouge sa distance afffichée ne sera pas correcte avant qu'il soit statique pendant au minimum 20 secondes.
### 2.1.1
> Comment pouvons-nous déterminer notre position ? Est-ce uniquement basé sur notion de proximité étudiée dans la question 1.1.3, selon vous est-ce que d’autres paramètres peuvent être pertinents ?

Pour connaître notre position, nous pouvons utiliser la distance entre le téléphone et les beacons. Dans ce labo, nous n'avions que 2 beacons, mais en disposant d'un 3e, il serait possible de les placer stratégiquement dans une pièce pour pouvoir trianguler relativement à ceux-ci notre position. Si en plus on connait les coordonnées de où sont positionnés les beacons (et que ceux-ci ne bouge pas), alors il serait possible de connaitre précisement notre position.

### 2.1.2
> Les iBeacons sont conçus pour permettre du positionnement en intérieur. D’après l’expérience que vous avez acquise sur cette technologie dans ce laboratoire, quels sont les cas d’utilisation pour lesquels les iBeacons sont pleinement adaptés (minimum deux) ? Est-ce que vous voyez des limitations qui rendraient difficile leur utilisation pour certaines applications ?

Les iBeacons sont utiles par exemple pour retrouver un objet auquel le beacon est attaché ou bien dans une applications de visite guidée où un beacon serait positionné vers les points d'intérêts (comme dans un musée par exemple). Les beacons sont moins adaptés quand il faut calculer une position à partir de plusieurs beacons, car il faut être certains que ceux ne soit pas déplacés. De plus la distance n'est pas exacte comme la distance est une moyenne sur plusieurs mesures.
