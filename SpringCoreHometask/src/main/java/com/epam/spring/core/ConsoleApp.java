package com.epam.spring.core;

import com.epam.spring.core.domain.Auditorium;
import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.Ticket;
import com.epam.spring.core.domain.User;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ConsoleApp {

    enum Commands {
        QUIT("quit"),
        HELP("help"),
        BOOK_TICKET("book_ticket"),
        GET_USERS("get users"),
        GET_EVENTS("get events"),
        GET_AUDITORIUMS("get auditoriums"),
        SET_AUDITORIUM("set auditorium"),
        GET_TICKETS("get tickets");

        private static final Map<String, Commands> map = new HashMap<>();

        static {
            Arrays.stream(values()).forEach(value -> map.putIfAbsent(value.command, value));
        }

        private String command;

        Commands(String command) {
            this.command = command;
        }

        static Commands getByCommandCommand(String command) {
            return map.get(command);
        }
    }

    private App app;

    private Scanner scanner;

    ConsoleApp(Object app) {
        this.app = (App) app;
        scanner = new Scanner(System.in);
    }

    void start() {
        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty() || Commands.getByCommandCommand(line) == null) continue;
            switch (Commands.getByCommandCommand(line)) {
                case QUIT:
                    System.exit(0);
                    break;
                case HELP:
                    usage();
                    break;
                case GET_USERS:
                    getUsers();
                    break;
                case GET_EVENTS:
                    getEvents();
                    break;
                case SET_AUDITORIUM:
                    setAuditorium();
                    break;
                case GET_AUDITORIUMS:
                    getAuditoriums();
                    break;
                case BOOK_TICKET:
                    bookTicket();
                    break;
                case GET_TICKETS:
                    getTickets();
                    break;
            }
        }
    }

    private void usage() {
        System.out.println("Available commands:");
        Commands.map.forEach((key, value) -> System.out.println(key));
    }

    private void getUsers() {
        app.userService.getAll().forEach(user -> System.out.println(user.toString()));
    }

    private void getEvents() {
        app.eventService.getAll().forEach(event -> System.out.println(event.toString()));
    }

    private void getAuditoriums() {
        app.auditoriumService.getAll().forEach(auditorium -> System.out.println(auditorium.toString()));
    }

    private void getTickets() {
        System.out.println("Specify event name, date time");
        String line = scanner.nextLine();
        String[] args = line.split(" ");
        Event event = app.eventService.getByName(args[0]);
        app.bookingService.getPurchasedTicketsForEvent(event, LocalDateTime.parse(args[1]))
                .forEach(ticket -> System.out.println(ticket.toString()));
    }

    private void bookTicket() {
        while (true) {
            System.out.println("Specify user email, event name, date time and seat number");
            String line = scanner.nextLine();
            String[] args = line.split(" ");
            if (args.length < 4) {
                System.out.println("Wrong amount of arguments");
                continue;
            }
            User user = app.userService.getUserByEmail(args[0]);
            Event event = app.eventService.getByName(args[1]);
            Set<Ticket> tickets = Stream.of(
                    new Ticket(user, event, LocalDateTime.parse(args[2]), Long.parseLong(args[3])))
                    .collect(Collectors.toSet());
            user.setTickets(tickets);
            app.bookingService.bookTickets(tickets);
            break;
        }
    }

    private void setAuditorium() {
        while (true) {
            System.out.println("Specify room name, number of seats and ids [x, y, z] for vip seats");
            String line = scanner.nextLine();
            String[] args = line.split(" ");
            if (args.length < 3) {
                System.out.println("Wrong amount of arguments");
                continue;
            }
            Auditorium auditorium = new Auditorium();
            auditorium.setName(args[0]);
            auditorium.setNumberOfSeats(Long.parseLong(args[1]));
            Set<Long> vipSeats = Stream.of(args[2].split(" "))
                    .map(Long::parseLong)
                    .collect(Collectors.toSet());
            auditorium.setVipSeats(vipSeats);
            if (app.auditoriumService.save(auditorium) != null) {
                System.out.println(auditorium.toString());
                System.out.println("You are returned to the main menu");
                break;
            }
        }
    }
}
