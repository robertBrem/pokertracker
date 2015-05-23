FROM jboss/base-jdk:8
MAINTAINER Robert Brem <brem_robert@hotmail.com>

ENV WILDFLY_VERSION 8.2.0.Final
ENV HOME /opt/jboss
ENV JBOSS_HOME $HOME/wildfly
ENV JBOSS_CLI $JBOSS_HOME/bin/jboss-cli.sh

RUN cd $HOME
RUN curl http://download.jboss.org/wildfly/$WILDFLY_VERSION/wildfly-$WILDFLY_VERSION.tar.gz | tar zx
RUN mv $HOME/wildfly-$WILDFLY_VERSION $JBOSS_HOME

EXPOSE 8080 9990

ADD init.sh $JBOSS_HOME/init.sh
CMD $JBOSS_HOME/init.sh
