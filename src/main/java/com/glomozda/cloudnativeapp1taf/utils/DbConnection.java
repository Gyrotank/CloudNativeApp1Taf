package com.glomozda.cloudnativeapp1taf.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

@Component
@Getter
@Setter
@Log4j2
public class DbConnection {
    private Connection connection;

    public void initDbConnection(final String dbDriverName,
                                         final String dbUrl,
                                         final String dbUser,
                                         final String dbPassword) {
        try {
            Class.forName(dbDriverName);
        } catch (ClassNotFoundException e) {
            log.error("!!! Failed to instantiate database driver " + dbDriverName + " !!!");
        }

        try {
            if (Objects.isNull(connection) || connection.isClosed()) {
                connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            }
        } catch (SQLException e) {
            log.error("!!! Failed to establish connection to " + dbUrl +
                    " with credentials " + dbUser + " / " + dbPassword + " !!!");
        }

        if (Objects.nonNull(connection)) {
            log.info("DB connection to " + dbUrl + " established successfully");
        }
    }

    public void closeDbConnection() {
         try {
             if (Objects.nonNull(connection) && !connection.isClosed()) {
                 connection.close();
                 if (connection.isClosed()) {
                     log.info("DB connection was successfully closed");
                 }
             }
         }
         catch (SQLException e) {
             log.error("!!! Failed to close database connection !!!");
        }
    }
}
