package com.compasso.msnotify.entities;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "users")
public class Notify implements Serializable {
    private String username;
    private String operation;
}
