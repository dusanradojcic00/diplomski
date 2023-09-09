#!/bin/bash

set -e
set -u
#Create separate databases for the app and for the keycloak
function create_user_and_database() {
  local database=$1
  PGPASSWORD="$POSTGRES_PASSWORD"
  echo "Creating database '$database'"
  echo "$PGPASSWORD"
  psql -v ON_ERROR_STOP=1 --username=$POSTGRES_USER -d order -w <<-EOSQL
	    CREATE DATABASE $database;
	    GRANT ALL PRIVILEGES ON DATABASE $database TO "$POSTGRES_USER";
EOSQL
}

if [ -n "$POSTGRES_ADDITIONAL_DATABASES" ]; then
  echo "Additional database creation requested: $POSTGRES_ADDITIONAL_DATABASES"
  for db in $(echo "$POSTGRES_ADDITIONAL_DATABASES" | tr ',' ' '); do
    create_user_and_database "$db"
  done
  echo "Additional databases created"
fi
