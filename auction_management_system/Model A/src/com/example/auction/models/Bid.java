package com.example.auction.models;

import java.time.LocalDateTime;

public class Bid {
    public Long id;
    public Long auctionId;
    public User bidder;
    public double amount;
    public LocalDateTime time;
}
