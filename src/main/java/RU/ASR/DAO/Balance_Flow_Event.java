package RU.ASR.DAO;

/**
 * Created by Aoi on 27.01.2017.
 */
public class Balance_Flow_Event {
    private String event_date;
    private String wd_event_date;
    private String event_name;
    private String event_type;
    private String event_cost;
    private String bal_before;
    private String bal_after;
    private String def_p_status;
    private String serv_single_id;
    private String serv_period_id;
    private String single_type;
    private String period_type;

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getWd_event_date() {
        return wd_event_date;
    }

    public void setWd_event_date(String wd_event_date) {
        this.wd_event_date = wd_event_date;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getEvent_cost() {
        return event_cost;
    }

    public void setEvent_cost(String event_cost) {
        this.event_cost = event_cost;
    }

    public String getBal_before() {
        return bal_before;
    }

    public void setBal_before(String bal_before) {
        this.bal_before = bal_before;
    }

    public String getBal_after() {
        return bal_after;
    }

    public void setBal_after(String bal_after) {
        this.bal_after = bal_after;
    }

    public String getDef_p_status() {
        return def_p_status;
    }

    public void setDef_p_status(String def_p_status) {
        this.def_p_status = def_p_status;
    }

    public String getServ_single_id() {
        return serv_single_id;
    }

    public void setServ_single_id(String serv_single_id) {
        this.serv_single_id = serv_single_id;
    }

    public String getServ_period_id() {
        return serv_period_id;
    }

    public void setServ_period_id(String serv_period_id) {
        this.serv_period_id = serv_period_id;
    }

    public String getSingle_type() {
        return single_type;
    }

    public void setSingle_type(String single_type) {
        this.single_type = single_type;
    }

    public String getPeriod_type() {
        return period_type;
    }

    public void setPeriod_type(String period_type) {
        this.period_type = period_type;
    }
}
