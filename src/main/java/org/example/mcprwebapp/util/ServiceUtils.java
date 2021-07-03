package org.example.mcprwebapp.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class ServiceUtils {
    public static final String PARAM_ID = "id";

    public static final String ADDRESS_PARAM_STREET = "street";
    public static final String ADDRESS_PARAM_CITY = "city";
    public static final String ADDRESS_PARAM_STATE = "state";
    public static final String ADDRESS_PARAM_POSTAL_CODE = "postalCode";
    public static final String ADDRESS_PARAM_COUNTRY = "country";

    public static final String PERSON_PARAM_NAME = "name";
    public static final String PERSON_PARAM_PHONE = "phone";
    public static final String PERSON_PARAM_EMAIL = "email";
    public static final String PERSON_PARAM_ADDRESS_ID = "addressId";

    public static final String ADDRESS_SERVICE_PATH = "rest/address";
    public static final String PERSON_SERVICE_PATH = "rest/person";

    public static final String GET_ALL_PATH = "/all";
    public static final String GET_BY_ID_PATH = "/find";
    public static final String DELETE_BY_ID_PATH = "/delete/{" + PARAM_ID + "}";
    public static final String UPDATE_BY_ID_PATH = "/update/{" + PARAM_ID + "}";
    public static final String ERROR_PATH = "/error";

    public static final String ANSWER_STATUS = "status";
    public static final String ANSWER_RESULT = "result";
    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_ERROR = "error";
    public static final String EMPTY_RESULT = "";

    public static final Map<String, Object> ERROR_EMPTY_ANSWER = new LinkedHashMap<String, Object>() {{
        put(ANSWER_STATUS, STATUS_ERROR);
        put(ANSWER_RESULT, EMPTY_RESULT);
    }};
}
