# Manual de Jogos

Sistema de gerenciamento de jogos digitais desenvolvido em Java como projeto de estudo de Programação Orientada a Objetos.

A aplicação simula uma pequena plataforma de distribuição digital, permitindo trabalhar com jogos, DLCs, usuários, compras, biblioteca, favoritos e catálogo de produtos.

O projeto é desenvolvido de forma incremental, adicionando novas funcionalidades conforme novos conceitos de Java são estudados.

---

## Funcionalidades

### Usuário

- Cadastro de usuários
- Alteração de nome
- Adição de saldo
- Compra de produtos digitais
- Biblioteca de produtos adquiridos
- Verificação de produtos já comprados
- Favoritar e desfavoritar produtos
- Exibição da biblioteca
- Exibição dos favoritos

### Produtos digitais

O sistema utiliza a classe abstrata `ProdutoDigital` como base para diferentes tipos de produtos.

Atualmente existem:

- `Jogo`
- `DLC`

Cada produto possui seu próprio cálculo de preço final.

### Jogos

Os jogos possuem:

- classificação indicativa
- gênero
- suporte a multiplayer
- taxa de desconto
- avaliação de 1 a 5

A interface `RestricaoEtaria` define o comportamento relacionado à classificação indicativa.

A interface `Avaliavel` define o comportamento de avaliação dos jogos.

### DLCs

Uma DLC está associada a um `Jogo` base.

Para comprar uma DLC, o usuário precisa possuir o jogo correspondente em sua biblioteca.

O preço final da DLC também é calculado a partir das informações do jogo base.

### Loja

A classe `Loja` é responsável pelo gerenciamento do catálogo e das vendas.

Entre as operações disponíveis estão:

- adicionar produtos
- remover produtos
- cadastrar usuários
- buscar produto por ID
- buscar produto por nome
- buscar produtos por gênero
- incluir DLCs na busca utilizando o gênero do jogo base
- buscar produtos por faixa de preço
- buscar jogos por limite de preço
- buscar os jogos de menor preço
- buscar jogos com maior classificação indicativa
- realizar vendas
- exibir catálogo
- exibir IDs e nomes dos produtos

---

## Regras de negócio

Durante uma compra, algumas regras precisam ser respeitadas:

- o usuário deve possuir saldo suficiente;
- a classificação indicativa do produto deve ser respeitada;
- uma DLC só pode ser comprada se o usuário possuir o jogo base;
- após uma compra válida, o produto é adicionado à biblioteca;
- situações inválidas são representadas por exceções específicas.

---

## Collections utilizadas

O projeto utiliza diferentes estruturas do Java Collections Framework.

### List

Utilizada para:

- biblioteca do usuário;
- usuários cadastrados;
- resultados de buscas.

### Set

Utilizado para armazenar produtos favoritos.

Como `Set` não permite elementos duplicados, um mesmo produto não é adicionado várias vezes aos favoritos.

### Map

O catálogo da loja utiliza:

```java
Map<Integer, ProdutoDigital>
```

O ID do produto funciona como chave e o próprio produto como valor.

Isso permite operações como:

```java
catalogo.put(produto.getId(), produto);
catalogo.get(id);
catalogo.remove(id);
catalogo.values();
catalogo.keySet();
catalogo.entrySet();
```

---

## Interfaces

### RestricaoEtaria

Define que um produto com restrição de idade precisa informar sua classificação indicativa.

```java
public interface RestricaoEtaria {

    int getIdadeRecomendada();
}
```

A classe `Jogo` implementa essa interface.

### Avaliavel

Define o comportamento de objetos que podem receber uma avaliação.

```java
public interface Avaliavel {

    boolean avaliar(int nota);
}
```

Atualmente, `Jogo` implementa essa interface e aceita avaliações entre 1 e 5.

---

## Exceções personalizadas

O projeto possui exceções próprias para representar falhas nas regras de negócio:

```text
ProdutoNaoEncontradoException
SaldoInsuficienteException
IdadeInsuficienteException
JogoBaseNaoEncontradoException
```

Elas são utilizadas junto com:

```java
throw
throws
try
catch
```

---

## Testes

O projeto utiliza JUnit 5 para testar as principais regras da aplicação.

Atualmente existem testes para:

