package training.jimmy.FakeShop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import training.jimmy.FakeShop.model.Image;
import training.jimmy.FakeShop.model.User;

import java.util.List;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
    List<Image> findByUser(User user);

}
