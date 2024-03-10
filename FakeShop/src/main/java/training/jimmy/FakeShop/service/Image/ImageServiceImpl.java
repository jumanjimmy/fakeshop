package training.jimmy.FakeShop.service.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training.jimmy.FakeShop.model.Image;
import training.jimmy.FakeShop.model.User;
import training.jimmy.FakeShop.repository.ImageRepository;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Image create(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public List<Image> viewAll() {
        return (List<Image>) imageRepository.findAll();
    }

    @Override
    public Image viewById(Long id) {
        return imageRepository.findById(id).get();
    }

    @Override
    public List<Image> findByUser(User user) {
        return imageRepository.findByUser(user);
    }
}