- busca de produtos por ID;
- produto inexistente;
- busca por nome;
- busca por gênero;
- busca por faixa de preço;
- remoção de produtos;
- compras;
- saldo insuficiente;
- idade insuficiente;
- biblioteca do usuário;
- favoritos;
- adição de saldo;
- avaliações válidas e inválidas.

Também são utilizados recursos como:

```java
@Test
@BeforeEach

assertEquals()
assertTrue()
assertFalse()
assertThrows()
```

Os testes ficam separados da classe `Main`, permitindo que o `Main` seja utilizado apenas como demonstração do funcionamento da aplicação.

---

## Conceitos praticados

Durante o desenvolvimento do projeto estão sendo aplicados conceitos como:

- classes e objetos
- encapsulamento
- herança
- polimorfismo
- abstração
- classes abstratas
- métodos abstratos
- interfaces
- sobrescrita de métodos
- `enum`
- associação entre objetos
- `List`
- `Set`
- `Map`
- Generics em Collections
- `for-each`
- `instanceof`
- pattern matching com `instanceof`
- exceções personalizadas
- `throw` e `throws`
- `try` e `catch`
- `toString`
- JUnit
- `@BeforeEach`
- reutilização de código
- separação de responsabilidades

---

## Estrutura do projeto

```text
ManualDeJogos
│
├── src
│   └── manualdejogos
│       │
│       ├── exception
│       │   ├── IdadeInsuficienteException.java
│       │   ├── JogoBaseNaoEncontradoException.java
│       │   ├── ProdutoNaoEncontradoException.java
│       │   └── SaldoInsuficienteException.java
│       │
│       ├── model
│       │   ├── Avaliavel.java
│       │   ├── DLC.java
│       │   ├── Genero.java
│       │   ├── Jogo.java
│       │   ├── Loja.java
│       │   ├── ProdutoDigital.java
│       │   ├── RestricaoEtaria.java
│       │   └── Usuario.java
│       │
│       ├── DadosIniciais.java
│       └── Main.java
│
└── test
    └── manualdejogos
        └── model
            ├── JogoTest.java
            ├── LojaTest.java
            └── UsuarioTest.java
```

---

## Principais classes

### ProdutoDigital

Classe abstrata que contém os dados e comportamentos comuns aos produtos da plataforma.

`Jogo` e `DLC` herdam dessa classe.

### Jogo

Representa um jogo disponível no catálogo.

Além dos dados herdados de `ProdutoDigital`, possui gênero, idade recomendada, multiplayer, desconto e avaliação.

### DLC

Representa um conteúdo adicional associado a um jogo base.

Possui regras próprias para cálculo de preço e exige que o usuário tenha o jogo correspondente antes da compra.

### Usuario

Gerencia os dados do usuário, saldo, biblioteca e favoritos.

Também é responsável pelas validações realizadas durante uma compra.

### Loja

Mantém o catálogo utilizando um `Map<Integer, ProdutoDigital>` e centraliza operações de cadastro, busca, remoção e venda.

### DadosIniciais

Responsável pela criação dos produtos e dados utilizados inicialmente pela aplicação.

Isso evita concentrar a configuração do sistema dentro da classe `Main`.

### Main

Utilizada como demonstração simples do funcionamento da aplicação.

As verificações das regras de negócio são realizadas principalmente pelos testes JUnit.

---

## Tecnologias

- Java
- JUnit 5
- Java Collections Framework
- IntelliJ IDEA
- Git
- GitHub

---

## Como executar

Clone o repositório:

```bash
git clone https://github.com/minlaura/ManualDeJogos.git
```

Abra o projeto no IntelliJ IDEA ou em outra IDE compatível com Java.

Execute:

```text
Main.java
```

Os testes podem ser executados separadamente através das classes:

```text
JogoTest
LojaTest
UsuarioTest
```

---

## Próximos passos

- ampliar a cobertura de testes;
- adicionar novos filtros ao catálogo;
- melhorar a organização dos pacotes;
- continuar refatorando responsabilidades das classes;
- implementar histórico de compras;
- estudar persistência de dados;
- futuramente integrar o projeto com banco de dados.

---

## Objetivo

Este projeto acompanha meu aprendizado em Java através da construção e evolução de uma aplicação real.

A proposta é aplicar cada conceito estudado diretamente no código, revisar implementações anteriores e melhorar gradualmente a estrutura do projeto conforme novos conhecimentos são adquiridos.