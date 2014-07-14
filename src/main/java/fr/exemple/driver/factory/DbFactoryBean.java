package fr.exemple.driver.factory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.mongodb.DB;
import com.mongodb.Mongo;

public class DbFactoryBean implements FactoryBean<DB> {
	
	private Log log = LogFactory.getLog(DbFactoryBean.class);
	   
    private Mongo mongo;
    private String name;

    @Override
    public DB getObject() throws Exception {
        Assert.notNull(mongo);
        Assert.notNull(name);
        log.info("Récupération de l'objet dans la Factory Bean");
        return mongo.getDB(name);
    }

   @Override
   public Class<?> getObjectType() {
	   log.info("Récupération de la classe (Class<?>) de l'objet de la Factory Bean");
       return DB.class;
   }

   @Override
   public boolean isSingleton() {
       return true;
   }

   @Required
   public void setMongo(Mongo mongo) {
       this.mongo = mongo;
   }

   @Required   
   public void setName(String name) {
       this.name = name;
   }
}
