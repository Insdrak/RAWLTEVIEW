package RU.ASR.DAO;

import RU.ASR.UTIL.OracleDateFormatter;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by Aoi on 27.01.2017.
 */
public class JDBCQuerries {
    protected JdbcTemplate template;
    private OracleDateFormatter df = new OracleDateFormatter();

    public JDBCQuerries(ApplicationContext context) {
        template = (JdbcTemplate) context.getBean("jdbcTemplate");
    }

    public List<String> get_auth_user_names_list (){
        String sql =
                "SELECT *                                     "+
                        "FROM STATBOY.ASR_ON_RAILS_RIGHTS AORR        "+
                        "WHERE AORR.R_APPLICATION = 'RawLteView' "
                ;
        List<Map<String, Object>> result = template.queryForList(sql, new Object[]{});
        List<String> out_list = new ArrayList<String>();
        for (Map<String, Object> r:result){
            String s = new String(String.valueOf(r.get("R_USER")));
            out_list.add(s);
        }
        return out_list;
    }

    public List<Accrec_extended> get_accrec_extended (String in_number){
        String sql =
                " SELECT                                                         " +
                        " A.ACCREC_ID,                                                   " +
                        " SA.SERV_ACCREC_ID,                                             " +
                        " C.CUST_ID,                                                     " +
                        " C.CLIENT_ID,                                                   " +
                        " B.BALANCE_ID,                                                  " +
                        " S2B.SERV2BAL_ID,                                               " +
                        " SA.DATTIM1,                                                    " +
                        " SA.DATTIM2,                                                    " +
                        " C.NICKNAME,                                                    " +
                        " C.CONTRACTTYPE_ID,                                             " +
                        " C.CONTRACT,                                                    " +
                        " B.PERSACC,                                                     " +
                        " A.NAME                                                         " +
                        " FROM SYSBEE.CUST        C,                                     " +
                        " SYSBEE.CLIENTTYPE  CT,                                         " +
                        " SYSBEE.BALANCE     B,                                          " +
                        " SYSBEE.SERV2BAL    S2B,                                        " +
                        " SYSBEE.SERV_ACCREC SA,                                         " +
                        " SYSBEE.ACCREC      A                                           " +
                        " WHERE A.NAME =                                                 " +
                        "'"+in_number+"'"                                                  +
                        " AND C.CLIENTTYPE_ID = CT.CLIENTTYPE_ID                         " +
                        " AND B.CUST_ID = C.CUST_ID                                      " +
                        " AND B.BALANCE_ID = S2B.BALANCE_ID                              " +
                        " AND S2B.SERV2BAL_ID = SA.SERV2BAL_ID                           " +
                        " AND SA.ACCREC_ID = A.ACCREC_ID                                 " +
                        " ORDER BY SA.DATTIM1 DESC                                       " ;
        List<Map<String, Object>> result = template.queryForList(sql, new Object[]{});
        List<Accrec_extended> out = new ArrayList<Accrec_extended>();

        for (Map<String, Object> in_objectMap : result){
            out.add(new Accrec_extended
                            (
                                    (BigDecimal)in_objectMap.get("ACCREC_ID"),
                                    (BigDecimal)in_objectMap.get("SERV_ACCREC_ID"),
                                    (BigDecimal)in_objectMap.get("CUST_ID"),
                                    (BigDecimal)in_objectMap.get("CLIENT_ID"),
                                    (BigDecimal)in_objectMap.get("BALANCE_ID"),
                                    (BigDecimal)in_objectMap.get("SERV2BAL_ID"),
                                    (Timestamp)in_objectMap.get("DATTIM1"),
                                    (Timestamp)in_objectMap.get("DATTIM2"),
                                    (String)in_objectMap.get("NICKNAME"),
                                    (BigDecimal)in_objectMap.get("CONTRACTTYPE_ID"),
                                    (String)in_objectMap.get("CONTRACT"),
                                    (String)in_objectMap.get("PERSACC"),
                                    (String)in_objectMap.get("NAME")
                            )
            );
        }
        return out;
    }

