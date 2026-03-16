
package model;

import java.util.ArrayList;
import java.util.List;

public class Question extends Post {

    private String title;
    private List<Tag> tags;
    private List<Answer> answers = new ArrayList<>();

    public Question(String id, String title, String content, User author, List<Tag> tags) {
        super(id, content, author);
        this.title = title;
        this.tags = tags;
    }

    public synchronized void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public List<Tag> getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }
}
