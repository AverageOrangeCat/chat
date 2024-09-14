package org.chat.program;

import org.chat.application.job.migration.MigrationJob;
import org.chat.shared.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@EnableJpaRepositories(basePackages = "org.chat.infrastructure.*") 
@EntityScan(basePackages = "org.chat.infrastructure.*")
@SpringBootApplication(scanBasePackages = {
	"org.chat.api.*",
	"org.chat.application.*",
	"org.chat.infrastructure.*",
	"org.chat.shared.*"
})
public class Program implements CommandLineRunner {

	@Autowired
	MigrationJob migrationJob;

	public static void main(String[] args) {
		SpringApplication.run(Program.class, args);
	}

	@Bean("asyncTask")
	public TaskExecutor getTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

		executor.setCorePoolSize(Constant.Program.CORE_POOL_SIZE);
		executor.setMaxPoolSize(Constant.Program.MAX_POOL_SIZE);

		return executor;
	}

	@Override
	public void run(String... args) throws Exception {
		// Wait for the migrations to be finished before starting new jobs
		migrationJob.run().join();
	}

}
