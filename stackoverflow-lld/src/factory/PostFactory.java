
package factory;

import model.*;
import java.util.*;

public class PostFactory {

    public static Question createQuestion(User user, String title, String body, List<Tag> tags) {
        return new Question(UUID.randomUUID().toString(), title, body, user, tags);
    }

    public static Answer createAnswer(User user, String content) {
        return new Answer(UUID.randomUUID().toString(), content, user);
    }
}
