package uz.pdp.g30springrestful.controller;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.g30springrestful.domain.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/posts")
public class PostController {
    private AtomicLong inc = new AtomicLong(1);
    private List<Post> posts = new ArrayList<>() {{
        add(new Post(inc.getAndIncrement(), "Java 8", "Java 8 features", "Josh Bloch"));
        add(new Post(inc.getAndIncrement(), "Java 17", "Java 17 features", "Josh Bloch"));
        add(new Post(inc.getAndIncrement(), "Java 21", "Java 21 features", "Josh Bloch"));
    }};

    @GetMapping(value = "/{id}", produces = {
            MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE
    })
    public Post getById(@PathVariable Long id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Post not found with id -" + id));
    }

    @GetMapping
    public List<Post> getPosts() {
        return posts;
    }

    @PostMapping
    public Post save(@RequestBody @Valid Post post) {
        post.setId(inc.getAndIncrement());
        posts.add(post);
        return post;
    }

}
