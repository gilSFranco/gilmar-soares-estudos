package com.compasso.demo_park_api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class JasperService {
    private final ResourceLoader resourceLoader;
    private final DataSource dataSource;

    private static Map<String, Object> params = new HashMap<>();
    private static final String JASPER_DIRETORIO = "classpath:reports/";

    public static void addParams(String key, Object value) {
        params.put("IMAGEM_DIRETORIO", JASPER_DIRETORIO);
        params.put("REPORT_LOCALE", new Locale("pt", "BR"));
        params.put(key, value);
    }

    public byte[] gerarPdf() {
        byte[] bytes = null;

        try{
            Resource resource = resourceLoader.getResource(JASPER_DIRETORIO.concat("estacionamentos.jasper"));
            InputStream inputStream = resource.getInputStream();

//            JasperPrint print = JasperFillManager.fillReport(inputStream, params, dataSource.getConnection());
//
//            bytes = JasperExportManager.exportReportToPdf(print);
        } catch (IOException e) {
            log.error("Jasper Reports ::: ", e.getCause());
            throw new RuntimeException(e);
        }

        return bytes;
    }
}
