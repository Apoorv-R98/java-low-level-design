
package strategy;

import model.Post;
import model.User;

public interface VoteStrategy {

    void vote(Post post, User user);
}
