# CitizenTech Bragança - API (GS 2026.2)
API REST em Java 21, Spring Boot 3.3, Maven e Oracle (FIAP).

Documentação interativa (Swagger): `/` (redireciona para `/swagger-ui/index.html`)
Teste de vida e do banco: `GET /health`

| Recurso | Métodos |
|---|---|
| /pontos | GET, GET/{id}, GET/{id}/historico, POST, PUT/{id}, DELETE/{id} |
| /relatos | GET (?idPonto=), GET/{id}, POST, PUT/{id}, DELETE/{id} |
| /alertas | GET, GET/{id}, POST, PUT/{id}, DELETE/{id} |
| /eventos | GET, GET/{id}, GET/{id}/pontos |
| /estacoes | GET, GET/{id} |
