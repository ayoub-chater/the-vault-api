package com.thevault.api.quiz.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "style_quiz_questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StyleQuizQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_number")
    private Integer questionNumber;

    private String section;

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "question_type")
    private String questionType;

    @Column(name = "placeholder_text")
    private String placeholderText;

    @Column(name = "is_required")
    @Builder.Default
    private boolean required = true;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> metadata;

    @Column(name = "display_order")
    private Integer displayOrder;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("displayOrder ASC")
    @Builder.Default
    private List<StyleQuizOption> options = new ArrayList<>();
}
