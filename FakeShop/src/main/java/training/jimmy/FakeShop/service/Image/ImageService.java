package training.jimmy.FakeShop.service.Image;

import org.springframework.stereotype.Service;
import training.jimmy.FakeShop.model.Image;
import training.jimmy.FakeShop.model.User;

import java.util.List;

@Service
public interface ImageService {
    public Image create(Image image);
    public List<Image> viewAll();
    public Image viewById(Long id);

    public List<Image> findByUser(User user);
}
