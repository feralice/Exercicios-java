# Exercicios-java
Repositório para guardar alguns exercícios de java

- Banco.java

Exercício para treinar semáforos. O marido e mulher (duas threads) compartilhem uma única conta no banco e que ao mesmo tempo o marido chame o método de retirada() quatro vezes; e a esposa chame o método depósito() também quatro vezes.

- Tarefa.java

Este exercício deve ser baseado nos slides sobre "Deadlocks". No caso, defina dois locks explícitos e use a estratégia de "adquirir todos os locks de uma vez" para gerar um Thread Pool de 10 threads sendo que, 5 threads adquirem o primeiro lock antes do segundo, e 5 threads adquirem o segundo lock antes do primeiro. Em cada thread imprima as três situações: (i) tentando adquirir os bloqueios; (ii) bloqueios adquiridos; e (iii) finalizou e liberou os bloqueios.
