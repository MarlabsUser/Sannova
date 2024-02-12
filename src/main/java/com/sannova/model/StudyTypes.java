package com.sannova.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "study_types")
public class StudyTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "study_name")
    private String studyName;

    @Column(name = "status")
    private Boolean status;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<TemplateDetails> templateDetails;

}
