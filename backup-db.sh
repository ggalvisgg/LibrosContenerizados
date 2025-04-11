#!/bin/bash

CONTAINER_NAME=mariadb-libros

DB_NAME=librosdb

# Carpeta de destino (dentro del proyecto)
DEST_DIR=backups

# Fecha actual
DATE=$(date +%Y-%m-%d)

# Archivo destino
OUTPUT_FILE=$DEST_DIR/respaldo-$DB_NAME-$DATE.sql

# Ejecutar el dump y guardar en la carpeta
echo "Creando respaldo en $OUTPUT_FILE..."
docker exec $CONTAINER_NAME sh -c \
  'exec mysqldump -uroot -p"$MARIADB_ROOT_PASSWORD" '$DB_NAME \
  > "$OUTPUT_FILE"
