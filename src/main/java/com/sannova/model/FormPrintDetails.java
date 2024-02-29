package com.sannova.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDate createdAt;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private StudyTypes studyId;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private TemplateDetails templateDetails;



    @OneToMany(mappedBy = "formPrintDetails")
    private List<SerialNumberCount> serialNumberCount;

    
}
