package RU.ASR.DAO;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by Aoi on 17.03.17.
 */
public class Lte_with_RG_spr_entry {
    private String PDP_ADDRESS;
    private Timestamp DATTIM;
    private String APN;
    private BigDecimal DOWN_UNIT_VALUE;
    private BigDecimal UP_UNIT_VALUE;
    private String NUM_IMSI;
    private String NUM_IMEI;
    private String DESCR;
    private BigDecimal RAT_TYPE;
    private BigDecimal RATING_GROUP_ID;
    private BigDecimal DURATION;

    public Lte_with_RG_spr_entry() {
    }

    public Lte_with_RG_spr_entry(String PDP_ADDRESS, Timestamp DATTIM, String APN, BigDecimal DOWN_UNIT_VALUE, BigDecimal UP_UNIT_VALUE, String NUM_IMSI, String NUM_IMEI, String DESCR, BigDecimal RAT_TYPE, BigDecimal RATING_GROUP_ID) {
        this.PDP_ADDRESS = PDP_ADDRESS;
        this.DATTIM = DATTIM;
        this.APN = APN;
        this.DOWN_UNIT_VALUE = DOWN_UNIT_VALUE;
        this.UP_UNIT_VALUE = UP_UNIT_VALUE;
        this.NUM_IMSI = NUM_IMSI;
        this.NUM_IMEI = NUM_IMEI;
        this.DESCR = DESCR;
        this.RAT_TYPE = RAT_TYPE;
        this.RATING_GROUP_ID = RATING_GROUP_ID;
    }

    public String getPDP_ADDRESS() {
        return PDP_ADDRESS;
    }

    public void setPDP_ADDRESS(String PDP_ADDRESS) {
        this.PDP_ADDRESS = PDP_ADDRESS;
    }

    public Timestamp getDATTIM() {
        return DATTIM;
    }

    public void setDATTIM(Timestamp DATTIM) {
        this.DATTIM = DATTIM;
    }

    public String getAPN() {
        return APN;
    }

    public void setAPN(String APN) {
        this.APN = APN;
    }

    public BigDecimal getDOWN_UNIT_VALUE() {
        return DOWN_UNIT_VALUE;
    }

    public void setDOWN_UNIT_VALUE(BigDecimal DOWN_UNIT_VALUE) {
        this.DOWN_UNIT_VALUE = DOWN_UNIT_VALUE;
    }

    public BigDecimal getUP_UNIT_VALUE() {
        return UP_UNIT_VALUE;
    }

    public void setUP_UNIT_VALUE(BigDecimal UP_UNIT_VALUE) {
        this.UP_UNIT_VALUE = UP_UNIT_VALUE;
    }

    public String getNUM_IMSI() {
        return NUM_IMSI;
    }

    public void setNUM_IMSI(String NUM_IMSI) {
        this.NUM_IMSI = NUM_IMSI;
    }

    public String getNUM_IMEI() {
        return NUM_IMEI;
    }

    public void setNUM_IMEI(String NUM_IMEI) {
        this.NUM_IMEI = NUM_IMEI;
    }

    public String getDESCR() {
        return DESCR;
    }

    public void setDESCR(String DESCR) {
        this.DESCR = DESCR;
    }

    public BigDecimal getRAT_TYPE() {
        return RAT_TYPE;
    }

    public void setRAT_TYPE(BigDecimal RAT_TYPE) {
        this.RAT_TYPE = RAT_TYPE;
    }

    public BigDecimal getRATING_GROUP_ID() {
        return RATING_GROUP_ID;
    }

    public void setRATING_GROUP_ID(BigDecimal RATING_GROUP_ID) {
        this.RATING_GROUP_ID = RATING_GROUP_ID;
    }

    public BigDecimal getDURATION() {
        return DURATION;
    }

    public void setDURATION(BigDecimal DURATION) {
        this.DURATION = DURATION;
    }
}
