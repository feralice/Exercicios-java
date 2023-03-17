# Exercicios-java
Repositório para guardar alguns exercícios de java

- Banco.java

Exercício para treinar semáforos. O marido e mulher (duas threads) compartilhem uma única conta no banco e que ao mesmo tempo o marido chame o método de retirada() quatro vezes; e a esposa chame o método depósito() também quatro vezes.


- Tarefa.java

Este exercício deve ser baseado nos slides sobre "Deadlocks". No caso, defina dois locks explícitos e use a estratégia de "adquirir todos os locks de uma vez" para gerar um Thread Pool de 10 threads sendo que, 5 threads adquirem o primeiro lock antes do segundo, e 5 threads adquirem o segundo lock antes do primeiro. Em cada thread imprima as três situações: (i) tentando adquirir os bloqueios; (ii) bloqueios adquiridos; e (iii) finalizou e liberou os bloqueios.


- ProblemaFumantes.java

O código é uma implementação do Problema dos Fumantes em Java, que envolve um agente que fornece ingredientes e três fumantes que precisam dos ingredientes para fumar. O código utiliza semáforos para controlar o acesso aos ingredientes pelos fumantes e ao agente, garantindo que cada fumante tenha acesso apenas aos ingredientes que precisa para fumar o cigarro. O programa cria objetos Fumante e Agente, que são iniciados com o método start().


- Matriz.java

Multiplicação de Matrizes usando Thread Pool.


- DepCaixaCom.java

Esse código em Java implementa um exemplo de produtor-consumidor, que é um problema clássico de sincronização em programação concorrente. Ele cria uma classe "Deposito" que simula um depósito de caixas com uma capacidade máxima definida pelo usuário, e duas classes que representam um produtor e um consumidor que podem armazenar e retirar caixas do depósito, respectivamente. O usuário é solicitado a inserir a quantidade máxima de produções e consumos que deseja realizar, e o programa cria threads para executar essas tarefas de forma assíncrona. O código utiliza o conceito de bloqueio ("lock") para garantir que o depósito não seja acessado simultaneamente por threads diferentes, o que pode resultar em condições de corrida ("race conditions") e outros problemas de sincronização.
