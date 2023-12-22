from django.urls import path, include
from galeria.views import index, oi, imagem

#* Como eu usei o metodo include no arquivo url principal, nosso colocar todas as urls relacionadas ao app galeria aqui.
urlpatterns = [
    path('', index, name='index'),
    path('oi/', oi),
    path('imagem/', imagem, name='imagem'),
]