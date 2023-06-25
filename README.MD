# Projekt testów automatycznych Sellenium w języku JAVA
## Informacje o projekcie
### Autor
    Mariusz Midzio
    Nr. albumu: 59035
    email: 59035-ckp@kozminski.edu.pl
    
### Testowana strona
W trakcie testów wykorzystywana będzie strona https://demob2b-xl.comarch.pl/

### Wykorzystane wzorce:
 - [X] PageObjectPattern
 - [X] PageFactory
 - [X] DataDriveTest
## Struktura projektu
### Klasy testowe
#### BaseTest [000] (klasa bazowa testów)

Klasa bazowa dla wszystkich testów zawiera wszystkie potrzebne strony.

##### Metoda setup()
* Inicjalizacja obiektów:
    + driver zgodnie z konfiguracją
    + maskymalizacja okna
    + uruchomienie strony głównej
##### Metoda tearDown()
* Sprzątanie po testach
    + usunięcie koszyka (jeżeli dodano)
    + wylogowanie z systemu (jeżeli zalogowano)
    + zamknięcie przeglądarki


#### Login test [100]
Testy logowania do systemu Comarch B2B. Przypadki testowe dotyczą zarówno poprawnego jak nie błędnego logowania.
##### TC101 Valid credentials login, terms accepted
Tests login with valid data.
###### Entry point: 
* Open browser 
* Navigate to: https://demob2b-xl.comarch.pl/ 
###### Tasks:
* Enter valid company name
* Enter valid user name
* Enter valid password
* Accept terms and conditions
* Click Log on button
###### Result:
* MainPage should be returned
##### TC102 Valid credentials login, terms not accepted
Tests login with valid data, do not accept terms and conditions
###### Entry point: 
* Open browser 
* Navigate to: https://demob2b-xl.comarch.pl/ 
###### Tasks:
* Enter valid company name
* Enter valid user name
* Enter valid password
* Do not accept terms and conditions
* Click Log on button
###### Result:
* Error should be displayed
##### TC103 invalid credentials login
Tests login with invalid data. 
###### Entry point: 
* Open browser
* Navigate to: https://demob2b-xl.comarch.pl/
###### Tasks:
* Enter invalid company name
* Enter valid user name
* Enter valid password
* Accept terms and conditions
* Click Log on button
###### Result:
* MainPage Error should be displayed

#### Cart test [200]
Testy dodawania produktów do koszyka. W ramach testów wykorzystywany jest widok kafelków. Testy są oparte o zestaw danych z pliku CSV.
##### TC201 Add items to cart
Tests adding products to cart based on csv file data. After adding product to cart verify if added quantity is equal to
data from csv file.
###### Entry point
* Open browser 
* * Navigate to: https://demob2b-xl.comarch.pl/
* Enter valid company name
* Enter valid user name
* Enter valid password
* Accept terms and conditions
* Click Log on button
###### Tasks:
* Click on a random product category
* Wait for items page to appear
* Click on thumbnail view button
* Open category provided in csv file
* Find item with code from csv file
* Set quantity from csv file
* Expand cart selection for product
* Chose new cart
* Add item to cart
* Go to the cart
* Check if quantity is equal to one provided
###### Results:
* Cart is added
* Item is added to cart
* Quantity in cart is equal to one from csv file

#### Items view test [300]
###### Entry point:
* Open browser
* Navigate to: https://demob2b-xl.comarch.pl/ 
* Enter valid company name
* Enter valid user name
* Enter valid password
* Accept terms and conditions
* Click Log on button
###### Tasks:
* Click on a random product category
* Wait for items page to appear\
* Click on thumbnail view button
###### Result:
* View is change to thumbnail type 
* Class of div is changed to display-type-2

#### Pending items  test [400]
###### Entry point:
* Open browser
* Navigate to: https://demob2b-xl.comarch.pl/ 
* Enter valid company name
* Enter valid user name
* Enter valid password
* Accept terms and conditions
* Click Log on button
###### Tasks:
* Click on a pending list button
* Wait for pending list to appear
* Count all items
* Get number from total lable
###### Result:
* List is loaded
* Items count from list is greater then 0 and equal to number from label
#### Attachment test[500]
###### Entry point:
* Open browser
* Navigate to: https://demob2b-xl.comarch.pl/
* Enter valid company name
* Enter valid user name
* Enter valid password
* Accept terms and conditions
* Click Log on button
###### Tasks:
* Click on a Customer Zone Icon
* Wait for Customer Zone to open
* Navigate do Downloads section
* Download a file
###### Result:
* File is downloaded in lest then 30s (default wait time)
* File is deleted after test finish
