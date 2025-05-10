package com.common;

import java.util.HashMap;
import java.util.Map;

public class LanguageClass {
    private static final Map<String, String[]> titlesByLanguage = new HashMap<>();

    static {
        titlesByLanguage.put("english",
                new String[]{
                        "Dashboard",
                        "Staff",
                        "Employee",
                        "Payroll",
                        "Timesheet",
                        "Performance",
                        "Finance",
                        "Training",
                        "HR Admin Setup",
                        "Recruitment",
                        "Contracts",
                        "Ticket",
                        "Event",
                        "Meeting",
                        "Zoom Meeting",
                        "Assets",
                        "Document",
                        "Company Policy",
                        "Messenger",
                        "Templates",
                        "HRM System Setup",
                        "Landing Page",
                        "System Setting"
                });
        titlesByLanguage.put("chinese",
                new String[]{
                        "仪表板",
                        "职员",
                        "员工",
                        "工资单",
                        "时间表",
                        "表现",
                        "金融",
                        "训练",
                        "人力资源管理设置",
                        "招聘",
                        "合同",
                        "票",
                        "事件",
                        "会议",
                        "缩放会议",
                        "资产",
                        "文档",
                        "公司政策",
                        "信使",
                        "模板",
                        "HRM系统设置",
                        "着陆页",
                        "系统设置"
                });
        titlesByLanguage.put("vietnam",
                new String[]{
                        "Tiêu đề 1",
                        "Tiêu đề 2",
                        "Tiêu đề 3"
                });
    }

    public static String[] getTitles(String language) {
        return titlesByLanguage.get(language.toLowerCase());
    }
}
