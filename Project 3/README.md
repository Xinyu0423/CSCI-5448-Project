# CSCI 5448 Project 3: Specialty Food Business Simulator

## Project Team

- Xinyu Jiang
- Qiuyang Wang 
- Vladimir Zhdanov 

## Instructions to Run

*To Run the Simulation:*
- Compile all of the `.java` files in the `src/` directory.
- Run the main function in `Store.java`.
- To change the number of days and inventory stock of the simulation, change the `inventoryStockCount` and `days` variables at the top of the main class and rerun the program.

*To Run the Tests:*
- Using JUnit, run all of the methods with the default testing annotation (`@Test`).


*Notes to Graders*: 
- Specific documentation which was requested in this project's writeup are under `\* *\`-style Java comments so that they are easier to find.
- Output of the unit tests is located in `unitTests.out`, and the outputs for the main business simulator with the three different stock levels are located in `30InventoryStock.out`, `45InventoryStock.out`, and `60InventoryStock.out`.
- The extra credit charts can be found under `Extra Credit Charts.pdf`.
- This project was developed in Java 8 and JUnit 4 using IntelliJ. Extra Credit was done using XChart 3.6.

## Project Description

*Program Design:*

The program was designed with three main patterns: a decorator, factory, and observer. 
- The decorator pattern was used to add extras (fillings, toppings, and sauces) to each of the rolls. 
- The factory pattern was used to instantiate customers and rolls, and also to apply extras to the correct roll types. 
- The observer pattern was used to capture events (such as customer orders, roll outages, etc.), which are kept track of for end-of-day and final outputs.

When run, the main `Store` program initializes several different objects.
- `StoreInventory`: Keeps track of the roll inventory levels.
- `StoreSalesManager`: Processes customer orders and manages the inventory.
- `StoreSalesTracker`: Observes the StoreSalesManager, and keeps track of sales and other aggregational statistics for output.
- `CustomerFactory`: Used to generate customer which will enter the store each day.

For each day the store is run, customers (random amounts of each type as specified in the writeup) will enter the store in a random order. The store sales manager will take each customer's order as long as the store inventory does not fully run out. At the end of each day, the store sales tracker will print all of the daily statistics about the store, and the inventory will be restocked. 

At the end of the 30 day simulation, overall statistics on number of rolls sold, sales, and roll outage impacts are printed.

The breakdown of roll and additional extra prices is as follows:
![Table of Roll and Extra Costs](https://i.imgur.com/hPTZCUr.png)

*Assumptions:*
During the design of this project, we made several assumptions about the requirements.
- Each roll type will have 1 unique filling, 2 unique toppings, and 3 unique sauces. The extras can be shared between rolls, but these shared extras will have unique prices for each roll type.
- In the daily output, we interpreted "Count inventory orders by roll type" as the number of each roll type which was sold that day.
- The customers do not have any knowledge of if a roll is in stock before they initially place their order.