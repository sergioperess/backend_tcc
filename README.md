# Controle Financeiro Backend

Este projeto é um sistema backend para gerenciar e controlar despesas e receitas financeiras. Ele fornece uma API RESTful construída com Spring Boot, permitindo a criação, atualização, exclusão e visualização de transações financeiras. Também suporta funcionalidades como categorização de gastos, planejamento orçamentário e filtros por período.

## Funcionalidades

- **Cadastro de Despesas e Receitas**: Adicione, edite e remova entradas de despesas e receitas.
- **Categorização**: As transações podem ser categorizadas por tipo (alimentação, transporte, etc.).
- **Filtros**: Permite filtrar as transações por mês, ano, categoria e tipo.
- **Planejamento Financeiro**: Função para criar planejamentos financeiros com base nos gastos.
- **API RESTful**: Fornece endpoints para todas as operações financeiras.
- **Persistência**: Utiliza um banco de dados relacional para armazenar os dados financeiros.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Data JPA** (para interação com o banco de dados)
- **Hibernate**
- **Banco de Dados**: MySQL
- **Maven** (para gerenciamento de dependências)
- **Spring Security** (opcional para autenticação de usuários)
- **Swagger** (para documentação da API)

## Instalação

1. Clone o repositório:

   ```bash
   git clone https://github.com/sergioperess/backend_tcc.git

2. Acesse o diretório do projeto:

   ```bash
   cd backend_tcc

3. Compile o projeto:

   ```bash
   mvn clean install

4. Configure o arquivo `application.properties` para conectar o banco de dados MySQL. Abra o arquivo `src/main/resources/application.properties` e insira as seguintes configurações:

   ```properties
   # Configuração da fonte de dados MySQL
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.datasource.url=jdbc:mysql://localhost:3306/test_banco2
   spring.datasource.username=root
   spring.datasource.password=password


   # Dialeto do Hibernate para MySQL
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect

   # Estratégia de atualização do banco de dados (use 'update' para ambientes de desenvolvimento)
   spring.jpa.hibernate.ddl-auto=update

   # Exibir SQL no console (opcional, útil para depuração)
   spring.jpa.show-sql=true

   # Formatador de SQL (opcional, melhora a legibilidade no console)
   spring.jpa.properties.hibernate.format_sql=true

**Como Usar**

- **`spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver`**:
    - Especifica o **driver JDBC** que o Spring Boot usará para se conectar ao banco de dados MySQL. O driver `com.mysql.cj.jdbc.Driver` faz parte da biblioteca MySQL Connector/J, que permite a comunicação entre o Spring Boot e o banco de dados MySQL via JDBC (Java Database Connectivity).

- **`spring.datasource.url=jdbc:mysql://localhost:3306/test_banco2`**:
    - Define a **URL de conexão** com o banco de dados. O formato da URL é o seguinte:
        - `jdbc:mysql://`: Indica o protocolo JDBC usado para a conexão com o MySQL.
        - `localhost`: Refere-se ao servidor onde o MySQL está rodando. Se o banco de dados estiver em outra máquina, substitua `localhost` pelo IP ou hostname do servidor.
        - `3306`: É a **porta padrão** do MySQL. Se você estiver utilizando uma porta diferente, altere conforme necessário.
        - `test_banco2`: É o **nome do banco de dados** que será utilizado pela aplicação. Certifique-se de que este banco de dados já foi criado no MySQL.

- **`spring.datasource.username=root`**:
    - Define o **nome de usuário** que o Spring Boot utilizará para acessar o banco de dados. No exemplo, `root` é o usuário administrador padrão do MySQL. Se estiver utilizando outro usuário, substitua `root` pelo nome correto.

- **`spring.datasource.password=password`**:
    - Especifica a **senha** do usuário do banco de dados configurado acima. No exemplo, a senha é `password`. Substitua essa senha pela senha real configurada no seu ambiente MySQL.


5. Execute a aplicação:

   ```bash
   mvn spring-boot:run

6. Acesse a documentação da API via Swagger em `http://localhost:8080/swagger-ui.html`.

## Como Usar

A API fornece os seguintes endpoints principais:

- **`/api/transaction`** (GET, POST, PUT, DELETE): Gerenciamento de gastos.
- **`/users`** (GET, POST, PUT, DELETE): Gerenciamento de usuários.
- **`/api/planejamentos`** (GET, POST): Criação e visualização de planejamentos financeiros.
- **`/api/gastos`** (GET): Gerenciamento de tipo de gastos.

### Exemplo de requisição para cadastrar um usuário:

```bash
POST /users
{
  "firstName": "João",
  "lastName": "Silva",
  "cpf": "865.877.740-59",
  "senha": "12345678",
  "email": "joao1@gmail.com"
} 
```

### Estrutura do Projeto

- **`src/main/java`**: Código-fonte da aplicação.
- **`src/main/resources`**: Arquivos de configuração.
- **`src/test`**: Testes unitários e de integração.

## Contribuição

Se deseja contribuir com o projeto, siga os seguintes passos:

1. Fork o repositório.

2. Crie um branch para a funcionalidade ou correção:

   ```bash
   git checkout -b minha-nova-funcionalidade
   ```

3. Faça commit das suas alterações:
    
   ```bash
   git commit -m 'Adicionar nova funcionalidade'
   ```
   
4. Envie para o branch original:
    
   ```bash
   git push origin minha-nova-funcionalidade
   ```
   
5. Abra um Pull Request.
