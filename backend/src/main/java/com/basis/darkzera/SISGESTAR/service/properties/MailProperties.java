package com.basis.darkzera.SISGESTAR.service.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "application.mail")
public class MailProperties {

    private String enderecoRemetente;
    private String nomeRemetente;

}
