package com.example.insuranceservice.global.replica;

import com.example.insuranceservice.global.logManager.LogManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class RoutingDataSource extends AbstractRoutingDataSource {

    private DataSource writeDataSource;
    private DataSource readDataSource;
    private String writeDatabaseUrl;
    private String readDatabaseUrl;
    private final LogManager logManager;
    private boolean isInitialized = false;

    public RoutingDataSource(LogManager logManager) {
        this.logManager = logManager;
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        this.writeDataSource = (DataSource) targetDataSources.get("write");
        this.readDataSource = (DataSource) targetDataSources.get("read");
    }

    @Override
    protected Object determineCurrentLookupKey() {
        if (!isInitialized) {
            cacheDatabaseUrls();
            isInitialized = true;
        }

        String currentKey = DataSourceRoutingContext.getDataSourceType();

        if (currentKey == null || currentKey.equals("write")) {
            System.out.println("### Using WRITE DataSource (Main DB)");
            System.out.println("### Current Database URL: " + writeDatabaseUrl);
            logManager.logSend("[INFO]", "VVV 아래 요청은 ### Current Database URL: " + writeDatabaseUrl + " 해당 database 소스에 요청되어 처리되었습니다.");
        } else if (currentKey.equals("read")) {
            System.out.println("### Using READ DataSource (Replica DB)");
            System.out.println("### Current Database URL: " + readDatabaseUrl);
            logManager.logSend("[INFO]", "VVV 아래 요청은 ### Current Database URL: " + readDatabaseUrl + " 해당 database 소스에 요청되어 처리되었습니다.");
        }

        return currentKey != null ? currentKey : "write";
    }

    private void cacheDatabaseUrls() {
        writeDatabaseUrl = getDatabaseUrl(writeDataSource, "write");
        readDatabaseUrl = getDatabaseUrl(readDataSource, "read");
    }

    private String getDatabaseUrl(DataSource dataSource, String type) {
        if (dataSource != null) {
            try (Connection connection = dataSource.getConnection()) {
                return connection.getMetaData().getURL();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "### " + type + " DataSource is not initialized!";
    }
}
