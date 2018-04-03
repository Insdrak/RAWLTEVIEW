package RU.ASR.DAO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by it_insdrak on 10.11.16.
 */
public class Accrec_extended {
    /*
    accrec_id	serv_accrec_id	cust_id	client_id	balance_id	serv2bal_id	dattim1	dattim2	nickname	contracttype_id	contract	persacc	name
    3	908255	383815	1248665	2652005	2354522	3506048	01.06.2005 18:15:05	26.11.2009 7:29:06	Карпов Игорь Викторович	14	733234	2476442	9089152825

*/
    private String accrec_id;
    private String serv_accrec_id;
    private String cust_id;
    private String client_id;
    private String balance_id;
    private String serv2bal_id;
    private String dattim1;
    private String dattim2;
    private String nickname;
    private String contracttype_id;
    private String contract;
    private String persacc;
    private String accrec_name;

    public Accrec_extended(String accrec_id, String serv_accrec_id, String cust_id, String client_id, String balance_id, String serv2bal_id, String dattim1, String dattim2, String nickname, String contracttype_id, String contract, String persacc, String accrec_name) {
        this.accrec_id = accrec_id;
        this.serv_accrec_id = serv_accrec_id;
        this.cust_id = cust_id;
        this.client_id = client_id;
        this.balance_id = balance_id;
        this.serv2bal_id = serv2bal_id;
        this.dattim1 = dattim1;
        this.dattim2 = dattim2;
        this.nickname = nickname;
        this.contracttype_id = contracttype_id;
        this.contract = contract;
        this.persacc = persacc;
        this.accrec_name = accrec_name;
    }

    public Accrec_extended(BigDecimal accrec_id, BigDecimal serv_accrec_id, BigDecimal cust_id, BigDecimal client_id, BigDecimal balance_id, BigDecimal serv2bal_id, Timestamp dattim1, Timestamp dattim2, String nickname, BigDecimal contracttype_id, String contract, String persacc, String accrec_name) {
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        this.accrec_id = accrec_id.toString();
        this.serv_accrec_id = serv_accrec_id.toString();
        this.cust_id = cust_id.toString();
        this.client_id = client_id.toString();
        this.balance_id = balance_id.toString();
        this.serv2bal_id = serv2bal_id.toString();
        Date date = new Date(dattim1.getTime());
        this.dattim1 = df.format(date);
        date = new Date(dattim2.getTime());
        this.dattim2 = df.format(date);
        this.nickname = nickname;
        this.contracttype_id = contracttype_id.toString();
        this.contract = contract;
        this.persacc = persacc;
        this.accrec_name = accrec_name;
    }

    @Override
    public String toString() {
        return this.getAccrec_name();
    }

    public String getAccrec_id() {
        return accrec_id;
    }

    public void setAccrec_id(String accrec_id) {
        this.accrec_id = accrec_id;
    }

    public String getServ_accrec_id() {
        return serv_accrec_id;
    }

    public void setServ_accrec_id(String serv_accrec_id) {
        this.serv_accrec_id = serv_accrec_id;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getBalance_id() {
        return balance_id;
    }

    public void setBalance_id(String balance_id) {
        this.balance_id = balance_id;
    }

    public String getServ2bal_id() {
        return serv2bal_id;
    }

    public void setServ2bal_id(String serv2bal_id) {
        this.serv2bal_id = serv2bal_id;
    }

    public String getDattim1() {
        return dattim1;
    }

    public void setDattim1(String dattim1) {
        this.dattim1 = dattim1;
    }

    public String getDattim2() {
        return dattim2;
    }

    public void setDattim2(String dattim2) {
        this.dattim2 = dattim2;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContracttype_id() {
        return contracttype_id;
    }

    public void setContracttype_id(String contracttype_id) {
        this.contracttype_id = contracttype_id;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getPersacc() {
        return persacc;
    }

    public void setPersacc(String persacc) {
        this.persacc = persacc;
    }

    public String getAccrec_name() {
        return accrec_name;
    }

    public void setAccrec_name(String accrec_name) {
        this.accrec_name = accrec_name;
    }
}
