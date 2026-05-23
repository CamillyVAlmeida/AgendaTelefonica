# Agenda Telefonica (Java + MySQL)

Aplicacao de console em Java que implementa o **CRUD completo** (Create, Read,
Update, Delete) de contatos telefonicos, com persistencia em **MySQL** via
JDBC. Trabalho individual da disciplina (PUC).

---

## 1. Estrutura do projeto

```
AgendaTelefonica/
├── pom.xml                                  # Configuracao Maven (driver JDBC + shade)
├── README.md
├── database/
│   ├── schema.sql                           # DDL para criar o banco e a tabela
│   └── dump_agenda_telefonica.sql           # Dump pronto para restauracao (entregavel)
└── src/main/
    ├── java/br/com/puc/agenda/
    │   ├── modelo/
    │   │   └── Contato.java                 # POJO: nome, telefone, email (+ id)
    │   ├── dao/
    │   │   ├── ConexaoBD.java               # Le db.properties e abre Connection
    │   │   └── ContatoDAO.java              # Operacoes JDBC (PreparedStatement)
    │   ├── AgendaTelefonica.java            # Gerenciamento dos contatos (delega ao DAO)
    │   └── AgendaTeste.java                 # Menu interativo (classe main)
    └── resources/
        └── db.properties                    # Credenciais do MySQL (ajustar!)
```

### Arquitetura (camadas)

- `AgendaTeste` faz a interacao com o usuario (menu via `Scanner`).
- `AgendaTelefonica` expoe os metodos exigidos pelo enunciado (`adicionarContato`,
  `removerContato`, `buscarContato`, `listarContatos`) + `atualizarContato`
  para fechar o U do CRUD.
- `ContatoDAO` concentra todo o codigo JDBC (`PreparedStatement`,
  `try-with-resources`).
- `ConexaoBD` le `db.properties` e devolve `java.sql.Connection`.

---

## 2. Pre-requisitos

| Ferramenta            | Versao recomendada | Verificacao              |
|-----------------------|--------------------|--------------------------|
| JDK                   | 17 ou superior     | `java -version`          |
| Apache Maven          | 3.8+               | `mvn -version`           |
| MySQL Server          | 8.x                | `mysql --version`        |
| MySQL Workbench       | (opcional)         | -                        |

> O Cursor pode ser usado como IDE. Recomenda-se instalar o
> **Extension Pack for Java** (Microsoft) e o **Maven for Java**.

---

## 3. Configuracao do banco de dados

### 3.1 Opcao A - Criar do zero (usar `schema.sql`)

```powershell
mysql -u root -p < database\schema.sql
```

### 3.2 Opcao B - Restaurar a partir do dump ja populado

```powershell
mysql -u root -p < database\dump_agenda_telefonica.sql
```

### 3.3 Ajustar credenciais

O arquivo `src/main/resources/db.properties` **nao e versionado no Git**
(esta no `.gitignore`) para evitar expor senhas. Voce precisa cria-lo a
partir do modelo `db.properties.example`:

```powershell
Copy-Item src\main\resources\db.properties.example src\main\resources\db.properties
```

Em seguida abra `src/main/resources/db.properties` e ajuste a senha:

```properties
db.url=jdbc:mysql://localhost:3306/agenda_telefonica?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=UTF-8
db.user=root
db.password=SUA_SENHA
```

---

## 4. Como executar

### 4.1 Via Maven (recomendado durante o desenvolvimento)

```powershell
mvn clean compile
mvn exec:java
```

### 4.2 Via fat-jar (recomendado para a demonstracao em video)

```powershell
mvn clean package
java -jar target\agenda-telefonica.jar
```

O `maven-shade-plugin` ja empacota o driver MySQL dentro do JAR, entao basta
o JDK instalado para executar.

---

## 5. Menu da aplicacao

```
====================================
    AGENDA TELEFONICA - PUC
====================================

--------- MENU ---------
1 - Adicionar novo contato
2 - Remover contato
3 - Buscar contato pelo nome
4 - Listar todos os contatos
5 - Atualizar contato
6 - Sair
------------------------
```

> O enunciado lista 5 opcoes no item III, mas o objetivo geral (secao 1) exige
> **CRUD completo**. Por isso o menu foi estendido para 6 opcoes, mantendo
> integralmente as funcionalidades pedidas e acrescentando "Atualizar
> contato" para cobrir o `U` do CRUD.

---

## 6. Gerar um novo dump apos popular o banco

Depois de inserir contatos via aplicacao, regere o dump para incluir no
entregavel:

```powershell
mysqldump -u root -p --databases agenda_telefonica > database\dump_agenda_telefonica.sql
```

---

## 7. Roteiro sugerido para o video (Win + G)

1. Mostrar a estrutura do projeto no Cursor.
2. Abrir `database/schema.sql` e explicar a tabela `contato`.
3. Mostrar o banco vazio no MySQL Workbench (`SELECT * FROM contato;`).
4. Executar a aplicacao (`java -jar target\agenda-telefonica.jar`).
5. Demonstrar cada opcao na ordem:
   1. **Adicionar** 2 ou 3 contatos.
   2. **Listar** para ver o resultado.
   3. **Buscar** um contato pelo nome.
   4. **Atualizar** o telefone/e-mail de um contato.
   5. **Remover** um contato.
   6. **Listar** novamente para confirmar.
   7. **Sair**.
6. Voltar ao Workbench e fazer `SELECT * FROM contato;` para comprovar a
   persistencia real no banco.
7. Mostrar o comando `mysqldump` gerando o `.sql` final.

---

## 8. Entregavel

Compactar em `.zip`:

- A pasta `AgendaTelefonica/` completa (codigo + `pom.xml` + `README.md`).
- A pasta `database/` (com `schema.sql` e `dump_agenda_telefonica.sql`).
- O video `.mp4` da demonstracao.
