package com.jmtsu.ms.payment.api;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;

@Service
@Slf4j
public class PixService {

    private final Logger logger = LoggerFactory.getLogger(PixService.class);

    private final JSONObject configuracoes;

    public PixService(final PixConfig pixConfig){
        this.configuracoes = new JSONObject();
        this.configuracoes.put("client_id", pixConfig.clientId());
        this.configuracoes.put("client_secret", pixConfig.clientSecret());
        this.configuracoes.put("certificate", pixConfig.certificatePath());
        this.configuracoes.put("sandbox", pixConfig.sandbox());
        this.configuracoes.put("debug", pixConfig.debug());
    }

    public JSONObject listarChavesPix(){
        return executarOperacao("pixListEvp", new HashMap<>());
    }

    public JSONObject criarChavePix(){
        return executarOperacao("pixCreateEvp", new HashMap<>());
    }

    private JSONObject executarOperacao(String operacao, Map<String, String> params) {
        final var retorno = new JSONObject();
        try {
            EfiPay efi = new EfiPay(configuracoes);
            JSONObject response = efi.call(operacao, params, new JSONObject());
            logger.info("Resultado: {}", response);
            return response;
        } catch (EfiPayException e) {
            logger.error(e.getError());
            retorno.put("erro", e.getErrorDescription());
        } catch (UnsupportedOperationException | JSONException operationException) {
            logger.warn("Invalid JSON format {}", operationException.getMessage());
        } catch (Exception e) {
            retorno.put("erro", "Não foi possível completar a operação!");
        }
        return retorno;
    }
    public JSONObject pixCreateCharge(PixRequestPayload pixChargeRequest){
        JSONObject options = configuringJsonObject();

        JSONObject body = new JSONObject();
        body.put("calendario", new JSONObject().put("expiracao", 3600));
        body.put("devedor", new JSONObject().put("cpf", "12345678909").put("nome", "Francisco da Silva"));
        body.put("valor", new JSONObject().put("original", pixChargeRequest.valor()));
        body.put("chave", pixChargeRequest.chave());

        JSONArray infoAdicionais = new JSONArray();
        infoAdicionais.put(new JSONObject().put("nome", "Campo 1").put("valor", "Informação Adicional1 do PSP-Recebedor"));
        infoAdicionais.put(new JSONObject().put("nome", "Campo 2").put("valor", "Informação Adicional2 do PSP-Recebedor"));
        body.put("infoAdicionais", infoAdicionais);

        try {
            EfiPay efi = new EfiPay(options);
            JSONObject response = efi.call("pixCreateImmediateCharge", new HashMap<String,String>(), body);

            int idFromJson = response.getJSONObject("loc").getInt("id");
            pixGenerateQRCode(String.valueOf(idFromJson));

            return response;
        } catch (EfiPayException e){
            logger.error(e.getError(), e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private void pixGenerateQRCode(String id){
        JSONObject options = configuringJsonObject();

        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);

        try {
            EfiPay efi = new EfiPay(options);
            Map<String, Object> response = efi.call("pixGenerateQRCode", params, new HashMap<String, Object>());

            String qrCodeBase64 = (String) response.get("imagemQrcode");
            File outputfile = new File("qrCodeImage.png");
            ImageIO.write(ImageIO.read(new ByteArrayInputStream(javax.xml.bind.DatatypeConverter.parseBase64Binary(qrCodeBase64.split(",")[1]))), "png", outputfile);

            Desktop desktop = Desktop.getDesktop();
            desktop.open(outputfile);

        } catch (EfiPayException e){
            logger.error(e.getError(), e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private JSONObject configuringJsonObject() {
        return this.configuracoes;
    }
}
