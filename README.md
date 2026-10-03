# Restaurant Concurrency Simulation

A Java Swing simulation of a café, built to demonstrate concurrency concepts. Customers, buffet stations and staff each run on their own thread and share resources safely using locks, conditions, semaphores and blocking queues.

## Features

- **Customers** (threads) each perform three random actions: place an order, play the piano, or listen to music.
- **Buffet stations** (coffee, tea, cake) process orders from a queue and track stock.
- **Staff** (threads) restock their assigned station when stock runs low.
- **Piano** access is limited to two customers at a time using a semaphore.
- **GUI** to configure the starting customers, stock and staff, plus a live log of events.
- **Runtime controls** to add more customers and change the simulation delay while it runs.

## Concurrency Concepts Used

- `Thread` subclasses for customers, stations and staff
- `ReentrantLock` and `Condition` for mutual exclusion and signalling
- `Semaphore` to limit piano access
- `LinkedBlockingQueue` for station order queues

## Project Structure

```
src/
├── main.java              # Entry point
├── SettingsData/Data.java # Shared simulation settings
└── Cafe/
    ├── Cafe.java          # Creates and starts customers
    ├── Buffet/            # Buffet, Station, Staff, Order
    ├── Customer/          # Customer, ActionFactory, Actions/
    └── GUI/               # Swing menus and log output
```

## Running

Requires **Java 17+**.

```bash
javac -d out $(find src -name "*.java")
java -cp out main
```

Or open the project in IntelliJ IDEA and run `main.java`.
