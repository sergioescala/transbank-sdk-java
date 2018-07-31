# Onepay Java SDK Quick Start

## Requerimientos

Java 1.7+

## Instalación

### Si usas Maven

Agrega la siguiente dependencia en el archivo pom de tu proyecto:

```xml
<dependency>
    <groupId>cl.transbank.onepay</groupId>
    <artifactId>transbank-onepay-sdk-java</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### Agregar repositorio de Transbank 

Actualmente estamos trabajando en deployar la librería en un repositorio 
publico de Maven. Mientras eso no este listo debes agregar nuestro
repositorio privado en el archivo pom de tu proyecto:

```xml
<repositories>
    <repository>
        <id>transbank-repo</id>
        <name>transbank-repo</name>
        <url>http://142.93.18.171/maven-repo/</url>
    </repository>
</repositories>
```

### Otros

Necesitaras descargar y agregar en forma manual los siguientes archivos JARs en tus dependencias:

* Librería Java [transbank-sdk-java-1.0.0.jar][jar_location]
* [Google Gson](https://github.com/google/gson) from <https://repo1.maven.org/maven2/com/google/code/gson/gson/2.6.2/gson-2.6.2.jar>.

## Mode de uso

### Crear transación

TransactionCreateExample.java

```java
import cl.transbank.onepay.Onepay;
import cl.transbank.onepay.exception.TransbankException;
import cl.transbank.onepay.model.*;

import java.io.IOException;

public class TransactionCreateExample {
    public static void main(String[] args) {
        Onepay.setSharedSecret("P4DCPS55QB2QLT56SQH6#W#LV76IAPYX");
        Onepay.setApiKey("mUc0GxYGor6X8u-_oB3e-HWJulRG01WoC96-_tUA3Bg");

        ShoppingCart cart = new ShoppingCart();
        try {
            cart.add(new Item("Zapatos", 1, 15000, null, -1));
            cart.add(new Item("Pantalon", 1, 12500, null, -1));

            TransactionCreateResponse response = Transaction.create(cart);
            
            System.out.println(response);
        } catch (IOException|TransbankException e) {
            e.printStackTrace();
        }
    }
}
```

#### Configuración por request

Para aplicaciones que necesiten usar múltiples keys durante el ciclo de vida del proceso,
es posible configurar estas por cada petición.

El ejemplo anterior podría ser también escrito de la siguiente manera

```java
import cl.transbank.onepay.exception.TransbankException;
import cl.transbank.onepay.model.*;

import java.io.IOException;

public class TransactionCreateExample {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        
        try {
            cart.add(new Item("Zapatos", 1, 15000, null, -1));
            cart.add(new Item("Pantalon", 1, 12500, null, -1));

            Options options = new Options()
                    .setApiKey("mUc0GxYGor6X8u-_oB3e-HWJulRG01WoC96-_tUA3Bg")
                    .setSharedSecret("P4DCPS55QB2QLT56SQH6#W#LV76IAPYX");

            TransactionCreateResponse response = Transaction.create(cart, options);
            
            System.out.println(response);
        } catch (IOException|TransbankException e) {
            e.printStackTrace();
        }
    }
}
```
#
### Confirmar transacción

```java
import cl.transbank.onepay.Onepay;
import cl.transbank.onepay.exception.TransbankException;
import cl.transbank.onepay.model.*;

import java.io.IOException;

public class TransactionCommitExample {
    private static final String EXTERNAL_UNIQUE_NUMBER_TO_COMMIT_TRANSACTION_TEST = "f506a955-800c-4185-8818-4ef9fca97aae";
    private static final String OCC_TO_COMMIT_TRANSACTION_TEST = "1807983490979289";

    public static void main(String[] args) {
        Onepay.setSharedSecret("P4DCPS55QB2QLT56SQH6#W#LV76IAPYX");
        Onepay.setApiKey("mUc0GxYGor6X8u-_oB3e-HWJulRG01WoC96-_tUA3Bg");
        
        try {
            TransactionCommitResponse response = Transaction.commit(OCC_TO_COMMIT_TRANSACTION_TEST,
                    EXTERNAL_UNIQUE_NUMBER_TO_COMMIT_TRANSACTION_TEST);

            System.out.println(response);
        } catch (IOException|TransbankException e) {
            e.printStackTrace();
        }
    }
}
```

#### Configuración por request

```java
import cl.transbank.onepay.exception.TransbankException;
import cl.transbank.onepay.model.*;

