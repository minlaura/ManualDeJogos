# 🎮 Manual de Jogos

> Plataforma digital desenvolvida em **Java** utilizando **Programação Orientada a Objetos (POO)** para simular a compra e o gerenciamento de jogos digitais.

## Sobre o projeto

O **Manual de Jogos** simula o funcionamento de uma plataforma de distribuição de jogos digitais, inspirada em serviços como Steam e Epic Games.

O sistema permite cadastrar jogos e DLCs, gerenciar usuários, realizar compras e controlar regras de negócio, como idade mínima, saldo disponível e dependência entre jogos e DLCs.

O objetivo do projeto é aplicar conceitos fundamentais de **Programação Orientada a Objetos**, coleções (`ArrayList`) e tratamento de exceções personalizadas.

---

## Funcionalidades

### 👤 Usuários

- Cadastro de usuários
- Adição de saldo
- Biblioteca pessoal de jogos
- Compra de jogos e DLCs
- Exibição da biblioteca

### 🎮 Produtos

- Jogos
- DLCs

### 🏪 Plataforma

- Adicionar produtos ao catálogo
- Buscar produto por ID
- Buscar produto por nome
- Buscar produtos por gênero
- Exibir catálogo
- Remover produtos
- Realizar vendas

---

## 📋 Regras de negócio

✔️ O usuário deve possuir saldo suficiente para realizar uma compra.

✔️ Jogos respeitam classificação indicativa.

✔️ DLCs só podem ser adquiridas caso o usuário possua o jogo base.

✔️ Produtos inexistentes geram exceções específicas.

---

## 📚 Conceitos de POO aplicados

- Classes e Objetos
- Encapsulamento
- Herança
- Polimorfismo
- Classes Abstratas
- Enum
- Sobrescrita de métodos
- Collections (`ArrayList`)
- Tratamento de exceções
- Reutilização de código

---

## 📂 Estrutura do projeto

```text
manualdejogos
│
├── model
│   ├── ProdutoDigital
│   ├── Jogo
│   ├── DLC
│   ├── Usuario
│   ├── Loja
│   └── Genero
│
├── exception
│   ├── ProdutoNaoEncontradoException
│   ├── SaldoInsuficienteException
│   ├── IdadeInsuficienteException
│   └── JogoBaseNaoEncontradoException
│
└── Main
```

---

## 🛠 Tecnologias utilizadas

- Java
- IntelliJ IDEA
- Programação Orientada a Objetos
- Java Collections Framework

---

## ▶️ Como executar

1. Clone este repositório.
2. Abra o projeto na IDE de sua preferência.
3. Execute a classe `Main.java`.

---

## Funcionalidades implementadas

- [x] Sistema de usuários
- [x] Sistema de compras
- [x] Biblioteca de jogos
- [x] Busca por ID
- [x] Busca por nome
- [x] Busca por gênero
- [x] Remoção de produtos
- [x] Catálogo da loja
- [x] Venda por ID
- [x] Exceções personalizadas

---

## Melhorias futuras

- [ ] Buscar apenas jogos
- [ ] Buscar apenas DLCs
- [ ] Contagem de produtos
- [ ] Ordenação por preço
- [ ] Persistência em arquivos
- [ ] Interface gráfica
- [ ] Sistema de login
- [ ] Histórico de compras

