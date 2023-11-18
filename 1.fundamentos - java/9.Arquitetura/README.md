# Arquitetura e padrões de projeto

### MVC - Model, View, Controller
- divisão de pastas e arquivos e suas funções
    - Model: Mapeamento das tabelas ou objetos do banco de dados
        - modelos, entidades: dados completos da tabela - identidades, senhas
        - DTO (Data Transfer Objects): dados parciais - evitando retornar um objeto com informações privadas como senhas e identidades
    - View: 
        - services: utilização da seleção e tratamento de erros
        - repositories: mapeamento de seleções - getIdadeByNome(nome)
        - casos de uso
    - Controller: exposição e mapeamento das rotas de GET, POST, PUT, PATCH, DELETE, UPDATE