package com.example.emtlabs.web.controllers;

import com.example.emtlabs.dto.WishlistDto;
import com.example.emtlabs.service.application.WishlistApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistApplicationService wishlistApplicationService;

    public WishlistController(WishlistApplicationService wishlistApplicationService) {
        this.wishlistApplicationService = wishlistApplicationService;
    }


    @GetMapping("/{username}")
    public ResponseEntity<WishlistDto> getWishlist(@PathVariable String username) {
        Optional<WishlistDto> wishlist = wishlistApplicationService.getWishlist(username);
        return wishlist.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/{username}/add")
    public ResponseEntity<WishlistDto> addBookToWishlist(@PathVariable String username, @RequestParam Long bookId) {
        try {
            Optional<WishlistDto> updatedWishlist = wishlistApplicationService.addToWishlist(username, bookId);
            return updatedWishlist.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(null);
        }
    }


    @PostMapping("/{username}/rent")
    public ResponseEntity<Void> rentAllBooks(@PathVariable String username) {
        try {
            wishlistApplicationService.rentAllBooks(username);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).build();
        }
    }
}