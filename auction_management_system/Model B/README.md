# Auction Management System — Model B

Per-item bidding inside a time-bounded auction: each `AuctionItem` tracks its own high bid and lock; `Auction` exposes lifecycle status from start/end times.

## Layout

Sources under `src/com/example/auction/`:

| Package | Role |
|--------|------|
| `com.example.auction.models` | `Auction`, `AuctionItem`, `AuctionStatus`, `AuctionItemStatus`, `Bid`, `Item`, `User` |
| `com.example.auction.exceptions` | `AuctionException`, `InvalidBidException`, `AuctionNotActiveException` |
| `com.example.auction.services` | `AuctionService` — validates auction is `ACTIVE`, then locks the item and updates the high bid |

## Compile

From this directory (`Model B`):

```bash
mkdir -p out
javac -d out $(find src -name "*.java")
```

## Design notes

- **Auction vs item**: `Auction.getStatus()` is derived from wall-clock vs `startTime` / `endTime`. Bidding is rejected unless the auction is `ACTIVE`.
- **Concurrency**: Each `AuctionItem` has its own `ReentrantLock`; different items can accept bids in parallel, while bids on the same item are serialized.

This variant does not include repositories or a `main` entry point; it is a focused domain + service slice.
