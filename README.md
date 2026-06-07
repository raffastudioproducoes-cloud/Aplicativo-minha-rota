# MinhaRota 🏍️

O aplicativo definitivo de controle financeiro e gestão de rotina para motoboys e entregadores de aplicativos.

## 🎯 Objetivo
O **MinhaRota** foi criado para agilizar e facilitar o controle financeiro dos motoristas e pilotos, transformando a complexidade da rotina de entregas em uma gestão simples, visual e eficiente.

## 🚀 Stack Tecnológica
- **Linguagem:** Kotlin
- **Interface:** Jetpack Compose (Material 3)
- **Arquitetura:** MVVM (Model-View-ViewModel)
- **Persistência Local:** SharedPreferences com Serialização JSON (Preparado para expansão futura)
- **Automação:** GitHub Actions para CI/CD (Builds Beta automatizados)

## ✨ Principais Funcionalidades
- **Gestão de Turnos (Hoje):** Controle em tempo real de horas trabalhadas, ganho bruto e custos de rua, com cálculo automático do ganho líquido.
- **Caixinhas Inteligentes:** Distribuição percentual automática dos ganhos em "envelopes virtuais" (ex: Reserva de Emergência, Manutenção da Moto, Lazer).
- **Gestão de Contas e Dívidas:** Planejamento financeiro com cálculo de metas diárias automáticas baseadas em suas contas fixas.
- **Garagem:** Controle rigoroso de quilometragem e alertas inteligentes para manutenção preventiva da sua moto.
- **Gráficos de Performance:** Análise detalhada com mapa de calor de "Horários de Ouro" e evolução dos ganhos mensais.

## 🛠️ Estrutura do Projeto
- `domain/model`: Entidades de dados fundamentais.
- `data/local`: Gerenciamento de persistência e armazenamento offline.
- `ui/theme`: Configurações de cores, tipografia e tema Material 3.
- `ui/components`: Componentes visuais reutilizáveis (Cards, NavBars, etc).
- `ui/screens`: Telas principais do aplicativo.

## 📱 Como Testar
Os APKs de desenvolvimento são gerados automaticamente a cada atualização na branch `main`. Você pode encontrá-los na aba **Actions** do repositório.

---
*Desenvolvido com foco total na produtividade de quem faz o Brasil se movimentar.*
