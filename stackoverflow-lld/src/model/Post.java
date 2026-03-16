
package model;

import java.util.ArrayList;
import java.util.List;

public abstract class Post {

    protected String id;
    protected String content;
    protected User author;

    protected List<Comment> comments = new ArrayList<>();
    protected List<Vote> votes = new ArrayList<>();

    public Post(String id, String content, User author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public synchronized void addComment(Comment comment) {
        comments.add(comment);
    }

    public synchronized void addVote(Vote vote) {
        votes.add(vote);
    }

    public int getScore() {
        return votes.stream().mapToInt(Vote::getValue).sum();
    }

    public String getId() {
        return id;
    }

    public User getAuthor() {
        return author;
    }
}
