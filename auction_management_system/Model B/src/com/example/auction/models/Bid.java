package com.example.auction.models;

import java.time.LocalDateTime;

public class Bid {
    public Long id;
    public Long auctionItemId;
    public User bidder;
    public double amount;
    public LocalDateTime time;

    public Bid(Long id, Long auctionItemId, User bidder, double amount) {
        this.id = id;
        this.auctionItemId = auctionItemId;
        this.bidder = bidder;
        this.amount = amount;
        this.time = LocalDateTime.now();
    }
}
