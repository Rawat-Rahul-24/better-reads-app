package io.rahul.betterreads.search;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Controller
public class SearchController {

    private final String COVER_IMAGE_URL = "https://covers.openlibrary.org/b/id/";

    private final WebClient webClient;

    public SearchController(WebClient.Builder builder) {
        this.webClient = builder.exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs()
                        .maxInMemorySize(16 * 1024 * 1024))
                .build())
                .baseUrl("http://openlibrary.org/search.json").build();
    }

    @GetMapping("/search")
    public String getSearchResults(@RequestParam String query, Model model) {
        Mono<SearchResult> foo = this.webClient.get().uri("?title={query}", query)
                .retrieve().bodyToMono(SearchResult.class);

        SearchResult result = foo.block();
        List<SearchResultBookContainer> books = result.getDocs().stream().limit(10)
                .map(book -> {
                    book.setKey(book.getKey().replace("/works/", ""));

                    String coverId = book.getCover_i();
                    // System.out.println(isbn);

                    if (StringUtils.hasText(coverId)) {
                        coverId = COVER_IMAGE_URL + book.getCover_i() + "-M.jpg";
                    } else {
                        coverId = "/images/no_image_found.jpg";
                    }

                    book.setCover_i(coverId);
                    book.setPublishedYear(book.getPublish_year().get(0));

                    return book;
                }).collect(Collectors.toList());
        System.out.println(result.getNumFound());
        model.addAttribute("books", books);
        return "search";
    }
}
