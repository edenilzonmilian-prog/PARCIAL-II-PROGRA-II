Sistema de Estacionamiento - Parcial II

Descripción

Aplicación de consola desarrollada en Java para gestionar el ingreso y control de vehículos en un estacionamiento.

El sistema permite registrar vehículos, consultar información, calcular costos, buscar vehículos y mostrar los ingresos generados.

Tecnologías utilizadas

- Java
- IntelliJ IDEA
- PostgreSQL
- pgAdmin 4
- GitHub

Programación orientada a objetos

El proyecto utiliza:

- Clase abstracta `Vehiculo`
- Herencia
- Encapsulamiento
- Abstracción
- Sobrescritura de métodos (`@Override`)
- Polimorfismo

Tipos de vehículos

Automóvil
- Tarifa: Q10.00 por hora.

 Motocicleta
- Tarifa: Q6.00 por hora.

 Descuento

Los vehículos que permanecen más de 5 horas reciben un descuento del 10% sobre el costo total.

Funcionalidades

El programa permite:

1. Registrar vehículo.
2. Mostrar todos los vehículos.
3. Buscar vehículo por placa.
4. Mostrar vehículo con mayor costo.
5. Mostrar total general recaudado.
6. Mostrar total recaudado por tipo.
7. Salir.

Colecciones utilizadas

- `ArrayList<Vehiculo>` para almacenar los vehículos.
- `HashSet<String>` para evitar placas duplicadas.
- `HashMap<String, Double>` para almacenar los totales recaudados por tipo.

Validaciones

El sistema valida:

- Placa no vacía.
- Propietario no vacío.
- Hora de ingreso no vacía.
- Horas utilizadas mayores que cero.
- Tipo de vehículo válido.
- Opciones válidas del menú.
- Placas duplicadas.
- Entrada numérica incorrecta.

También se utilizan excepciones para manejar entradas inválidas.

Base de datos

Se utiliza PostgreSQL con una base de datos llamada:

`parcial2_estacionamiento`

La tabla principal es:

`vehiculo`

Incluye restricciones como:

- Clave primaria.
- Placa única.
- Campos obligatorios.
- Validación de horas mayores que cero.
- Validación de costo mayor o igual a cero.
- Estado activo/inactivo.

Estructura del proyecto

text
parcial2/

├── src/
│   ├── Main.java
│   ├── Vehiculo.java
│   ├── Automovil.java
│   └── Motocicleta.java
│
├── database/
│   └── estacionamiento.sql
│
├── evidencias/
├── README.md
└── .gitignore
