# Challenge-Operacion Fuego de Quasar :rocket: :new_moon: :tokyo_tower: :tokyo_tower: :tokyo_tower:

Apirest en lenguaje java que implementa la trilateración para obtener las coordenadas de un punto.

- La información de los satelites se almacena en H2.
- El apirest fue desarrollado bajo el framework Spring.
- Se utilizó una librería para el algoritmo de [trilateración](https://github.com/lemmingapex/trilateration).
- El proyecto se desplegó en [Azure](https://fuego-quasar-1623801598676.azurewebsites.net/)

## Prerequisitos

Para correr este proyecto necesita tener instalado y configurado:

* Git
*	JDK 8
*	Maven
*	Postman o Insomnia


### Despliegue local

*	Abrir la consola Git Bash en la ruta en la que se quiere descargar el proyecto y clonar el repositorio con el comando

```console
git clone https://github.com/camilo654/challenge-fuego.git
```

* Ingresar al proyecto con el comando 

```console
cd challenge-fuego
```

* Ejecutar el siguiente comando para compilar el proyecto

```console
mvn clean package
```

* Para ejecutar el programa ingrese a la carpeta /target

```console
cd target
```

* Posteriormente ejecutar el comando

```console
java -jar fuego-quasar-0.0.1-SNAPSHOT.jar
```
* Una vez desplegado, para consumir el servicio creamos un nuevo Request de tipo Post apuntando a la ruta `localhost:8080/topsecret` y enviamos el siguiente request:

```json
{
	"satellites": [
		{
			"name": "kenobi",
			"distance": 100.0,
			"message": [
				"este", "", "", "mensaje", ""
			]
		},
		{
			"name": "skywalker",
			"distance": 115.5,
			"message": [
				"", "es", "", "", "secreto"
			]
		},
		{
			"name": "sato",
			"distance": 142.7,
			"message": [
				"este", "", "un", "", ""
			]
		}
	]
}
```

### Consumo API REST en la nube

Para probar el desarrollo de este nivel se deben realizar mínimo 3 peticiones tipo POST a `https://fuego-quasar-1623801598676.azurewebsites.net/topsecret_split/{satellite_name}`, donde {satellite_name} se debe reemplazar por el nombre de alguno de los 3 satélites (kenobi, skywalker, sato), el Request debe tener la siguiente estructura

```json
{
	"distance": 100.0,
	"message": ["este", "", "", "mensaje", ""]
}
```

Una vez se haya cargado la información de los 3 satélites al hacer una petición tipo GET a `https://fuego-quasar-1623801598676.azurewebsites.net/topsecret_split` se podrá obtener la información sobre el portacargas.

Los anteriores pasos los podrá encontrar con imagenes guía en el documento [2. Como probar apirest.pdf](documentos/2.%20Como%20probar%20apirest.pdf)
