package com.example.emt1.service.domain.Impl;

import com.example.emt1.model.domain.Book;
import com.example.emt1.model.domain.User;
import com.example.emt1.model.domain.Wishlist;
import com.example.emt1.model.exceptions.BookNotAvailableException;
import com.example.emt1.model.exceptions.UserNotFoundException;
import com.example.emt1.repository.BookRepository;
import com.example.emt1.repository.UserRepository;
import com.example.emt1.repository.WishlistRepository;
import com.example.emt1.service.domain.WishlistService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.wishlistRepository = wishlistRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<Wishlist> findByUserUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("User not found"));
        return wishlistRepository.findByUser(user);
    }

    @Override
    public Wishlist createWishlist(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("User not found"));
        Wishlist wishlist = new Wishlist(user);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist addBookToWishlist(String username, Long bookId) {
        Wishlist wishlist = findByUserUsername(username).orElseGet(() -> createWishlist(username));
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotAvailableException::new);

        if (book.getAvailableCopies() <= 0) {
            throw new BookNotAvailableException();
        }

        wishlist.addBook(book);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public void rentAllBooks(String username) {
        Wishlist wishlist = findByUserUsername(username).orElseThrow(() -> new RuntimeException("Wishlist not found"));
        wishlist.getBooks().forEach(book -> {
            if (book.getAvailableCopies() > 0) {
                book.setAvailableCopies(book.getAvailableCopies() - 1);
            } else {
                throw new BookNotAvailableException();
            }
        });
        wishlist.clearWishlist();
        wishlistRepository.save(wishlist);
    }
    public void save(Wishlist wishlist) {
        wishlistRepository.save(wishlist);
    }

}