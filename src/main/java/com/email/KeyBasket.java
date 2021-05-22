package com.email;

public class KeyBasket {
    
    public static final String MAIL_FROM = ResourceConfig.getString("mailFrom");
    public static final String MAIL_TO = ResourceConfig.getString("mailTo");
    public static final String MAIL_FROM_PASSWORD = ResourceConfig.getString("mailToPassword");
    public static final int MAIL_PORT  = Integer.parseInt(ResourceConfig.getString("mailPort"));
    public static final String MAIL_HOST = ResourceConfig.getString("mailHost");
    public static final String MAIL_AUTH = ResourceConfig.getString("mailAuth");
    public static final String MAIL_SSL = ResourceConfig.getString("mailSSL");
    public static final String MAIL_MESSAGE = ResourceConfig.getString("mailMessage");
    public static final String MAIL_SUBJECT = ResourceConfig.getString("mailSubject");
    public static final String MAIL_TEXT = ResourceConfig.getString("mailText");
    public static final String MAIL_HTML= ResourceConfig.getString("mailHTML");



}
