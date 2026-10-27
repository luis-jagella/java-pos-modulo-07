# Podcast 01 — Polêmicas e opiniões dev

O episódio é uma conversa sobre escolhas de desenvolvimento. Não é uma aula de APIs de concorrência. A ideia recorrente é discutir **contexto, trade-offs e convenções da equipe**, evitando transformar preferência pessoal em regra universal.

## Temas abordados

- Tabs ou espaços e padronização automática do código; Maven ou Gradle e extensibilidade do build.
- Lombok versus recursos nativos como `record`, código explícito e suporte da IDE.
- Exceções checked ou unchecked: obrigação de tratar na compilação versus clareza dos contratos e ruído na API.
- IntelliJ, Eclipse e VS Code; custo de adaptação e adequação ao projeto.
- Código em português ou inglês: alinhar com equipe e linguagem do domínio. Traduções improvisadas podem distorcer termos de negócio.
- Controller acessando repository diretamente versus camada de service; evitar uma camada vazia que apenas delega, mas concentrar regras e transformação onde elas existem.
- Devolver entidades JPA diretamente ou DTOs; os participantes defendem DTOs para controlar contrato e reduzir risco de expor campos sensíveis.
- Lista vazia normalmente responde `200` com coleção vazia; busca de recurso específico inexistente pode responder `404`.
- Streams/lambdas ou loops tradicionais: escolher legibilidade e depuração, sem forçar uma forma quando complica o caso.
- Injeção de dependências via construtor ou campo: construtor explicita dependências e permite campos `final`.

## Aplicação no projeto

Este repositório usa Maven, nomes em português para o domínio de estudo e testes de comportamento. A decisão não torna as alternativas inválidas. Ao revisar uma escolha, pergunte: quem mantém, como testa, qual problema resolve e que custo acrescenta?

Fonte: podcast 01 do portal e legenda automática. A síntese registra os assuntos da conversa; opiniões dos participantes não são tratadas como normas técnicas.
