import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) {
        LocalDate textoParaData = LocalDate.parse("2024-08-14");
        LocalDateTime textoParaDataEHora = LocalDateTime.parse("2024-08-14T14:20:30");
        Instant textoParaDataEHoraComFuso = Instant.parse("2024-08-14T01:30:30Z");

        LocalDate r1 = LocalDate.ofInstant(textoParaDataEHoraComFuso, ZoneId.systemDefault());
        LocalDate r2 = LocalDate.ofInstant(textoParaDataEHoraComFuso, ZoneId.of("Portugal"));

        LocalDateTime r3 = LocalDateTime.ofInstant(textoParaDataEHoraComFuso, ZoneId.systemDefault());
        LocalDateTime r4 = LocalDateTime.ofInstant(textoParaDataEHoraComFuso, ZoneId.of("Portugal"));

        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
        System.out.println(r4);

        System.out.println(textoParaData.getDayOfMonth());
        System.out.println(textoParaData.getMonthValue());
        System.out.println(textoParaData.getYear());

        System.out.println(textoParaDataEHora.getHour());
        System.out.println(textoParaDataEHora.getMinute());

        //for(String s : ZoneId.getAvailableZoneIds()) {
        //    System.out.println(s);
        //}
    }
}