package service_rdv.demo.services;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class GoogleCalendarService {

    @CircuitBreaker(name = "googleCalendarBreaker", fallbackMethod = "generateGoogleCalendarLinkFallback")
    @Retry(name = "googleCalendarRetry", fallbackMethod = "generateGoogleCalendarLinkFallback")
    public String generateGoogleCalendarLink(String eventTitle, LocalDateTime startDateTime, LocalDateTime endDateTime,
            String description, String location) {
        String startUtc = convertToUTC(startDateTime);
        String endUtc = convertToUTC(endDateTime);

        return "https://www.google.com/calendar/render?action=TEMPLATE" +
                "&text=" + URLEncoder.encode(eventTitle, StandardCharsets.UTF_8) +
                "&dates=" + startUtc + "/" + endUtc +
                "&details=" + URLEncoder.encode(description, StandardCharsets.UTF_8) +
                "&location=" + URLEncoder.encode(location, StandardCharsets.UTF_8) +
                "&sf=true";
    }

    public String generateGoogleCalendarLinkFallback(String eventTitle, LocalDateTime startDateTime,
            LocalDateTime endDateTime, String description,
            String location, Throwable e) {
        return "Erreur lors de la création de l'événement Google Calendar. Veuillez réessayer plus tard.";
    }

    private String convertToUTC(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'"));
    }
}