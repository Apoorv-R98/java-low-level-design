# Elevator system (Java LLD)

A small simulation of elevators handling floor requests: a controller assigns work, each elevator runs a simple state machine, and floor displays observe status updates.

## Requirements

- **JDK 8 or newer**
- **No external libraries or build tools** — use `javac` and `java` only

## Features

- Submit pickup / drop-off pairs as `Request` (source and destination floors)
- Controller picks an elevator using a pluggable selection strategy (nearest to source by default)
- Per-elevator **step** simulation (idle → moving → door open → idle)
- Passenger **capacity** with `OverCapacityException` when full
- **Observer**-style floor displays for movement and door events
- Validation for invalid requests (e.g. same source and destination)

## Design patterns used

| Pattern | Usage |
|--------|--------|
| State | `ElevatorState` and concrete states (`Idle`, `MovingUp`, `MovingDown`, `DoorOpen`) drive `Elevator.step()` |
| Strategy | `ElevatorSelectionStrategy` / `NearestElevatorStrategy` choose which elevator serves a request |
| Observer | `Observer` / `FloorDisplay` get notified when the elevator moves or opens doors |
| Singleton | `ElevatorController.getInstance(int count)` — single controller instance in the demo |

## Project structure

```
src/com/elevator/
├── Main.java
├── controller/
│   └── ElevatorController.java
├── model/
│   ├── Elevator.java
│   ├── Request.java
│   └── Direction.java
├── state/
│   ├── ElevatorState.java
│   ├── IdleState.java
│   ├── MovingUpState.java
│   ├── MovingDownState.java
│   └── DoorOpenState.java
├── strategy/
│   ├── ElevatorSelectionStrategy.java
│   └── NearestElevatorStrategy.java
├── observer/
│   ├── Observer.java
│   └── FloorDisplay.java
└── exception/
    ├── ElevatorException.java
    ├── InvalidRequestException.java
    └── OverCapacityException.java
```

## Key concepts

**Request queue** — Each `Request` enqueues the source floor then the destination; the state machine consumes the queue one target floor at a time.

**Simulation loop** — `ElevatorController.step()` advances every elevator one state transition per call (see `Main` for a fixed number of steps).

## How to run

From the **project root** (`elevator_system/`):

```bash
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out com.elevator.Main
```

You should see log lines from each registered `FloorDisplay` as the elevator moves and stops.

**Alternative** (explicit file list, e.g. Windows PowerShell from project root):

```powershell
mkdir out -ea 0
javac -d out (Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName })
java -cp out com.elevator.Main
```
