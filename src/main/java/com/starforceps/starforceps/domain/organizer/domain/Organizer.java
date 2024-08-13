package com.starforceps.starforceps.domain.organizer.domain;

import com.starforceps.starforceps.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "organizers")
public class Organizer {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private String originalText;

    private String organizedText;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
