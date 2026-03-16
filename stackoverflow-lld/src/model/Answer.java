
package model;

public class Answer extends Post {

    private boolean accepted;

    public Answer(String id, String content, User author) {
        super(id, content, author);
    }

    public void accept() {
        accepted = true;
    }

    public boolean isAccepted() {
        return accepted;
    }
}
