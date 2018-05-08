ActiveMQ Tutorial Step By Step
===

ActiveMQ 简单教程
---

# 安装ActiveMQ
**保证docker已经正常安装**

1. docker search activemq -- 搜索activemq相关的image镜像
```bat
AME                                DESCRIPTION                                     STARS               OFFICIAL            AUTOMATED
webcenter/activemq                  ActiveMQ 5.14.3 with OpenJDK-jre-8-headless …   122                                     [OK]
rmohr/activemq                      Various versions of ActiveMQ neatly packet i…   43                                      [OK]
vromero/activemq-artemis            ActiveMQ Artemis image (Debian and Alpine ba…   9                                       [OK]
cloudesire/activemq                 Latest activemq                                 3                                       [OK]
aterreno/activemq-dockerfile                                                        3                                       [OK]
andreptb/activemq                   Debian Jessie based image with ActiveMQ inst…   2                                       [OK]
kdomanski/activemq                  Apache ActiveMQ                                 2                                       [OK]
elsammons/centos-activemq-artemis   Will create a centos image running Apache Ac…   2                                       [OK]
njmittet/alpine-activemq            Docker image for running Apache ActiveMQ. Ba…   1                                       [OK]
woahbase/alpine-activemq            Apache ActiveMQ built on Alpine Linux + Open…   1
antonw/activemq-jmx                 ActiveMQ with (remote) JMX                      1                                       [OK]
ddmlu/activemq-openshift            Fork of ayannah/activemq for openShift          1                                       [OK]
jtech/activemq                      Latest ActiveMQ production distribution on l…   1                                       [OK]
maxird/activemq                     ActiveMQ                                        0                                       [OK]
joakimgreenbird/activemq-bridge     Bridge from kafka to activemq.                  0
beeyond/activemq                    ActiveMQ MySQL                                  0
duffqiu/activemq-hub                                                                0                                       [OK]
mrlambert/activemq                  ActiveMq 5.12.0 Image with apache and superv…   0
javierprovecho/docker-activemq      Docker image for Apache ActiveMQ (AMQ) (v5.1…   0                                       [OK]
albertonavarro/activemq12s                                                          0
expertsystems/activemq              ActiveMQ                                        0                                       [OK]
aungzy/activemq                     Docker image for ActiveMQ, forked from https…   0                                       [OK]
camptocamp/activemq-mcollective     Activemq image for mcollective                  0                                       [OK]
ayannah/activemq                    Dockerized ActiveMQ                             0                                       [OK]
duffqiu/activemq-edge                                                               0                                       [OK]
```
2. docker pull webcenter/activemq -- 下载镜像
3. docker image tag webcenter/activemq activemq -- 创建一个指向源镜像的标签
4. docker images -- 查看本地镜像列表
```bat
REPOSITORY           TAG                 IMAGE ID            CREATED             SIZE
zookeeper            latest              56d414270ae3        3 months ago        146MB
nginx                latest              3f8a4339aadd        4 months ago        108MB
activemq             latest              3af156432993        15 months ago       422MB
webcenter/activemq   latest              3af156432993        15 months ago       422MB
```
5. docker run -d 61616:61616 -p 8161:8161 activemq -- 后台运行activemq镜像，并映射端口(61616是ActiveMQ默认的监听端口，8161是ActiveMQ控制台的端口)
6. docker ps -- 查看镜像运行情况
```bat
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                                                                                   NAMES
f3206e8ad900        activemq            "/app/run.sh"            22 minutes ago      Up 23 minutes       1883/tcp, 5672/tcp, 0.0.0.0:8161->8161/tcp, 61613-61614/tcp, 0.0.0.0:61616->61616/tcp   zealous_wright
466af12a8af8        zookeeper           "/docker-entrypoint.…"   11 hours ago        Up 11 hours         2888/tcp, 0.0.0.0:2181->2181/tcp, 3888/tcp                                              elegant_joliot
```

# 运行

1. git clone https://github.com/scutuyu/activemq.git
2. mvn clean compile
3. mvn idea:idea -Dmaven.test.skip=true(或者mvn idea eclipse:eclipse -Dmaven.test.skip=true)
4. 导入IDE
5. 依次运行P2pProducer.java、P2pConsumer.java;TopicProvider.java、TopicSubscriber.java