package com.example.graphqldemo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BlogGraphQLController {


	@Autowired
	Userservice userservice;
    @QueryMapping
    public User user(@Argument String id) {

        User user = userservice.getUser("1");
        List<Post> posts = userservice.getPosts("1");

        for (Post post : posts) {
            List<Comment> comments = userservice.getComments(post.getId());
            post.setComments(comments);
        }

        user.setPosts(posts);
        return user;
    }
}