    public List<Lte_with_RG_spr_entry> get_raw_lte (String in_accrec_id,Date in_date1,Date in_date2){
        System.out.printf("get_raw_lte \n\r");
        System.out.printf(in_date1.toString() + "\n\r");
        System.out.printf(in_date2.toString() + "\n\r");
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String string_date1 = df.format(in_date1);
        String string_date2 = df.format(in_date2);
        String sql =
        " SELECT *                                   " +
        " FROM                                       " +
        " SYSBEE.LTE L,                              " +
        " SYSBEE.SPR_RATING_GROUP S                  " +
        " WHERE                                      " +
        " L.NUM = '"+in_accrec_id+"'                 " +
        " AND L.DATTIM BETWEEN                       " +
        " MY_DATE('"+string_date1+"')                " +
        " AND                                        " +
        " MY_DATE('"+string_date2+"')                " +
        " AND L.RATING_GROUP_ID = S.RATING_GROUP_ID  " +
        " AND S.EVENT_SRC_TYPE_ID = 13               " +
        " AND L.DOWN_UNIT_VALUE != 0                 " +
        " ORDER BY L.DATTIM                          " ;
        List<Map<String, Object>> result = template.queryForList(sql, new Object[]{});
        System.out.printf("result "+result.size()+"\n\r");
        return Lte_with_RG_spr_entry_converter.convert_lte_with_RG_spr_entry_from_DB(result);
    }
    public Map<String,String> get_RAW_LTE_VIEW_RG_SPEED (){
        String sql =
                " SELECT *                            " +
                " FROM STATBOY.TMP_DLISTS             " +
                " WHERE C1 = 'RAW_LTE_VIEW_RG_SPEED'  " ;
        List<Map<String, Object>> result = template.queryForList(sql, new Object[]{});
        Map<String,String> out_map = new HashMap<>();
        for (Map<String, Object> in_map:result){
            out_map.put((String) in_map.get("C3"),(String) in_map.get("C2"));
        }
        return out_map;
    }

    public Map<String,String> get_RAW_LTE_VIEW_RG_TYPE (){
        String sql =
                " SELECT *                            " +
                        " FROM STATBOY.TMP_DLISTS             " +
                        " WHERE C1 = 'RAW_LTE_VIEW_RG_TYPE'  " ;
        List<Map<String, Object>> result = template.queryForList(sql, new Object[]{});
        Map<String,String> out_map = new HashMap<>();
        for (Map<String, Object> in_map:result){
            out_map.put((String) in_map.get("C2"),(String) in_map.get("C3"));
        }
        return out_map;
    }

    public Map<String,String> get_RAW_LTE_VIEW_RG_USAGE (){
        String sql =
                " SELECT *                            " +
                        " FROM STATBOY.TMP_DLISTS             " +
                        " WHERE C1 = 'RAW_LTE_VIEW_RG_USAGE'  " ;
        List<Map<String, Object>> result = template.queryForList(sql, new Object[]{});
        Map<String,String> out_map = new HashMap<>();
        for (Map<String, Object> in_map:result){
            out_map.put((String) in_map.get("C2"),(String) in_map.get("C3"));
        }
        return out_map;
    }

    public Boolean isECSAccrec (String accrec){
        String sql =
                "SELECT "+
                "1 "+
                "FROM "+
                "SYSBEE.SERV2BAL    S2B, "+
                "SYSBEE.SERV_ACCREC SA, "+
                "SYSBEE.ACCREC      A, "+
                "SYSBEE.SERV        S "+
                "WHERE "+
                "S.SERV_ID         = S2B.SERV_ID "+
                "AND S2B.P_SERV2BAL_ID = SA.SERV2BAL_ID "+
                "AND SA.ACCREC_ID      = A.ACCREC_ID "+
                "AND A.NAME = '9530407002' "+
                "AND S.SERV_ID = 5301 "+
                "AND SYSDATE BETWEEN S2B.DATTIM1 AND S2B.DATTIM2 ";
        try (Connection connection = template.getDataSource().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.first()){
                    return true;
                }
                else return false;
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}