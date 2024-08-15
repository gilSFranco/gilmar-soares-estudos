import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        LocalDate dataAtual = LocalDate.now();
        LocalDateTime dataEHoraAtual = LocalDateTime.now();
        Instant dataEHoraAtualComFusoHorario = Instant.now();

        LocalDate textoParaData = LocalDate.parse("2024-08-14");
        LocalDateTime textoParaDataEHora = LocalDateTime.parse("2024-08-14T14:20:30");
        Instant textoParaDataEHoraComFuso = Instant.parse("2024-08-14T01:30:30Z");
        Instant textoParaDataEHoraComFusoSaoPaulo = Instant.parse("2024-08-14T14:20:30-03:00");

        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate d08 = LocalDate.parse("20/07/2024", fmt1);

        DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime d09 = LocalDateTime.parse("20/07/2024 01:30", fmt2);

        DateTimeFormatter fmt3 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault());

        DateTimeFormatter fmt4 = DateTimeFormatter.ISO_DATE_TIME;

        DateTimeFormatter fmt5 = DateTimeFormatter.ISO_INSTANT;

        LocalDate d10 = LocalDate.of(2022, 7, 20);
        LocalDateTime d11 = LocalDateTime.of(2022, 7, 20, 1, 30);

        //É a mesma coisa que fazer dataAtual.toString()
        System.out.println(fmt2.format(dataEHoraAtual));

        System.out.println(dataAtual);
        System.out.println(dataEHoraAtual);
        System.out.println(dataEHoraAtualComFusoHorario);
        System.out.println(textoParaData);
        System.out.println(textoParaDataEHora);
        System.out.println(textoParaDataEHoraComFuso);
        System.out.println(textoParaDataEHoraComFusoSaoPaulo);
        System.out.println(d08);
        System.out.println(d09 + "a");
        System.out.println(d10);
        System.out.println(d11);

        System.out.println(d08.format(fmt1));
        System.out.println(fmt1.format(d08));

        System.out.println(d08.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        System.out.println(d09.format(fmt1));
        System.out.println(d09.format(fmt2));

        System.out.println(fmt3.format(textoParaDataEHoraComFuso));

        System.out.println(textoParaDataEHora.format(fmt4));

        System.out.println(fmt5.format(textoParaDataEHoraComFuso));
    }
}