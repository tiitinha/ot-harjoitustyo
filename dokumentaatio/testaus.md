# Testausdokumentti

## Yksikkö- ja integraatiotestaus

Integraatiotestaus on toteutettu manuaalisesti testaamalla sovelluksen toimintaa. 

### Sovelluslogiikka

Sovelluslogiikkakerroksen luokille User, Recipe ja Ingredient on muutama yksikkötesti, jotka kattavat tapaukset (kuten olioiden equals-metodit), joita ei kateta integraatiotesteissä. Lisäksi olioiden sisäisiä toiminnallisuuksia, kuten raaka-aineen lisäämistä reseptiin, testataan. 

### DAO-luokat

DAOn luokille UserDao ja RecipeDao on muutama yksikkötesti, joka testaa, että luokkien toiminnot (tietojen tallennus sekä haku) toimii toivotusti. Testaamista varten käytetään testitietokantaa, jonne tallennetaan ja josta haetaan tietoa testeissä. Lisäksi tietokannan luomisesta vastaavaa DatabaseService-luokkaa testataan testiluokassa DatabaseTest.

### Testikattavuus

