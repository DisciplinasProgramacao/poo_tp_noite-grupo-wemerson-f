-Parte 1:
    Dentre as formas de entretenimento contemporâneas, os serviços de streaming de conteúdo se multiplicaram e
    tomaram conta de boa parte do mercado nos últimos anos. Isto pode ser percebido em números relatados por pesquisas
    recentes.
    A companhia de monitoramento de mercado Hibou realizou uma pesquisa em maio de 2022, e seu resultado
    aponta que 71% dos brasileiros usam ou já usaram algum serviço de streaming.
    1 Segundo a mesma pesquisa, 70% destes usuários têm como principal motivo da assinatura o fato de poder ver séries originais das plataformas, muitas vezes realizando o que ficou conhecido como maratonar uma série, ou seja, ver todos os seus episódios de maneira
    consecutiva em poucos dias.
    A Forbes, uma das principais revistas de economia e negócios da América do Norte, afirmou recentemente
    números espantosos para os serviços de streaming mais conhecidos
    2: a Netflix tinha mundialmente, em fevereiro de 2023, mais de 230 milhões de assinantes. Outras empresas apresentam números igualmente enormes: 161 milhões para a Disney+ e mais de 77 milhões para o grupo Paramount.
    Considerando este contexto, seu grupo de trabalho recebeu a responsabilidade de desenvolver um sistema de software
    que possa ser vendido para companhias de streaming. O sistema se mostra abrangente e de média complexidade, de
    modo que a estratégia escolhida foi a de desenvolvimento por módulos que serão integrados cumulativamente. O
    primeiro passo é entender os requisitos mais básicos de um serviço simples de streaming:
    • O serviço precisa manter dados de um número grande de clientes, cada um com seu login único.
    • Igualmente, os dados de séries precisam ser armazenados. Uma série terá cadastrados os dados seguintes:
    nome, idioma e gênero. É preciso registrar quantos clientes já assistiram uma série.
    • Os clientes poderão adicionar séries a duas listas em sua conta: uma contendo séries para assistir futuramente
    e outra para registrar e consultar suas séries já assistidas.
    • O cliente pode realizar buscas em suas listas, ou na lista geral de séries do sistema. A busca pode ser por nome,
    gênero ou idioma.
    Neste momento, portanto, seu grupo precisa:
    a) propor um diagrama de classes UML que modele corretamente o que foi descrito até 31/03;
    b) validar o diagrama com o analista sênior;
    c) implementar as classes após a validação.
    O trabalho será acompanhado constantemente junto à equipe de desenvolvimento e estão previstos pontos de
    validação nos dias 19/04 e 24/05. Até lá, novos requisitos irão aparecer. O projeto precisará estar finalizado e será
    apresentado ao cliente em 14/06.
    O que deve ser produzido:
    • Modelo/diagrama de classes desenvolvido para resolver o problema. Não é necessário incluir construtores
    nem métodos get/set no modelo.
    • Código das classes, de acordo com o diagrama, documentado/comentado.
    Observações:
    • Trabalho em grupos de 5 alunos.
    • Todos os artefatos do trabalho devem ser hospedados em uma tarefa do GitHub Classroom, conforme explicado
    em aula.
    • O grupo deve criar um projeto/Kanban no repositório para acompanhamento as tarefas individuais e em grupo.
    • A nota será atribuída considerando:
    o Produção e acompanhamento do grupo;
    o Produção e acompanhamento individual;
    o Cumprimento dos requisitos e prazos do projeto.
    1 Disponível em http://www.lehibou.com.br/wp-content/uploads/2022/07/22STR01.pdf
    2 Disponível em https://www.forbes.com/sites/marisadellatto/2023/02/16/paramount-gains-subscribers-as-disney-reports-losseswhere-
    all-the-major-streaming-services-stand/?sh=14df9763c4ac

