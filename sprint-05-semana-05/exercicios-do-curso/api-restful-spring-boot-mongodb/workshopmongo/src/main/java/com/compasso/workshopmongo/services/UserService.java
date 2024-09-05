package com.compasso.workshopmongo.services;

import com.compasso.workshopmongo.domain.User;
import com.compasso.workshopmongo.dto.UserDTO;
import com.compasso.workshopmongo.repository.UserRepository;
import com.compasso.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> object = repository.findById(id);
        return object.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public User insert(User object) {
        return repository.save(object);
    }

    public User fromDTO(UserDTO object) {
        return new User(object.getId(), object.getName(), object.getEmail());
    }
}
