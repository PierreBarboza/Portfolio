package salus.well.projetosaluswell.domain.email;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
    @Scheduled(cron = "0 15 16 * * ?", zone = "America/Sao_Paulo")
    public void observer() throws EmailException {
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath("Alimentos.csv");
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Planilha de alimentos cadastrados.csv");
        attachment.setName("Alimentos");

        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp-mail.outlook.com");
        email.setSmtpPort(587);
        email.setAuthentication("salus-well@outlook.com", "#Gfgrupo3");
        email.setSSLOnConnect(false);
        email.setStartTLSRequired(true);
        email.setFrom("salus-well@outlook.com");
        email.addTo("SalusWell@outlook.com");
        email.setSubject("A planilha");
        email.setMsg("Um novo Alimento foi cadastrado, confira no anexo encaminhado!");

        email.attach(attachment);

        email.send();

    }
}
