
import model.*;
import repository.QuestionRepository;
import service.SearchService;
import strategy.UpvoteStrategy;

import java.util.Arrays;
import java.util.List;

public class Demo {

    public static void main(String[] args) {

        User user1 = new User("1", "Alice");
        User user2 = new User("2", "Bob");

        Tag javaTag = new Tag("java");

        Question q1 = user1.askQuestion(
                "What is Low Level Design?",
                "Can someone explain LLD in interviews?",
                Arrays.asList(javaTag)
        );

        QuestionRepository repo = new QuestionRepository();
        repo.save(q1);

        user2.answerQuestion(q1, "LLD focuses on class design.");

        UpvoteStrategy upvote = new UpvoteStrategy();
        upvote.vote(q1, user2);

        SearchService searchService = new SearchService(repo);

        List<Question> results = searchService.searchByKeyword("design");

        System.out.println("Search results: " + results.size());
    }
}
