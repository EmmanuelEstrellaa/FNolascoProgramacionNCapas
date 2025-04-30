package com.digis01.FNolascoProgramacionNCapas.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demoapi")
public class DemoUser {

    @GetMapping("/suma/{uno}/{dos}")
    public ResponseEntity Suma(@PathVariable int uno, @PathVariable int dos) {
        int result = uno + dos;
        return ResponseEntity.ok().body("La suma es: " + result);
    }
    @GetMapping("/resta/{uno}/{dos}")
    public ResponseEntity Resta(@PathVariable int uno, @PathVariable int dos) {
        int result = uno - dos;
        return ResponseEntity.ok().body("La resta es: " + result);
    }
    @GetMapping("/multi/{uno}/{dos}")
    public ResponseEntity Multiplicacion(@PathVariable int uno, @PathVariable int dos) {
        int result = uno * dos;
        return ResponseEntity.ok().body("La multiplicacion es: " + result);
    }
    @GetMapping("/division/{uno}/{dos}")
    public ResponseEntity Division(@PathVariable int uno, @PathVariable int dos) {
        int result = uno / dos;
        return ResponseEntity.ok().body("La division es: " + result);
    }



}
