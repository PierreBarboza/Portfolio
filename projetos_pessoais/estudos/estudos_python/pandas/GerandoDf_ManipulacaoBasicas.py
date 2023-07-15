import pandas as pd
import numpy as np

#------------------------------------------------------------------------------
#-------------------------- Geração de df -------------------------------------

# Gerando DF com dadas aleatorias
df_meses = pd.date_range('20221231', periods=12, freq="M")
print(df_meses)

df_datas = pd.date_range('20221201', periods=31, freq='D')
print(df_datas)

df_num_alea = pd.DataFrame(np.random.rand(10, 5) * 100, columns=('A', 'B', 'C', 'D', 'E'))
print(df_num_alea)
print(df_num_alea.columns)

#Gerando df a partir de dicionario
df_media = pd.DataFrame({
    'Nome': ['Pedro', 'Bia', 'Luiz'],
    'Media': [7, 9, 8]
})

dict_media = {
    'Nome': ['Pedro', 'Bia', 'Luiz'],
    'Media': [7, 9, 8]
}

#Podemos passar um dict já existente tb
df_media = pd.DataFrame(dict_media)
print(df_media)

df_notas = pd.DataFrame({
    'Nome': ['Pedro', 'Bia', 'Luiz'],
    'Nota1': [10, 9, 8],
    'Nota2': [8, 9, 10],
    'Nota3': [8, 9, 10],
})
print(df_notas)

#------------------------------------------------------------------------------
#-------------------------- Adicionando e Removendo colunas--------------------

#Adicionando coluna
df_notas['Media'] = (df_notas['Nota1'] + df_notas['Nota2'] + df_notas['Nota3']) / 3
print(df_notas)

faltas = [5, 6, 8]
df_notas['Faltas'] = faltas
print(df_notas)

# loc(localiza) passamos uma linha e coluna para ser localizada no Df para ser manipulada
df_notas.loc[1, 'Nota2'] = 20
print(df_notas)

#Gerando df a partir de arquivos
df_vendas = pd.read_excel('dados/Vendas_Jan.xlsx')

#Podemos passar o caminho tb
#df_vendas2 = pd.read_excel('/home/henriquebarbosa/Pedro/portfolio/projetos_pessoais/estudos/estudos_python/pandas/Vendas_Jan.xlsx')
print(df_vendas)

#Exibir informações do df, como quantidade de linhas e colunas
print(f'Informações do df: {df_vendas.index}')
# Ou podemos usar
print(f'Informações do df = {df_vendas.shape}')

# Head() exibe apenas as 5 primieras linhas, mas podemos passar um valor de quantas linhas ele deve exibir
print(df_vendas.head())

# tail() exibe apenas as 5 ultimas linhas, mas podemos passar um valor de quantas linhas ele deve exibir
print(df_vendas.tail())

# Exibir colunas especificas
print(df_vendas[['Vendedor', 'Total Vendas']])

#Limitando a quantidades de linha a serem exibidas
# Podemos usar apenas o [] com os valores dos indices das linhas sem o .loc,
# mas sem ele o ultimo numero não será considerado, então será exibido apenas as linhas 13 e 14, com o .loc a linha 15 tb é exibida
print(df_vendas.loc[13:15])

#Exibir linhas de um vendedor especifico
print(df_vendas.loc[df_vendas['Vendedor'] == 'Leonardo Almeida'])

#Exibir colunas especificas de um vendedor especifico
print(df_vendas.loc[df_vendas['Vendedor'] == 'Leonardo Almeida', ['Vendedor', 'Total Vendas']])

#Transformando linhas em colunas e colunas em linhas
print(df_vendas.T)

df_vendas = pd.read_excel('dados/Deletar_Linhas_Colunas.xlsx')
print(df_vendas)
print(f'Total de registros antes do drop Nan = {len(df_vendas)}')

#Para deletar linhas com NaN, ele vai deletar a linha se ela tiver o NaN em qualquer lugar
df_vendas = df_vendas.dropna()
print(f'Total de registros depois do drop Nan = {len(df_vendas)}')

# Deletando coluna especifica
del df_vendas['Produto']
print(df_vendas)

#Deletando mais de uma coluna
df_vendas = df_vendas.drop(columns=['Data Venda', 'Total Vendas'])
print(df_vendas)

#Excluindo linha. OBS: o axis (Eixo) indica se o valor que passamos antes foi para se referencias a uma linha ou coluna
# Para indicar que vamos excluir uma linha usamos o 0 (axis=0) e para indicar coluna o 1 (axis=1)
# Se não passarmos o axis ele vem o valor 0 como padrão
df_vendas = df_vendas.drop(2, axis=0)
print(df_vendas)

