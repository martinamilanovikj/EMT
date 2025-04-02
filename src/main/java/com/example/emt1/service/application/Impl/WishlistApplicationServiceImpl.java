package com.example.emt1.service.application.Impl;

import com.example.emt1.dto.WishlistDto;
import com.example.emt1.model.domain.Book;
import com.example.emt1.model.domain.Wishlist;
import com.example.emt1.service.application.WishlistApplicationService;
import com.example.emt1.service.domain.BookService;
import com.example.emt1.service.domain.WishlistService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistApplicationServiceImpl implements WishlistApplicationService {

    private final WishlistService wishlistService;
    private final BookService bookService;


    public WishlistApplicationServiceImpl(WishlistService wishlistService, BookService bookService) {
        this.wishlistService = wishlistService;
        this.bookService = bookService;

    }

    @Override
    public Optional<WishlistDto> getWishlist(String username) {
        return wishlistService.findByUserUsername(username).map(WishlistDto::from);
    }

    @Override
    public Optional<WishlistDto> addToWishlist(String username, Long bookId) {
        Book book = bookService.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new IllegalStateException("Cannot add book to wishlist: No available copies");
        }

        return Optional.of(WishlistDto.from(wishlistService.addBookToWishlist(username, bookId)));
    }

    @Override
    public void rentAllBooks(String username) {
        Wishlist wishlist = wishlistService.findByUserUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Wishlist not found for user: " + username));

        wishlist.getBooks().forEach(book -> {
            if (book.getAvailableCopies() > 0) {
                book.setAvailableCopies(book.getAvailableCopies() - 1);
            } else {
                throw new IllegalStateException("Cannot rent book: No available copies");
            }
        });


        wishlistService.save(wishlist);


        wishlist.getBooks().clear();

        wishlistService.save(wishlist);

    }
}