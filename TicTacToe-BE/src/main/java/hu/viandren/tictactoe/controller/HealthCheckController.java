package hu.viandren.tictactoe.controller;

import hu.viandren.tictactoe.repository.ParamRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @Autowired
    private ParamRepository paramRepository;


    private static final Logger LOGGER = LogManager.getLogger(HealthCheckController.class);

    @RequestMapping(path = "/healthcheck", method = RequestMethod.GET)
    public String healthcheck(){
        return "OK";
    }

    @RequestMapping(path = "/healthcheckWithDB", method = RequestMethod.GET)
    public String healthcheckWithDB(){
        return paramRepository.findByCode();
    }
}