-Parte 2:
    Após a validação do diagrama inicial e da implementação das classes, é necessário um módulo que leia arquivos com dados em formato texto (csv) e crie os objetos correspondentes para utilização no sistema. Os arquivos são 3, cada um
    sendo formatado linha a linha como indicado:
    Espectadores
    Nome;Login;Senha
    Séries
    IdSerie;Nome;DataDeLançamento
    Audiência
    Login;F/A;IdSerie
    (sendo “F” para lista de séries a assistir futuramente e “A” para séries já assistidas.)
    A próxima tarefa do grupo, então, é atualizar o projeto para contemplar o requisito de leitura dos dados. Portanto, as
    tarefas constantes do trabalho são, no momento:
    a) implementar as classes conforme validação do diagrama
    b) atualizar o diagrama para o requisito de leitura
    c) implementar os códigos para leitura e testar seu funcionamento.
    O trabalho será acompanhado constantemente junto à equipe de desenvolvimento e estão previstos pontos de
    validação nos dias 19/04 e 24/05. Até lá, novos requisitos irão aparecer. O projeto precisará estar finalizado e será
    apresentado ao cliente em 14/06.
    O que deve ser produzido:
    • Modelo/diagrama de classes desenvolvido para resolver o problema. Não é necessário incluir construtores
    nem métodos get/set no modelo.
    • Código das classes, de acordo com o diagrama, documentado/comentado.
    Observações:
    • Trabalho em grupos de 5 alunos.
    • Todos os artefatos do trabalho devem ser hospedados em uma tarefa do GitHub Classroom, conforme explicado
    em aula.
    • O grupo deve criar um projeto/Kanban no repositório para acompanhamento as tarefas individuais e em grupo.
    • A nota será atribuída considerando:
    o Produção e acompanhamento do grupo;
    o Produção e acompanhamento individual;
    o Cumprimento dos requisitos e prazos do projeto.

Parte 3:
    Com os testes iniciais indicando um bom funcionamento do sistema “Minhas Séries”, é natural que as próximas fases
    do projeto comecem a ser planejas. O passo seguinte é simples, porém muito importante: o serviço de streaming passará
    a incluir em seu catálogo filmes de longa-metragem. Inicialmente, a única diferença entre séries e filmes é que as
    primeiras passarão a armazenar sua quantidade de episódios, enquanto os filmes armazenarão sua duração em
    segundos. Apesar da diferença ser pequena, os caminhos de evolução do projeto sugerem que é uma boa ideia fazer a
    diferenciação destes dois tipos de mídia.
    O catálogo de filmes pode ser encontrado em um arquivo csv que, em sua primeira linha, contém comentários
    e, a partir da segunda linha, contém dados dos filmes no formato indicado:
    Filme
    IdFilme;Nome;DataDeLançamento;Duração(min)
    Para o protótipo do sistema, os gêneros e idiomas de séries e filmes podem ser definidos aleatoriamente no
    momento do seu carregamento.
    Portanto, neste momento, o backlog do projeto conta com as tarefas abaixo e seus testes:
    • Implementação das classes Cliente, Serie e App/PlataformaStreaming como planejadas;
    • Atualização do diagrama original para contemplar requisito de carga de dados;
    • Implementação dos métodos para carga de dados de séries, clientes e audiência em funcionamento;
    • Atualização do diagrama para abrigar o novo tipo de mídia: Filme;
    • Implementação da lógica de carga de dados do catálogo de filmes;
    • Implementação do protótipo de sistema cobrindo os requisitos atuais (documentos anteriores).
    O trabalho será acompanhado constantemente junto à equipe de desenvolvimento e estão previstos pontos de
    validação nos dias 19/04 e 24/05. Até lá, novos requisitos irão aparecer. O projeto precisará estar finalizado e será
    apresentado ao cliente em 14/06.
    O que deve ser produzido:
    • Modelo/diagrama de classes desenvolvido para resolver o problema. Não é necessário incluir construtores
    nem métodos get/set no modelo.
    • Código das classes, de acordo com o diagrama, documentado/comentado.
    Observações:
    • Trabalho em grupos de 5 alunos.
    • Todos os artefatos do trabalho devem ser hospedados em uma tarefa do GitHub Classroom, conforme explicado
    em aula.
    • O grupo deve criar um projeto/Kanban no repositório para acompanhamento as tarefas individuais e em grupo.
    • A nota será atribuída considerando:
    o Produção e acompanhamento do grupo;
    o Produção e acompanhamento individual;
    o Cumprimento dos requisitos e prazos do projeto.