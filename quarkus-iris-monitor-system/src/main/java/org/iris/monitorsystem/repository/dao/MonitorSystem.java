package org.iris.monitorsystem.repository.dao;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.intersystems.jdbc.IRIS;
import com.intersystems.jdbc.IRISConnection;

@ApplicationScoped
public class MonitorSystem {

    private IRIS iris;

    @ConfigProperty(name = "quarkus.datasource.jdbc.url")
    String jdbcUrl;

    @ConfigProperty(name = "quarkus.datasource.username")
    String username;

    @ConfigProperty(name = "quarkus.datasource.password")
    String password;

    @PostConstruct
    public void init() {
        try {
            IRISConnection conn = (IRISConnection) DriverManager.getConnection(jdbcUrl, username, password);
            this.iris = IRIS.createIRIS(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void startPerfmon() {
        String result = iris.classMethodString("iris.src.dc.AdapterPerfmonProc", "start");
    }

    public void generateReportPerfmon(String nameReport) {
        String result = iris.classMethodString("iris.src.dc.AdapterPerfmonProc", "generateReport", nameReport);
    }

    public void stopPerfmon() {
        String result = iris.classMethodString("iris.src.dc.AdapterPerfmonProc", "stop");
    }
}
