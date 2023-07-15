import pandas as pd
import numpy as np

#------------------------------------------------------------------------------
#-------------------------- Pivot ---------------------------------------------

# Pivot é uma forma de resumir e analisar dados em uma tabela, fornecendo uma visão compacta e resumida das informações.
# Pivot tb é conhecida como tabela dinamica. PIVOT NAO ACEITA DADOS DUPLICADOS ENTAO O INDEX DEVE SER UM DADO UNICO
# Nela vamos determinar quai ssão nossas linhas e como as colunas devem se comportar e quais serão as colunas.
df = pd.read_excel('dados/Vendas_Lanchonete_Pivot.xlsx')
print(df)

#Indes irá determinar qual informação do df será nosso index (linha)
# columns irá determinar quais dados do Df serão nossas colunas,
# values ira determinar qual é a coluna do dataframe que irá popular a pivot
df_pivot = df.pivot(index='Data Venda', columns='Cliente', values='Preço com Desconto')
print(df_pivot)

df_pivot = df.pivot(index='Cliente', columns='Data Venda', values='Preço com Desconto')
print(df_pivot)

#------------------------------------------------------------------------------
#-------------------------- Pivot Table----------------------------------------

df = pd.read_excel('dados/Vendas_Lanchonete_Pivot_Table.xlsx')
print(df)

#O metodo pivot_table permite dados repedidos
# aggfunc é o tipo de calculo que ele vai fazer caso a coluna de numero sejá passada (no nosso caso a de preco)
# Caso não passemos uma aggfunc, ele ira fazer uma media como padrão
df_pivot_table = df.pivot_table(index = 'Data Venda', columns = 'Cliente', values='Preço com Desconto', aggfunc = 'sum')
print(df_pivot_table)

# Exemplo com mais de uma coluna e mais de um values
df_pivot_table = df.pivot_table(index = 'Data Venda', columns = ['Cliente', 'Produto'], values=['Preço Total', 'Preço com Desconto'], aggfunc = 'sum')

df_pivot_table['Preço com Desconto'] = df_pivot_table['Preço com Desconto'].fillna(0)
df_pivot_table['Preço Total'] = df_pivot_table['Preço Total'].fillna(0)
print(df_pivot_table)