df_vendas = df_vendas.drop([1,5], axis=0)
print(df_vendas)


#------------------------------------------------------------------------------
#-------------------------- Tratamentos no DF ---------------------------------
#-------------------------- Deduplicacao e GroupBy ----------------------------


df_vendas = pd.read_excel('dados/Tratamento_Dados.xlsx')
print(df_vendas)

#Preenchendo campos com Nan com o calculos de media usando a função mean
df_vendas['Total Vendas'] = df_vendas['Total Vendas'].fillna(df_vendas['Total Vendas'].mean())
#df_vendas['Total Vendas'] = df_vendas['Total Vendas'].fillna(0) Aqui passamos um valor fixo Ou podemos
#df_vendas['Total Vendas'] = df_vendas['Total Vendas'].ffill() Aqui vamos pegar o ultimo valor da coluna antes do nan e colocar ele em todos os lugares com NaN
print(df_vendas)

#Dando um resulmo por coluna, contando quantos vendedores temos por coluna
print(df_vendas['Vendedor'].value_counts())

#Groupby vai agrupar os valores por uma coluna que nós informamos, no caso a de vendedor
#Alem de agrupar ele irá somar os valores de todas as colunas que tenham valores numericos dos vendedores agrupados.
print(df_vendas.groupby(by=['Vendedor']).sum())

df_vendas['Media'] = df_vendas['Total Vendas']
print(df_vendas.groupby(by=['Vendedor']).mean())

df_vendas = pd.read_excel('dados/Base_Vendas.xlsx')
print(df_vendas)

#Mostrando um resulmo de quantos valores unicos temos no DF
print(df_vendas.nunique())

#Para mostrar um resumo dos dados. O parametro subset é para apontar a coluna onde estão os dados duplicados
# O parametro keep é para ele saber quais dos valores duplicados ele deve manter.
# O keep pode receber como parametro first, last, False (O false irá marcar tudo que está duplicado como false)
print(df_vendas.duplicated(subset='Vendedor', keep='first'))

df_vendas['Duplicados'] = df_vendas.duplicated(subset='Vendedor', keep='first')
print(df_vendas)

#Removendo itens duplicados passando uma coluna para ser comparada e quando existirem valores iguais ele irá deduplicar
#Se ele detectar valores iguais entre as colunas que passamos ele irá apagar, CUIDADO
print(f'Total de registros do df antes de deduplicar {len(df_vendas)}')
df_vendas = df_vendas.drop_duplicates(subset='Vendedor')
print(f'Total de registros do df depois de deduplicar {len(df_vendas)}')
print(df_vendas)

#Caso não passemos uma coluna, apenas o drop_duplicates() ele irá deduplicar apenas se a linha toda for igual
teste1 = df_vendas
teste2 = df_vendas
df_duplicado = pd.concat([df_vendas, teste1, teste2])
df_duplicado = df_duplicado.drop_duplicates()

#------------------------------------------------------------------------------
#-------------------------- Ordenação -----------------------------------------

df_vendas = pd.read_excel('dados/Ordenação.xlsx')
print(df_vendas)

#Ordenando por ordem alfabetica uma coluna especifica
print(df_vendas.sort_values(by='Vendedor'))

#Ordenando de trás para frente. Se não passaros o parametro ascending ele vem como true
print(df_vendas.sort_values(by='Vendedor', ascending=False))

#ordenando por duas colunas, ele ordena primeiro pelo nome do vendedor e depois deixa os produtos ordenados
print(df_vendas.sort_values(by=['Vendedor','Produto']))

#------------------------------------------------------------------------------
#-------------------------- Mescla de DF --------------------------------------

df_jan = pd.read_excel('dados/Vendas_Jan.xlsx')
df_fev = pd.read_excel('dados/Vendas_Fev.xlsx')
df_mar = pd.read_excel('dados/Vendas_Mar.xlsx')

#Mesclandos os DF
df_geral = pd.concat([df_jan, df_fev, df_mar])
print(df_geral)

#Podemos mesclar os df, gerando grupos, mantendo cada registro dentro do seu grupo
df_geral = pd.concat([df_jan, df_fev, df_mar], keys=['Janeira', 'Fevereiro', 'Março'])
print(df_geral)

#Chamando um grupo inteiro
print(df_geral.loc['Fevereiro'])