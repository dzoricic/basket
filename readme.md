# Simple Basket Management App

## Introduction

The goal of this project is to showcase a simple backend application using spring boot framework, maven, simple dockerization and h2 database.<br/>
There will be another project for frontend part in React with TypeScript coming soon.

## Content
I will use this paragraph to showcase features added to the application

### How To Start

The application doesn't have prod/dev environment so all of the properties are stored in application.yml

#### Local Machine
To run on local machine using IntelliJ, right click on hr.abysalto.hiring.mid.**Application.java** and hit **run Application.main()**

#### Dockerized Environment
To run, I suggest creating a configuration for running Dockerfile in IntelliJ.
Steps:
* Edit Configurations...
* \+ -> Docker -> Docker Compose
* Select docker-compose.yml from root 
* In modify options, select the option to always build new image 
* Add Before Launch "Run Maven Goal" **clean package -DskipTests**

This will open 2 containers and a connection between them. It will also expose the ports on local machine.

### Features

* Login and Registration
* Get Products with size and page
* Get single product
* Add and Remove from favourites
* Get list of favourites

### Misc

## Changelog
Self explanitory

## 0.0.1 - The Baseline
I've had to set my private laptop up since I completely restarted it half a year ago since it was old and slow.
I forked the project and started installing all the necessary tools (IntelliJ, VSCode, JDK, Node, Docker Desktop...)
<br/><br/>
In the first part I familiarized myself with the layout of the project. The project seems to hold only the "service" project used to store main logic of the service.
We can regard this as a monolith since I'm not planning to overcomplicate the solution with microservices.
<br/><br/>
I dockerized the application (I didn't really complicate it, I've written a simple Dockerfile that IntelliJ uses to build an image and create a container in Docker Desktop)
I connected the h2 database, enabled the console and initialized the database.
<br/><br/>
Also I'm not using any ci/cd here so I'll just uplift versions manually through separate commits.

## 0.0.2 - First Requests

* Added DummyJsonApiClient that works as an adapter towards DummyJson API
  * Including Dto models
* Added ProductService to hold business logic regarding products
  * transforming API model into client model
  * calling the service
  * validating pagination
* Added ProductController as an interface between the client/frontend and the services
* Added PaginationLimits annotation to showcase validation through annotation
* Added ProductFilterMapper and ProductsResponseMapper to show mapper (final class with static fields)
* Added RestControllerExceptionHandler to prettify messages towards clients
* And More, I'm writing this before committing, so I may forget something now and then.

* I'll focus more on Tests next commit to master

## 0.0.3 - Tests And First Refactors

* Refactored to package by feature
* Introduced 3 models (I do know that it doesn't make sense to do it logically but I wanted to differentiate models even though I'm basically passing the same data through system)
  * Api model exposed to client
  * Basic Dto model used out of product service when needed
  * DummyDto model used as a response from external service. This can be treated like entity model that is mapped to dto inside the feature service.
* Removed redundant models, params and error handling
* Swapped application.properties to application.yml
* Added custom error for id decryption
* Added tests for all current components except for controller
  * I plan to add functional tests later on

## 0.1.0 - First Minor Version?

* Added docker-compose.yml file
  * Added postgresql database and flyway scripts which drastically changes the way the app works. That's why we have a new minor version indicating a bigger update that may be breaking change.
Even though the diff is smaller the change is significant.

## 0.1.1 - Build It And They Will Come

* Added Login and Registration
* Added bearer token authorization for all endpoints apart from swagger, openapi, login and registration
* Added UserService and tests
* Added user related models
  * I know I'm kind of throwing new models left and right and complicating things but since this is a task I want to simulate a project/environment that uses multiple services which rely on DB/External model/entity, model that's used inside our project (DTO model) and model exposed to clients (ApiModel)

## 0.1.2 - My Favourite

* Added favourites management
* New flyway script for favourites table
* Added option to get favourites
* Added option to add or remove from favourites
* I'm speeding this up a bit, so I'm not writing any tests at the moment
  * Don't judge me, I'll add them later, we're still not in production :)
  * I'll also have to update the documentation
  * And fixed stuff like unused imports

## 0.1.3 - The Basket

* Added basket management
* check basket, update basket
  * Both functionalities create baskets if non existant
* Cancel basket (I don't see much use for this one but I felt like i needed to add it)