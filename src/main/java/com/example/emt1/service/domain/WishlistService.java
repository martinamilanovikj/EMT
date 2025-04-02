package com.example.emt1.service.domain;

import com.example.emt1.model.domain.Book;
import com.example.emt1.model.domain.Wishlist;

import java.util.List;
import java.util.Optional;

public interface WishlistService {
    Optional<Wishlist> findByUserUsername(String username);
    Wishlist createWishlist(String username);
    Wishlist addBookToWishlist(String username, Long bookId);
    void rentAllBooks(String username);
    void save(Wishlist wishlist);

}
