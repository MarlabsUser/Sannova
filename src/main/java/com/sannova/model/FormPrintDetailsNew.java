package com.sannova.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "form_print_details_new")
public class FormPrintDetailsNew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "study_name")
    private String studyName;


    @Column(name = "number_of_forms_count")
    private  Integer numberOfFormsCount;

    @Column(name = "print_by")
    private String printBy;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private StudyTypes studyId;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private TemplateDetails templateDetails;

}
