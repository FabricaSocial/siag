SIAG - Sistema de Agendamento
=========

Sistema de Agendamento de inscrições da [Fábrica Social] (http://www.fabricasocial.df.gov.br/). Com ele, é possível realizar inscrições para o sorteio de vagas para a Fábrica Social, bem como o próprio sorteio, através de um algoritmo gerador de números aleatórios.

Linguagens, Ferramentas e Frameworks
------
* Linguagem principal: **Java**
* Linguagens Secundárias: **HTML 5**, **CSS 3** e **Javascript** (todas utilizadas para o *front-end*)
* Padrão MVC, utilizando o *framework* [SpringMVC] (http://www.caelum.com.br/apostila-java-web/spring-mvc/)
* [Hibernate] (http://hibernate.org/) Framework para persistência de dados.
* IDE: [Eclipse for Java EE Developers] (http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/keplersr2)
  * [EGit] (https://www.eclipse.org/egit/) - Integração da IDE com o repositório
  * [SpringSource Suite] (http://spring.io/tools) - Ferramenta para facilitar o desenvolvimento com o *framework* SpringMVC
* [Foundation Framework] (http://foundation.zurb.com/) - Para implementar a interface de usuário
* Banco de Dados [MySQL] (http://dev.mysql.com/downloads/mysql/)
* [Apache Tomcat] (http://tomcat.apache.org/download-70.cgi) - [Configurar Tomcat no Eclipse] (http://felipebbarbosa.wordpress.com/2012/02/26/configurando-o-apache-tomcat-7-no-eclipse-ide/)

Política de *Branches*
-----
1. A *branch* **master** receberá apenas as releases, ou seja, a cada entrega haverá um *merge* para a **master**.
2. Haverá uma *branch* **development**, que será a base do projeto, ou seja, assim que uma funcionalidade estiver pronta, haverá um *merge* para a *branch* **development**.
3. A cada funcionalidade, uma nova *branch* será criada com o nome "**ft_nome_da_funcionalidade**".
  
Padrões de Programação
------------------

####Cabeçalho

    package pacote.exemplo

    /**
     * Descrição da classe
     * 
     * @author DETI - Departamento de Tecnologia de Informação
     */

####Comentar Métodos

    /**
     * Descrição do método
     * @author Nome do Autor do Método <email.do.autor@exemplo.com>
     * 
     * @param nomeDoParametro Breve descricao do parametro
     * @return Descrição do retorno do método
     */
    public void metodo() {
      ...
    }
    
####Nomenclaturas
Nomenclatura devem seguir o padrão Java [CamelCase] (http://pt.wikipedia.org/wiki/CamelCase), com exceção de variáveis, que devem ser iniciadas sempre com letra minúscula, e constantes, que devem ser escritas sempre em caixa alta.

    public class ClasseXpto {
      static final CONSTANTE = 0;
      
      public void metodoXpto() {
        ...
      }
    }
  
