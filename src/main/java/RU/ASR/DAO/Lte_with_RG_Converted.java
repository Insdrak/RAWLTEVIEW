package RU.ASR.DAO;

import java.math.BigInteger;

/**
 * Created by Aoi on 17.03.17.
 */
public class Lte_with_RG_Converted {
    private String ip;
    private String apn;
    private BigInteger total_value;
    private String rg_type;
    private String rg_usage;
    private String rg_speed;
    private String rg;
    private String rat_type;
    private String imsi;
    private String imei;
    private String date;
    private Integer duration;
    private String MK;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getApn() {
        return apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
    }

    public BigInteger getTotal_value() {
        return total_value;
    }

    public void setTotal_value(BigInteger total_value) {
        this.total_value = total_value;
    }

    public String getRg_type() {
        return rg_type;
    }

    public void setRg_type(String rg_type) {
        this.rg_type = rg_type;
    }

    public String getRat_type() {
        return rat_type;
    }

    public void setRat_type(String rat_type) {
        this.rat_type = rat_type;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getRg_usage() {
        return rg_usage;
    }

    public void setRg_usage(String rg_usage) {
        this.rg_usage = rg_usage;
    }

    public String getRg_speed() {
        return rg_speed;
    }

    public void setRg_speed(String rg_speed) {
        this.rg_speed = rg_speed;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMK() {
        return MK;
    }

    public void setMK(String MK) {
        this.MK = MK;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
