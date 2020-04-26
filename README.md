# Recipebook

Sovelluksen avulla käyttäjät voivat koota reseptikirjaa (lisätä uusia reseptejä sekä selata ja etsiä muiden lisäämiä reseptejä). Sovellusta voi käyttää useampi rekisteröitynyt käyttäjä, jotka voivat hallinnoida (poistaa) omia reseptejään.

## Dokumentaatio
[Käyttöohje](https://github.com/afroseppo/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/afroseppo/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/afroseppo/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/afroseppo/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md)

[Työaikakirjanpito](https://github.com/afroseppo/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Releaset

Viikko 6

## Komentorivitoiminnot

### Testaus
Testit suoritetaan komennolla
``` mvn test```

Testikattavuusraportti luodaan komennolla
```mvn jacoco:report```

Kattavuusraporttia voi luonnin jälkeen tarkastella tiedostosta target/site/jacoco/index.html avaamalla tiedoston selaimella.

### Suoritettavan jarin generointi

Suoritettavan jarin voi luoda komennolla
```mvn package```
hakemistoon target. Komento generoi jar-tiedoston recipebook-1.0-SNAPSHOT.jar. Hakemistossa tulee olla config.properties -tiedosto.

### JavaDoc
JavaDoc generoidaan komennolla
```mvn javadoc:javadoc```

JavaDocia pääsee tarkastelemaan avaamalla tiedoston target/site/apidocs/index.html

### Checkstyle
Tiedostossa [checkstyle.xml](https://github.com/afroseppo/ot-harjoitustyo/blob/master/recipebook/checkstyle.xml) määritellyt tarkistukset voi suorittaa komennolla 
```mvn jxr:jxr checkstyle:checkstyle```

Virheilmoitukset voi tarkastaa avaamalla tiedoston target/site/checkstyle.html
