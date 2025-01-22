# Tech Challenge 02 - Parquímetro

- Introdução
- Funções
- Tecnologias
- Instalação
- Banco de dados
- Fluxo principal
- API


***

## Introdução

Esta aplicação é um sistema de gerenciamento e controle de parquímetros desenvolvido com o framework Springboot. Através dela se pode cadastrar parquímetros com tarifas diferentes, gerenciar a sessão de veículos e calcular o valor a ser cobrado. 

O projeto pode ser acessado em: https://github.com/joao-v170r/tech-challenge-02

***

## Funções

* Cadastrar parquímetros diferentes.
* Definir taxas por período de tempo para cada parquímetro. 
* Registrar e gerenciar a sessão de veículos em cada parquímetro
* Calcular o valor a ser pago conforme a tarifa estipulada e o tempo da sessão.
* Verificação de pagamento para encerrar a sessão.

***

## Tecnologias

* Springboot: Framework web
* Gradle: Gerenciador de dependências
* Swagger: Documentação e interface para testes
* H2: Banco de dados em memória

***

## Instalação

* Instalar Java 23 e o Gradle. 
* Baixe os arquivos repositório. `https://github.com/joao-v170r/tech-challenge-02`.
* Entre na pasta do repositório. `cd tech-challenge-02`.
* Execute o projeto com Gradle.
  * Utilize este comando no terminal: `./gradlew bootRun`.
  * No Windows, caso esteja utilizando CMD, use: `gradlew.bat bootRun`.
 
O Swagger pode ser acessado diretamente em `http://localhost:8080/swagger-ui/index.html`. 

***

## Banco de dados


<div align="center">
    <img src="src/main/resources/img/db.png" width="486px" height="334px">
</div>

***

## Fluxo principal

Você deve inicialmente cadastrar um parquímetro (`POST /parquimetro`), cadastrar tarifas (`POST /tarifa`) e vincular a tarifa ao parquímetro (`POST /parquimetro/link-tarifa`). 

A entrada do veículo no estacionamento é sinalizado com a criação de uma sessão (`POST /sessao/init`). Para sair do estacionamento após o tempo de tolerância, é necessário realizar um pagamento (`POST /pagamento/init`). Após o pagamento, na saída, a sessão será finalizada (`PUT /sessao/{id}/finish`). 

### Cadastrar parquímetro: `POST /parquimetro`
Exemplo de envio:
  ```
{
   "enderecoCompleto": "Rua das Flores, 123",
   "latitude": -23,
   "longitude": -46,
   "tolerancia": "00:15:00",
   "statusParquimetro": "ATIVO"
}
  ```
Observação: A tolerância é um tempo de sessão (15 minutos) no qual não haverá cobrança para sair do estacionamento.

### Cadastrar tarifas  `POST /tarifa`
Exemplo de envio:
```
{
  "precoIntervalo": 15,
  "intervalo": "01:00:00"
}
 ```
Observação: O "precoIntervalo" (15 reais) será o preço cobrado para até 1 hora no estacionamento. 

### Vincular tarifa `POST /parquimetro/link-tarifa`
Exemplo de envio:
```
{
  "parquimetroId": 1,
  "tarifaId": 1
}
 ```

### Iniciar sessão `POST /sessao/init`
Exemplo de envio:
```
{
  "placaCarro": "HQW5678",
  "parquimetroId": 1
}
 ```

### Realizar pagamento `POST /pagamento/init`
Exemplo de envio:
```
{
  "sessaoId": 1,
  "formaPagamento": "PIX"
}
 ```
Observação: Apenas é necessário realizar o pagamento caso ultrapasse a tolerância do parquímetro. Caso esteja dentro da tolerância, poderá finalizar a sessão diretamente.

### Finalizar sessão `POST /sessao/{id}/finish`
Observação: Esta requisição não precisa de corpo, o id é captado da url, e é verificado se o pagamento já foi realizado. 

***

## API

### Pagamento

| Método | URL | Ação |
|-|-|-|
| PUT | /pagamento/{id} | Alterar um pagamento. |
| POST | /pagamento/init | Cria um pagamento. |

<br>

### Tarifa

| Método | URL | Ação |
|-|-|-|
| GET | /tarifa | Lista todas as tarifas. |
| POST | /tarifa | Cria uma tarifa. |
| GET | /tarifa/{id} | Obtém dados de uma tarifa. |
| DELETE | /tarifa/{id} | Deleta uma tarifa. |
| GET | /tarifa/{id}/parquimetros | Lista todos os parquimetros da tarifa. |

<br>

### Sessão

| Método | URL | Ação |
|-|-|-|
| PUT | /sessao/{id}/finish | Conclui a sessão. |
| POST | /sessao/init | Cria uma sessão. |
| GET | /sessao/{id} | Obtém dados de uma sessão. |
| GET | /sessao/parquimetro/{id} | Obtém todas as sessões de um parquimetro. |

<br>

### Parquimetro

| Método | URL | Ação |
|-|-|-|
| GET | /parquimetro/{id} | Obtém dados de um parquimetro. |
| PUT | /parquimetro/{id} | Atualiza um parquimetro. |
| GET | /parquimetro | Lista todos os parquimetros. |
| POST | /parquimetro | Cria um parquimetro. |
| POST | /parquimetro/link-tarifa | Vincula tarifa. |
| DELETE | /parquimetro/unlink-tarifa | Desvincula tarifa. |
