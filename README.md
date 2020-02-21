# FootballGenerator
Generates an ideal football lineup for DFS (Daily Fantasy Sports) using Genetic Algorithms in a Java GUI Window

1. Updating current datasets
  -Parse in CSV files from DFS sites and projections (samples in folder)
  -Using both file selection and direct file opening
2. Genetic Algorithm for Optimization
  -Carry data over to Genetic Algorithm
  -Create local Optima based on randomized generation
  -Calculate fitness of the generation
  -Evolve generation based on Fitness criteria to find a more optimal solution each generation
  -Break after a set number of generations or a lack of improvement
3. (TO DO) Manually build lineup around personally selected players
