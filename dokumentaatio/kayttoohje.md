# Asennusohje

Ohjelman suorittamista varten .jar tiedoston kanssa samassa polussa tulee olla config.properties -tiedosto, jossa on määritelty propertyt database (tietokannan nimi) ja databasePath (tietokannan sijainti, ./src/main/resources).

# Käyttöohje

## Uuden käyttäjän luominen

Reseptikirjaan voi luoda uuden käyttäjän painamalla aloitusruudulla nappia 'Create new user'. Tämän jälkeen tulee syöttää käyttäjätunnus (jonka tulee olla yli 3 merkkiä pitkä) sekä salasana (yli 5 merkkiä pitkä).

## Sisäänkirjautuminen

Reseptikirjaan voi sisäänkirjautua luodulla käyttäjällä syöttämällä käyttäjänimen ja salasanan. Sisäänkirjautumisen jälkeen aukeaa yleisnäkymä.

## Uuden reseptin luominen

Reseptikirjassa voi siirtyä reseptinluomisnäkymään painamalla nappia 'Add recipe'.

### Raaka-aineen lisääminen uuteen reseptiin

Uuden reseptin luomisen jälkeen ohjelmassa voi syöttää reseptiin raaka-aineita. Raaka-aineen pystyy lisäämään napista 'Add ingredient'. Raaka-aineita ei voi lisätä repsetiin jälkikäteen.

## Reseptin hakeminen

Käyttäjä voi hakea reseptikirjan reseptejä päänäkymästä kirjoittamalla reseptin nimen tekstikenttään ja klikkaamalla "Search". Haku löytää vain täysin oikealla nimellä haetut reseptit.
