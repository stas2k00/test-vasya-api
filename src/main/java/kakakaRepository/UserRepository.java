package kakakaRepository;

import kakaka.Color;
import kakaka.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByAgeGreaterThan(int age);
    List<User> findByArticlesColor(Color color);
    List<User> findByArticlesSizeGreaterThan(int size);
}