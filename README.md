# Manual de Jogos

Projeto em Java que simula uma plataforma de distribuição de jogos digitais, com catálogo, usuários, carrinho de compras, biblioteca, favoritos e regras específicas para jogos e DLCs.

O projeto foi desenvolvido de forma incremental com foco em Programação Orientada a Objetos, regras de negócio, Collections, Streams, tratamento de exceções e testes automatizados com JUnit 5.

---

## Sobre o projeto

O `ManualDeJogos` começou como um projeto para praticar fundamentos de Java e evoluiu gradualmente com novas funcionalidades e refatorações.

Atualmente, o sistema permite gerenciar produtos digitais e usuários, realizar compras através de um carrinho e validar diferentes regras antes de alterar o estado da aplicação.

Um dos principais objetivos durante o desenvolvimento foi evitar concentrar responsabilidades em uma única classe.

Por isso:

- `Loja` gerencia catálogo, produtos e buscas;
- `Usuario` gerencia saldo, carrinho, biblioteca, favoritos e fluxo de compra;
- `ProdutoDigital` fornece a abstração comum para os produtos;
- `Jogo` e `DLC` possuem comportamentos específicos;
- exceções próprias representam falhas nas regras de negócio.

---

## Principais funcionalidades

### Catálogo

A loja mantém seus produtos utilizando:

```java
Map<Integer, ProdutoDigital>
```

O ID é utilizado como chave e o produto como valor.

O catálogo permite:

- adicionar e remover produtos;
- buscar por ID;
- buscar por nome;
- buscar por gênero;
- buscar por faixa de preço;
- encontrar produtos com o menor preço;
- considerar empates entre produtos com o mesmo menor preço;
- buscar jogos com maior classificação indicativa;
- alterar informações de produtos;
- exibir produtos e seus respectivos IDs.

### Usuário

Cada usuário possui:

```java
double saldo;
List<ProdutoDigital> carrinho;
List<ProdutoDigital> biblioteca;
Set<ProdutoDigital> produtosFavoritos;
```

O usuário pode:

- adicionar saldo;
- adicionar e remover produtos do carrinho;
- calcular o valor total da compra;
- finalizar compras;
- consultar produtos adquiridos;
- favoritar e desfavoritar produtos.

### Carrinho de compras

As compras são realizadas através do carrinho.

Antes de alterar saldo ou biblioteca, todos os produtos são validados.

```java
for (ProdutoDigital produto : produtos) {
    validarProdutoParaCompra(produto, produtos);
}
```

Somente depois das validações o valor total é calculado e a compra é efetivada.

```java
saldo -= total;
biblioteca.addAll(produtos);
```

Isso evita que uma compra seja concluída parcialmente caso alguma regra falhe.

Após uma compra válida, o carrinho é esvaziado.

---

## Regras de negócio

### Saldo

O usuário precisa possuir saldo suficiente para pagar o valor total da compra.

Caso contrário:

```text
SaldoInsuficienteException
```

### Classificação indicativa

Produtos que implementam `RestricaoEtaria` são validados de acordo com a idade do usuário.

Caso a idade seja insuficiente:

```text
IdadeInsuficienteException
```

### Compra de DLC

Uma DLC depende de um jogo base.

Ela pode ser comprada quando:

- o usuário já possui o jogo base na biblioteca; ou
- o jogo base está sendo comprado no mesmo carrinho.

Caso nenhuma condição seja atendida:

```text
JogoBaseNaoEncontradoException
```

### Descontos

Cada `Jogo` possui sua própria taxa de desconto.

```java
double desconto =
        (getTaxaDesconto() / 100) * getPrecoBase();
```

A DLC utiliza metade da taxa de desconto de seu jogo base.

```java
double descontoDlc =
        jogoBase.getTaxaDesconto() / 2;
```

Dessa forma, cada tipo de produto mantém sua própria implementação de:

```java
calcularPrecoFinal()
```

---

## Estrutura e decisões de design

### ProdutoDigital

Classe abstrata responsável pelos atributos e comportamentos compartilhados entre os produtos.

```text
ProdutoDigital
├── Jogo
└── DLC
```

O cálculo de preço final é definido de forma polimórfica pelas subclasses.

### Jogo

Representa um jogo disponível no catálogo.

Possui informações como:

- gênero;
- classificação indicativa;
- multiplayer;
- taxa de desconto;
- avaliação.

Implementa:

```java
RestricaoEtaria
Avaliavel
```

### DLC

Representa um conteúdo adicional relacionado a um `Jogo`.

