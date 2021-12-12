package icu.junyao.back.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author johnson
 * @since 2021-10-23
 */
@RestController
public class ErrController {

    @RequestMapping("/error/expire")
    public void handleError(HttpServletRequest request) throws Throwable {
        if (request.getAttribute("error") != null) {
            throw (Throwable) request.getAttribute("error");
        }
    }
}
