Changelog
===

# Version 1.1.0
## Dokumentation 
* [howto.dita](documentation/howto.dita): Erläuterung hinzugefügt
* [introduction_en.dita](documentation/introduction_en.dita): Grammatikfehler verbessert
* [legende.dita](documentation/legende.dita): Erläuterung zu "manuscripts" hinzugefügt
* [uebersichtText.dita](documentation/uebersichtText.dita): manuskriptspezifische Elemente hinzugefügt
* [ziel.dita](documentation/ziel.dita): Absatz "Mit der Ausarbeitung" ergänzt 
* [klTabelle.dita](documentation/klTabelle.dita): related-links-Section entfernt [Darstellungsfehler]; cf. [#7](../../issues/#7)
* [trNasalstrich.dita](documentation/trNasalstrich.dita): Dokumentation von U+0315 für Abkürzungsstriche; cf. [#9](../../issues/#9)
* [fnFortlaufend.dita](documentation/fnFortlaufend.dita): Typo korrigiert; cf. [#11](../../issues/#11)
* [mdUeberblick.dita](documentation/mdUeberblick.dita):
    * Elemente in `<resp>` in korrekte Reihenfolge gebracht; cf. [#18](../../issues/#18)
    * Elemente in `<biblFull>` in korrekte Reihenfolge gebracht; cf. [#19](../../issues/#19)
* [brAllg.dita](documentation/brAllg.dita): Typo korrigiert [#31](../../issues/#31)
* [uebersichtText.dita](documentation/uebersichtText.dita): Dokumentation `@target`-Attribut angepasst: Einschränkung auf externe Ressourcen aufgehoben; cf. [#36](../../issues/#36)
* [inhaltsverzeichnis.dita](documentation/inhaltsverzeichnis.dita): Hinweis, dass `<ref>` auch `@target` enthalten kann, eingefügt; cf. [#36](../../issues/#36)
* [einrueckung.dita](documentation/einrueckung.dita): Kodierungsfehler im Beispiel behoben; cf. [#37](../../issues/#37)
* [tbAllg.dita](documentation/tbAllg.dita): `@type="series"` in `<titlePart>` ergänzt; cf. [#11](../../issues/#46)
* [introduction_en.dita](documentation/introduction_en.dita): falsche Verlinkung Vorlagedatei korrigiert; cf. [#47](../../issues/#47)
* [abbKennzeichnung.dita](documentation/abbKennzeichnung.dita): `@facs` in `<figure>` dokumentiert; cf. [#65](../../issues/#65)
* [absatz.dita](documentation/absatz.dita): Kodierungsbeispiel korrigiert; cf. [#13](../../issues/#13) 

## Schema
### Update auf TEI 3.4.0
* `att.textCritical` entfernt
* `att.global.source` entfernt
* `data.count` und `data.pointer` werden `teidata.count` und `teidata.pointer`

### [DTABf_all](schema/basisformat_all.odd):
* `<div>` erhält neue `@type`-Werte `"jVarious"` und `"jLocal"` für Zeitungen (Vermischtes und Lokalnachrichten)

### [Schematron](schema/schematron):
* Ticket [#40](../../issues/#40) behoben: Regel prüft nun, ob `<quote>` "parent" von `<bibl>` ist (nicht "ancestor") (E0003)
* Ticket [#10](../../issues/#10) behoben: Majuskel I wird in `<note type="editorial"/>` nicht mehr beanstandet (W0004)


# Version 1.0 vom 01.03.2017
* Initial commit