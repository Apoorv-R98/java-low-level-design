
package strategy;

import model.Post;
import model.User;
import model.Vote;

public class UpvoteStrategy implements VoteStrategy {

    public void vote(Post post, User user) {
        post.addVote(new Vote(user, 1));
    }
}
