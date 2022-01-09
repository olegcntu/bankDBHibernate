package dbEntities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "credit", schema = "public", catalog = "Bank")
public class CreditEntity implements ArrGenerate {
    private int id;
    private Integer amount;
    private String currency;
    private Integer interest;
    private Integer estimatedTime;
    private Integer amountToBePaid;

    private Collection<ContractEntity> contractsById;

    public CreditEntity() {

    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    @Override
    public String[] toTable() {
        String idS = Integer.toString(id);
        String amountS=Integer.toString(amount);
        String interestS = interest.toString();
        String estimatedTimeS = estimatedTime.toString();
        String amountToBePaidS = amountToBePaid.toString();
        return new String[]{idS, amountS, currency, interestS, estimatedTimeS, amountToBePaidS};
    }

    public CreditEntity(int id, Integer amount, String currency, Integer interest,
                        Integer estimatedTime, Integer amountToBePaid) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.interest = interest;
        this.estimatedTime = estimatedTime;
        this.amountToBePaid = amountToBePaid;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "amount")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Basic
    @Column(name = "interest")
    public Integer getInterest() {
        return interest;
    }

    public void setInterest(Integer interest) {
        this.interest = interest;
    }

    @Basic
    @Column(name = "estimated_time")
    public Integer getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Integer estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    @Basic
    @Column(name = "amount_to_be_paid")
    public Integer getAmountToBePaid() {
        return amountToBePaid;
    }

    public void setAmountToBePaid(Integer amountToBePaid) {
        this.amountToBePaid = amountToBePaid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditEntity that = (CreditEntity) o;
        return id == that.id && Objects.equals(amount, that.amount) && Objects.equals(currency, that.currency) && Objects.equals(interest, that.interest) && Objects.equals(estimatedTime, that.estimatedTime) && Objects.equals(amountToBePaid, that.amountToBePaid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, currency, interest, estimatedTime, amountToBePaid);
    }

    @OneToMany(mappedBy = "creditByCreditId")
    public Collection<ContractEntity> getContractsById() {
        return contractsById;
    }

    public void setContractsById(Collection<ContractEntity> contractsById) {
        this.contractsById = contractsById;
    }
}
