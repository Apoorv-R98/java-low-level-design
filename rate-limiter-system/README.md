# Rate Limiter System (Java LLD)

A small low-level design exercise for API rate limiting: pluggable algorithms, hierarchical policy resolution, and in-memory storage.

## Requirements

- **JDK 17 or newer** (uses `switch` expressions on enums).
- **No external libraries or build tools** — compile and run with `javac` / `java` only.

## Features

- **Fixed window**, **sliding window**, and **token bucket** rate limiting
- Hierarchical config lookup: user → API key → endpoint → default
- Per-request allow/deny with `RateLimitResult` or `RateLimitExceededException` (includes retry-after hint)
- Thread-safe in-memory stores (`ConcurrentHashMap`, synchronized timestamp deque)
- Immutable `Request` DTO (constructor-based)

## Design patterns used

| Pattern | Usage |
|--------|--------|
| Strategy | `RateLimiterStrategy` with fixed, sliding, and token-bucket implementations |
| Factory | `RateLimiterFactory` creates the right strategy from `RateLimiterType` |
| Service layer | `RateLimiterService` orchestrates policy resolution and limiting |
| Repository / store | `ConfigStore` and `RateLimitStore` abstractions with in-memory implementations |

## Project structure

```
src/
├── Main.java                 # entry point (default package)
├── model/                    # Request, RateLimitConfig, RateLimitPolicy, RateLimitResult, RateLimiterType, TokenBucket
├── strategy/                 # RateLimiterStrategy, FixedWindow, SlidingWindow, TokenBucket limiters
├── factory/                  # RateLimiterFactory
├── store/                    # ConfigStore, RateLimitStore, in-memory implementations
├── service/                  # RateLimiterService, PolicyManager
└── exception/                # RateLimitExceededException, ConfigurationNotFoundException
```

## Key concepts

**Policy resolution**

```
Request → PolicyManager
            ├── USER:{userId}
            ├── API:{apiKey}
            ├── ENDPOINT:{endpoint}
            └── DEFAULT
```

**Rate limiting flow**

```
RateLimiterService
  → resolve config (PolicyManager)
  → create strategy (Factory)
  → build policy from config
  → strategy.allowRequest(userId:endpoint, policy)
```

**Algorithms**

| Type | Idea |
|------|------|
| Fixed window | Count requests per time bucket; reject when count exceeds max |
| Sliding window | Track request timestamps in a deque; evict expired entries |
| Token bucket | Refill tokens over time; consume one token per allowed request |

## How to run

From the **project root** (`rate-limiter-system/`):

```bash
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out Main
```

Expected output: the first five requests are allowed, then requests are blocked with a retry-after value.

**Alternative** (compile from `src/`, output one directory up):

```bash
cd src
mkdir -p ../out
javac -d ../out $(find . -name "*.java")
cd ..
java -cp out Main
```

On Windows PowerShell, compile all sources explicitly or use a glob that your shell expands; for example from project root:

```powershell
mkdir out -ea 0
javac -d out src/Main.java src/model/*.java src/strategy/*.java src/factory/*.java src/store/*.java src/service/*.java src/exception/*.java
java -cp out Main
```
