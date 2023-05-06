# Projekt testów automatycznych 
## Informacje o projekcie
### Testowana strona
W trakcie testów wykorzystywana będzie strona https://demob2b-xl.comarch.pl/

### Wykorzystane wzorce:
 - [X] PageObjectPattern
 - [X] PageFactory
 - [X] DataDriveTest

## Testy
### Login test [100]
Testy logowania do systemu Comarch B2B. Przypadki testowe dotyczą zarówno poprawnego jak nie błędnego logowania.
#### TC101 Valid credentials login, terms accepted
Tests login with valid data. Entry point: 
1. Open browser 
2. Navigate to: https://demob2b-xl.comarch.pl/ 
Tasks:
1. Enter valid company name
2. Enter valid user name
3. Enter valid password
4. Accept terms and conditions
5. Click Log on button
Result:
1. MainPage should be returned
#### TC102 Valid credentials login, terms not accepted
Tests login with valid data. Entry point: 
1. Open browser 
2. Navigate to: https://demob2b-xl.comarch.pl/ 
Tasks:
1. Enter valid company name
2. Enter valid user name
3. Enter valid password
4. Do not accept terms and conditions
5. Click Log on button
Result:
1. Error should be displayed
#### TC10# Valid credentials login, terms not accepted