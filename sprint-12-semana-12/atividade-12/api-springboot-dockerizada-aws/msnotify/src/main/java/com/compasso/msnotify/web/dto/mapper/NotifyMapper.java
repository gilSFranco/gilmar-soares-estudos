package com.compasso.msnotify.web.dto.mapper;

import com.compasso.msnotify.entities.Notify;
import com.compasso.msnotify.web.dto.NotifyDTO;
import org.modelmapper.ModelMapper;

public class NotifyMapper {
    private NotifyMapper() {
    }

    public static Notify toNotify(NotifyDTO notifyDTO) {
        return new ModelMapper().map(notifyDTO, Notify.class);
    }
}
