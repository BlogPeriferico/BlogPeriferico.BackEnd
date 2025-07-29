---
name: Falha no Deploy
about: Cria uma issue automaticamente quando o deploy falha no GitHub Actions
title: "🚨 Falha no deploy do BlogPerigericoBackEnd"
labels: [bug, deploy, urgente]
assignees: ''

---

## ❌ O que aconteceu

O workflow de deploy automático para o **BlogPerigericoBackEnd** falhou.

### 🔧 Pipeline
- Nome: `Deploy App to Azure App Service`
- Branch: `main`
- Data/Hora: `${{ github.event.head_commit.timestamp }}`

### 📋 Último commit
- Autor: `${{ github.event.head_commit.author.name }}`
- Mensagem: `${{ github.event.head_commit.message }}`

## ✅ Próximos passos sugeridos

- Verificar os logs do GitHub Actions.
- Validar se o `publish-profile` está correto.
- Garantir que o build Maven finalizou corretamente.
- Verificar se o arquivo `.jar` foi gerado com sucesso.

---

> Esta issue foi gerada automaticamente por `GitHub Actions` após falha no deploy.
