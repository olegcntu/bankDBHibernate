package dbEntities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "client", schema = "public", catalog = "Bank")
public class ClientEntity {
    private int id;
    private String fullName;
    private String residenceAddress;
    private String phoneNumber;
    private Boolean youredicPerson;
    private Collection<ContractEntity> contractsById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "residence_address")
    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    @Basic
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "youredic_person")
    public Boolean getYouredicPerson() {
        return youredicPerson;
    }

    public void setYouredicPerson(Boolean youredicPerson) {
        this.youredicPerson = youredicPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return id == that.id && Objects.equals(fullName, that.fullName) && Objects.equals(residenceAddress, that.residenceAddress) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(youredicPerson, that.youredicPerson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, residenceAddress, phoneNumber, youredicPerson);
    }

    @OneToMany(mappedBy = "clientByClientId")
    public Collection<ContractEntity> getContractsById() {
        return contractsById;
    }

    public void setContractsById(Collection<ContractEntity> contractsById) {
        this.contractsById = contractsById;
    }
}
