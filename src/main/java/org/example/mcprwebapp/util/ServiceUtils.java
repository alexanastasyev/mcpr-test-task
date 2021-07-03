package org.example.mcprwebapp.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class ServiceUtils {
    public static final String ADDRESS_SERVICE_PATH = "rest/address";
    public static final String PERSON_SERVICE_PATH = "rest/person";

    public static final String GET_ALL_PATH = "/all";
    public static final String GET_BY_ID_PATH = "/find";
    public static final String DELETE_BY_ID_PATH = "/delete";
    public static final String UPDATE_BY_ID_PATH = "/update";
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
