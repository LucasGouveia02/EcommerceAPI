package com.br.senac.EcommerceAPI.Services;

import com.br.senac.EcommerceAPI.ViaCep.ViaCepResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    @Autowired
    private RestTemplate restTemplate;

    public ViaCepResponse dadosCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json";
        return restTemplate.getForObject(url, ViaCepResponse.class);
    }
}
