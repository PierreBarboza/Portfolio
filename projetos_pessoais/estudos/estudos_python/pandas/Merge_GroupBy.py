import pandas as pd
import numpy as np

#------------------------------------------------------------------------------
#-------------------------- Merge simples -------------------------------------

df_vendas = pd.read_excel('dados/Vendas_Merge.xlsx')
df_vendedores = pd.read_excel('dados/Vendedores_Merge.xlsx')
df_produto = pd.read_excel('dados/Produtos_Merge.xlsx')

# merge irá usar uma coluna em comum (nesse cado o id) para unir os dataframes
vendas_vendedores = df_vendas.merge(df_vendedores)
print(vendas_vendedores)

#------------------------------------------------------------------------------
#-------------------------- Merge de forma correta com inner -----------------

df_loja1 = pd.read_excel('dados/Vendas_+INNER_JOIN_Loja1.xlsx')
df_loja2 = pd.read_excel('dados/Vendas_+INNER_JOIN_Loja2.xlsx')

#no parametro on é onde apontamos a coluna que queremos que ele use como referencia para mesclar os df
# Normalmente no on usamos coluna de id, pois deve ser uma coluna com valor unico para que ele agrupe as informações
#inner = Faz um merge entre dos dfs
#Quando mesclamos os df por uma chave unica, o merge diferencia as colunas do df por x, y, z (nomes genericos)
#Para isso utilizamos o suffixes, para dar nome a essas colunas de acordo co o df do qual eles vieram
df_vendedores_ambas_lojas = pd.merge(df_loja1, df_loja2, on=['Vendedor'], how='inner', suffixes=('_Loja1', '_Loja2'))
#how='inner': A junção interna ('inner join') retorna apenas as linhas onde há correspondência entre o dataframe da esquerda
# e o dataframe da direita com base na(s) coluna(s) de junção em comum. Linhas que não possuem correspondência são excluídas.
print(df_vendedores_ambas_lojas.columns)
print(df_vendedores_ambas_lojas)

#------------------------------------------------------------------------------
#-------------------------- Merge de forma correta com left -------------------

df_vendas = pd.read_excel('dados/Vendas_LEFT_JOIN.xlsx')
df_vendedores = pd.read_excel('dados/Vendedores_LEFT_JOIN.xlsx')

#how='left': A junção esquerda ('left join') retorna todas as linhas do dataframe da esquerda
# e as correspondentes do dataframe da direita, com base na(s) coluna(s) de junção em comum.
# Se não houver correspondência para uma linha do dataframe da esquerda no dataframe da direita,
# os valores ausentes são preenchidos com NaN.
vendas_gerais = pd.merge(df_vendas, df_vendedores, on='Id Vendedor', how='left', suffixes=('Vendas', 'Checagem'))
vendas_gerais = vendas_gerais.dropna()
print(vendas_gerais)

#------------------------------------------------------------------------------
#-------------------------- groupby -------------------------------------------

df_vendas = pd.read_excel('dados/Groupby.xlsx')
print(df_vendas)
# No parametro By= passamos quais columas queremos agrupar
# No dropna é para deletar as linhas com Nan
media_vendas = df_vendas.groupby(by=['Vendedor'], dropna=False).mean()
print(media_vendas)

# atenção a ordem pelo qual indicamos quais as colunas ele vai agrupar, pois ele considera a ordem.
soma_vendas = df_vendas.groupby(by=['Produto', 'Vendedor'], dropna=True).sum()
print(soma_vendas)