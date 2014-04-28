SIAG - Requisitos
=============


Funcionais
-------------

* **Formulário de Inscrição**
  * Campos: CPF, Nome, Cidade Satélite e Telefones.
  * Opção para *Reserva de Vagas*.
  * Data de Inscrição e Usuário que realizou a inscrição.
  * Total de Inscritos
* **Formulário de Agendamento**
  * Campos: CPF e Nome.
  * *Dropdown* de dias e horários vagos.
  * Exibir vagas disponíveis no horario selecionado.
  * Data e hora do agendamento
* **Formulário de Sorteio**
  * Data e hora do sorteio, opção para auditoria, semente (para gerar um número randômico) e o número do sorteio.
  * Exibir inscritos moradores da região, de outras regiões e número de vagas.
  * Opções de Novo Sorteio, Gerar Sorteio e Gerar Relatorio
* **Relatório de Inscritos**
  * Listar inscritos em um determinado período, agrupando por cidade.
  * Campos: Cidade, Protocolo, CPF, Nome e Categoria de cada inscrito.
  * Caso tenha alguma necessidade especial, a deficiência também deve ser listada.
* **Relatório de Agendamento**
  * Listar inscritos, agrupando por data e hora de agendamento.
  * Campos: Cidade, Protocolo, CPF, Nome e Categoria de cada inscrito.
  * Caso tenha alguma necessidade especial, a deficiência também deve ser listada.
  
Não-Funcionais
----------------

* O sistema deve ser disponibilizado para a CODEPLAN, via internet.

Regras de Negócio
--------------------

* **Formulário de Inscrição**
  * [Verificar CPF](http://pt.wikipedia.org/wiki/D%C3%ADgito_verificador).
  * Verificar Período de Inscrição.
  * Gerar Número de Protocolo.
* **Formulário de Sorteio**
  * Um sorteio para cada grupo de inscritos.
  * Para um sorteio, uma mesma semente geradora de números aleatórios deve ser utilizada em todos os grupos de inscritos. A semente deve variar de sorteio para sorteio.
  * Sorteios devem ser feitos primeiro com os moradores da região, que podem ocupar todas as vagas disponíveis.
  * Inscritos que não foram sorteados formam o cadastro reserva. A ordem de chamada dessa lista é definida dentro do proprio sorteio.
