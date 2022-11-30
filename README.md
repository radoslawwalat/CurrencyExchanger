# REST CurrencyExchanger

## Opis 
Aplikacja przeliczająca kursy walut przygotowana w Spring-Boot. Aktualne kursy walut są pobierane z API Nbp przy uruchamianiu aplikacji oraz są odświeżane raz dziennie. Dostęp do usługi można nawiązać przez metody GET i POST.

# Uruchamianie 
Żeby uruchomić aplikację należy znajdować się na ścieżce projektu i wywołać polecenie 

`docker-compose -up -d`

Uruchomiona jest na wbudowanym w springa serwerze lokalnym Tomcat na porcie 81
(Plik .jar jest już w projekcie dlatego łatwo uruchomić aplikację na serwerze zewnętrznym) 

## Funkcjonalność wymagana 

Listę dostępnych walut na których można wykonać przeliczenia oraz ich aktualne kursy możemy uzyskać kierując się na 

`http://localhost:81/list`<br>
lub <br>
`curl -X GET --location "http://localhost:81/list"` 

### Przeliczenie na podstawie kursu wymiany dostępne jest pod ścieżką /convert , przykładowo: 
Parametry:
- amount – kwota do przeliczenia
- baseCurrency – kod waluty podanej kwoty 
- targetCurrency – kod waluty docelowego przeliczenia

### GET
`http://localhost:81/convert?amount=100&baseCurrency=USD&targetCurrency=EUR` <br>
lub <br>
`curl -X GET --location "http://localhost:81/convert?amount=100&baseCurrency=USD&targetCurrency=EUR"`

### POST

`curl --header "Content-Type: application/json" --request POST --data '{"amount": 1000, "baseCurrency": "AUD", "targetCurrency": "USD"}' http://localhost:81/convert`
<br>
<br>
W kontenerze z bazą danych przechowywane są  wszystkie wywołania wykonywane na udostępnionych usługach

## Dodatkowa funkcjonalność niewymagana

Dodatkowo przygotowałem trzy pliki: haproxy.cfg, HAProxyDockerfile, loadbalancer.docker-compose.yml<br>
Po wywołaniu polecenia: 

`docker-compose -f loadbalancer.docker-compose.yml up --build -d` <br>

Uruchamiają się trzy serwisy aplikacji działające na localhost:80, baza danych która jest połączona ze wszystkimi oraz serwis HAProxy który powinien zachowywać się jak LoadBalancer.
Wywołując `curl -X GET --location "http://localhost:80/sleep"` można zasymulować 30 sekundowe opóźnienie od jednego z serwisów aplikacji. 


