package RU.ASR.DAO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Aoi on 17.03.17.
 */
public class Lte_with_RG_spr_entry_converter {
    public static List<Lte_with_RG_spr_entry> convert_lte_with_RG_spr_entry_from_DB(List<Map<String, Object>> in_map){
        List<Lte_with_RG_spr_entry> out_result = new ArrayList<>();
        for (Map<String,Object> map_element:in_map){
            Lte_with_RG_spr_entry out_element = new Lte_with_RG_spr_entry ();
            out_element.setPDP_ADDRESS((String) map_element.get("PDP_ADDRESS"));
            out_element.setDATTIM((Timestamp) map_element.get("DATTIM"));
            out_element.setAPN((String) map_element.get("APN"));
            out_element.setDOWN_UNIT_VALUE((BigDecimal) map_element.get("DOWN_UNIT_VALUE"));
            out_element.setUP_UNIT_VALUE((BigDecimal) map_element.get("UP_UNIT_VALUE"));
            out_element.setNUM_IMSI((String) map_element.get("NUM_IMSI"));
            out_element.setNUM_IMEI((String) map_element.get("NUM_IMEI"));
            out_element.setDESCR((String) map_element.get("DESCR"));
            out_element.setRAT_TYPE((BigDecimal) map_element.get("RAT_TYPE"));
            out_element.setRATING_GROUP_ID((BigDecimal) map_element.get("RATING_GROUP_ID"));
            out_element.setDURATION((BigDecimal) map_element.get("DUR"));
            out_result.add(out_element);
        }
        return out_result;
    }

    public static List<Lte_with_RG_Converted> convert_lte_with_RG_spr_entry_from (List<Lte_with_RG_spr_entry> in_data){
        List<Lte_with_RG_Converted> out_result = new ArrayList<>();
        for (Lte_with_RG_spr_entry in_entry:in_data){
            Lte_with_RG_Converted out_element = new Lte_with_RG_Converted();
            out_element.setApn(in_entry.getAPN());
            out_element.setImei(in_entry.getNUM_IMEI());
            out_element.setImsi(in_entry.getNUM_IMSI());
            out_element.setIp(in_entry.getPDP_ADDRESS());
            BigDecimal total = new BigDecimal(0);
            total = total.add(in_entry.getDOWN_UNIT_VALUE());
            total = total.add(in_entry.getUP_UNIT_VALUE());
            out_element.setTotal_value(total.toBigInteger());
            out_element.setRg(String.valueOf(in_entry.getRATING_GROUP_ID()));
            out_element.setDate(in_entry.getDATTIM().toString());
            total = BigDecimal.valueOf(0);
            total = total.add(in_entry.getDURATION());
            out_element.setDuration(total.intValue());
            out_element.setMK(
                    in_entry.getDESCR().substring(
                            in_entry.getDESCR().indexOf("{MK}=")+5,
                            in_entry.getDESCR().indexOf(',',in_entry.getDESCR().indexOf("{MK}=")+5)
                    )
            );

            if (out_element.getRg().length() == 5) out_element.setRg('0'+out_element.getRg());
            switch (in_entry.getRAT_TYPE().intValue()){
                case 2:
                    out_element.setRat_type("GPRS");
                    break;
                case 6:
                    out_element.setRat_type("LTE");
                    break;
                default:
                    out_element.setRat_type("");
            }
            out_result.add(out_element);
        }
        return out_result;
    }
}
