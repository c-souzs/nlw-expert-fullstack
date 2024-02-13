**API End points**
```markdown
- Retorna todas as tecnologias disponíveis para tirar certificação:            - GET /api/techs/
- Cria a certificação para um estudante:                                       - POST /api/students/certificationAnswer
- Retorna todas as certificações de um estudante:                              - GET /api/students/certificationsStudent/{email}
- Retorna as 10 certificações com maiores notas de uma tecnologia:             - GET /api/ranking/{techId}
- Retorna todas as questões de uma certificação por tecnologia:                - GET /api/questions/tech/{techId}
```
