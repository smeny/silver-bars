# Silver Bars Marketplace

The goal of this TDD exercise is to implement a library used by a live order board.

### Functions expected
* Register an order
* Delete an order
* Display a summary for each order type

### Requirements
* An order contains: a user ID, a quantity, a price and an order type (BUY / SELL)
* Orders for the same price (and type) are merged on the summary (quantity summed)
* BUY orders summary is ordered by highest price first (descending order)
* SELL orders summary is ordered by lowest price first (ascending order)

### Assumptions made
* Registering an order returns an order ID
* Deleting an order is done by providing an order ID
* Representing prices with integers is enough for the purpose of this exercise (no decimals or computation required)
* Representing quantities with doubles is enough for the purpose of this exercise (no operations introducing a severe loss of precision)
* We are not implementing matches between BUY and SELL orders

### Execution environment
* Java 11 (Compatible from Java 8)
* Maven 3.x
* JUnit 5

To run tests from the command line: `mvn clean test`
