
Use:
# Descrição

O projeto consiste em um sistema para realização de enquetes sobre temas
diversos, as enquetes consistem em questões que podem ser respondidas com
respostas simples, como: sim, não e não sei. Além de permitir responder as
enquetes, o sistema apresentar os resultados e nele deve ser possível realizar
diversos filtros para permitir uma analise melhor sobre o tema.

# Pre-requisitos

Para executar o projeto é preciso ter o [postgresql com extensão postgis](http://postgis.net/install/) e o [Apache Maven](http://maven.apache.org/install.html)
# Passos para executar
### Passo 1
 baixe ou clone o projeto no [bitbucket](https://bitbucket.org/Jose_Ferreira/bdnc1) 

### Passo 2

 com o potsgis e o maven configurados crie uma base de dados

### Passo 3
Dentro da pasta do projeto navege até o diretório src/main/resources/bd
abra o arquivo database_create.sql, rode esse script na base de dados criada,
Depois rode o script UserAdmin.sql
### Passo 4 
No mesmo diretório abra o arqquivo connection.properties
Nesse arquivo configure os seguinte propiedades
#### URL
EXEMPLO: dbc:postgresql://localhost:5432/nome da base de dados criada anteriormente 
#### USER
Nome do seu usuário por padrão utiliza-se postgres
#### Password 
sua senha do postgresql

### Passo 4

Com as tabelas criadas abra o terminal/cmd execulte  



``` shell
$  mvn clean compile tomcat7:run
```

Acesse:

[http://localhost:8080/falai/index.xhtml](http://localhost:8080/falai/index.xhtml)

