package com.sannova.util;

public class URLDetails {

    public static final String URL_BASE ="/sannova";
    public static final String URL_LOGIN = URL_BASE +"/login";
    public static final String URL_STUDY_TYPES=URL_BASE+"/study_types";

    public static final String URL_TEMPLATE_DETAILS=URL_BASE+"/template_details";
    public static final String URL_TEMPLATE_DETAILS_BY_ID=URL_TEMPLATE_DETAILS+"/{study_id}";
    public static final String URL_STUDY_NUMBER=URL_BASE+"/study_number/{study_id}";
    public static final String URL_UPLOAD_TEMPLATE=URL_BASE+"/upload_template";
    public static final String URL_DELETE_TEMPLATE_BY_TEMPLATE_ID=URL_BASE+"/delete_template";
    public static final String URL_RECONSILIATION = URL_BASE +"/reconsiliation";
    public static final String URL_RECONSILIATION_PRINT = URL_BASE +"/reconsiliation/print";
    public static final String URL_PRINT_FORM = URL_BASE +"/print_form";
    public static final String ID_GENERATOR_GENERATE=URL_BASE+"/id_generator/generate";



}
