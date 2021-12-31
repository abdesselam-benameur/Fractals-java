# ProjetCPOO-Fractales
Projet de Compléments en Programmation Orientée Objet : Fractales - L3 Info générale - Université de Paris

## Comment compiler le projet
Le projet est déjà compilé et le fichier .jar existe dans le répertoire du projet

## Comment lancer le projet
Pour lancer le projet, il suffit de lancer le programme depuis la ligne de commande
en introduisant cette commande `java -jar ProjetCPOO.jar` en spécifiant les bonnes
options. Voici le message d'aide de la ligne de commande :

```
usage: java -jar ProjetCPOO.jar
-ci <arg>             imaginary part of the constant
-cr <arg>             real part of the constant
-f,--filename <arg>   the filename of the generated image
-g,--gui              launch the GUI version
-h,--height <arg>     height of the image
-help                 print this message
-m,--maxIter <arg>    max iterations
-mandelbrot           generate mandelbrot set image
-w,--width <arg>      width of the image
-xmax <arg>           X_MAX
-xmin <arg>           X_MIN
-ymax <arg>           Y_MAX
-ymin <arg>           Y_MIN
-z,--zoom <arg>       zoom factor
```

Pour la version graphique, on a l'ensemble des champs à remplir, puis on génère
l'image. Une fois l'image générée, on peut zoomer par un clic gauche sur l'image
ou bien dézoomer par un clic droit.

## Les principaux choix techniques
On a créé une classe abstraite `FractalSetGenerator` qui contient les méthodes de
génération de fractales. On a ensuite créé deux classes filles `JuliaSetGenerator`
et `MandelbrotSetGenerator` qui implémentent les fractales de Mandelbrot et de Julia.