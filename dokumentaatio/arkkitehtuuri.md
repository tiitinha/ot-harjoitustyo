# Arkkitehtuurikuvaus

## Rakenne

Ohjelman arkkitehtuuri on kolmikerroksinen ja perusrakenne koostuu kolmesta pakkauksesta: recipebook.ui, recipebook.domain ja recipebook.dao. Pakkaus recipebook.ui sisältää gravfisen käytöliittymän toetutuksen JafaFX:llä, recipebook.domin sisältää käyttöliittymäkoodin sekä ohjelman rakenteen kannalta tärkeät luokat ja recipebook.dao sisältää pysyväistallennuksesta vastavat luokat ja rajapinnat.

<img src="https://raw.githubusercontent.com/afroseppo/ot-harjoitustyo/77d1fe376c7805ee66a40e9d55e7112e77fed30d/dokumentaatio/kuvaajat/structure.svg" height="450">

## Käyttöliittymä

Käyttöiittymä sisältää viisi erillistä näkymää:
- kirjautuminen
- uuden käyttäjän luominen
- päänäkymä
- uuden repseptin luominen
- raaka-aineen lisääminen reseptiin
- reseptihaun tulokset

Jokainen näkymä on toteutettu omana Scene-oliona ja yksi näkymä on kerrallaan sijoitettuna sovelluksen stageen. Käyttöliittymä on ohjelmallisesti luokassa recipebook.ui.Gui

Käyttöliittymä on eristetty sovelluslogiikasta ja kutsuu sovelluslogiikkaluokan 'RecipebookService' olion metodeja.

## Sovelluslogiikka

Sovelluksen looginen datamalli muodostuu luokista User, Recipe ja Ingredient, jotka kuvaavat käyttäjiä, reseptejä sekä raaka-aineita. Luokkien väliset suhteet kuvattu alla:

<img src="https://raw.githubusercontent.com/afroseppo/ot-harjoitustyo/master/dokumentaatio/kuvaajat/luokatDomain.png" width="450">

Toiminnallisuudesta vastaa luokan RecipebookService olio, joka tarjoaa käyttöliittymän toiminnoille metodeita. Näitä metodeita ovat:
- void logout()
- User getLoggedUser()
- boolean createUser(String username, String password)
- boolean login(String username, String password)
- boolean createNewRecipe(String name, String user)

RecipebookService pääsee käsittelemään käyttäjä-, resepti-, ja reseptin raaka-ainetietoja rajapintojen RecipeDao ja UserDao toteuttavien luokkien kautta.

Alla oleva kuva esittää pakkausten ja luokkien suhdetta.

<img src="https://raw.githubusercontent.com/afroseppo/ot-harjoitustyo/master/dokumentaatio/kuvaajat/luokka_pakkaus.png" width="450">

## Tietojen pysyväistallennus

Pakkauksen recipebook.dao luokat DatabaseRecipeDao ja DatabaseUserDao käsittelevät tietojen tallentamisen h2-tietokantaan.

Luokat on toteutettu Data Access Object -mallilla, joten ne on mahdollista korvata jollain muulla toteutusmenetelmällä, jos tieton tallennustapaa halutaan vaihtaa esimerkiksi tekstiteidostoon tai muuhun vastaavaan.

### Tietokannat

Sovellus tallentaa käyttäjien, reseptien ja reseptiin kuuluvien raaka-aineiden tiedot erilliseen h2-tietokantaan. Sovelluksen juuressa on konfiguraatiotiedosto config.properties, joka määrittelee sekä tietokannan nimen, että tietokannan polun. Tietokantayhteyksiä käsitellään Javan java.sql-paketin avulla.

## Päätoiminnallisuudet

Sovelluksen toimintalogiikka kuvataan alla päätoiminnallisuuksien osalta.

### Kirjautuminen

Kun kirjautumisnäkymässä on syötetty käyttäjätunnus sekä salasana, klikattaessa painiketta Login sovelluksessa tapahtuu seuravaaa:

Painikkeen tapahtumakäsittelijä kutsuu sovelluslogiikan recipebookService metodia login parametreina käyttäjätunnus sekä salasana. Sovelluslogiikka hakee UserDaon findUserByName-metodia kutsumalla käyttäjää. Jos käyttäjänimellä löytyy käyttäjätunnus, niin recipebookService tarkistaa, että vastaako käyttäjän salasana syötettyä salasanaa. Jos salasana on oikein, palauttaa metodi totuusarvon true. Tällöin käyttöliittymä vaihtaa näkymäksi mainScenen eli sovelluksen päänäkymän, josta pääsee siirtymään muihin toiminnallisuuksiin (eli uuden reseptin luontiin) sekä voi hakea olemass olevia reseptejä.

### Uuden käyttäjän luominen

Syötettäessä uuden käyttäjän luomisnäkymään käyttäjä, jota ei ole olemassa ja painettessa painiketta Create user tapahtuu seuraavaa:

Tapahtumankäsittelijä kutsuu sovelluslogiikan recipebookService metodia createUser, joka luo uuden luokan User-olion ja kutsuu userDao:n metodia createUser paraetrina tämä User-olio. Jos käyttäjää ei ole olemassa, niin userDao lisää käyttäjän tietokantaan ja palauttaa totuusarvon true, muutoin se palauttaa totuusarvon false. Kun käyttäjä on luotu, niin käyttöliittymä vaihtaa näkymäksi loginScenen ja loginSceneen tulee ilmoitus "User successfully created".

