# How to use and setup this application

## Running
If you are new to spring boot please refer to [official spring boot documentation](https://docs.spring.io/spring-boot/docs/2.7.0/reference/html/using.html#using.running-your-application).

## Setup
As spring boot was used to create this application most of the things are set up out of the box. Only thing left to set up is the [APILayer](https://apilayer.com/) api key. It should be set in the `freefi.exchange.integration.api-key` property. It is confidential, therefore, please do not commit the `application.properties` file with the set value. You can configure it in many ways, as described in [spring boot documentation](https://docs.spring.io/spring-boot/docs/2.7.0/reference/html/features.html#features.external-config).

# How to use this application - curl examples

## Validating SSN
If you need to check if `131052-308T` is a proper finnish (`FI`) ssn then you need to execute such request:

`curl -X POST -H 'Content-Type: application/json' -i 'http://localhost:8080/validate_ssn?country_code=FI' --data '{"ssn":  "131052-308T"}'`

## Exchange
If you would like to check how much `EUR` you can get for `2` `USD` then you should run such request:

`curl -X GET -i 'http://localhost:8080/exchange_amount?from=USD&to=EUR&from_amount=2'`

# How to dockerize this application
In case you would like to get a docker image of this application please use default spring mechanism - just invoke `mvn spring-boot:build-image` as described in [documentation](https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/#build-image).
