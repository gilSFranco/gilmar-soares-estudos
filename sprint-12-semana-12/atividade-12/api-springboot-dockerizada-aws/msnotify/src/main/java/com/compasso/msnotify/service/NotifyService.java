package com.compasso.msnotify.service;

import com.compasso.msnotify.entities.Notify;
import com.compasso.msnotify.repository.NotifyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NotifyService {

    private final NotifyRepository notifyRepository;

    public void insert(Notify notify) {
        notifyRepository.save(notify);
    }
}
