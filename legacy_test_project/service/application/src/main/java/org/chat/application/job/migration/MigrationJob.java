package org.chat.application.job.migration;

import java.util.concurrent.CompletableFuture;

import org.chat.infrastructure.migration.MigrationManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MigrationJob {

    @Autowired
    private MigrationManger migrationManger;

    @Async("asyncTask")
    public CompletableFuture<Void> run() throws Exception {
        migrationManger.migrateDatabase();
        return null;
    }
    
}
