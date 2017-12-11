# Genetic Algorithm: The Traveling Salesman Problem
Final Project for INFO 6205 Program Structures and Algorithms

## Getting Started
This project is a genetic algorithm to find a solution to the Traveling Salesmen Problem. I approached the problem as finding the Hamiltonian circuit with the smallest weight on a complete weighted undirected graph. 

The program uses both sexual and asexual reproduction to generate new solutions. A solution is evaluated based on the inverse of the sum of the weights for each edge visited, so that the shortest path has the highest score.

## Prerequisites
This project uses:
* [Maven 4.0.0](https://maven.apache.org/)
* [JUnit 4.12](http://junit.org/junit4/)
* [Log4J 1.2.15](https://logging.apache.org/log4j/2.x/)

## Running the Genetic Algorithm
1. By default three parameters can be set - the number of vertices in the graph (default 50), and the maximum weight of each edge (default 5). To change these edit Main.java. 
2. The program creates a random complete weighted undirected graph. Random is seeded so that solutions can be compared.
3. You can run the algorithm in Eclipse or on the command line with Maven. 
4. Output is saved to log4j-application.log

## Running the Unit Tests
Unit tests can be run in Eclipse or on the command line with Maven.

## Authors
 * Emily Strong

## References
The code for creating a weighted undirected graph is based on code from:

Sedgewick, R., & Wayne, K. D. (2015). Algorithms. Upper Saddle River (NJ) etc.: Addison-Wesley. Booksite: [https://algs4.cs.princeton.edu/home/](https://algs4.cs.princeton.edu/home/)

