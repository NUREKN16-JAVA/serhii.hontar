package ua.nure.kn16.hontar.usermanagement.db;

import java.sql.Connection;

public interface ConnectionFactory {
  Connection createConnection() throws DatabaseException;
}