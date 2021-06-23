## Description

Opera app is an application to manage tickets bought on the occasion of opera session by a user.

## Setting

file src/main/resources/`hibernate.cfg.xml` is to be configured under RDBMS in use

## Usage

OrderServiceImpl.class
- `completeOrder`(ShoppingCart shoppingCart) - completes the order writing the data into the DB
- `getOrderHistory`(User user) - retrieves purchase history of the user
***

PerformanceServiceImpl.class
- `add`(Performance performance) - saves performance to DB
- `get`(Long id) - gets performance from DB
- `getAll`() - gets all performances recorded to DB
***

PerformanceSessionsServiceImpl.class
- `findAlavilableSession`(Long movieId, LocalDate date)  - finds sessions that fall into search criteria of a date and movie
- `get`(Long id) - gets performance session from DB
- `add`(Performance performance) - saves performance session to DB
***

ShoppingCartServiceImpl.class
- `addSession`(PerformanceSession performanceSession, User user) - adds session to a shopping cart of a particular user
- `getByUser`(User user) - finds shopping cart tied to a user
- `registerNewShoppingCart`(User user) - method used in user registration process, registers and links shopping cart to the newly registered user
- `clearShoppingCart`(ShoppingCart cart) - clears all items from shopping cart
***



StageServiceImpl.class
- `add`(Stage stage) - saves stage to DB
- `get`(Long id) - gets stage from DB
- `getAll`() - gets all stages from DB
***

UserServiceImpl.class
- `add`(User user) - adds user to a DB
- `findByEmail`(String email) - searches for a particular user in DB using its e-mail
***

AuthenticationServiceImpl
- `login`(String email, String password) - logs a registered user into the system
- `register`(String email, String password) - registers a user
- `matchPasswords`(String rawPassword, User userFromDb) - private method used in login method

### Technologies: 
Spring - Core / MVC / Web / Security,
Hibernate,
RDBMS - MySQL,
Packaging - Apache Maven,
Tomcat,
JSON
