package com.sannova.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "form_print_details")
public class FormPrintDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "study_name")
    private String studyName;


    @Column(name = "number_of_forms_count")
    private  Integer numberOfFormsCount;

    @Column(name = "print_by")
    private String printBy;

    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;


    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private StudyTypes studyId;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private TemplateDetails templateDetails;
    
}
