package dbEntities;

import jakarta.persistence.*;

import javax.swing.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "bank", schema = "public", catalog = "Bank")
public class BankEntity {
    private int id;

    public BankEntity(int id, String town, String number, String address, Double workStart, Double workEnd) {
        this.id = id;
        this.town = town;
        this.number = number;
        this.address = address;
        this.workStart = workStart;
        this.workEnd = workEnd;
    }

    private String town;
    private String number;
    private String address;
    private Double workStart;
    private Double workEnd;
    private Collection<AccountantEntity> accountantsById;

    public BankEntity() {

    }



    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "town")
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Basic
    @Column(name = "number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "work_start")
    public Double getWorkStart() {
        return workStart;
    }

    public void setWorkStart(Double workStart) {
        this.workStart = workStart;
    }

    @Basic
    @Column(name = "work_end")
    public Double getWorkEnd() {
        return workEnd;
    }

    public void setWorkEnd(Double workEnd) {
        this.workEnd = workEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankEntity that = (BankEntity) o;
        return id == that.id && Objects.equals(town, that.town) && Objects.equals(number, that.number) && Objects.equals(address, that.address) && Objects.equals(workStart, that.workStart) && Objects.equals(workEnd, that.workEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, town, number, address, workStart, workEnd);
    }

    @OneToMany(mappedBy = "bankByIdBank")
    public Collection<AccountantEntity> getAccountantsById() {
        return accountantsById;
    }

    public void setAccountantsById(Collection<AccountantEntity> accountantsById) {
        this.accountantsById = accountantsById;
    }
}
