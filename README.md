cat > README.md << 'EOF'
# 🛒 MercadoFácil

Sistema de gestão para pequenos mercados desenvolvido com Java e Spring Boot.

## 📸 Telas do Sistema

> Produtos, PDV e Relatório de Vendas

## 🚀 Funcionalidades

- ✅ Cadastro e gerenciamento de produtos
- ✅ Controle de estoque com alerta de estoque baixo
- ✅ PDV (Ponto de Venda) com carrinho de compras
- ✅ Baixa automática de estoque ao finalizar venda
- ✅ Suporte a múltiplas formas de pagamento (Dinheiro, Cartão, PIX)
- ✅ Relatório de vendas do dia com total arrecadado
- ✅ Proteção contra deleção de produtos com vendas vinculadas

## 🛠️ Tecnologias

| Tecnologia | Uso |
|---|---|
| Java 21 | Linguagem principal |
| Spring Boot 3 | Framework backend |
| Spring Data JPA | Persistência de dados |
| Thymeleaf | Templates HTML |
| MySQL 8 | Banco de dados |
| Bootstrap 5 | Estilização |
| Maven | Gerenciamento de dependências |

## 🗄️ Modelagem do Banco
```
product          sale              sale_item
--------         --------          ----------
id               id                id
name             created_at        sale_id (FK)
barcode          total             product_id (FK)
price            payment_method    quantity
cost                               unit_price
stock_quantity                     subtotal
minimum_stock
category
```

## ▶️ Como rodar localmente

### Pré-requisitos
- Java 21
- MySQL 8
- Maven

### Passo a passo
```bash
# 1. Clone o repositório
git clone https://github.com/Kaua19/MercadoFacil.git

# 2. Entre na pasta
cd MercadoFacil

# 3. Configure o banco de dados
cp src/main/resources/application-example.properties src/main/resources/application.properties
# Edite o application.properties com sua senha do MySQL

# 4. Crie o banco
mysql -u root -p -e "CREATE DATABASE mercadofacil;"

# 5. Rode o projeto
./mvnw spring-boot:run
```

### 6. Acesse no navegador

| Tela | URL |
|---|---|
| Produtos | http://localhost:8080/products |
| PDV | http://localhost:8080/pdv |
| Relatório | http://localhost:8080/sales |

## 📁 Estrutura do Projeto
```
src/main/java/com/kaua8/mercadoFacil/
├── controller/
│   ├── ProductController.java
│   ├── SaleController.java
│   └── SalesController.java
├── model/
│   ├── Product.java
│   ├── Sale.java
│   └── SaleItem.java
├── repository/
│   ├── ProductRepository.java
│   ├── SaleRepository.java
│   └── SaleItemRepository.java
└── service/
    ├── ProductService.java
    └── SaleService.java
```

## 👨‍💻 Autor

Feito por **Kaua** — estudante de Java e Spring Boot.

[![GitHub](https://img.shields.io/badge/GitHub-Kaua19-black?logo=github)](https://github.com/Kaua19)
EOF
