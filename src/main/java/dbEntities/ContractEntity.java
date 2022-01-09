package dbEntities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "contract", schema = "public", catalog = "Bank")
public class ContractEntity implements ArrGenerate {
    private int id;
    private Date dateOfConckusion;
    private String pledge;
    private AccountantEntity accountantByAccountantId;
    private ClientEntity clientByClientId;
    private CreditEntity creditByCreditId;

    public ContractEntity() {

    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public ContractEntity(int id, AccountantEntity accountantByAccountantId,
                          ClientEntity clientByClientId, CreditEntity creditByCreditId,
                          Date dateOfConckusion, String pledge) {
        this.id = id;
        this.dateOfConckusion = dateOfConckusion;
        this.pledge = pledge;
        this.accountantByAccountantId = accountantByAccountantId;
        this.clientByClientId = clientByClientId;
        this.creditByCreditId = creditByCreditId;
    }

    @Override
    public String[] toTable() {
        String idS = Integer.toString((int) id);
        String idAccountant = Integer.toString(accountantByAccountantId.getId());
        String idClient = Integer.toString(clientByClientId.getId());
        String idCredit = Integer.toString(creditByCreditId.getId());
        String date = dateOfConckusion.toString();

        return new String[]{idS, idAccountant, idClient,
                idCredit, date, pledge};
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date_of_conckusion")
    public Date getDateOfConckusion() {
        return dateOfConckusion;
    }

    public void setDateOfConckusion(Date dateOfConckusion) {
        this.dateOfConckusion = dateOfConckusion;
    }

    @Basic
    @Column(name = "pledge")
    public String getPledge() {
        return pledge;
    }

    public void setPledge(String pledge) {
        this.pledge = pledge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractEntity that = (ContractEntity) o;
        return id == that.id && Objects.equals(dateOfConckusion, that.dateOfConckusion) && Objects.equals(pledge, that.pledge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOfConckusion, pledge);
    }

    @ManyToOne
    @JoinColumn(name = "accountant_id", referencedColumnName = "id")
    public AccountantEntity getAccountantByAccountantId() {
        return accountantByAccountantId;
    }

    public void setAccountantByAccountantId(AccountantEntity accountantByAccountantId) {
        this.accountantByAccountantId = accountantByAccountantId;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    public ClientEntity getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(ClientEntity clientByClientId) {
        this.clientByClientId = clientByClientId;
    }

    @ManyToOne
    @JoinColumn(name = "credit_id", referencedColumnName = "id")
    public CreditEntity getCreditByCreditId() {
        return creditByCreditId;
    }

    public void setCreditByCreditId(CreditEntity creditByCreditId) {
        this.creditByCreditId = creditByCreditId;
    }
}
