package com.gorgeous.ringolift.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "part_of_speech")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PartOfSpeech extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pos_type")
    @UniqueElements
    private String posType;
}
