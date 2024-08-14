import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        LocalDate textoParaData = LocalDate.parse("2024-08-14");
        LocalDateTime textoParaDataEHora = LocalDateTime.parse("2024-08-14T14:20:30");
        Instant textoParaDataEHoraComFuso = Instant.parse("2024-08-14T01:30:30Z");

        LocalDate pastWeekLocalDate = textoParaData.minusWeeks(1);
        LocalDate nextWeekLocalDate = textoParaData.plusDays(7);

        System.out.println("pastWeekLocalDate: " + pastWeekLocalDate);
        System.out.println("original: " + textoParaData);
        System.out.println("nextWeekLocalDate: " + nextWeekLocalDate);

        LocalDateTime pastWeekLocalDateTime = textoParaDataEHora.minusDays(7);
        LocalDateTime nextWeekLocalDateTime = textoParaDataEHora.plusDays(7);

        System.out.println();

        System.out.println("pastWeekLocalDate: " + pastWeekLocalDateTime);
        System.out.println("original: " + textoParaDataEHora);
        System.out.println("nextWeekLocalDate: " + nextWeekLocalDateTime);

        Instant pastWeekInstant = textoParaDataEHoraComFuso.minus(7, ChronoUnit.DAYS);
        Instant nextWeekInstant = textoParaDataEHoraComFuso.plus(7, ChronoUnit.DAYS);

        System.out.println();

        System.out.println("pastWeekLocalDate: " + pastWeekInstant);
        System.out.println("original: " + textoParaDataEHoraComFuso);
        System.out.println("nextWeekLocalDate: " + nextWeekInstant);

        System.out.println();

        Duration t1 = Duration.between(pastWeekLocalDate.atStartOfDay(), textoParaData.atStartOfDay());
        Duration t2 = Duration.between(pastWeekLocalDateTime, textoParaDataEHora);
        Duration t3 = Duration.between(pastWeekInstant, textoParaDataEHoraComFuso);
        Duration t4 = Duration.between(textoParaDataEHoraComFuso, pastWeekInstant);

        System.out.println("Intervalo de dias: " + t1.toDays());
        System.out.println("Intervalo de dias: " + t2.toDays());
        System.out.println("Intervalo de dias: " + t3.toDays());
        System.out.println("Intervalo de dias: " + t4.toDays());
    }
}