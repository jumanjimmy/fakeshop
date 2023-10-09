package training.jimmy.FakeShop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import training.jimmy.FakeShop.model.Item;
import training.jimmy.FakeShop.model.User;
import training.jimmy.FakeShop.repository.ItemRepository;
import training.jimmy.FakeShop.repository.UserRepository;
import training.jimmy.FakeShop.service.IUserService;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DbInit implements CommandLineRunner {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Autowired
    public DbInit(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        itemRepository.saveAll(List.of(
                new Item("Wentylator", new BigDecimal("199.99"),
                        "https://prod-api.mediaexpert.pl/api/images/gallery_500_500/thumbnails/images/15/1577949/AC4540S_1.jpg"),
                new Item("Podkładka pod mysz", new BigDecimal("30.99"),
                        "https://prod-api.mediaexpert.pl/api/images/gallery_500_500/thumbnails/images/83/836578/5907512854631.jpg"),
                new Item("Podkładka pod mysz", new BigDecimal("30.99"),
                        "https://prod-api.mediaexpert.pl/api/images/gallery_500_500/thumbnails/images/83/836578/5907512854631.jpg"),
                new Item("Podkładka pod mysz", new BigDecimal("30.99"),
                        "https://prod-api.mediaexpert.pl/api/images/gallery_500_500/thumbnails/images/83/836578/5907512854631.jpg"),
                new Item("Podkładka pod mysz", new BigDecimal("30.99"),
                        "https://prod-api.mediaexpert.pl/api/images/gallery_500_500/thumbnails/images/83/836578/5907512854631.jpg"),
                new Item("Java Poradnik", new BigDecimal("69.99"),
                        "https://ecsmedia.pl/cdn-cgi/image/format=webp,width=544,height=544,/c/java-przewodnik-dla-poczatkujacych-wydanie-viii-b-iext123475743.jpg"),
                new Item("Mysz", new BigDecimal("74.99"),
                        "https://m.media-amazon.com/images/I/51c-RQIr8bL.__AC_SX300_SY300_QL70_ML2_.jpg")
        ));


        User user = new User();
        user.setEmail("cwel@wp.pl");
        user.setPassword("cwel");
        user.setFirstName("jakub");
        user.setLastName("dziag");

        userRepository.save(user);

    }
}
