package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public ModelAndView getErrorPage(HttpServletRequest httpRequest) {
        String errorMsg = "An error occurred: please contact us.";
        int httpErrorCode = getErrorCode(httpRequest);


        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Http Error Code: 400. Bad Request";
                break;
            }
            case 401: {
                errorMsg = "OPS! You're not authorized to view this page: please login and try again.";
                break;
            }
            case 404: {
                errorMsg = "This resource doesn't exist.";
                break;
            }
            case 500: {
                errorMsg = "An error occurred: please contact us.";
                break;
            }
        }
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.getModel().put("errorMsg", errorMsg);
        return modelAndView;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
