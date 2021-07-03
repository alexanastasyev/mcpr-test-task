package org.example.mcprwebapp.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @RequestMapping(ServiceUtils.ERROR_PATH)
    @ResponseBody
    public Map<String, Object> showErrorMessage() {
        return ServiceUtils.ERROR_EMPTY_ANSWER;
    }
}
