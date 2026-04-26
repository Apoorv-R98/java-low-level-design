package com.example.auction.exceptions;

public class AuctionNotActiveException extends AuctionException {
    public AuctionNotActiveException(String message) {
        super(message);
    }
}
