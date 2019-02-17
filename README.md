# DataFetchingService

This is a Console tool to fetch online store product data in Specific Json format from SainsBury's Test store category page https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for Production/development and testing purposes. 

### Prerequisites

you need to have Java Java SE 8u202 and latest version of MAVEN on your mechine.


### Installation Instructions

Download this repository as Zip file from github and Un-Zip Code. 
CD into the folder containing the code
```
cd sbury
```

And Run the following command to start the Console tool

```
java -jar .\target\sbury-1.0.0-SNAPSHOT-jar-with-dependencies.jar
```
### User Guide

after going through above installasion instructions the Console tool should show the Fetched Data in Json format.
#### Fetched JSON as shown below

![jsonscreetshot](https://user-images.githubusercontent.com/13693247/52543742-da7c2900-2da3-11e9-8b77-17eddbdbe483.PNG)

### Fetching Invalid Url to Show Error Logging
![errorwarning](https://user-images.githubusercontent.com/13693247/52784644-afb4fd80-304d-11e9-83f1-318c390a935e.PNG)

## Running the System tests

To run the tests for this system

run the following command

```
mvn test
```
it will show the outcomes of the test as shown below.

![testscreenshot](https://user-images.githubusercontent.com/13693247/52544294-dfdb7280-2da7-11e9-838f-81fcb999dcaa.PNG)


## Built With

* [MAVEN](https://maven.apache.org/) - The build automation tool
* [Junit](https://junit.org/junit4/) - The Unit Testing Package
* [Jsoup](https://jsoup.org/) - Jsoup is a Java HTML Parser Package 
* [Gson](https://google.github.io/gson/apidocs/com/google/gson/package-summary.html) - Gson is Json Parser Package

## Design Details

As shown below the illustration the Desigh of Softwear Architecture

![newdigram](https://user-images.githubusercontent.com/13693247/52784614-9c099700-304d-11e9-8941-9d32d3cecd65.PNG)


## Authors

* **Adil Khan** - *Design & Build* - [LinkedIn](https://uk.linkedin.com/in/adil-khan-466155b7)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
