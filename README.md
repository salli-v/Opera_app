## Description

OperaApp is an application to manage tickets bought on the occasion of an opera session by a user. An app can be used to organize the interactions between the customer and selling department, a user has an opportunity to choose an available session, purchase tickets, make an order, login, register. The App has integrated ability to manage opera sessions, performances, schedule, and administrating user' customer experience.

## Setting

file src/main/resources/`hibernate.cfg.xml` is to be configured under RDBMS in use:

db.driver=YOUR_DRIVER

db.url=YOUR_URL

db.username=YOUR_USERNAME

db.password=YOUR_PASSWORD

## Usage

Guest (unauthorized and non registered) can do the fllowing:
- register 
- login
- get all available performance sessions according to request
- view current stages in Database
***

Authenticated user:
- has the same capabilities as the guest + capability to 
  - purchase tickets
  - add tickets to shoping cart
  - view purchase history
  - complete orders
  - check current shopping cart
***

Admin:
- has the same capabilties of user, additionally can 
  - add new performance sessions
  - manage stages
  - manage performances
  - administer schedule
  - add user/remove user

### Technologies: 
Spring - Core / MVC / Web / Security

MySQL

Hibernate

Apache Maven

Tomcat

JSON
