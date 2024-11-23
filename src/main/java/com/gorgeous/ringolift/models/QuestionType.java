package com.gorgeous.ringolift.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "question_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionType extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ques_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionTypeEnum type;

    public static QuestionType fromString(String type) {
        var questionTypeEnum = QuestionType.QuestionTypeEnum.valueOf(type.toUpperCase());
        return QuestionType.builder().type(questionTypeEnum).build();
    }

    @Override
    public String toString() {
        return type.toString();
    }

    public enum QuestionTypeEnum {
        MULTIPLE_CHOICE,
        FILL_IN_THE_BLANK
    }
}