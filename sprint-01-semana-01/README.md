# Conforme orientado, eu pratiquei juntamente com os professores, portanto na semana 1 estarão todos os meus documentos.
# Exercicios, anotações e a minha prática:

- Exercicios utilizando o Git (e anotações)
- Exercicios utilizando a linguagem java (e anotações)

# Adicionando o documento de descrição da atividade ao README:

Atividade Semanal - 01 

Observações Gerais: 

Siga o seguinte padrão para o nome do repositório:

    Nome do repositório: primeiroNome-sobreNome-estudos 
    Exemplo: edmar-gabriel-estudos 

    Tenha zelo por esse repositório e mantenha-o privado, pois ele te 
    acompanhará em toda sua jornada de estudo.

    Adicione o SM e os instrutores como colaboradores: 
        • https://github.com/lucasmgn 
        • https://github.com/mosilveira 
        • https://github.com/andreyygc 
        • https://github.com/JulianeMaran32

    Emails: 
        • andrey.coradin@compasso.com.br 
        • lucas.lima@compasso.com.br 
        • edmar.gabriel@compasso.com.br 
        • ian.paschoal@compasso.com.br 
        • juliane.maran@compasso.com.br 

    Crie um diretório por semana, até a semana 12, seguindo o seguinte padrão: 
        • sprint-01-semana-01 
        ▪ README.md 
        ▪ outra-pasta-qualquer 
        • sprint-01-semana-02 
        ▪ README.md 
        ▪ outra-pasta-qualquer

    Entregável da Semana 1:
    
        1. Instalação e Configuração do Git

        2. Criação de um Repositório Local

        3. Adição de Arquivos e Commit
            • Crie um arquivo README.md

        4. Criação de uma Conta no GitHub e Repositório Remoto
            • Crie uma conta no GitHub, crie um novo repositório e adicione-o como 
            remoto ao repositório local.

        5. Push do Repositório Local para o GitHub 
            • Envie os commits locais para o repositório remoto no GitHub.

        6. Criação e Mudança de Branches 
            • Crie uma nova branch e mude para ela. 
            • Adicione esse documento ao README.md.   

        7. Merge de Branches 
            • Faça merge de uma branch de desenvolvimento com a branch principal.

        8. Configuração de um Arquivo README 
            • Adicione uma seção ao README.md com uma lista dos principais 
            comandos do Git aprendidos.

        9. Criação de um Pull Request 
            • Faça alterações em uma branch, envie para o repositório remoto e abra 
            um pull request no GitHub.

        10. Revisão de Código e Merge de Pull Request 
            • Revise um pull request, aprove ou solicite mudanças, e faça o merge na 
            branch principal.

OBS: Cada tópico vale um ponto.

# Adicionando uma lista com os principais comandos aprendidos em git:

    - git config --global user.name
    - git config --global user.email
    - git config --global init.branch

    - git init
    - git status
    - git add
    - git add .
    - git rm --cached
    - git commit -m "Primeiro commit - Todos os arquivos"

    - Se quiser descartar as mudanças feitas(voltar a como
    era no commit anterior), antes de estar no stage.
        git restore

    - Se quiser tirar o arquivo do stage.
        git restore --staged
        git diff

    - Para saber quantas e quais alterações foram feitas no projeto.
        git log

    - Para mostrar cada log em só uma linha.
        git log --oneline

    - Para pular o status de staging e fazer o commit mais rápido .
        git commit -a -m "Commit"

    - Para renomear arquivos, file1.jpg é o nome que esta no momento,
    file.jpg é o nome que eu quero colocar nesse arquivo.
        git mv file1.jpg file.jpg

    - Para mudar a mensagem do commit do ultimo commit realizado.
        git commit -m "Alterei a data de fundação da empresa para 1880" --amend

    - Para saber tudo o que mudou em cada commit.
        git log -p

    - Para voltar em um ponto do projeto especifico, que é determinado pelo
    codigo do commit, no modo hard, tudo que estava "a frente" deste commit
    é apagado.
        git reset --hard codigo do commit

    - Usado para poder digitar o comando "log --oneline" como "log1", ficando
    assim: git log1.
        git config --global alias.log1 "log --oneline"

    - Ver todas as branchs
        git branch

    - Cria a branch
        git branch AddMenu

    - Troca entre as branchs
        git switch AddMenu

    - Cria a branch e ja muda para ela
        git checkout AddMenu

    - Juntando a branch AddMenu com a Main. -m é para adicionar uma mensagem.
        git merge -m "Merge da Branch AddMenu para a Branch Main" AddMenu

    - Deletando a branch AddMenu
        git branch -d AddMenu

    - Fazer o push no gitHub
        git remote add origin link do repositorio
        git branch -M main
        git push -u origin main

    - Cria e muda para a branch
        git switch -c AddItem

    - Fazer o push de mais de uma branch
        git push --all

    - Trazer as alterações do repositorio remoto para o local
        git pull