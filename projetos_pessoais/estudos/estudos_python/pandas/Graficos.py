import pandas as pd
import matplotlib.pyplot as mp

df_graficos = pd.read_excel('dados_graficos/Base_Grafico.xlsx')
print(df_graficos)


#plot é o tipo de grafico que estamos criando. Tb temos scatter | hist | pie
# Para mais tipo acessar a documentacao, link a baixo;
#  https://www.w3schools.com/python/matplotlib_pyplot.asp
# label vamos tar um nome para linha do grafico, mas para ele aparecer devemos usar o mp.legend()
# linestyle é o estio de nilha
# lw é o tamanho da linha
# para mais tipo: https://matplotlib.org/stable/gallery/lines_bars_and_markers/linestyles.html
mp.plot(df_graficos['Frutas'], df_graficos['Total Vendas'], label='Total Vendas', linestyle=':', lw='2', color='m')
mp.legend()

#Dando um titulo para o grafico
mp.title('Vendas Frutas')

#Titilo para eixo X
mp.xlabel('Nome Frutas', size=20)

#Titilo para eixo y
mp.ylabel('Total Vendas', size=30)

#Determinamos as escalas do eixo x e y
mp.xticks([0, 1, 2, 3, 4])
mp.yticks([0, 10, 20, 30, 40, 50, 60, 65, 70, 75, 80, 90, 100, 110, 120, 130])

# definindo o minimo e maximo para exios x e y
#mp.axis(xmin=0, xmax=4, ymin=0, ymax=80)
mp.axis('auto')

#Passando a possições para colocar as anotações dos nomes das frutas
# Precisamos passar o titulo que queremos, e depois os valores de interseção entre o eixo x e y para colocar o titulo
mp.annotate(df_graficos['Frutas'][0], (df_graficos['Frutas'][0], df_graficos['Total Vendas'][0]))
mp.annotate(df_graficos['Frutas'][1], (df_graficos['Frutas'][1], df_graficos['Total Vendas'][1]))
mp.annotate(df_graficos['Frutas'][2], (df_graficos['Frutas'][2], df_graficos['Total Vendas'][2]))
mp.annotate(df_graficos['Frutas'][3], (df_graficos['Frutas'][3], df_graficos['Total Vendas'][3]))
mp.annotate(df_graficos['Frutas'][4], (df_graficos['Frutas'][4], df_graficos['Total Vendas'][4]))

# Para salvar o grafico passando o nome do arquivo e formato
mp.savefig('imagemGrafico.png')

# Mostar o grafico
mp.show()
