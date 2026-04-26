package com.example.auction.services;

import com.example.auction.exceptions.AuctionNotActiveException;
import com.example.auction.exceptions.InvalidBidException;
import com.example.auction.models.Auction;
import com.example.auction.models.Bid;

import java.time.LocalDateTime;

public class AuctionService {

    public boolean placeBid(Auction auction, Bid bid) {

        if (!isActive(auction)) {
            throw new AuctionNotActiveException("Auction is not active");
        }

        auction.lock.lock();

        try {
            if (!isActive(auction)) {
                throw new AuctionNotActiveException("Auction ended during bidding");
            }

            if (bid.amount <= auction.currentHighestBid) {
                throw new InvalidBidException("Bid must be higher than current highest bid");
            }

            auction.currentHighestBid = bid.amount;
            auction.highestBidder = bid.bidder;

            return true;

        } finally {
            auction.lock.unlock();
        }
    }

    private boolean isActive(Auction auction) {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(auction.startTime) && now.isBefore(auction.endTime);
    }
}
