
package model;

public class Comment {

    private String id;
    private String text;
    private User author;

    public Comment(String id, String text, User author) {
        this.id = id;
        this.text = text;
        this.author = author;
    }
}