### Reseptin lisäys

Reseptiä lisättäessä eli klikattaessa päänäkymässä painiketta 'Add a recipe' asettaa tapahtumankäsitteljiä uudeksi näkymäksi addRecipeScenen. Tässä näkymässä käyttäjä voi kirjoittaa reseptin nimen ja painamalla painiketta 'Add a new recipe' tapahtumankäsittelijä kutsuu recipebookService-luokan metodia createNewRecipe parametreina syötetty reseptin nimi sekä kirjautuneen käyttäjän nimi. Sovelluslogiikan metodi createNewRecipe kutsuu reipeDaon metodia addRecipe parametreillä reseptin nimi ja käyttäjän nimi. Sovelluslogiikka palauttaa tämän metodin palauttaman totuusarvon käyttöliittymälle. RecipeDao:n addRecipe-metodi tarkistaa, onko reseptikirjassa jo olemassa reseptiä samlla nimellä. Jos reseptin nimi on uniikki, niin metodi luo tietokantaan uuden reseptin. Tätä varten addRecipe-metodi kutsuu recipeDao:n metodia getUserId, joka hakee tietokannasta käyttäjätaulusta reseptiä lisäävän käyttäjän id:n ja palauttaa sen. Tämän jälkeen addRecipe-metodi lisää reseptitauluun uuden reseptin, lisää reseptin recipeDao-luokan listamuuttujaan recipes  ja palauttaa totuusarvon true. Tämän jälkeen tapahtumankäsittelijä asettaa näkymäksi ingredientScenen, jossa reseptiin voi lisätä raaka-aineita.

### Raaka-aineen lisäys reseptiin

Käyttäjän syötettyä raaka-aineen nimen, määrän sekä yksikön ja klikattua näppäintä 'Add ingredient' tapahtumakäsittelijä tarkistaa ensin, että onko raaka-aineen määrä numeerinen. Tämän jälkeen määrän ollessa numeerinen kutsuu tapahtumakäsittelijä sovelluslogiikan metodia addIngredient parametreinä raaka-aineen nimi, määrä ja yksikkö.

Sovelluslogiikan metodi addIngredient luo ensin Recipe-luokan olion ja asettaa olioksi metodin fetchRecipe palautusarvona saatavan olion (fetchRecipe hakee reseptin nimellä resptiä ja palautaa Recipe-luokan olion). Tämän jälkeen metodi luo uuden Ingredient-luokan olion parametreinä nimi, määrä ja yksikkö. Tämän jälkeen addIngredient kutsuu luodun reseptiolion metodia addIngredient parametreinä nimi, määrä ja yksikkö. Reseptiluokan metodi addIngredient tarkistaa, että onko reseptissä jo samaa raaka-ainetta lisättynä. Jos raaka-ainetta ei vielä ole lisättynä, lisätään se ja palautetaan totuusarvo true.

Tämän jälkeen sovelluslogiikan metodi addIngredient kutsuu recipeDao-luokan metodia addIngredient parametreina raaka-aineluokan olio sekä reseptin nimi. RecipeDao:n metodi addIngredient tarkistaa onko reseptiä, johon raaka-ainetta ollaan lisäämässä olemassa. Jos on, niin kutsutaan metodia getRecipeId, joka hakee reseptin id:n tietokannasta. Tämän jäkeen lisätään reseptille tietokannan raaka-ainetauluun uusi raaka-aine. 

### Reseptin haku

Klikattaessa päänäkymän painiketta 'Search' tapahtumakäsittelijä asettaa näkymäksi metodin searchResultScene palauttaman Scene-olion. Metodi searchResultScene saa parametreina etsittävän reseptin nimen (tekstikentän merkkijonoarvo) sekä Stage-luokan olion. Metodi kutsuu sovelluslogiikan metodia fetchRecipe, joka edelleen kutsuu ja palauttaa recipeDao:n fetchRecipe-metodin palautusarvon. RecipeDao:n fetchRecipe-metodi kutsuu palauttaa reseptiluokan olion, jos reseptin nimellä löytyy resepti. Tämän jälkeen käyttöliittymäluokan metodi searchRecipeScene kutsuu reseptiluokan metodia getIngredients, joka palauttaa reseptiluokan luokkamuuttujan recipes (joka on lista). Tämän jälkeen käyttöliittymä iteroi raaka-ainelistan läpi ja kutsuu jokaisen raaka-aineen luokkametodia toString.

## Ohjelman rakenteen puutteet

### DAO-luokat

Tietokannan datankäsittelyn DAO-luokkiin on jäänyt pituudeltaan pitkiä metodeita, joissa tehdään suoria tietokantahakuja sekä asetetaan tietokantaan dataa. Lisäksi luokissa on toisteista koodia, sillä tietokantaan tallentaminen sekä tietokannasta haku ovat jokaisella kerralla melko samankaltaisia.

## Käyttöliittymä

Käyttöliittymän toteutukse JavaFX:llä voisi korvata esim. FXML-määrittelyllä, jotta koodin selkeys lisääntyisi (suuri määrä sovelluslogiikkaa ja tapahtumakäsittelijöitä ja GUI-elementtejä sekaisin). Lisäksi käyttöliittymän visuaalinen ilme olisi vaivattomampi saada kuntoon FXLM-määrittelyllä (ilman ylimääräisiä rivejä koodia).