Além de possuir seu próprio cálculo de preço, participa de uma regra de negócio que exige a existência do jogo base durante a compra.

### Usuario

Centraliza o estado e os comportamentos relacionados ao usuário:

```text
saldo
carrinho
biblioteca
favoritos
validações de compra
finalização da compra
```

A lógica de compra foi centralizada nessa classe após uma refatoração para evitar duplicação e reduzir o acoplamento com `Loja`.

### Loja

Mantém o catálogo e os usuários cadastrados.

Sua responsabilidade está concentrada principalmente em:

```text
cadastro
busca
filtros
alterações
remoção
consulta do catálogo
```

A classe não executa diretamente o fluxo de compra.

---

## Streams e Collections

O projeto utiliza diferentes estruturas do Java Collections Framework:

```java
List<ProdutoDigital>
Set<ProdutoDigital>
Map<Integer, ProdutoDigital>
```

Também utiliza a Stream API em operações de consulta e cálculo.

Exemplo de filtro por preço:

```java
return catalogo.values()
        .stream()
        .filter(produto ->
                produto.calcularPrecoFinal() >= precoMinimo
                        && produto.calcularPrecoFinal() <= precoMaximo
        )
        .toList();
```

Busca pelo menor preço:

```java
ProdutoDigital produto = catalogo.values()
        .stream()
        .min(Comparator.comparingDouble(
                produto -> produto.calcularPrecoFinal()
        ))
        .orElseThrow(
                ProdutoNaoEncontradoException::new
        );
```

Cálculo do total do carrinho:

```java
return produtos.stream()
        .map(produto -> produto.calcularPrecoFinal())
        .reduce(0.0, (soma, valor) -> soma + valor);
```

---

## Testes automatizados

O projeto utiliza **JUnit 5** para testar tanto operações básicas quanto regras de negócio.

### UsuarioTest

Possui cenários para:

- adição de saldo;
- favoritos;
- cálculo do total do carrinho;
- remoção de produto do carrinho;
- finalização de compra;
- esvaziamento do carrinho;
- saldo insuficiente;
- idade insuficiente;
- DLC sem jogo base;
- DLC e jogo base no mesmo carrinho;
- DLC quando o jogo base já pertence ao usuário;
- garantia de que compras inválidas não alterem saldo nem biblioteca.

### LojaTest

Possui cenários para:

- busca por ID;
- busca por nome;
- produto inexistente;
- remoção de produto;
- busca por gênero;
- busca por faixa de preço;
- alteração de nome;
- busca pelo menor preço;
- empate entre produtos no menor preço;
- catálogo vazio.

### JogoTest

Possui testes para:

- avaliação válida;
- rejeição de avaliação inválida.

Entre os recursos utilizados estão:

```java
@Test
@BeforeEach
assertEquals()
assertTrue()
assertFalse()
assertThrows()
assertAll()
```

---

## Tecnologias e conceitos utilizados

- Java
- JUnit 5
- Programação Orientada a Objetos
- classes abstratas
- interfaces
- herança
- polimorfismo
- encapsulamento
- associação entre objetos
- enums
- Collections Framework
- `List`
- `Set`
- `Map`
- Generics
- Stream API
- expressões lambda
- `filter`
- `map`
- `reduce`
- `min`
- `Comparator`
- `Optional`
- `orElseThrow`
- pattern matching com `instanceof`
- exceções personalizadas
- refatoração
- testes automatizados
- Git
- GitHub

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

## Como executar

Clone o repositório:

```bash
git clone https://github.com/minlaura/ManualDeJogos.git
```

Abra o projeto utilizando IntelliJ IDEA ou outra IDE compatível com Java.

Para executar a aplicação:

```text
src/manualdejogos/Main.java
```

Os testes estão disponíveis em:

```text
test/manualdejogos/model
```

---

## Próximos passos

O projeto continua em evolução.

Os próximos objetivos incluem:

- adoção do Maven para gerenciamento do projeto e dependências;
- aprofundamento no uso de lambdas e method references;
- evolução da organização interna do código;
- implementação de histórico de compras;
- persistência de dados;
- integração com banco de dados;
- evolução gradual para uma aplicação back-end Java.

---

## Objetivo

O `ManualDeJogos` é um projeto de estudo utilizado para aplicar conceitos de Java em uma aplicação que evolui continuamente.

Além de implementar novas funcionalidades, o desenvolvimento inclui revisão de código existente, criação de testes, identificação de responsabilidades entre classes e refatoração de decisões anteriores conforme novos conceitos são aprendidos.