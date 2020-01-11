Changelog
===
# Version 1.1.0 vom 10.01.2020
## Dokumentation 
* [howto.dita](documentation/howto.dita): Erläuterung hinzugefügt
* [introduction_en.dita](documentation/introduction_en.dita): Grammatikfehler verbessert
* [legende.dita](documentation/legende.dita): Erläuterung zu "manuscripts" hinzugefügt
* [uebersichtText.dita](documentation/uebersichtText.dita): manuskriptspezifische Elemente hinzugefügt
* [ziel.dita](documentation/ziel.dita): Absatz "Mit der Ausarbeitung" ergänzt 
* [klTabelle.dita](documentation/klTabelle.dita): related-links-Section entfernt [Darstellungsfehler]; cf. [#7](../../issues/7)
* [trNasalstrich.dita](documentation/trNasalstrich.dita): Dokumentation von U+0315 für Abkürzungsstriche; cf. [#9](../../issues/9)
* [fnFortlaufend.dita](documentation/fnFortlaufend.dita): Typo korrigiert; cf. [#11](../../issues/11)
* [mdUeberblick.dita](documentation/mdUeberblick.dita):
    * Elemente in `<resp>` in korrekte Reihenfolge gebracht; cf. [#18](../../issues/18)
    * Elemente in `<biblFull>` in korrekte Reihenfolge gebracht; cf. [#19](../../issues/19)
* [brAllg.dita](documentation/brAllg.dita): Typo korrigiert [#31](../../issues/31)
* [uebersichtText.dita](documentation/uebersichtText.dita): Dokumentation `@target`-Attribut angepasst: Einschränkung auf externe Ressourcen aufgehoben; cf. [#36](../../issues/36)
* [inhaltsverzeichnis.dita](documentation/inhaltsverzeichnis.dita): Hinweis, dass `<ref>` auch `@target` enthalten kann, eingefügt; cf. [#36](../../issues/36)
* [einrueckung.dita](documentation/einrueckung.dita): Kodierungsfehler im Beispiel behoben; cf. [#37](../../issues/37)
* [tbAllg.dita](documentation/tbAllg.dita): `@type="series"` in `<titlePart>` ergänzt; cf. [#11](../../issues/46)
* [introduction_en.dita](documentation/introduction_en.dita): falsche Verlinkung Vorlagedatei korrigiert; cf. [#47](../../issues/47)
* [abbKennzeichnung.dita](documentation/abbKennzeichnung.dita): `@facs` in `<figure>` dokumentiert; cf. [#65](../../issues/65)
* [absatz.dita](documentation/absatz.dita): Kodierungsbeispiel korrigiert; cf. [#13](../../issues/13) 
* [leitlinien.dita](documentation/leitlinien.dita): neu veröffentlicht
* [steuerungsgruppe.dita](documentation/steuerungsgruppe.dita): neu veröffentlicht
* [impressum.dita](documentation/impressum.dita): Steuerungsgruppe ergänzt
* [mdPubStmtAllg.dita](documentation/mdPubStmtAllg.dita): neue `<idno>`-Werte dokumentiert
* [mdSdMsDesc.dita](documentation/mdSdMsDesc.dita): neue `<idno>`-Werte dokumentiert
* [mdKtAufnahme.dita](documentation/mdKtAufnahme): Typo verbessert
* allgemein: Zitierempfehlung im Footer ergänzt; zugehörige Themen bei übergeordneten Topics in den Sektionen "Transkription", "Metadaten", "Formal", "Inhaltlich", "Spezial" ergänzt

## Schema

### Update auf TEI 3.5.0
* keine Änderungen notwendig

### Update auf TEI 3.4.0
* `att.textCritical` entfernt
* `att.global.source` entfernt
* `data.count` und `data.pointer` werden `teidata.count` und `teidata.pointer`

### Update auf TEI 3.2.0
* `att.global.source` entfernt
* `@when` in `<title>` entfernt 

### Sonstige Anpassungen
* `"data.count"` &rarr; `"teidata.count"`
* `"data.pointer"` &rarr; `"teidata.pointer"`
* `"tei.global.source"` eliminiert
* `@key` in `<publisher>` eliminiert
* `@status` in `<availability>` entfernt
* `@sameAs` in `<channel>` entfernt

### [DTABf_all](schema/basisformat_all.odd):
* `<div>` erhält neue `@type`-Werte `"jVarious"`, `"jCulturalNews"` und `"jLocal"` für Zeitungen (Vermischtes, Kulturnachrichten und Lokalnachrichten)
* Typo in `<div>`-Typen-Legende behoben
* Tickets [#68](../../issues/68) und [#76](../../issues/76) gelöst: `<idno>`erhält neue `@type`-Werte `"PPN"`, `"VD"`, `"URLIIIF"`, `"DOI"`
* Ticket [#59](../../issues/59) gelöst: Wert `DTACorpusPublisher` für `//teiHeader//editor/@corresp` und `//teiHeader//publisher/@xml:id` optional
* Update des DTABf-teiHeader für CMDI 1.2: added `<abstract>`, `<textDesc>` in `header` module, added module `corpus` with `<channel>`, `<channel>` with `@mode="w"` (Ticket [#6](../../issues/6) )
* Fehler behoben: `@type` in `<title> ist wieder "required"
* Unschönheiten am ODD: Elementspezifikationen der Header-Elemente `<abstract>`, `<channel>`, `<typeDesc>` von den Body- zu den Header-Elementen sortiert; Typos behoben

### [Schematron](schema/schematron):
* Ticket [#40](../../issues/40) gelöst: Regel prüft nun, ob `<quote>` "parent" von `<bibl>` ist (nicht "ancestor") (E0003)
* Ticket [#10](../../issues/10) gelöst: Majuskel I wird in `<note type="editorial"/>` nicht mehr beanstandet (W0004)

## Repository
* new "dist" directory for derivatives of ODD chaining
* new "scripts" directory for scripts and software useful in the DTABf context

# Version 1.0 vom 01.03.2017
* Initial commit