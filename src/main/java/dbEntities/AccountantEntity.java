package dbEntities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "accountant", schema = "public", catalog = "Bank")
public class AccountantEntity implements ArrGenerate{
    private int id;
    private String fullName;
    private String residenceAddress;
    private String position;
    private String phoneNumber;
    private BankEntity bankByIdBank;
    private Collection<ContractEntity> contractsById;

    public AccountantEntity(int id, BankEntity bankByIdBank, String fullName,
                            String residenceAddress, String position,
                            String phoneNumber) {
        this.id = id;
        this.fullName = fullName;
        this.residenceAddress = residenceAddress;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.bankByIdBank = bankByIdBank;
    }

    public AccountantEntity() {

    }

    @Id
    @Column(name = "id")
    public int getId() {
        return (int) id;
    }

    @Override
    public String[] toTable() {
        String idS=Integer.toString((int) id);
        String idBankS=Integer.toString(bankByIdBank.getId());
        String name=fullName;

        return new String[]{idS,idBankS,fullName,residenceAddress,position,phoneNumber};

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
