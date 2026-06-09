# Freitas-Brandao-Back

Backend Spring Boot da Casa de Passagem Freitas Brandao.

## Variaveis de ambiente

Antes de executar o backend, configure a conexao com o banco e as origens liberadas no CORS.

No PowerShell:

```powershell
$env:SPRING_DATASOURCE_URL="jdbc:postgresql://SEU_HOST:5432/postgres?sslmode=require"
$env:SPRING_DATASOURCE_USERNAME="SEU_USUARIO"
$env:SPRING_DATASOURCE_PASSWORD="SUA_SENHA"
$env:APP_CORS_ALLOWED_ORIGINS="http://localhost:5173,http://127.0.0.1:5173"
```

Para deploy, use a URL real do frontend:

```text
APP_CORS_ALLOWED_ORIGINS=https://url-do-front
```

## Como executar

```powershell
.\mvnw.cmd spring-boot:run
```

O backend sobe em:

```text
http://localhost:8080
```

## Observacao de seguranca

As credenciais do banco nao devem ficar salvas no codigo. Configure-as somente por variaveis de ambiente no computador local e na plataforma de deploy.
