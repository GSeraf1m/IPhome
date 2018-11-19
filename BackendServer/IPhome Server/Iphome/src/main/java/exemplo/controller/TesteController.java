package exemplo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;





@Controller    
@RequestMapping(path="") 

public class TesteController {
    public static final Logger logger = LoggerFactory.getLogger(TesteController.class);
    
    
    // -------------------Retorna todos os produtos---------------------------------------------
 
    @RequestMapping(value = "/teste/", method = RequestMethod.GET)
    public ResponseEntity<String> teste() {
        logger.info("Teste {}");
    	     return new ResponseEntity<String>("testando", HttpStatus.OK);
    }
 
}
