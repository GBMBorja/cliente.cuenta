# cliente.cuenta
CLIENTE CUENTA

Se realizo lo solictado en el test, para su ejecucion solo se debe ejecutar mvn install y ejecutar el jar genereado en el target este microservicio se crea en el puerto 8080

su curl:
curl --location --request GET 'localhost:8080/clientes/1' \
--header 'Content-Type: application/json' \
--data-raw '{
  "identificacion": "1720445715",
  "nombre": "Gabriel",
  "apellido": "Borja",
  "correo": "gabriel.borja@example.com",
  "telefono": "3101234567",
  "direccion": "Calle 123 #45-67",
  "contrasena": "claveSegura123",
  "estado": true,
  "genero": "M",
  "edad":30
  }'
