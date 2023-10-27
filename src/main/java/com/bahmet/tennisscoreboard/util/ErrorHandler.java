package com.bahmet.tennisscoreboard.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorHandler {
    public static void sendErrorResponse(int code, String message, HttpServletRequest req) {
        String statusTitle = null;
        switch (code) {
            case HttpServletResponse.SC_INTERNAL_SERVER_ERROR:
                statusTitle = "Internal server error";
                break;
            case HttpServletResponse.SC_BAD_REQUEST:
                statusTitle = "Bad request";
                break;
            case HttpServletResponse.SC_NOT_FOUND:
                statusTitle = "Not found";
                break;
        }
        req.setAttribute("status", code);
        req.setAttribute("statusTitle", statusTitle);
        req.setAttribute("message", message);
    }
}
