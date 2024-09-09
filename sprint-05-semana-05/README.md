# Sistema de Gestão de Estacionamento

Estou no programa de bolsas da compass UOL, na 5° semana. Na atividade da semana
foi proposto que desenvolvêssemos uma API de uma livraria utilizando o springboot,
e o mongoDB para armazenar os dados vindos das requisições dessa API criada. Além de cadastrar,
deletar e atualizar esses dados, deve ser possivel também filtrar os livros escritos
por um determinado autor, ou até encontrar todos os livros publicados apartir de um certo ano.

## Requisitos

- Java Development Kit (JDK) 21
- Spring Boot 3.3.3
- MongoDB 7.0.8
- Apache Maven

## Instalação

Siga o passo a passo abaixo para configurar o projeto em seu ambiente de desenvolvimento:

1. **Clone o repositório**

   ```bash
   git clone https://github.com/gilSFranco/gilmar-soares-estudos.git
   cd gilmar-soares-estudos
   ```

## Testando o projeto

Abaixo estarei disponibilizando um json para realização de testes com a aplicação:

1. **Cadastrando**

   ```json
      {
         "title": "1984",
         "author": "George Orwell",
         "year": 1949,
         "gender": "Ficção Científica"
      },
      {
         "title": "Dom Casmurro",
         "author": "Machado de Assis",
         "year": 1899,
         "gender": "Romance"
      },
      {
         "title": "The Lord of the Rings",
         "author": "J.R.R. Tolkien",
         "year": 1954,
         "gender": "Fantasia"
      },
      {
         "title": "Animal Farm",
         "author": "George Orwell",
         "year": 1945,
         "gender": "Fábuala"
      }
   ```

### Estrutura de Projeto

 * `src/main/java`: Código-fonte principal do projeto
 * `pom.xml`: Arquivo de configuração do Maven
 * `src/main/resources/application.properties`: Arquivo de configuração de conexão do banco de dados (MongoDB)

 ### Contato

 Para dúvidas ou problemas, entre em contato com:

 * Nome: Gilmar Soares Franco
 * Email: gilmarsoaresfranco@gmail.com
 * GitHub: [github.com/gilSFranco ](https://github.com/gilSFranco)