package fr.exemple.driver.configuration;

import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@PropertySource(value = { "classpath:/fr/exemple/driver/configuration/app.config" })
public class ApplicationConfiguration {
	
	private Log log = LogFactory.getLog(ApplicationConfiguration.class);

	@Value("${app.db.name}")
	private String appDbName;

	@Value("${db.host}")
	private String dbHost;

	@Value("${db.port}")
	private int dbPort;

	@Bean
	public DB db() throws UnknownHostException {
		log.info("Récupération de la base de données.");
		return mongo().getDB(appDbName);
	}

	@Bean
	public Mongo mongo() throws UnknownHostException {
		log.info("Création de l'objet Mongo.");
		return new MongoClient(dbHost, dbPort);
	}
}