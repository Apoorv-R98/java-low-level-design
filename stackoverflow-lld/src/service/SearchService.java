
package service;

import model.Question;
import model.Tag;
import repository.QuestionRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SearchService {

    private QuestionRepository repository;

    public SearchService(QuestionRepository repository) {
        this.repository = repository;
    }

    public List<Question> searchByKeyword(String keyword) {
        return repository.findAll()
                .stream()
                .filter(q -> q.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Question> searchByTag(String tagName) {
        return repository.findAll()
                .stream()
                .filter(q -> q.getTags()
                        .stream()
                        .map(Tag::getName)
                        .anyMatch(tag -> tag.equalsIgnoreCase(tagName)))
                .collect(Collectors.toList());
    }
}
