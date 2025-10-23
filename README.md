# Proyecto_Final_Catedra

---

# Proyecto Aerolíneas 

Este proyecto es una aplicación web completa desarrollada con Spring Boot. Su objetivo es gestionar las operaciones CRUD (Crear, Leer, Actualizar y Eliminar), conectándose a una base de datos MySQL.

---

## Tecnologías Utilizadas

### Backend
- Java 17  
- Spring Boot  
- Spring Web  
- Spring Data JPA  
- MySQL Driver  
- Maven  

### Frontend (Incompleto)
- HTML5  
- CSS3  
- JavaScript  

### Base de datos
- MySQL (phpMyadmin de Wampserver  

---

## Como correr el proyecto

1. Clonar el repositorio o descargar mediante propio git hub

 ```
git clone https://github.com/XxRdorixX/DWF_DesafPrac2.git
   ```
 ```
cd DWF_DesafPrac2
 ```

2. Importar la base de datos

`sistemaboletosaereos.sql`

3. abrir en IntelliJ IDEA

4. En la terminal usar los siguientes comandos 

```
cd bookapi
 ```
  ```
mvn clean install
  ```

5. Correr el proyecto 

Opcion A (via IDE): Darle Run archivo `BookApiApplication.java`
Opcion B (via terminal):
```
mvn spring-boot:run
```

## Pruebas (Postman)

Login y Token → POST http://localhost:8080/api/auth/login  

{     
"correo": "admin@correo.com",  
"contrasena": "12345"  
}

Aerolineas → POST http://localhost:8080/api/v1/aerolíneas  

{    
"nombre": "Aerolinea",  
"pais": "El Salvador"  
}

Aviones → POST http://localhost:8080/api/v1/aviones  

{    
"modelo": "Airbus A320",  
"capacidad": 180,  
"idaerolinea": 2  
}

Rutas → POST http://localhost:8080/api/v1/rutas  

{    
"ciudadorigen": "San Salvador",  
"ciudaddestino": "Guatemala",  
"duracion": 50  
}

Vuelos → POST http://localhost:8080/api/v1/vuelos  

{    
"idruta": 1,  
"idavion": 2,  
"fechasalida": "2025-12-05",  
"horasalida": "08:45:00",  
"tarifa": 250.75  
} 

Pasajeros → POST http://localhost:8080/api/v1/pasajeros  

{     
"nombrecompleto": "Rodrigo Rivas",  
"fechanacimiento": "1998-06-15",  
"pasaporte": "Colombiano",  
"numeroPasaporte": "A1234567",  
"preferenciaasiento": "Ventana"  
}

Reservaciones → POST http://localhost:8080/api/v1/reservaciones  

{    
"idvuelo": 1,  
"idpasajero": 1,  
"estado": "pendiente"  
}

Pagos → POST http://localhost:8080/api/v1/pagos  

{  
"reservacion": { "idreservacion": 1 },  
"metodopago": "tarjeta",    
"monto": 120.50  
}  
 
Reclamos → POST http://localhost:8080/api/v1/reclamos  

}   
"pasajero": { "idpasajero": 1 },  
"descripcion": "El vuelo se retrasó más de 3 horas y perdí mi conexión."  
Tripulacion → POST http://localhost:8080/api/v1/tripulacion  
{  
