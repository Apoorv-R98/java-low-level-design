
package model;

import factory.PostFactory;
import java.util.List;

public class User {

    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Question askQuestion(String title, String body, List<Tag> tags) {
        return PostFactory.createQuestion(this, title, body, tags);
    }

    public Answer answerQuestion(Question question, String content) {
        Answer answer = PostFactory.createAnswer(this, content);
        question.addAnswer(answer);
        return answer;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
