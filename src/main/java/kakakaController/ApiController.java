package kakakaController;
import kakaka.Article;
import kakaka.Color;
import kakaka.User;
import kakakaRepository.ArticleRepository;
import kakakaRepository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public ApiController(UserRepository userRepository, ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    @GetMapping("/users/age/{age}")
    public List<User> getUsersByAge(@PathVariable int age) {
        return userRepository.findByAgeGreaterThan(age);
    }

    @GetMapping("/users/color/{color}")
    public List<User> getUsersByArticleColor(@PathVariable Color color) {
        return userRepository.findByArticlesColor(color);
    }

    @GetMapping("/users/name")
    public List<String> getUniqueUserNamesWithMoreThanThreeArticles() {
        return userRepository.findByArticlesSizeGreaterThan(3)
                .stream()
                .map(User::getName)
                .distinct()
                .collect(Collectors.toList());
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/articles")
    public Article saveArticle(@RequestBody Article article) {
        return articleRepository.save(article);
    }
}