#language: pt
@funcionais

@tag
Funcionalidade: Pesquisar Produto 
   
 Contexto:
 
  Dado queEstou no siteAmazon
  Quando abro o menu lateral "Todos"
  E seleciono a categoria "Eletrônicos"
  Entao o titulo da pagina deve conter "Eletrônicos"

 
  @tag1
Cenario: Filtrar por categoria Eletronicos
  Dado queEstou no siteAmazon
  Quando abro o menu lateral "Todos"
  E seleciono a categoria "Eletrônicos"
  Entao o titulo da pagina deve conter "Eletrônicos"