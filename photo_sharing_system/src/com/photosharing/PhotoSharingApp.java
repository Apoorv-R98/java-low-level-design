package com.photosharing;

import com.photosharing.models.Photo;
import com.photosharing.models.User;
import com.photosharing.repositories.InMemoryPhotoRepository;
import com.photosharing.repositories.InMemoryUserRepository;
import com.photosharing.repositories.PhotoRepository;
import com.photosharing.repositories.UserRepository;
import com.photosharing.services.FeedService;
import com.photosharing.services.PhotoService;
import com.photosharing.services.UserService;

import java.util.Arrays;
import java.util.List;

public class PhotoSharingApp {
    
    public static void main(String[] args) {
        System.out.println("=== Photo Sharing System - Demo ===\n");

        UserRepository userRepository = new InMemoryUserRepository();
        PhotoRepository photoRepository = new InMemoryPhotoRepository();

        UserService userService = new UserService(userRepository);
        PhotoService photoService = new PhotoService(photoRepository, userRepository);
        FeedService feedService = new FeedService(photoRepository, userRepository);

        try {
            System.out.println("1. Creating Users...");
            userService.addUser("user1", "Alice");
            userService.addUser("user2", "Bob");
            userService.addUser("user3", "Charlie");
            userService.addUser("user4", "Diana");
            System.out.println("✓ Created users: Alice, Bob, Charlie, Diana\n");

            System.out.println("2. Setting up Follow Relationships...");
            userService.followUser("user1", "user2"); // Alice follows Bob
            userService.followUser("user1", "user3"); // Alice follows Charlie
            userService.followUser("user2", "user3"); // Bob follows Charlie
            userService.followUser("user3", "user4"); // Charlie follows Diana
            System.out.println("✓ Alice follows: Bob, Charlie");
            System.out.println("✓ Bob follows: Charlie");
            System.out.println("✓ Charlie follows: Diana\n");

            System.out.println("3. Posting Photos with Tags...");
            Thread.sleep(100); // Small delay to ensure different timestamps
            photoService.postPhoto("user2", "https://photos.com/bob_vacation.jpg", 
                                                  Arrays.asList("user1"));
            System.out.println("✓ Bob posted a photo and tagged Alice");
            
            Thread.sleep(100);
            photoService.postPhoto("user3", "https://photos.com/charlie_sunset.jpg", 
                                                  Arrays.asList("user2", "user4"));
            System.out.println("✓ Charlie posted a photo and tagged Bob and Diana");
            
            Thread.sleep(100);
            photoService.postPhoto("user2", "https://photos.com/bob_concert.jpg", 
                                                  Arrays.asList());
            System.out.println("✓ Bob posted another photo with no tags");
            
            Thread.sleep(100);
            photoService.postPhoto("user4", "https://photos.com/diana_art.jpg", 
                                                  Arrays.asList("user3"));
            System.out.println("✓ Diana posted a photo and tagged Charlie\n");

            System.out.println("4. Fetching Photos by User...");
            List<Photo> bobPhotos = photoService.getPhotosByUser("user2");
            System.out.println("✓ Bob has posted " + bobPhotos.size() + " photos:");
            bobPhotos.forEach(p -> System.out.println("  - " + p.getPhotoUrl()));
            System.out.println();

            System.out.println("5. Fetching Photos where User is Tagged...");
            List<Photo> aliceTaggedPhotos = photoService.getPhotosWhereUserIsTagged("user1");
            System.out.println("✓ Alice is tagged in " + aliceTaggedPhotos.size() + " photo(s):");
            aliceTaggedPhotos.forEach(p -> System.out.println("  - " + p.getPhotoUrl() + 
                                                             " (by " + p.getPostedByUserId() + ")"));
            System.out.println();

            System.out.println("6. Getting Followers and Following...");
            List<User> bobFollowers = userService.getFollowers("user2");
            System.out.println("✓ Bob has " + bobFollowers.size() + " follower(s): " + 
                             bobFollowers.stream().map(User::getUsername).toList());
            
            List<User> aliceFollowing = userService.getFollowing("user1");
            System.out.println("✓ Alice is following " + aliceFollowing.size() + " user(s): " + 
                             aliceFollowing.stream().map(User::getUsername).toList());
            System.out.println();

            System.out.println("7. Generating Feed for Alice (Chronological Strategy)...");
            System.out.println("   Alice follows Bob and Charlie, so feed shows their photos:");
            List<Photo> aliceFeed = feedService.getFeedForUser("user1");
            System.out.println("✓ Alice's feed has " + aliceFeed.size() + " photos (newest first):");
            for (Photo photo : aliceFeed) {
                System.out.println("  - " + photo.getPhotoUrl() + 
                                 " (by " + photo.getPostedByUserId() + 
                                 ", tags: " + photo.getTaggedUserIds().size() + 
                                 ", time: " + photo.getTimestamp().toLocalTime() + ")");
            }
            System.out.println();

            System.out.println("8. Generating Feed for Bob...");
            List<Photo> bobFeed = feedService.getFeedForUser("user2");
            System.out.println("✓ Bob's feed has " + bobFeed.size() + " photos:");
            for (Photo photo : bobFeed) {
                System.out.println("  - " + photo.getPhotoUrl() + 
                                 " (by " + photo.getPostedByUserId() + ")");
            }
            System.out.println();

            System.out.println("9. Testing Unfollow...");
            userService.unfollowUser("user1", "user2"); // Alice unfollows Bob
            System.out.println("✓ Alice unfollowed Bob");
            
            List<Photo> aliceFeedAfterUnfollow = feedService.getFeedForUser("user1");
            System.out.println("✓ Alice's new feed has " + aliceFeedAfterUnfollow.size() + 
                             " photos (only from Charlie now)");
            System.out.println();

            System.out.println("10. Testing Exception Handling...");
            try {
                userService.followUser("user1", "user1"); // Self-follow
            } catch (Exception e) {
                System.out.println("✗ " + e.getMessage());
            }
            
            try {
                userService.addUser("user1", "AliceDuplicate"); // Duplicate user
            } catch (Exception e) {
                System.out.println("✗ " + e.getMessage());
            }
            
            try {
                photoService.postPhoto("user1", "photo.jpg", Arrays.asList("nonexistent"));
            } catch (Exception e) {
                System.out.println("✗ " + e.getMessage());
            }

            System.out.println("\n=== Demo Completed Successfully ===");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

