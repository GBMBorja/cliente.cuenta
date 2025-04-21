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

  El Segundo microservicio que realiza la creacion de cuentas y movimientos que consume el prier servico via webflux usa el siguinete puerto 8081:
   su curl es:
    curl --location 'localhost:8081/cuentas' \
--header 'Content-Type: application/json' \
--data '{
  "numeroCuenta": "1234567890",
  "tipoCuenta": "Ahorro",
  "estado": true,
  "clienteId": "1720445715",
  "saldoInicial":250
}'


curl --location 'localhost:8081/movimientos' \
--header 'Content-Type: application/json' \
--data '{
  "fecha": "2025-04-21T10:00:00",
  "tipoMovimiento": "DEPOSITO",   
  "valor": 500.00,
  "saldo": 1500.00,
  "numeroCuenta": "1234567890"
}'
