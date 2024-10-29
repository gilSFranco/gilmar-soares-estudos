package com.compass.aws_springboot.infra.clients;

import com.compass.aws_springboot.web.dto.ResponseViaCepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepResourceClient {

    @GetMapping("/{cep}/json")
    ResponseViaCepDTO getZipCodeInformation(@PathVariable("cep") String cep);
}
