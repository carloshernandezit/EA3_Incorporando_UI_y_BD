# EA3 - Incorporando UI y BD

Aplicación de escritorio en Java (Swing) que permite ingresar la configuración de una base de datos MySQL y validar la conexión mediante JDBC.

## Estructura

- `src/ConexionBD.java`: lógica de conexión con DriverManager y try-with-resources.
- `src/ConexionTester.java`: interfaz gráfica (JFrame) y manejo de excepciones.
- `lib/`: driver mysql-connector-j.

## Ejecución

```
javac -cp "lib/*" -d bin src/*.java
java -cp "bin;lib/*" ConexionTester
```

En Linux o Mac se usa `:` en lugar de `;` en el classpath.