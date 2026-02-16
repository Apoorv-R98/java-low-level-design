# Parking Lot System - Low Level Design

A simplified parking lot management application demonstrating object-oriented design principles, the Strategy pattern, and thread-safe spot allocation.

## Features Implemented

1. ✅ Park vehicles (Motorcycle, Car, Truck) with type-based spot assignment
2. ✅ Unpark vehicles using ticket ID and get calculated fare
3. ✅ Multi-level parking with multiple spot types (Motorcycle, Compact, Large)
4. ✅ Configurable spot allocation strategy (e.g., first-available)
5. ✅ Configurable pricing strategy (e.g., hourly rate)
6. ✅ Thread-safe spot assignment using per-spot locks
7. ✅ Custom exceptions (ParkingFullException, InvalidTicketException)

## Design Patterns Used

### 1. **Strategy Pattern**
- **SpotAllocationStrategy**: Interface for finding an available spot for a vehicle (e.g., `DefaultSpotAllocationStrategy` — first available)
- **PricingStrategy**: Interface for computing fare from a ticket (e.g., `HourlyPricingStrategy`)
- Easy to add new strategies (nearest-to-entrance allocation, flat/day pricing, etc.) without changing core logic

### 2. **Service / Facade Pattern**
- `ParkingLot` acts as the main facade: `parkVehicle()`, `unparkVehicle()`
- Encapsulates levels, allocation, pricing, and active tickets

### 3. **Concurrency**
- `ParkingSpot` uses `ReentrantLock` for thread-safe assign/remove
- `ParkingLot` uses `ConcurrentHashMap` for active tickets

## Class Diagram

A PlantUML class diagram (excluding exceptions) is in `ParkingLot_Diagram.puml`. Open it with any PlantUML viewer or render with: `plantuml ParkingLot_Diagram.puml`.

## Build & Run

From the project root (parent of `src`):

```bash
javac -sourcepath src src/Main.java
java -cp src src.Main
```

## Project Structure

| Component        | Description                                      |
|-----------------|---------------------------------------------------|
| `entities/`      | Vehicle, VehicleType, ParkingSpot, SpotType, ParkingLevel, Ticket |
| `strategy/`      | SpotAllocationStrategy, PricingStrategy and implementations |
| `Exception/`     | ParkingException, ParkingFullException, InvalidTicketException |
| `ParkingLot.java`| Main parking lot facade                           |
| `Main.java`      | Demo usage                                        |

## Time Complexity

| Operation        | Complexity |
|------------------|------------|
| Park Vehicle     | O(L × S) where L = levels, S = spots per level (first-available scan) |
| Unpark Vehicle   | O(1)       |
| Calculate Fare   | O(1)       |

## Future Enhancements

1. Add more allocation strategies (nearest-to-entrance, preferred level, handicap-first)
2. Add more pricing strategies (flat rate, daily cap, vehicle-type multipliers)
3. Admin APIs: add/remove levels or spots, view occupancy
4. Persistence for tickets and audit log
5. Support for reservations or time-slot booking
6. Display board / availability query by vehicle type

## SOLID Principles Applied

- **Single Responsibility**: Each class has one clear purpose (e.g., Ticket holds trip data, ParkingSpot manages occupancy).
- **Open/Closed**: New allocation and pricing strategies can be added without modifying `ParkingLot` or entities.
- **Liskov Substitution**: Any `SpotAllocationStrategy` or `PricingStrategy` implementation can be swapped in.
- **Interface Segregation**: Small, focused interfaces for allocation and pricing.
- **Dependency Inversion**: `ParkingLot` depends on strategy interfaces, not concrete implementations.
