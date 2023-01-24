package com.goit.homwork6.feature.database;

import com.goit.homwork6.feature.pref.Preferences;
import org.flywaydb.core.Flyway;

public class MigrationService {
    public void initUpdate(String connectionUrl) {


        Flyway flyway = Flyway
                .configure()
                .dataSource(connectionUrl, null, null)
                .load();
        flyway.migrate();
    }
}
