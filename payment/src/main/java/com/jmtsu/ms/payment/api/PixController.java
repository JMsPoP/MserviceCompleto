package com.jmtsu.ms.payment.api;


import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE)
public record PixController(PixService pixService) {


    @GetMapping("/listar")
    public ResponseEntity<String> listarChavesPix(){
        var response = this.pixService.listarChavesPix();
        return ResponseEntity.ok().body(response.toString());
    }

    @GetMapping
    public ResponseEntity<String> criarChavePix(){
        var response = this.pixService.criarChavePix();
        return ResponseEntity.ok().body(response.toString());
    }

    @PostMapping
    public ResponseEntity pixCreateCharge(@RequestBody PixRequestPayload pixChargeRequest) {
        JSONObject response = this.pixService.pixCreateCharge(pixChargeRequest);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response.toString());
    }


    /*@DeleteMapping
    public ResponseEntity<String> deletarChavePix(@RequestParam("chavePix") String chavePix) {
        var response = this.pixService.deletarChavePix(chavePix);
        return ResponseEntity.ok()
                .body(response.toString());
    }*/


}