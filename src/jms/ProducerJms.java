package jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;

public class ProducerJms {
	// Verificando a mensagem sem consumir
    public static void main(String[] args) throws Exception {
    	InitialContext context = new InitialContext();
    	ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

    	Connection connection = factory.createConnection();
    	connection.start();
    	Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    	
    	// Ex: Fila
    	// Destination fila = (Destination) context.lookup("financeiro");
    	Destination topico = (Destination) context.lookup("loja");
    	MessageProducer producer = session.createProducer(topico);
    
    	for (int i = 0; i < 100; i++) {
    		Message message = session.createTextMessage("<pedido><id>"+i+"</id></pedido>");
    		producer.send(message);
			
		}
    	
    	session.close();
    	connection.close();
    	context.close();
    }
}
