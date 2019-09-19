package com.kodilla.kodilla.diplomaBackend.GoogleCalendar.client;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.EventDateTime;
import com.kodilla.kodilla.diplomaBackend.domain.Rent;
import org.springframework.stereotype.Component;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;


@Component
public class CalendarClient {

    private static final String APPLICATION_NAME = "Car Rental Calendar API";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "resources/credentials.json";
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    private static final String CALENDAR_ID = "4ueb78lf25kffagn6h89q98e9g@group.calendar.google.com";

    private Calendar buildAuthorizedClientService() throws IOException, GeneralSecurityException{
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        return service;
    }


    public void createRentEvent(Rent rent) {

        Event event = new Event()
                .setSummary(rent.getCarRented().getCategory() + ": " + rent.getCarRented())
                .setDescription("Rent for: " + rent.getUser().getFirstname() + " " + rent.getUser().getLastname());

        DateTime startDate = new DateTime(rent.getStartDay().toString());
        EventDateTime start = new EventDateTime()
                .setDate(startDate)
                .setTimeZone("Europe/Warsaw");
        event.setStart(start);

        DateTime endDateTime = new DateTime(rent.getEndDay().toString());
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("Europe/Warsaw");
        event.setEnd(end);

        try {
            Calendar service = buildAuthorizedClientService();
            event = service.events().insert(CALENDAR_ID, event).execute();
        }
        catch(Exception e){
        }

        System.out.printf("Event created: %s\n", event.getHtmlLink());
    }

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = CalendarClient.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }




    /** Authorizes the installed application to access user's protected data. */
    /*
    private static Credential authorize() throws Exception {
        // load client secrets
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(CalendarSample.class.getResourceAsStream("/client_secrets.json")));
        // set up authorization code flow
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets,
                Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(dataStoreFactory)
                .build();
        // authorize
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }
    */

    /*
        public static void main(String... args) throws IOException, GeneralSecurityException {
            // Build a new authorized API client service.
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                    .setApplicationName(APPLICATION_NAME)
                    .build();
    */
        /*
        URI url = UriComponentsBuilder.fromHttpUrl(googleCalendarEndpoint + "/calendars/calendarId/events")
                .queryParam("fields", "start, end, summary")
                
                .build()
                .encode()
                .toUri();


       return EventDto createdEvent= restTemplate.postForObject(url, null, EventDto.class);


        */
}
