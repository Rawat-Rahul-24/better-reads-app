package io.rahul.betterreads.book;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookController {

    private final String COVER_IMAGE_URL = "https://covers.openlibrary.org/b/id/";

    @Autowired
    BookRepo bookRepo;

    @GetMapping("/books/{bookId}")
    public String getBook(@PathVariable String bookId, Model model) {
        Optional<Book> oBook = bookRepo.findById(bookId);
        if (oBook.isPresent()) {
            Book book = oBook.get();
            String coverUrl = "/images/no_image_found.jpg";
            if (book.getCoverId() != null && book.getCoverId().size() > 0) {
                coverUrl = COVER_IMAGE_URL + book.getCoverId().get(0) + "-L.jpg";

            }
            model.addAttribute("coverUrl", coverUrl);
            model.addAttribute("book", book);
            return "book";
        }

        return "book-not-found";
    }

}
