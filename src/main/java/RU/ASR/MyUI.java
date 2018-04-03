package RU.ASR;

import RU.ASR.DAO.JDBCQuerries;
import RU.ASR.DAO.Lte_with_RG_Converted;
import RU.ASR.DAO.Lte_with_RG_spr_entry;
import RU.ASR.DAO.Lte_with_RG_spr_entry_converter;
import RU.ASR.SEC.IIS2TOMCATMediator;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.annotation.WebServlet;
import java.math.BigInteger;
import java.util.*;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
    private final Integer session_timeout = 18000;
    private String user_name = "";
    JDBCQuerries jdbcQuerries;

    private final String access_denied = "Доступ к данному приложению ограничен. Пожалуйста обратитесь в отдел АСР";

    private List<Lte_with_RG_spr_entry> current_data;
    private List<Lte_with_RG_Converted> current_data_converted;
    private List<Lte_with_RG_Converted> current_data_converted_backup;
    private final DateField input_start_range_date_field = new DateField();
    private final DateField input_end_range_date_field = new DateField();
    private final Table raw_lte_main_table = new Table();
    private final Label out_total_value_label = new Label();

    private Map<String,String>spr_RAW_LTE_VIEW_RG_USAGE = new HashMap<>();
    private Map<String,String>spr_RAW_LTE_VIEW_RG_SPEED = new HashMap<>();
    private Map<String,String>spr_RAW_LTE_VIEW_RG_TYPE = new HashMap<>();

    private final CheckBox main_q_checkBox = new CheckBox("основная дневная квота");
    private final CheckBox turbo_button_q_checkBox = new CheckBox("турбо кнопка");
    private final CheckBox oneGB_pack_q_checkBox = new CheckBox("пакет 1гб");
    private final CheckBox p_to_p_q_checkBox = new CheckBox("P2P");

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VaadinSession.getCurrent().getSession().setMaxInactiveInterval(session_timeout); //60*60*5
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        jdbcQuerries = new JDBCQuerries(applicationContext);
        user_name = vaadinRequest.getParameter("authUser");
        if (IIS2TOMCATMediator.check_access(user_name, jdbcQuerries)) {
            //HorizontalLayout main_hor_layout = new HorizontalLayout();
            VerticalLayout main_page_layout = new VerticalLayout();
            setContent(main_page_layout);
            spr_RAW_LTE_VIEW_RG_SPEED = jdbcQuerries.get_RAW_LTE_VIEW_RG_SPEED();
            spr_RAW_LTE_VIEW_RG_TYPE = jdbcQuerries.get_RAW_LTE_VIEW_RG_TYPE();
            spr_RAW_LTE_VIEW_RG_USAGE = jdbcQuerries.get_RAW_LTE_VIEW_RG_USAGE();
            final Panel control_panel = new Panel("Просмотр информацию по трафиковым LTE данным");
            VerticalLayout main_control_panel_layout = new VerticalLayout();
            main_control_panel_layout.setMargin(true);
            Label accrec_name_input_label = new Label("<b>Учетная запись абонента: </b>", ContentMode.HTML);
            final TextField accrec_name_input_text_field = new TextField();
            accrec_name_input_text_field.setMaxLength(10);
            Label date_range_input_label = new Label("<b>Период активности : </b>", ContentMode.HTML);
            input_start_range_date_field.setDateFormat("dd.MM.yyyy HH:mm:ss");
            input_start_range_date_field.setResolution(Resolution.SECOND);
            input_start_range_date_field.setValue(new Date());
            input_start_range_date_field.setWidth("250");
            input_end_range_date_field.setDateFormat("dd.MM.yyyy HH:mm:ss");
            input_end_range_date_field.setResolution(Resolution.SECOND);
            input_end_range_date_field.setValue(new Date());
            input_end_range_date_field.setWidth("250");
            Button get_raw_lte_button = new Button("Загрузить данные");
            raw_lte_main_table.addContainerProperty("Дата", String.class, "");
            raw_lte_main_table.addContainerProperty("Категория", String.class, "");
            raw_lte_main_table.addContainerProperty("Квота", String.class, "");
            raw_lte_main_table.addContainerProperty("Скорость", String.class, "");
            raw_lte_main_table.addContainerProperty("Объем данных", BigInteger.class, 0);
            raw_lte_main_table.addContainerProperty("Время", Integer.class, 0);
            raw_lte_main_table.addContainerProperty("IP адрес", String.class, "");
            raw_lte_main_table.addContainerProperty("Точка доступа", String.class, "");
            raw_lte_main_table.addContainerProperty("Тип", String.class, "");
            raw_lte_main_table.setWidth("100%");
            //raw_lte_main_table.addContainerProperty("MK", String.class, "");
            //raw_lte_main_table.addContainerProperty("IMSI", String.class, "");
            //raw_lte_main_table.addContainerProperty("IMEI", String.class, "");

            /*main_q_checkBox.setImmediate(true);
            oneGB_pack_q_checkBox.setImmediate(true);
            turbo_button_q_checkBox.setImmediate(true);
            p_to_p_q_checkBox.setImmediate(true);*/
            main_q_checkBox.setEnabled(false);
            oneGB_pack_q_checkBox.setEnabled(false);
            turbo_button_q_checkBox.setEnabled(false);
            p_to_p_q_checkBox.setEnabled(false);
            final Boolean flag = true;

            get_raw_lte_button.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    if(jdbcQuerries.isECSAccrec(accrec_name_input_text_field.getValue())){
                        main_q_checkBox.setValue(false);
                        oneGB_pack_q_checkBox.setValue(false);
                        turbo_button_q_checkBox.setValue(false);
                        p_to_p_q_checkBox.setValue(false);
                        main_q_checkBox.setEnabled(true);
                        oneGB_pack_q_checkBox.setEnabled(true);
                        turbo_button_q_checkBox.setEnabled(true);
                        p_to_p_q_checkBox.setEnabled(true);
                        current_data = jdbcQuerries.get_raw_lte(accrec_name_input_text_field.getValue(), input_start_range_date_field.getValue(), input_end_range_date_field.getValue());
                        current_data_converted = Lte_with_RG_spr_entry_converter.convert_lte_with_RG_spr_entry_from(current_data);
                        parse_RG_in_converted_data();
                        reassemble_table();
                    }
                    System.out.printf("Данный сервис не потдерживает работу с номерами перенесенными в ECS");
                }
            });

            final Label filter_label = new Label("Доступные фильтры: ");

            main_q_checkBox.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                    if (flag){
                        List<Lte_with_RG_Converted> to_remove_list = new ArrayList<Lte_with_RG_Converted>();
                        for (Lte_with_RG_Converted lte_with_rg_converted:current_data_converted){
                            if (!lte_with_rg_converted.getMK().matches("121")) to_remove_list.add(lte_with_rg_converted);
                        }
                        main_q_checkBox.setEnabled(false);
                        current_data_converted.removeAll(to_remove_list);
                        reassemble_table();
                    }
                }
            });

            turbo_button_q_checkBox.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                    if (flag){
                        List<Lte_with_RG_Converted> to_remove_list = new ArrayList<Lte_with_RG_Converted>();
                        for (Lte_with_RG_Converted lte_with_rg_converted:current_data_converted){
                            if (!lte_with_rg_converted.getMK().matches("100")) to_remove_list.add(lte_with_rg_converted);
                        }
                        turbo_button_q_checkBox.setEnabled(false);
                        current_data_converted.removeAll(to_remove_list);
                        reassemble_table();
                    }
                }
            });

            oneGB_pack_q_checkBox.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                    if (flag){
                        List<Lte_with_RG_Converted> to_remove_list = new ArrayList<Lte_with_RG_Converted>();
                        for (Lte_with_RG_Converted lte_with_rg_converted:current_data_converted){
                            if (!lte_with_rg_converted.getMK().matches("111")) to_remove_list.add(lte_with_rg_converted);
                        }
                        oneGB_pack_q_checkBox.setEnabled(false);
                        current_data_converted.removeAll(to_remove_list);
                        reassemble_table();
                    }
                }
            });

            p_to_p_q_checkBox.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                    if (flag){
                        List<Lte_with_RG_Converted> to_remove_list = new ArrayList<Lte_with_RG_Converted>();
                        for (Lte_with_RG_Converted lte_with_rg_converted : current_data_converted) {
                            if (!lte_with_rg_converted.getRg_type().matches("P2P"))
                                to_remove_list.add(lte_with_rg_converted);
                        }
                        p_to_p_q_checkBox.setEnabled(false);
                        current_data_converted.removeAll(to_remove_list);
                        reassemble_table();
                    }
                }
            });



            main_control_panel_layout.addComponent(accrec_name_input_label);
            main_control_panel_layout.addComponent(accrec_name_input_text_field);
            main_control_panel_layout.addComponent(date_range_input_label);
            //main_control_panel_layout.addComponent(accrec_name_input_result_label);
            main_control_panel_layout.addComponent(input_start_range_date_field);
            main_control_panel_layout.addComponent(input_end_range_date_field);
            main_control_panel_layout.addComponent(get_raw_lte_button);

            main_control_panel_layout.addComponent(filter_label);
            main_control_panel_layout.addComponent(main_q_checkBox);
            main_control_panel_layout.addComponent(oneGB_pack_q_checkBox);
            main_control_panel_layout.addComponent(turbo_button_q_checkBox);
            main_control_panel_layout.addComponent(p_to_p_q_checkBox);

            main_control_panel_layout.addComponent(out_total_value_label);
            main_control_panel_layout.addComponent(raw_lte_main_table);
            control_panel.setContent(main_control_panel_layout);
            main_page_layout.addComponent(control_panel);
            main_page_layout.setMargin(true);
            main_page_layout.setSpacing(true);
            main_control_panel_layout.setMargin(true);
            main_control_panel_layout.setSpacing(true);
        }
        else {
            Notification.show(user_name + ": " + access_denied, Notification.Type.ERROR_MESSAGE);
        }
    }

    private void reassemble_table() {
        BigInteger total_value_int = new BigInteger("0");
        raw_lte_main_table.removeAllItems();
        //Collections.reverse(current_balance_flow);
        System.out.printf("current "+current_data_converted.size()+"\n\r");
        for (Lte_with_RG_Converted entry:current_data_converted){
            Object newItemId = raw_lte_main_table.addItem();
            Item row = raw_lte_main_table.getItem(newItemId);
            row.getItemProperty("Дата").setValue(entry.getDate());
            row.getItemProperty("Категория").setValue(entry.getRg_type());
            row.getItemProperty("Квота").setValue(entry.getRg_usage());
            row.getItemProperty("Скорость").setValue(entry.getRg_speed());
            row.getItemProperty("Объем данных").setValue(entry.getTotal_value());
            row.getItemProperty("Время").setValue(entry.getDuration());
            row.getItemProperty("IP адрес").setValue(entry.getIp());
            row.getItemProperty("Точка доступа").setValue(entry.getApn());
            row.getItemProperty("Тип").setValue(entry.getRat_type());
            total_value_int = total_value_int.add(entry.getTotal_value());
            //row.getItemProperty("MK").setValue(entry.getMK());
            //row.getItemProperty("IMSI").setValue(entry.getImsi());
            //row.getItemProperty("IMEI").setValue(entry.getImei());
        }
        BigInteger divider = new BigInteger("1024");
        total_value_int = total_value_int.divide(divider);
        total_value_int = total_value_int.divide(divider);
        out_total_value_label.setValue(" Скачано всего: "+(total_value_int).toString()+" MB");
    }

    private void parse_RG_in_converted_data (){
        for (Lte_with_RG_Converted entry:current_data_converted){
            entry.setRg_type(spr_RAW_LTE_VIEW_RG_TYPE.get(entry.getRg().substring(0,2)));
            entry.setRg_usage(spr_RAW_LTE_VIEW_RG_USAGE.get(entry.getRg().substring(2,4)));
            entry.setRg_speed(spr_RAW_LTE_VIEW_RG_SPEED.get(entry.getRg().substring(4,6)));
        }
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
