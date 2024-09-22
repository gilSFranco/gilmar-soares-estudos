# Sistema de Gestão de Estacionamento

Estou no programa de bolsas da compass UOL, na 7° semana. Nesta semana foi proposto
que fizéssemos uma API que consumisse um JSON contendo um nome e uma idade. A idade
deveria ser validada na camada de serviço, verificando se era maior, igual ou menor
que 18 anos. Sendo maior ou igual, a "entrada" seria permitida, abaixo disso a entrada
é negada. A resposta de validação seria devolvida no corpo da resposta da requisição,
com a mensagem correspondente a validação.

## Requisitos feitos na atividade

1. **Receber um JSON contendo nome e idade**
2. **Verificar se a idade é maior ou igual a 18 anos, e retornar mensagem personalizada baseada na idade**
3. **Foi utilizado a dependência `spring-boot-starter-web`.**
4. **As responsabilidades estão bem divididas.**
   - Criar um controller para receber a requisição
   - A lógica de negócios está contida em um service
5. **O corpo da requisição está no formato JSON**

## Estrutura do projeto

A estrutura foi criada conforme o sugerido:

1. **`Controller`: Responsável por receber a requisição e enviar para o `service`.**
1. **`Service`: Responsável pela validação da idade e retorno da mensagem.**
1. **DTO para receber a requisição.**
1. **DTO para responder a requisição.**

## Incrementos feitos ao projeto

Adições feitas pensando na tratativa de erro na validação de entrada de dados
do usuário. As classes criadas, em conjunto, tem o papel de customizar, capturar e lidar
com as exceções geradas apartir da validação feita nos campos da classe DTO.

1. **Lidando com erros de validação**
   - A classe ErrorMessage foi criada.
   - A classe ApiExceptionHandler foi criada.

## Requisitos

- Java Development Kit (JDK) 21
- Spring Boot 3.3.3
- Apache Maven

## Instalação

Siga o passo a passo abaixo para configurar o projeto em seu ambiente de desenvolvimento:

1. **Clone o repositório**

   ```bash
   git clone https://github.com/gilSFranco/gilmar-soares-estudos.git
   cd gilmar-soares-estudos
   ```

### Estrutura de Projeto

 * `src/main/java`: Código-fonte principal do projeto
 * `pom.xml`: Arquivo de configuração do Maven

 ### Contato

 Para dúvidas ou problemas, entre em contato com:

 * Nome: Gilmar Soares Franco
 * Email: gilmarsoaresfranco@gmail.com
 * GitHub: [github.com/gilSFranco ](https://github.com/gilSFranco)