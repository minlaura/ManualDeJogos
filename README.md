# 🎮 Manual de Jogos

Sistema de gerenciamento de jogos digitais desenvolvido em **Java**, com foco na aplicação prática dos principais conceitos de **Programação Orientada a Objetos (POO)**.

O projeto simula uma plataforma de distribuição digital, permitindo o gerenciamento de usuários, jogos, DLCs, compras, biblioteca pessoal, favoritos e catálogo de produtos.

---

## Sobre o projeto

O **Manual de Jogos** foi desenvolvido como projeto de estudo para consolidar conceitos fundamentais da linguagem Java e da Programação Orientada a Objetos.

A aplicação possui regras de negócio relacionadas à compra de produtos digitais, como:

* classificação indicativa;
* saldo disponível;
* necessidade de possuir o jogo base para adquirir uma DLC;
* busca e gerenciamento de produtos;
* tratamento de situações inválidas através de exceções personalizadas.

O projeto utiliza coleções Java para armazenar produtos, usuários, biblioteca e favoritos.

---

## Funcionalidades

### Usuários

* Cadastro de usuários
* Alteração de nome
* Adição de saldo
* Compra de produtos digitais
* Biblioteca pessoal
* Verificação de produtos adquiridos
* Favoritar produtos
* Desfavoritar produtos
* Exibição da biblioteca
* Exibição dos produtos favoritos

### Produtos digitais

O sistema trabalha com diferentes tipos de produtos através da classe abstrata `ProdutoDigital`.

Atualmente estão implementados:

* `Jogo`
* `DLC`

Cada tipo possui atributos e regras específicas.

### Loja

* Cadastro de produtos
* Remoção de produtos
* Cadastro de usuários
* Busca de produto por ID
* Busca de produto por nome
* Busca de jogos por gênero
* Busca de produtos por faixa de preço
* Realização de vendas
* Gerenciamento do catálogo

---

## Regras de negócio

* O usuário deve possuir saldo suficiente para realizar uma compra.
* Jogos respeitam sua classificação indicativa.
* DLCs exigem que o usuário possua o jogo base correspondente.
* Produtos adquiridos são adicionados à biblioteca do usuário.
* Situações inválidas são tratadas através de exceções específicas.

---

## Conceitos aplicados

* Classes e objetos
* Encapsulamento
* Herança
* Polimorfismo
* Classes abstratas
* Métodos abstratos
* Sobrescrita de métodos
* Enum
* Associação entre objetos
* Collections
* `List`
* `ArrayList`
* `for-each`
* Tratamento de exceções
* Exceções personalizadas
* `instanceof`
* `toString`
* Reutilização de código
* Separação de responsabilidades

---

## Estrutura do projeto

```text
manualdejogos
│
├── model
│   ├── ProdutoDigital.java
│   ├── Jogo.java
│   ├── DLC.java
│   ├── Usuario.java
│   ├── Loja.java
│   └── Genero.java
│
├── exception
│   ├── ProdutoNaoEncontradoException.java
│   ├── SaldoInsuficienteException.java
│   ├── IdadeInsuficienteException.java
│   └── JogoBaseNaoEncontradoException.java
│
├── DadosIniciais.java
│
└── Main.java
```

---

## Principais classes

### `ProdutoDigital`

Classe abstrata responsável pelos atributos e comportamentos comuns aos produtos digitais.

É utilizada como classe base para `Jogo` e `DLC`.

### `Jogo`

Representa um jogo disponível na plataforma.

Possui informações como nome, preço, ID, idade recomendada, gênero, multiplayer e desconto.

### `DLC`

Representa um conteúdo adicional relacionado a um jogo existente.

Mantém uma referência para o jogo base e possui regras específicas para sua compra.

### `Usuario`

Responsável pelos dados e operações do usuário, incluindo saldo, biblioteca, compras e produtos favoritos.

### `Loja`

Responsável pelo gerenciamento do catálogo e dos usuários.

Centraliza operações de busca, cadastro, remoção e venda de produtos.

### `DadosIniciais`

Responsável pela criação dos dados iniciais utilizados pela aplicação, evitando concentrar toda a configuração do sistema dentro da classe `Main`.

---

## Exceções personalizadas

O projeto utiliza exceções próprias para representar situações relacionadas às regras de negócio:

```text
ProdutoNaoEncontradoException
SaldoInsuficienteException
IdadeInsuficienteException
JogoBaseNaoEncontradoException
```

---

## Tecnologias utilizadas

* Java
* IntelliJ IDEA
* Java Collections Framework
* Git
* GitHub

---

## Como executar

1. Clone este repositório:

```bash
git clone URL_DO_REPOSITORIO
```

2. Abra o projeto no IntelliJ IDEA ou em outra IDE compatível com Java.

3. Execute a classe:

```text
Main.java
```

---

## Próximos passos

* Implementar testes automatizados com JUnit
* Ampliar os testes das regras de negócio
* Ordenar produtos por preço
* Criar filtros adicionais para jogos e DLCs
* Implementar histórico de compras
* Adicionar persistência de dados
* Melhorar a organização em pacotes
* Futuramente integrar com banco de dados

---

## Objetivo de aprendizado

O principal objetivo deste projeto é acompanhar minha evolução em Java através da construção gradual de uma aplicação orientada a objetos.

Cada nova funcionalidade é utilizada para praticar e consolidar conceitos antes de avançar para tópicos mais complexos.
