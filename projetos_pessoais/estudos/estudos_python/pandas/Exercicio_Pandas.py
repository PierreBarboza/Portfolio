import pandas as pd

#https://docs.google.com/spreadsheets/d/1uxYa8NKhoPQVAO_LNqNWxyn30qn5S_qD/edit?usp=sharing&ouid=103286032416998039927&rtpof=true&sd=true

planilha_id = "1uxYa8NKhoPQVAO_LNqNWxyn30qn5S_qD"


df_dados = pd.read_csv(f"https://docs.google.com/spreadsheets/d/{planilha_id}/export?format=csv")

print("\n DataFrame Google Sheets \n")
print(df_dados)
print("\n")

"""
Exercicio

1 - Após carregar os dados, deixe somente as colunas de Vendedor e Total Vendas
2 - Com o groupby use a coluna de vendedor para criar um resumo do vendedor e a soma total das vendas
3 - Salve o dataFrame como um arquivo de Excel csv

Parece fácil, mas não é! Boa sorte!

"""
df_resumo = df_dados[['Vendedor', 'Total Vendas']]

for index, row in df_resumo.iterrows():
    row['Total Vendas'] = row['Total Vendas'].replace(',', '.')

df_resumo['Total Vendas'] = df_resumo['Total Vendas'].astype('double')
df = df_resumo.groupby(by=['Vendedor'], dropna=False).sum()

df.to_csv('atv_pandas.csv')
print(df)