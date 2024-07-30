# 🚗 Parking API Java - API de Estacionamento com Java

> Esse projeto consiste em um sistema de gerenciamento de estacionamentos, onde os clientes podem estacionar seu(s) veículo(s) nas vagas disponíveis e o administrador lida com toda a gestão do estacionamento.

## 💻 Pré-requisitos

Antes de começar, verifique se você atende aos seguintes requisitos:

- Versão utilizada no projeto de `<java / SDK 21.0.2>`
- Alguma ferramenta para testes de APIs, essas são as que recomendo `<Postman / Insomnia>`. Fique à vontade para usar a que preferir
- IDE que suporta Java eu recomendo `<IntelliJ IDEA>`, que será o exemplo de instalação nesse arquivo.
- Você deve possuir alguma banco de dados instalado e configurado na sua máquina. Nesse projeto foi utilizado, por padrão, o `<PostgresSQL>`.

## 🚀 Clonando Parking API Java

Para clonar o projeto, siga essa etapa:

1 - Acesse a pasta que deseja clonar/salvar o projeto e execute: <br/>
(para clone usando HTTPS)
```
git clone https://github.com/AntonioAndreDev/parking-api-java.git
```
ou
(para clone usando SSH)
```
git clone git@github.com:AntonioAndreDev/parking-api-java.git
```

## ⚙️ Setando as variáveis de ambiente com IntelliJ IDEA

Nesse projeto temos algumas variáveis de ambiente, são elas:
- db_password -> senha do seu banco de dados
- db_user -> usuário do seu banco de dados
- db_port -> porta de execução do seu banco de dados
- server_port -> porta de execução da API (se não declarado, a porta padrão será `8080`)
- SECRET_KEY_JWT -> chave que irá criptograr o JWT Token

## 🎲 Conexão com banco de dados PostgresSQL

No arquivo presente no caminho `resources/application.properties` estão presentes as configurações gerais de projeto, entre eles a conexão com o PostgresSQL <br/>
`spring.datasource.url=jdbc:postgresql://localhost:${db_port}/demo` <br/>
`spring.datasource.username=${db_user}` <br/> 
`spring.datasource.password=${db_password}` <br/>
`spring.datasource.driver-class-name=org.postgresql.Driver` <br/>

> ⚠️ Lembre-se de setar as variáveis de ambiente!

## ▶️ Executando Parking API Java

Para executar o projeto, siga esta etapa:
- Execute o arquivo `DemoParkApiApplication`
Caso tudo der certo, no terminal/console da sua IDE vai mostrar que a aplicação está em execução na porta `8080` ou na porta que foi definida na variável de ambiente `server_port`.

## 🛣️ Conferindo as rotas da aplicação

Esse projeto foi inteiramente documentado utilizando Swagger. Portando, é possível conferir todos os _endpoints_ da aplicação, suas funcionalidades, suas respostas à requisições, sua necessidade ou não de autenticação e seus erros de requisições por meio desse caminho abaixo.

No seu navegador acesse:
```
http://localhost:8080/docs-park.html
```
> ⚠️ Se você alterou a variável de ambiente `server_port` lembre-se de trocar 8080 pelo valor que foi atribuído à server_port!


## 🌟 Funcionalidades
- [X] Validação de dados
- [X] Tratamento de exceções personalizados
- [X] Documentação com Swagger
- [X] Teste ponta a ponta (end-to-end)
- [X] Autenticação e autorização com JWT Token
- [X] Utilização de DTO para transferência de dados entre o cliente e o servidor
- [X] JasperReports para geração de relatórios
- [X] Internacionalização de mensagens de erros em pt_BR, en (inglês) e es (espanhol)

##

> ⭐ Se esse projeto pode te ajudar, deixe uma estrela nesse repositório!
