package io.rahul.betterreads.home;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.rahul.betterreads.user.BooksByUser;
import io.rahul.betterreads.user.BooksByUserRepo;
import io.rahul.betterreads.user.UserBook;

@Controller
public class HomeController {

    private final String COVER_IMAGE_URL = "https://covers.openlibrary.org/b/id/";

    @Autowired
    private BooksByUserRepo booksByUserRepo;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal OAuth2User principal, Model model) {

        if (principal == null) {
            return "index";
        }

        String userId = principal.getAttribute("login");
        Slice<BooksByUser> bookSlice = booksByUserRepo.findAllById(userId, CassandraPageRequest.of(0, 50));
        List<BooksByUser> bookByUser = bookSlice.getContent();
        System.out.println(bookByUser);
        if (bookByUser.size() == 0 || bookByUser == null) {
            model.addAttribute("userHasBooks", false);
        } else {
            model.addAttribute("userHasBooks", true);
            bookByUser = bookByUser.stream().distinct().map(book -> {
                String coverUrl = "/images/no_image_found.jpg";
                if (book.getCoverIds() != null && book.getCoverIds().size() > 0) {
                    coverUrl = COVER_IMAGE_URL + book.getCoverIds().get(0) + "-L.jpg";
                }
                book.setCoverUrl(coverUrl);
                // String status = book.getReadingStatus();
                // book.setReadingStatus(status.substring(status.indexOf("-")));
                return book;
            }).collect(Collectors.toList());
            model.addAttribute("bookByUser", bookByUser);
        }
        return "home";
    }

}
