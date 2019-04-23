# DASA API 

Api de cadastro, alteracao e desativacao de Laboratorios e exames

## Tecnologias

Esta api foi cencebida usado spring boot e as implementacoes do JAX-RS

## Banco de dados

 Por se tratar de uma api de tests, foi decidido usar o banco de dados embarcado H2. Ou seja, toda vez que aplicaçao é resetada, os dados sao perdidos.


### Build e excecucap da aplicacao

Para buildar a aplicacao nao é necessario nenhuma configuracao alternativa, basta usar os comandos do maven para buildar e executar a aplicacao.

BUILD (dentro do diretorio raiz da aplicacao)

```
mvn clean install
```

RUN (dentro do diretorio raiz da aplicacao)

```
mvn spring-boot:run
```

## Executando os testes

Para executar os testes automatizados, basta usar o comando

```
mvn test
```

## Padrao de Arquitetura 
 O padrao de arquitetura para as classes dessa aplicacao segue:
 
 	*DAO - Camada de persistencia da aplicacao
 	*Model - Entidades da aplicacao
 	*Resource - Classes responsavel pelas chamadas HTTP
 	
 OBS:. Vale lembrar, que para esta aplicacao, nao foi incluido uma camada de *BUSINESS* onde fica contido as regras de negocio. Foi decido assim, pois as chamadas eram simples e nao visavam regras complexas.
 
 
## URLs e ENDPOINTS
 
 	URL BASE: dasa/
 	
**Laboratorio** 
Para cadastro de laboratorio

	[POST] dasa/apiLaboratorio/laboratorio

Para listar todos os laboratorios

	[GET] dasa/apiLaboratorio/laboratorios

Para atualizar um laboratorio

	[PUT] dasa/apiLaboratorio/laboratorio/{id}	
	
Para desativar um laboratorio

	[DELETE] dasa/apiLaboratorio/laboratorio/{id}	
 	
 **Exame** 
Para cadastro de exame

	[POST] dasa/apiExame/exame

Para listar todos os exames

	[GET] dasa/apiExame/exames

Para atualizar um laboratorio

	[PUT] dasa/apiExame/exame/{id}	
	
Para desativar um laboratorio

	[DELETE] dasa/apiExame/exame/{id}
	
Para obter uma lista de laboratorios associados a um exame
	
	[GET] dasa/apiExame/exame/{nome}
	
**Associacoes**

Para associar um exame a um laboratorio

	[PUT] apiAssociacao/associar/{idExame}/{idLaboratorio}

Para desassociar um exame de um laboratorio

	[PUT] apiAssociacao/desassociar/{idExame}/{idLaboratorio}	
	
**Processos em LOTES**

Para cadastrar varios registros de uma vez, basta enviar um array do objeto desejado (json) para cada situacao:
<br>
Cadastrar varios Laboratorios

	[POST] batch/cadastro/laboratorio 	

Cadastrar varios Exames

	[POST] batch/cadastro/exame
			
Atualizar varios Laboratorios

	[POST] batch/atualizar/laboratorio 	

Atualizar varios Exames

	[POST] batch/atualizar/exame
	
Desativar varios Laboratorios

	[POST] batch/desativar/laboratorio 	

Desativar varios Exames

	[POST] batch/desativar/exame				
	
## Built With

* [SpringBoot](https://spring.io/projects/spring-boot) - SPRING
* [Maven](https://maven.apache.org/) - Dependency Management
* [Eclipse](https://eclispsefundation.org) - The Eclipse Fundation


## Versioning

Para versionar, estou usando o padrao [SemVer](http://semver.org/)

## Authors

* **Newton Santos** - *Initial work* - [NeewRobert](https://github.com/neewrobert)
