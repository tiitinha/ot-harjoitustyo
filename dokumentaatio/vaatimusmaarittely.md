# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen tarkoitus on mahdollistaa käyttäjille syöttää uusia reseptejä sekä etsiä olemassa olevia reseptejä. Sovellus mahdollistaa useamman käyttäjän, jotka voivat lisätä omia reseptejä sekä lukea toisten reseptejä.

## Käyttäjät

Aluksi sovelluksessa on käyttäjäroolina ''normaali käyttäjä'', joka kykenee ainoastaan luomaan uusia reseptejä sekä poistamaan luomiaan reseptejä. Myöhemmin sovellukseen saatetaan lisätä ''pääkäyttäjä'', jolla on oikeus poistaa myös muiden lisäämiä reseptejä.

## Käyttöliittymäluonnos

## Toiminnallisuus

### Ennen kirjautumista

- käyttäjä voi luoda uuden käyttäjän
	- käyttäjänimen tulee olla uniikki ja vähintään viisi merkkiä pitkä, salasanan tulee olla 8 merkkiä pitkä
- käyttäjä voi kirjautua reseptikirjasovellukseen
	- jos käyttäjää ei ole olemassa, ohjelma ilmoittaa siitä

### Kirjautumisen jälkeen

- käyttäjä näkee perusnäkymän, josta voi valita eri toiminnallisuuksia
	- toiminnallisuuksia ovat uuden reseptin luominen, reseptien etsiminen sekä omien reseptien tarkastelu
- käyttäjä voi luoda uuden reseptin
	- reseptille tulee lisätä nimi sekä raaka-aineita ja työvaiheita
	- uusi luotu resepti näkyy kaikille käyttäjille
- käyttäjä voi etsiä reseptejä järjestelmästä
	- reseptiä voi hakea reseptin nimellä
- käyttäjä voi kirjautua ulos järjestelmästä

## Jatkokehitysideoita

- muiden lisäämien reseptien arvostelu
- jo luotujen reseptien muokkaaminen
- kuvien lisääminen reseptiin
- reseptien luokittelu eri kategorioihin (tagit)
- reseptien selaaminen ja filtteröinti eri tekijöiden perusteella
