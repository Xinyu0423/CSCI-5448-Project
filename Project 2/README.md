# CSCI 5448 Project 2: Zoo Patterns and UML Diagrams

## Project Team

- Xinyu Jiang
- Qiuyang Wang 
- Vladimir Zhdanov 

## Instructions to Run

1. Compile all of the `.java` files in the `src/` directory.
2. Run the main function in `Zoo.java`.
3. Input a number of days for which the program should run to see the program output.

*Note to Graders*: Specific documentation which was requested in this project's writeup are under `/* */`-style Java comments so that they are easier to find.

## Assumptions
We made several assumptions during the development of this project:
- The Zoo Clock can be a 24-hour clock, but it only announces the time during when the Zoo is open (8am to 8pm).
- The Zoo Announcer will always be present at the Zoo when there are other employees there. So, he will arrive when the first Zoo Employee arrives, and will leave after the last Zoo Employee leaves for the day.
- The ZooKeeper generally doesn't tell the animals to make noise, but this method was added to show off the implementation of the `MakeNoise` strategy pattern.
