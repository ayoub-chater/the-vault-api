package com.thevault.api.quiz.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "style_quiz_options")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StyleQuizOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private StyleQuizQuestion question;

    @Column(name = "option_text")
    private String optionText;

    @Column(name = "option_value")
    private String optionValue;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "display_order")
    private Integer displayOrder;
}
