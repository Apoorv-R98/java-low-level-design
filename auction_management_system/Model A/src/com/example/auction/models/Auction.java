package com.example.auction.models;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class Auction {
    public Long id;
    public AuctionLot lot;

    public double startingPrice;
    public double currentHighestBid;
    public User highestBidder;

    public LocalDateTime startTime;
    public LocalDateTime endTime;

    public AuctionStatus status;

    public final ReentrantLock lock = new ReentrantLock();
}
