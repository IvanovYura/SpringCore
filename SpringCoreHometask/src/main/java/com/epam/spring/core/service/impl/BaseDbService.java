package com.epam.spring.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BaseDbService {

    private static final String CREATE_USERS_TABLE =
            "CREATE TABLE USERS " +
                    "(" +
                    "USER_ID INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    "FIRST_NAME VARCHAR(30) NOT NULL, " +
                    "LAST_NAME VARCHAR(30) NOT NULL, " +
                    "EMAIL VARCHAR(30) NOT NULL, " +
                    "BIRTHDAY VARCHAR(20) NOT NULL" +
                    ")";

    private static final String INSERT_USER =
            "INSERT INTO USERS (FIRST_NAME, LAST_NAME, EMAIL, BIRTHDAY) " +
                    "VALUES ('ALEX', 'SMITH', 'alex_smith@mail.com', '1990-11-26')";

    public static final String SELECT_USERS =
            "SELECT * FROM USERS";

    private static final String CREATE_AUDITORIUM_TABLE =
            "CREATE TABLE AUDITORIUMS " +
                    "(" +
                    "AUDITORIUM_ID INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    "NAME VARCHAR(30) NOT NULL, " +
                    "NUMBER_OF_SEATS INTEGER NOT NULL, " +
                    "VIP_SEAT VARCHAR(30) " +
                    ")";

    private static final String SELECT_AUDITORIUMS =
            "SELECT * FROM AUDITORIUMS";

    private static final String CREATE_EVENTS_TABLE =
            "CREATE TABLE EVENTS " +
                    "(" +
                    "EVENT_ID INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                    "NAME VARCHAR(30) NOT NULL, " +
                    "BASE_PRICE INTEGER NOT NULL, " +
                    "AIR_DATES VARCHAR(30) NOT NULL, " +
                    "RAITING VARCHAR(10) NOT NULL, " +
                    "AUDITORIUM_ID INTEGER NOT NULL" +
                    ")";

    private static final String SELECT_EVENTS =
            "SELECT * FROM EVENTS";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private JdbcTemplate simplaeJdbcTemplate;

    @PostConstruct
    private void initDB() {
        createUsersTable();
        createAuditoriumsTable();
    }

    private void createUsersTable() {
        try {
            simplaeJdbcTemplate.execute(SELECT_USERS);
        } catch (Exception ex) {
            simplaeJdbcTemplate.execute(CREATE_USERS_TABLE);
            simplaeJdbcTemplate.execute(INSERT_USER);
        }
    }

    private void createAuditoriumsTable() {
        try {
            simplaeJdbcTemplate.execute(SELECT_AUDITORIUMS);
        } catch (Exception ex) {
            simplaeJdbcTemplate.execute(CREATE_AUDITORIUM_TABLE);
        }
    }

    private void createEventsTable() {
        try {
            simplaeJdbcTemplate.execute(SELECT_EVENTS);
        } catch (Exception ex) {
            simplaeJdbcTemplate.execute(CREATE_EVENTS_TABLE);
        }
    }
}
