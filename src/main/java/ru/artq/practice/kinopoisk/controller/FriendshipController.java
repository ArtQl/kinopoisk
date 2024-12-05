package ru.artq.practice.kinopoisk.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.artq.practice.kinopoisk.model.User;
import ru.artq.practice.kinopoisk.service.FriendshipService;

import java.util.Collection;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FriendshipController {
    private final FriendshipService friendshipService;

    @GetMapping("/{id}/friends")
    public Collection<User> getFriends(@PathVariable Integer id) {
        return friendshipService.getFriends(id);
    }

    @PostMapping("/{id}/friends/{friendId}")
    public void sendFriendRequest(@PathVariable Integer id, @PathVariable Integer friendId) {
        friendshipService.sendFriendRequest(id, friendId);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public void acceptFriendRequest(@PathVariable Integer id, @PathVariable Integer friendId) {
        friendshipService.acceptFriendRequest(id, friendId);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void rejectFriendRequest(@PathVariable Integer id, @PathVariable Integer friendId) {
        friendshipService.rejectFriendRequest(id, friendId);
    }

    @GetMapping("/{id}/friends/{otherId}/mutual/")
    public Collection<User> getMutualFriends(@PathVariable Integer id, @PathVariable Integer otherId) {
        return friendshipService.getCommonFriends(id, otherId);
    }
}
