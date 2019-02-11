package za.co.fenya.demo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import sun.security.krb5.Credentials;
import za.co.fenya.demo.model.Accessories;
import za.co.fenya.demo.model.Customer;
import za.co.fenya.demo.model.CustomerContactDetails;
import za.co.fenya.demo.model.Device;
import za.co.fenya.demo.model.DeviceContactPerson;
import za.co.fenya.demo.model.Employee;
import za.co.fenya.demo.model.Leave;
import za.co.fenya.demo.model.LoginAttempt;
import za.co.fenya.demo.model.OrderHeader;
import za.co.fenya.demo.model.TicketHistory;
import za.co.fenya.demo.model.Tickets;
import za.co.fenya.demo.model.User;
import za.co.fenya.demo.model.UserDocument;
import za.co.fenya.demo.model.UserLogDetails;



@Configuration
@EnableTransactionManagement
@ComponentScan({ "za.co.fenya.demo.config" })
@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {
	
	@Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "za.co.fenya.demo.model" });
        sessionFactory.setAnnotatedClasses(User.class,UserDocument.class,Employee.class,Customer.class,Accessories.class,
        		Device.class,DeviceContactPerson.class,Leave.class,OrderHeader.class,Tickets.class,TicketHistory.class,UserLogDetails.class,
        		LoginAttempt.class, Credentials.class,CustomerContactDetails.class);
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
	
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }
    
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;        
    }
    
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }

}
