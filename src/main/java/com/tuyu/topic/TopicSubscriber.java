package com.tuyu.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * Pub/Sub 发布订阅模式
 * <p>订阅者</p>
 * @author tuyu
 * @date 5/2/18
 * Stay Hungry, Stay Foolish.
 */
public class TopicSubscriber {

    private static final String USERNAME= ActiveMQConnection.DEFAULT_USER; // 默认的连接用户名
    private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD; // 默认的连接密码
    private static final String BROKEURL=ActiveMQConnection.DEFAULT_BROKER_URL; // 默认的连接地址

    private static final Logger logger = LoggerFactory.getLogger(TopicSubscriber.class);

    /**
     * 使用JMS通用规范创建点对点模式消费者
     * <p>1. 创建ActiveMQ的连接工厂 factory</p>
     * <p>2. 用连接工厂创建链接 connection</p>
     * <p>3. 启动连接 connection.start()</p>
     * <p>4. 用连接创建会话 session</p>
     * <p>5. 用会话创建目的地 destination</p>
     * <p>6. 用会话创建消费者 consumer</p>
     * <p>7. 用消费者实例接收消息，可以同步接收，也可以异步接收</p>
     * <p>8. 关闭资源 connection、session、consumer</p>
     * @param args
     * @throws JMSException
     */
    public static void main(String[] args) throws JMSException{
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageConsumer consumer = null;
        try {
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            // 目的地是topic而不是queue
            destination = session.createTopic("helloTopic");
            consumer = session.createConsumer(destination);
            // 注册监听
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    TextMessage text = (TextMessage) message;
                    try {
                        logger.info("consumer: {}", text.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            // 虽然是异步接收消息，但是如果不让线程睡眠，则看不到接收的消息
            Thread.currentThread().sleep(Integer.MAX_VALUE);
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
            if (consumer != null){
                consumer.close();
                logger.info("------ closed consumer -----");
            }
        }
    }
}
