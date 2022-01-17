package io.rahul.betterreads.user;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import io.rahul.betterreads.book.Book;
import io.rahul.betterreads.book.BookRepo;

@Controller
public class UserBookController {

    @Autowired
    private UserBookRepo userBookRepo;

    @Autowired
    private BooksByUserRepo booksByUserRepo;

    @Autowired
    private BookRepo bookRepo;

    @PostMapping("/userbook")
    public ModelAndView addBookForUser(@AuthenticationPrincipal OAuth2User principal,
            @RequestBody MultiValueMap<String, String> data) {
        UserBook userBook = new UserBook();
        UserBookPrimaryKey key = new UserBookPrimaryKey();
        if (principal == null && principal.getAttribute("login") == null) {
            return null;
        }
        key.setUserId(principal.getAttribute("login"));
        String bookId = data.getFirst("bookId");

        Optional<Book> optionalBook = bookRepo.findById(bookId);
        if (!optionalBook.isPresent()) {
            return new ModelAndView("redirect:/");
        }
        Book book = optionalBook.get();

        key.setBookId(bookId);
        userBook.setKey(key);
        userBook.setStartDate(LocalDate.parse(data.getFirst("startDate")));
        userBook.setEndDate(LocalDate.parse(data.getFirst("endDate")));
        userBook.setRating(Integer.parseInt(data.getFirst("rating")));
        userBook.setStatus(data.getFirst("status"));
        userBookRepo.save(userBook);

        BooksByUser booksByUser = new BooksByUser();
        booksByUser.setBookId(bookId);
        booksByUser.setId(principal.getAttribute("login"));
        booksByUser.setAuthorNames(book.getAuthorName());
        booksByUser.setBookName(book.getTitle());
        booksByUser.setCoverIds(book.getCoverId());
        booksByUser.setRating(userBook.getRating());
        booksByUser.setReadingStatus(userBook.getStatus());
        booksByUserRepo.save(booksByUser);

        return new ModelAndView("redirect:/books/" + bookId);
    }
}
