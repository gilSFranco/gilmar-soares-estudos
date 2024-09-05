package com.compasso.workshopmongo.services;

import com.compasso.workshopmongo.domain.Post;
import com.compasso.workshopmongo.repository.PostRepository;
import com.compasso.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post findById(String id) {
        Optional<Post> object = repository.findById(id);
        return object.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }
}
