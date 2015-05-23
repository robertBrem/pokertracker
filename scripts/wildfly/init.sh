#!/bin/bash

function wait_for_server() {
  until `$JBOSS_CLI -c "ls /deployment" &> /dev/null`; do
    sleep 1
  done
}

if [ "$TEST_MODE" ]; then
  echo "Do not set TEST_MODE if you would hide the management service!"
  $JBOSS_HOME/bin/add-user.sh admin admin --silent
  $JBOSS_HOME/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0 &
else
  echo "Set TEST_MODE to expose the management service!"
  $JBOSS_HOME/bin/standalone.sh -b 0.0.0.0 &
fi

echo "=> Waiting for the server to boot"
wait_for_server

echo "=> Downloading the db driver"
if [ "$DB_DRIVER_PATH" ] && [ "$DB_DRIVER_NAME" ]; then
  cd $JBOSS_HOME/standalone/deployments
  curl -O $DB_DRIVER_PATH/$DB_DRIVER_NAME

  echo "=> Add the datasource"
  if [ "$JNDI_NAME" ] && [ "$NAME" ] && [ "$CONNECTION_URL" ] && [ "$DB_USER_NAME" ] && [ "$DB_PASSWORD" ]; then
    $JBOSS_HOME/bin/jboss-cli.sh --connect <<EOF
batch

data-source add --jndi-name=$JNDI_NAME --name=$NAME --connection-url=$CONNECTION_URL --driver-name=$DB_DRIVER_NAME --user-name=$DB_USER_NAME --password=$DB_PASSWORD

run-batch
exit
EOF

    if [ "$DEPLOYMENT_ARTIFACT" ]; then
      cd $JBOSS_HOME/standalone/deployments
      curl -O $DEPLOYMENT_ARTIFACT
    else
      echo "You have to set DEPLOYMENT_ARTIFACT to deploy an artifact!"
    fi
  else
    echo "You have to set JNDI_NAME, NAME, CONNECTION_URL, DB_USER_NAME and DB_PASSWORD to add datasource!"
  fi
else
  echo "You have to set JNDI_NAME, NAME, CONNECTION_URL, DB_USER_NAME and DB_PASSWORD to add datasource!"
fi

while true; do sleep 1000; done