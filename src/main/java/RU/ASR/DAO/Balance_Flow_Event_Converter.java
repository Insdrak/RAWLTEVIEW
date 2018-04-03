package RU.ASR.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Aoi on 27.01.2017.
 */
public class Balance_Flow_Event_Converter {
    public static List<Balance_Flow_Event> convert_balance_flow_events (List<Map<String, Object>> in_map){
        List<Balance_Flow_Event> out_result = new ArrayList<>();
        for (Map<String,Object> map_element:in_map){
            Balance_Flow_Event out_element = new Balance_Flow_Event();
            out_element.setEvent_date(String.valueOf(map_element.get("EVENT_DATTIM")));
            out_element.setWd_event_date(String.valueOf(map_element.get("WD_DATTIM")));
            out_element.setEvent_name(String.valueOf(map_element.get("NAME")));
            out_element.setEvent_type(String.valueOf(map_element.get("EDR_TYPE_NAME")));
            out_element.setEvent_cost(String.valueOf(map_element.get("EVENT_COST")));
            out_element.setBal_before(String.valueOf(map_element.get("DECODE(BALPERIOD.DEFERRED_PAY,1,BALPERIOD.BALANCE_BEFORE+100,BALPERIOD.BALANCE_BEFORE)")));
            out_element.setBal_after(String.valueOf(map_element.get("DECODE(BALPERIOD.DEFERRED_PAY,1,BALPERIOD.BALANCE_AFTER+100,BALPERIOD.BALANCE_AFTER)")));
            out_element.setDef_p_status(String.valueOf(map_element.get("DECODE(BALPERIOD.DEFERRED_PAY,0,'ВЫКЛ','ВКЛ')")));
            out_element.setServ_single_id(String.valueOf(map_element.get("SERV_SINGLE_ID")));
            out_element.setServ_period_id(String.valueOf(map_element.get("SERV_PERIOD_ID")));
            out_result.add(out_element);
        }
        return out_result;
    }
}
