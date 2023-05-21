# REST CurrencyExchanger

## Description
An application for currency exchange rates conversion prepared in Spring Boot. Current currency rates are fetched from the NBP API upon application startup and refreshed once a day. Access to the service can be made through GET and POST http methods. 

## Deployment
To run the application, you need to navigate to the project working directory and execute the following command:

`docker-compose -up -d`

It runs on the built-in Tomcat server in Spring on port 81.
(The .jar file is already in the project, making it easy to run the application on an external server.)

## Main Functionality

You can obtain a list of available currencies for conversion and their current rates by navigating to:

`http://localhost:81/list`<br>
or<br>
`curl -X GET --location "http://localhost:81/list"`

### Conversion based on the exchange rate is available under the path /convert, for example:
Parameters:
- amount – amount to convert
- baseCurrency – currency code of the provided amount
- targetCurrency – currency code for the conversion target

### GET
`http://localhost:81/convert?amount=100&baseCurrency=USD&targetCurrency=EUR`<br>
or<br>
`curl -X GET --location "http://localhost:81/convert?amount=100&baseCurrency=USD&targetCurrency=EUR"`

### POST
`curl --header "Content-Type: application/json" --request POST --data '{"amount": 1000, "baseCurrency": "AUD", "targetCurrency": "USD"}' http://localhost:81/convert`
<br>
<br>
All the requests made to the provided services are stored in the database container.

## Load Balancing using HAProxy

I have prepared three files: haproxy.cfg, HAProxyDockerfile, loadbalancer.docker-compose.yml.
By executing the command:

`docker-compose -f loadbalancer.docker-compose.yml up --build -d` <br>

Three application services are launched, running on localhost:80, with a database that is connected to all of them, and an HAProxy service acting as a load balancer.
By calling `curl -X GET --location "http://localhost:80/sleep"`, you can simulate a 30-second delay from one of the application services. Then, by making multiple calls to `curl -X GET --location "http://localhost:80/list"`, you will always receive a response because HAProxy automatically detects and disables the sleeping service from the pool of available nodes, which would otherwise load the error response for 30 seconds.


