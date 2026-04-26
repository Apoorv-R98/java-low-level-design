package com.example.auction.services;

import com.example.auction.exceptions.AuctionNotActiveException;
import com.example.auction.exceptions.InvalidBidException;
import com.example.auction.models.Auction;
import com.example.auction.models.AuctionItem;
import com.example.auction.models.AuctionStatus;
import com.example.auction.models.Bid;

public class AuctionService {

    public void placeBid(Auction auction, AuctionItem item, Bid bid) {

        if (auction.getStatus() != AuctionStatus.ACTIVE) {
            throw new AuctionNotActiveException("Auction not active");
        }

        item.lock.lock();
        try {
            if (bid.amount <= item.currentHighestBid) {
                throw new InvalidBidException("Invalid bid");
            }

            item.currentHighestBid = bid.amount;
            item.highestBidder = bid.bidder;

        } finally {
            item.lock.unlock();
        }
    }
}
