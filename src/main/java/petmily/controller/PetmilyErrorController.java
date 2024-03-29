package petmily.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PetmilyErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null){
            int statusCode = (int) status;

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/error_404";
            } else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
                return "error/error_400";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/error_500";
            }
        }

        return "/error/error_default";
    }
}