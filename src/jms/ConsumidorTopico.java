package jms;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.naming.InitialContext;

public class ConsumidorTopico {
	public static void main(String[] args) throws Exception {
		InitialContext context = new InitialContext();

		// importe do package javax.jms
		ConnectionFactory cf = (ConnectionFactory) context
				.lookup("ConnectionFactory");
		Connection conexao = cf.createConnection();

		// cria context, factory, connection
		conexao.start();
		Session session = conexao
				.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination fila = (Destination) context.lookup("financeiro");
		MessageConsumer consumer = session.createConsumer(fila);
		
		System.out.println("Recebendo msg:");
		Message message = consumer.receive();
		System.out.println("Recebendo msg: " + message);

		

		new Scanner(System.in).nextLine();

		conexao.close();
		context.close();
	}
}
