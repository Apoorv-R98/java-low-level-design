# Auction Management System — Model A

A minimal auction domain sketch: lots, items, bids, and a service that places bids with concurrency and time-window checks.

## Layout

Sources live under `src/com/example/auction/`:

| Package | Role |
|--------|------|
| `com.example.auction.models` | Domain types: `Auction`, `AuctionLot`, `AuctionStatus`, `Bid`, `Item`, `User` |
| `com.example.auction.exceptions` | `AuctionException` and specific failures (`InvalidBidException`, `AuctionNotActiveException`) |
| `com.example.auction.services` | `AuctionService` — `placeBid` with lock + active-window validation |

## Compile

From this directory (`Model A`):

```bash
mkdir -p out
javac -d out $(find src -name "*.java")
```

## Design notes

- **Thread safety**: `Auction` holds a `ReentrantLock`; `AuctionService.placeBid` locks around the critical section so concurrent bids serialize per auction.
- **Active window**: Bids are allowed only while `LocalDateTime.now()` is strictly after `startTime` and strictly before `endTime`; the check is repeated after taking the lock to catch auctions that end mid-flight.

There is no `main` or persistence layer here; this model focuses on core types and bid placement rules.
