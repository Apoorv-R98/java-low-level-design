package com.example.auction.models;

import java.util.concurrent.locks.ReentrantLock;

public class AuctionItem {
    public Long id;
    public Item item;
    public double currentHighestBid;
    public User highestBidder;
    public AuctionItemStatus status;
    public final ReentrantLock lock = new ReentrantLock();

    public AuctionItem(Long id, Item item, double startingPrice) {
        this.id = id;
        this.item = item;
        this.currentHighestBid = startingPrice;
        this.status = AuctionItemStatus.ACTIVE;
    }
}
