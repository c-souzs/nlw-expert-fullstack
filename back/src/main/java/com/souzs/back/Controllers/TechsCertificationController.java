package com.souzs.back.Controllers;

import com.souzs.back.Service.TechsCertification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/techs")
public class TechsCertificationController {

    @Autowired
    private TechsCertification techsCertificationService;

    @GetMapping("/")
    public ResponseEntity<Object> techsCertification() {
        var result = this.techsCertificationService.execute();
        return ResponseEntity.ok().body(result);
    }
}
