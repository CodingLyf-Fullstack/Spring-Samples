package com.example.graphqldemo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class Userservice {

    public User getUser( String id) {
        return new User(id, "Raju", "raju@mail.com", null);
    }

    public List<Post> getPosts( String userId) {
        return List.of(
            new Post("101", "GraphQL Made Easy", null),
            new Post("102", "REST vs GraphQL", null)
        );
    }

    public List<Comment> getComments(String postId) {
        return List.of(
            new Comment("1", "Nice post!"),
            new Comment("2", "Very helpful!")
        );
    }
}
