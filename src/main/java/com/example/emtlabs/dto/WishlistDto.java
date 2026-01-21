package com.example.emtlabs.dto;
import com.example.emtlabs.model.domain.Wishlist;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record WishlistDto(
        Long id,
        LocalDateTime dateCreated,
        DisplayUserDto user,
        List<DisplayBookDto> books
) {

    public static WishlistDto from(Wishlist wishlist) {
        return new WishlistDto(
                wishlist.getId(),
                wishlist.getDateCreated(),
                DisplayUserDto.from(wishlist.getUser()),
                wishlist.getBooks().stream()
                        .map(DisplayBookDto::from)
                        .collect(Collectors.toList())

        );
    }
}

