package com.stackspot.cucumber.integration.integration.steps;

import io.cucumber.java.pt.*;
import org.apache.logging.log4j.LogManager;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrincipalStep {

    @LocalServerPort
    private int port = 8080;

    private String baseURL = "http://localhost";
    private String basePath = "v1";
    private String payloadsRequest = "payload/request/";
    private String payloadResponse = "payload/response/";
    private String verbo;
    private String rota;
    private ResponseEntity<String> response;
    private final TestRestTemplate restTemplate = new TestRestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();
    private final HttpHeaders headersHttp = new HttpHeaders();

    @Dado("o endpoint {string} {string} {string} {string}")
    public void o_endpoint(String verbo, String rota, String id, String descricao) {
        this.verbo = verbo;
        this.rota = rota;
    }
    @Quando("envia a requisicao {string}")
    public void envia_a_requisicao(String request) throws Exception {
        if(verbo.equals("GET")){
            this.response = this.getResource();

        }else if(verbo.equals("POST")){
            this.response = this.saveResource(this.verbo, request);

        }else if(verbo.equals("PUT")){

        }else if(verbo.equals("PATCH")){

        }else if(verbo.equals("DELETE")){

        }else{
            throw new Exception("Verbo inválido, verifique o verbo http passado!  "+ verbo.toString());
        }
    }
    @Entao("devera retornar statusCode {string}")
    public void devera_retornar_status_code(String statusCode) {
        assertEquals(statusCode, String.valueOf(this.response.getStatusCodeValue()));
    }
    @Entao("o payload contendo a resposta da requisicao {string}")
    public void o_payload_contendo_a_resposta_da_requisicao(String response) throws IOException, JSONException {
        if(response.isEmpty()){
            Assertions.assertNull(this.response.getBody());
        }else{
            JSONAssert.assertEquals(this.mapper.readTree(this.readJsonFile(payloadResponse, response)).toString(),
                    this.mapper.readTree(this.response.getBody()).toString(),
                    new CustomComparator(JSONCompareMode.LENIENT));
        }
    }

    private ResponseEntity<String> getResource(){
        return this.restTemplate.getForEntity(this.getURL(this.rota), String.class);
    }

    private ResponseEntity<String> saveResource(String verbo, String request) throws Exception {
        if(request.isEmpty()){
            throw new Exception("Por favor, informe o payload de entrada!!!");
        }

        final HttpMethod httpMethod = HttpMethod.valueOf(verbo);
        final String body = this.mapper.readTree(this.readJsonFile(payloadsRequest, request)).toString();
        this.headersHttp.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<String> httpEntity = new HttpEntity<>(body,headersHttp);
        return  this.restTemplate.exchange(this.getURL(rota), httpMethod, httpEntity, String.class);
    }

    private ResponseEntity<String> deleteResource(){
        return this.restTemplate.exchange(this.getURL(rota), HttpMethod.DELETE, null, String.class);
    }

    private String getURL(String rota){
        String endpoint = this.baseURL + ":" + this.port + "/" + this.basePath + rota;
        return endpoint;
    }

    private File readJsonFile(String filePath, String fileName) throws IOException {
        return new ClassPathResource(filePath + fileName + ".json").getFile();
    }

}
