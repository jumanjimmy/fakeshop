package training.jimmy.FakeShop.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import training.jimmy.FakeShop.config.UserDetail;
import training.jimmy.FakeShop.model.Image;
import training.jimmy.FakeShop.model.User;
import training.jimmy.FakeShop.service.Image.ImageService;
import training.jimmy.FakeShop.service.UserService;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/profile")
    public String profile(@AuthenticationPrincipal UserDetail userDetail, Model model) {
        if (userDetail != null) {
            String firstName = userDetail.getFirstName();
            model.addAttribute("firstName", firstName);

            Optional<User> userOptional = userService.findByEmail(userDetail.getEmail());
            User user = userOptional.orElse(null);

            if (user != null) {
                model.addAttribute("user", user);

                List<Image> imageList = imageService.findByUser(user);
                model.addAttribute("imageList", imageList);

                return "profile";
            }
        }
        return "profile";
    }

    // display image
    @GetMapping("/display")
    public ResponseEntity<byte[]> displayImage(@RequestParam("id") long id) throws IOException, SQLException {
        Image image = imageService.viewById(id);
        byte [] imageBytes = null;
        imageBytes = image.getImage().getBytes(1,(int) image.getImage().length());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }


    @GetMapping("/addimage")
    public ModelAndView addImage(){
        return new ModelAndView("addimage");
    }

    @PostMapping("/addimage")
    public String addImagePost(HttpServletRequest request, @RequestParam("image") MultipartFile file) throws IOException, SerialException, SQLException{

        byte[] bytes = file.getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);

        Image image = new Image();
        image.setImage(blob);
        imageService.create(image);
        return "redirect:/profile";
    }


}
