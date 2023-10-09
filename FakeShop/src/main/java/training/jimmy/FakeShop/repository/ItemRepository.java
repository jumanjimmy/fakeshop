package training.jimmy.FakeShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training.jimmy.FakeShop.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
