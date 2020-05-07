# altenTask
*This repository was created for the application task given by Alten GmbH*

In diesem Repository finden Sie den Code für die erfüllte Aufgabe über die MERGE Funktion. Ich habe mich dabei für die Programmiersprache Java entschieden, da meine Bewerbung bei der Alten GmbH für diesen Bereich war.

Die Aufgabe bestand darin, eine Funktion zu entwickeln, die eine gegebene Liste aus Intervallen anhand der Überlappungen zusammenfasst und eine resultierende Liste aus unabhängigen Intervallen zurückgibt.
Ein möglicher Lösungsansatz wäre, das erste Intervall zu nehmen und dieses mit jedem anderen Intervall auf Überlappungen zu vergleichen. Anschließend entfernt man alle überlappenden Intervalle und gibt das resultierende zusammengefasste Intervall aus. Dies Wiederholt man solange, bis die Liste leer ist und man alle unabhängigen Intervalle gefunden hat. Diese Methode würde eine Laufzeit von O(n^2) bedeuten, da die Liste bei jedem Durchlauf ganz betrachtet werden muss.

Meine Lösung hat einen ähnlichen Ansatz, jedoch wird durch eine zuvor durchgeführte Sortierung nach ansteigendem Anfang der Intervalle die Laufzeit verbessert. Die Sortierung hat dabei eine O(nlog(n)) Laufzeit und der anschließende Durchlauf der sortierten Liste eine lineare Laufzeit O(n).

Das Programm ist in zwei Klassen aufgeteilt. Der *IntervalSorter* dient dazu, die anfängliche Sortierung der Liste durchzuführen. Hier habe ich mich der java.util zugewandt, da es für solche Aufgaben die passenden Werkzeuge liefert, namentlich java.util.Collections, die bereits eine passende *sort* Funktion bereitstellt. Um diese nutzen zu können, benötigt die Klasse Interval einen java.util.Comparator, der speziell auf die zu vergleichenden Parameter angesetzt ist.

Die Klasse *Interval* enthält neben der *main*-Funktion das eigentliche Ziel der Aufgabe, die *merge*-Funktion. Dabei wird zu Beginn geprüft, ob die Eingabe überhaupt Intervalle beinhaltet. Danach wird die übergebene Liste sortiert und ist bereit für den Durchlauf. Ich arbeite dabei auf einer temporären Liste, damit der eigentliche Input unversehrt bleibt. Hier könnte man Speicherplatz sparen und die Funktion direkt auf die Eingangsliste durchführen.
Die Liste wird durchlaufen, bis ein Intervall gefunden wird, dass nicht mehr überlappt. Die Listeneinträge davor und das Gesamtintervall aus dem bisherigen niedrigsten Start und höchsten Ende wird in die Ausgabeliste hinzugefügt. Die überlappenden Intervalle werden aus der Liste entfernt und der Algorithmus beginnt beim Intervall, das keine Überlappung gezeigt hat. Dies wird wiederholt, bis die Liste nur noch ein oder kein Intervall mehr besitzt. Wenn ein Intervall übrig bleibt, ist dies automatisch unabhängig von allen bisherigen Intervallen und kann in die resultierende Liste eingefügt werden.

Um das Programm zu testen, habe ich eine einfache manuelle Liste erstellt und die Funktion darauf angewandt. Das Result wurde dann zum Test in die Konsole ausgegeben.
Verbesserung hinsichtlich Robustheit könnte man anhand einer Baumstruktur gewinnen. Da die Liste sortiert ist, kann sie aufgeteilt werden und der Algorithmus auf die einzelnen Zweige angewandt werden. Durch die Sortierung sind die Zwischenresulte der einzelnen Blätter ebenfalls aufsteigend sortiert und können deswegen genauso durchlaufen werden.
Dadurch, dass die temporäre Liste während des Durchgangs schrumpft, verbessert sich das Speicherverbrauch. 

Ingesamt habe ich 8 Stunden für die Aufgabe benötigt. Dies beinhaltet das wieder hinkommen in Java, da ich schon etwas länger nicht mehr damit gearbeitet habe.

Das Programm habe ich als runnable JAR-Datei exportiert und ein ANT-Skript bereitsgestellt, um das Projekt zu bauen und Abhängigkeiten zu verfizieren. Falls eine WAR-Datei benötigt wird, kann diese durch *ant war* in der Commandline erstellt werden. Dies setzt jedoch eine ANT-Installation voraus.
