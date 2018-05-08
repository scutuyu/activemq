package com.tuyu.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * Pub/Sub 发布订阅模式
 * <p>发布者</p>
 * @author tuyu
 * @date 5/2/18
 * Stay Hungry, Stay Foolish.
 */
public class TopicProvider {

    private static final String USERNAME= ActiveMQConnection.DEFAULT_USER; // 默认的连接用户名
    private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD; // 默认的连接密码
    private static final String BROKEURL=ActiveMQConnection.DEFAULT_BROKER_URL; // 默认的连接地址

    private static final Logger logger = LoggerFactory.getLogger(TopicProvider.class);

    /**
     * 使用JMS通用规范创建点对点模式消费者
     * <p>1. 创建ActiveMQ的连接工厂 factory</p>
     * <p>2. 用连接工厂创建链接 connection</p>
     * <p>3. 启动连接 connection.start()</p>
     * <p>4. 用连接创建会话 session</p>
     * <p>5. 用会话创建目的地 destination</p>
     * <p>6. 用会话创建生产者 producer</p>
     * <p>7. 构造消息 textMessage</p>
     * <p>8. 用生产者发送消息 producer.send(textMessage)</p>
     * <p>9. 关闭资源 connection、session、consumer</p>
     * @param args
     * @throws JMSException
     */
    public static void main(String[] args) throws JMSException{
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageProducer producer = null;
        try {
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic("helloTopic");
            producer = session.createProducer(destination);
            TextMessage textMessage = null;
            while (true){
                // 构造消息textMessage,JMS定义了5种消息类型分别是TextMessage, MapMessage, BytesMessage, StreamMessage, ObjectMessage
                textMessage = session.createTextMessage(Thread.currentThread().toString() + "_1_" + System.currentTimeMillis());
                producer.send(textMessage);
                logger.info("producer: {}", textMessage.getText());
                session.commit();
                Thread.currentThread().sleep(1000L);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                connection.close();
                logger.info("------ closed connection -----");
            }
            if (session != null){
                session.close();
                logger.info("------ closed session -----");
            }
            if (producer != null){
                producer.close();
                logger.info("------ closed producer -----");
            }
        }
    }
}
