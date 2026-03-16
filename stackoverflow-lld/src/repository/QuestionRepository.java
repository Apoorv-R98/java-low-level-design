
package repository;

import model.Question;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class QuestionRepository {

    private Map<String, Question> questionStore = new ConcurrentHashMap<>();

    public void save(Question question) {
        questionStore.put(question.getId(), question);
    }

    public Optional<Question> findById(String id) {
        return Optional.ofNullable(questionStore.get(id));
    }

    public List<Question> findAll() {
        return new ArrayList<>(questionStore.values());
    }
}
