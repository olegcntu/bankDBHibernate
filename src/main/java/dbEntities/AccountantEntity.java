package dbEntities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "accountant", schema = "public", catalog = "Bank")
public class AccountantEntity {
    private long id;
    private String fullName;
    private String residenceAddress;
    private String position;
    private String phoneNumber;
    private BankEntity bankByIdBank;
    private Collection<ContractEntity> contractsById;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    @Column(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Basic
    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountantEntity that = (AccountantEntity) o;
        return id == that.id && Objects.equals(fullName, that.fullName) && Objects.equals(residenceAddress, that.residenceAddress) && Objects.equals(position, that.position) && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, residenceAddress, position, phoneNumber);
    }

    @ManyToOne
    @JoinColumn(name = "id_bank", referencedColumnName = "id", nullable = false)
    public BankEntity getBankByIdBank() {
        return bankByIdBank;
    }

    public void setBankByIdBank(BankEntity bankByIdBank) {
        this.bankByIdBank = bankByIdBank;
    }

    @OneToMany(mappedBy = "accountantByAccountantId")
    public Collection<ContractEntity> getContractsById() {
        return contractsById;
    }

    public void setContractsById(Collection<ContractEntity> contractsById) {
        this.contractsById = contractsById;
    }
}
