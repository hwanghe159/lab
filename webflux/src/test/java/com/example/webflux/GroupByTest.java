package com.example.webflux;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
public class GroupByTest {

  public static final List<Book> books =
      Arrays.asList(
          new Book("Advance Java", "Tom",
              "Tom-boy", 25000, 100),
          new Book("Advance Python", "Grace",
              "Grace-girl", 22000, 150),
          new Book("Advance Reactor", "Smith",
              "David-boy", 35000, 200),
          new Book("Getting started Java", "Tom",
              "Tom-boy", 32000, 230),
          new Book("Advance Kotlin", "Kevin",
              "Kevin-boy", 32000, 250),
          new Book("Advance Javascript", "Mike",
              "Tom-boy", 32000, 320),
          new Book("Getting started Kotlin", "Kevin",
              "Kevin-boy", 32000, 150),
          new Book("Getting started Python", "Grace",
              "Grace-girl", 32000, 200),
          new Book("Getting started Reactor", "Smith",
              null, 32000, 250),
          new Book("Getting started Javascript", "Mike",
              "David-boy", 32000, 330)
      );

  @Test
  void groupByTest() {
    Flux.fromIterable(books)
        .groupBy(book -> book.getAuthorName())
        .flatMap(
            groupedFlux -> groupedFlux.map(
                    book -> book.getBookName() + "(" + book.getAuthorName() + ")"
                )
                .collectList())
        .subscribe(bookByAuthor -> log.info("# book by author: {}", bookByAuthor));
  }

  @Test
  void groupByTest2() {
    Flux.fromIterable(books)
        .groupBy(
            book -> book.getAuthorName(),
            book -> book.getBookName() + "(" + book.getAuthorName() + ")"
        )
        .flatMap(groupedFlux -> groupedFlux.collectList())
        .subscribe(bookByAuthor -> log.info("# book by author: {}", bookByAuthor));
  }

  @Test
  void groupByTest3() {
    Flux.fromIterable(books)
        .groupBy(book -> book.getAuthorName())
        .flatMap(
            groupedFlux ->
                Mono.just(groupedFlux.key())
                    .zipWith(
                        groupedFlux
                            .map(book -> (int) (book.getPrice() * book.getStockQuantity() * 0.1))
                            .reduce((y1, y2) -> y1 + y2),
                        (authorName, sumRoyalty) -> authorName + "'s royalty: " + sumRoyalty
                    )
        )
        .subscribe(log::info);
  }

  @AllArgsConstructor
  @Data
  private static class Book {

    private String bookName;
    private String authorName;
    private String penName;
    private int price;
    private int stockQuantity;
  }
}
