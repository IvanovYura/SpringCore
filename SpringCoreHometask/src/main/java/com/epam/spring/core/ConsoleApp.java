package com.epam.spring.core;

import com.epam.spring.core.domain.Auditorium;
import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.Ticket;
import com.epam.spring.core.domain.User;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class ConsoleApp {

    private App app;

    private Scanner scanner;

    ConsoleApp(Object app) {
        this.app = (App) app;
        scanner = new Scanner(System.in);
    }

    void start() {
        while (true) {
            String line = scanner.nextLine();
            switch (line) {
                case "quit":
                    System.exit(0);
                    break;
                case "help":
                    usage();
                    break;
                case "get users":
                    getUsers();
                    break;
                case "get events":
                    getEvents();
                    break;
                case "set auditorium":
                    setAuditorium();
                    break;
                case "get auditoriums":
                    getAuditoriums();
                    break;
                case "book ticket":
                    bookTicket();
                    break;
                case "get tickets":
                    getTickets();
                    break;
            }
        }
    }

    private void usage() {

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
