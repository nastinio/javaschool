package com.nastinio.spring.model;

import javax.persistence.*;

@Entity
@Table(name = "contract")
public class Contract {

    @Id
    @Column(name = "id_contract")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String id_person_c;
    private int id_tariff_c;
    private String configuration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_person_c() {
        return id_person_c;
    }

    public void setId_person_c(String id_person_c) {
        this.id_person_c = id_person_c;
    }

    public int getId_tariff_c() {
        return id_tariff_c;
    }

    public void setId_tariff_c(int id_tariff_c) {
        this.id_tariff_c = id_tariff_c;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }
}
