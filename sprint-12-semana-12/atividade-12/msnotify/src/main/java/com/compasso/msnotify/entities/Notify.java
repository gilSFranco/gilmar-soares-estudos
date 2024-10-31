package com.compasso.msnotify.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "users")
public class Notify implements Serializable {

    @Id
    private String id;
    private String username;
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notify notify = (Notify) o;
        return Objects.equals(id, notify.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
