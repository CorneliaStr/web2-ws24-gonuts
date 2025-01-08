<img src="frontend-vue/src/assets/images/GoNutsLogo.jpg" alt="drawing" width="100"/>

# Wiki
* [Projektsteckbrief](https://github.com/CorneliaStr/web2-ws24-gonuts/wiki/Projektsteckbrief)
* [Funktionale Anforderungen](https://github.com/CorneliaStr/web2-ws24-gonuts/wiki/Funktionale-Anforderungen)
* [Fachmodell](https://github.com/CorneliaStr/web2-ws24-gonuts/wiki/Fachmodell)
* [Oberflächenkonzept](https://github.com/CorneliaStr/web2-ws24-gonuts/wiki/Oberfl%C3%A4chenkonzept)


# Getting Started 

Der Code für die Abnahme befindet sich alles im Branch main.
Der Code ist in 4 Ordner unterteilt.   
* **clickable-prototype**
  * Klick-Prototyp zum Oberflächenkonzept 
* **frontend-csr**
  * Wie in Aufgabe 7 gefordert, befinden sich in diesem Ordner alle "CSR-Views", die nur native Browser-API aufrufe verwenden
* **frontend-vue**
  * Single-page application (SPA) auf Vue-Basis 
* **goNuts**
  * Spring-Boot-Projekt mit Persistenz- und Fachlogikschicht
  * Serverseitig erzeugte Views (Thymeleaf)
  * REST-API Controller, die JSON und XML unterstützen
  * GraphQL-API Controller


# User 
| E-Mail          | Passwort | Rolle |
|-----------------|----------|-------|
| user@gmail.com  | user     | Kunde |
| admin@gmail.com | admin    | Admin |

# Hinweise 
Admin Seiten sind nur erreichbar, wenn man als Admin angemeldet ist. 
Anhand von Routing-Guards wird der Zugriff darauf anderen Nutzern verwehrt.