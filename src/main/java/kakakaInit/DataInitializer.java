package kakakaInit;

import kakaka.Article;
import kakaka.Color;
import kakaka.User;
import kakakaRepository.ArticleRepository;
import kakakaRepository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public DataInitializer(UserRepository userRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public void run(String... args) {
        Random random = new Random();
        List<User> users = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("User " + (i + 1));
            user.setAge(random.nextInt(50) + 18);

            List<Article> articles = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                Article article = new Article();
                article.setText("Article " + (j + 1) + " by " + user.getName());
                article.setColor(Color.values()[random.nextInt(Color.values().length)]);
                article.setUser(user);
                articles.add(article);
            }

            user.setArticles(articles);
            users.add(user);
        }

        userRepository.saveAll(users);
    }
}