package io.rahul.betterreads.book;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.rahul.betterreads.user.BooksByUser;
import io.rahul.betterreads.user.UserBook;
import io.rahul.betterreads.user.UserBookPrimaryKey;
import io.rahul.betterreads.user.UserBookRepo;

@Controller
public class BookController {

    private final String COVER_IMAGE_URL = "https://covers.openlibrary.org/b/id/";

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private UserBookRepo userBookRepo;

    @GetMapping("/books/{bookId}")
    public String getBook(@PathVariable String bookId, Model model, @AuthenticationPrincipal OAuth2User principal) {
        Optional<Book> oBook = bookRepo.findById(bookId);

        if (oBook.isPresent()) {
            Book book = oBook.get();
            String coverUrl = "/images/no_image_found.jpg";
            if (book.getCoverId() != null && book.getCoverId().size() > 0) {
                coverUrl = COVER_IMAGE_URL + book.getCoverId().get(0) + "-L.jpg";

            }
            model.addAttribute("coverUrl", coverUrl);
            model.addAttribute("book", book);

            if (principal != null && principal.getAttribute("login") != null) {
                model.addAttribute("loginId", principal.getAttribute("login"));
                Optional<UserBook> oUserBook = userBookRepo
                        .findById(new UserBookPrimaryKey(bookId, principal.getAttribute("login")));

                if (oUserBook.isPresent()) {
                    UserBook userBook = oUserBook.get();
                    model.addAttribute("userBook", userBook);
                } else {
                    model.addAttribute("userBook", new UserBook());
                }

            }
            return "book";
        }

        return "book-not-found";
    }

}
