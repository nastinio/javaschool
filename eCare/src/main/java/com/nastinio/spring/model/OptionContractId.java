package com.nastinio.spring.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OptionContractId implements Serializable {
    @Column(name = "id_option")
    public Integer idOption;

    @Column(name = "id_contract")
    public Integer idContract;

    public OptionContractId() {
    }

    public OptionContractId(Integer idOption, Integer idContract) {
        this.idContract = idContract;
        this.idOption = idOption;
    }

    public Integer getIdOption() {
        return idOption;
    }

    public Integer getIdContract() {
        return idContract;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdContract(), getIdOption());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OptionContractId)) return false;
        OptionContractId that = (OptionContractId) o;
        return Objects.equals(getIdContract(), that.getIdContract()) &&
                Objects.equals(getIdOption(), that.getIdOption());
    }

    @Override
    public String toString() {
        return "OptionContractId{" +
                "idOption=" + idOption +
                ", idContract=" + idContract +
                '}';
    }
}
