package com.waldron.ptcproject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Task {

    @Id
    private Long id;
    private String description;
    private Long priority;
}
