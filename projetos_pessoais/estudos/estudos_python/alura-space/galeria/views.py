from django.shortcuts import render
from django.http import HttpResponse # Precisamos desse import para ter uma resposta visual.

def index(request):
    return render(request, 'galeria/index.html')

def oi(request):
    return render(request, 'pagina2.html')


def imagem(request):
    return render(request, 'imagem/imagem.html')