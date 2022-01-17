package io.rahul.betterreads.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.rahul.betterreads.user.BooksByUserRepo;
import io.rahul.betterreads.user.UserBook;
import io.rahul.betterreads.user.UserBookRepo;

@Controller
public class HomeController {

    @Autowired
    private BooksByUserRepo booksByUserRepo;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal OAuth2User principal, Model model) {

        if (principal == null) {
            return "index";
        }

        String userId = principal.getAttribute("login");
        Slice<UserBook> bookSlice = booksByUserRepo.findAllById(userId, CassandraPageRequest.of(0, 50));
        List<UserBook> userBook = bookSlice.getContent();
        model.addAttribute("userBook", userBook);
        return "home";
    }

}
