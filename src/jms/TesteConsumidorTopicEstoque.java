package jms;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.InitialContext;

public class TesteConsumidorTopicEstoque {
	public static void main(String[] args) throws Exception {
		InitialContext context = new InitialContext();

		// importe do package javax.jms
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");
		
		Connection conexao = cf.createConnection();
		//criação da conexão
		conexao.setClientID("estoque");

		
		conexao.start();
		
		// cria context, factory, connection

		Session session = conexao.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination topico = (Destination) context.lookup("loja");
		
		// Assinatura duravel
		MessageConsumer consumer = session.createDurableSubscriber((Topic) topico, "assinatura","ebook is null OR ebook=false ", false);
		

		consumer.setMessageListener(new MessageListener(){

		    @Override
		    public void onMessage(Message message){
		    	   TextMessage textMessage  = (TextMessage)message;
		           try{
		               System.out.println(textMessage.getText());
		           } catch(JMSException e){
		               e.printStackTrace();
		           }    
		    }		    

		});


		new Scanner(System.in).nextLine();

		conexao.close();
		context.close();
	}
}
