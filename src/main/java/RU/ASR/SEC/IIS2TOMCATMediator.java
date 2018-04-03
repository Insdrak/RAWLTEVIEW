package RU.ASR.SEC;

import RU.ASR.DAO.JDBCQuerries;

import java.util.List;

/**
 * Created by Aoi on 27.01.2017.
 */
public class IIS2TOMCATMediator {
    static public boolean  check_access (String in_login_data, JDBCQuerries querries){
        List<String> user_list = querries.get_auth_user_names_list();
        if (user_list.contains(in_login_data)) return true;
        return false;
    }

    private String froram_user_in_login_data (String in_login_data){
        return in_login_data;
    }

}