import java.io.IOException;

public class TransactionCommitExample {
    private static final String EXTERNAL_UNIQUE_NUMBER_TO_COMMIT_TRANSACTION_TEST = "f506a955-800c-4185-8818-4ef9fca97aae";
    private static final String OCC_TO_COMMIT_TRANSACTION_TEST = "1807983490979289";
    
    public static void main(String[] args) {
        try {
            Options options = new Options()
                    .setApiKey("mUc0GxYGor6X8u-_oB3e-HWJulRG01WoC96-_tUA3Bg")
                    .setSharedSecret("P4DCPS55QB2QLT56SQH6#W#LV76IAPYX");

            TransactionCommitResponse response = Transaction.commit(OCC_TO_COMMIT_TRANSACTION_TEST,
                    EXTERNAL_UNIQUE_NUMBER_TO_COMMIT_TRANSACTION_TEST, options);

            System.out.println(response);
        } catch (IOException|TransbankException e) {
            e.printStackTrace();
        }
    }
}
```
#
### Anular transacción

```java
import cl.transbank.onepay.Onepay;
import cl.transbank.onepay.exception.TransbankException;
import cl.transbank.onepay.model.*;

import java.io.IOException;

public class TransactionRefundExample {
    public static void main(String[] args) {
        Onepay.setSharedSecret("P4DCPS55QB2QLT56SQH6#W#LV76IAPYX");
        Onepay.setApiKey("mUc0GxYGor6X8u-_oB3e-HWJulRG01WoC96-_tUA3Bg");
        
        try {
            RefundCreateResponse response = Refund.create(27500, "1807983490979289", "f506a955-800c-4185-8818-4ef9fca97aae",
                    "623245");
            
            System.out.println(response);
        } catch (IOException|TransbankException e) {
            e.printStackTrace();
        }
    }
}
```

#### Configuración por request

```java
import cl.transbank.onepay.Onepay;
import cl.transbank.onepay.exception.TransbankException;
import cl.transbank.onepay.model.*;

import java.io.IOException;

public class TransactionRefundExample {
    public static void main(String[] args) {
        try {
            Options options = new Options()
                    .setApiKey("mUc0GxYGor6X8u-_oB3e-HWJulRG01WoC96-_tUA3Bg")
                    .setSharedSecret("P4DCPS55QB2QLT56SQH6#W#LV76IAPYX");

            RefundCreateResponse response = Refund.create(27500, "1807983490979289", "f506a955-800c-4185-8818-4ef9fca97aae",
                    "623245", options);
            
            System.out.println(response);
        } catch (IOException|TransbankException e) {
            e.printStackTrace();
        }
    }
}
```

Puedes mirara los [test funcionales](http://ruta.a.los.test) para mas ejemplos.

## Ambientes

El SDK incluye tres ambientes distintos para trabajar: LIVE, TEST y MOCK

#### LIVE

Es el ambiente productivo de Transbank y debe ser configurado una vez que tu integración
esta probada y certificada. La forma de hacerlo es la siguiente:

```java
Onepay.setIntegrationType(Onepay.IntegrationType.LIVE);
```

La configuración de ambiente se realiza en forma estática, lo que quiere decir que basta 
con que se realice una vez durante el ciclo de vida de la aplicación y debemos asegurarnos
de hacerlo antes de realizar cualquier tipo de transacción.

#### TEST

Esta es la configuración por defecto del SDK. Es decir, si no configuramos el tipo de
integración se usaran los servidores de test.

```java
Onepay.setIntegrationType(Onepay.IntegrationType.TEST);
```

#### MOCK

Diseñado princiupalmente para efectos de test, este tipo de integracion apunta a servicios
los cuales responden una respuesta estatica.

```java
Onepay.setIntegrationType(Onepay.IntegrationType.MOCK);
```

## Desarrollo

Esta librería usa [Project Lombok][lombok] en su desarrollo. Si bien no es necesario podrías querer instalar el [plugin][lombok-plugins]
para tu IDE favorito con el fin de evitar que veas errores marcados por la herramienta de desarrollo.

[jar_location]: http://142.93.18.171/maven-repo/cl/transbank/transbank-sdk-java/1.0.0/transbank-sdk-java-1.0.0.jar
[lombok]: https://projectlombok.org
[lombok-plugins]: https://projectlombok.org/setup/overview

<!--
# vim: set tw=79:
-->
