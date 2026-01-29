package com.example.emtlabs.service.application.Impl;

import com.example.emtlabs.dto.WishlistDto;
import com.example.emtlabs.model.domain.Book;
import com.example.emtlabs.model.domain.Wishlist;
import com.example.emtlabs.service.application.WishlistApplicationService;
import com.example.emtlabs.service.domain.BookService;
import com.example.emtlabs.service.domain.WishlistService;
import jakarta.transaction.Transactional;
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

    @Transactional
    @Override
    public void rentAllBooks(String username) {
        Wishlist wishlist = wishlistService.findByUserUsername(username)
                .orElseThrow(() ->
                        new IllegalArgumentException("Wishlist not found for user: " + username)
                );

        for (Book book : wishlist.getBooks()) {
            if (book.getAvailableCopies() <= 0) {
                throw new IllegalStateException(
                        "Cannot rent book: " + book.getName() + " has no available copies"
                );
            }
            book.setAvailableCopies(book.getAvailableCopies() - 1);
        }


        wishlist.getBooks().clear();

        wishlistService.save(wishlist);
    }

}