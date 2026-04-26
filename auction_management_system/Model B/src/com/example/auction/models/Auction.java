package com.example.auction.models;

import java.time.LocalDateTime;
import java.util.List;

public class Auction {
    public Long id;
    public List<AuctionItem> items;
    public LocalDateTime startTime;
    public LocalDateTime endTime;

    public Auction(Long id, List<AuctionItem> items, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.items = items;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public AuctionStatus getStatus() {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(startTime)) {
            return AuctionStatus.SCHEDULED;
        }
        if (now.isAfter(endTime)) {
            return AuctionStatus.ENDED;
        }
        return AuctionStatus.ACTIVE;
    }
}
