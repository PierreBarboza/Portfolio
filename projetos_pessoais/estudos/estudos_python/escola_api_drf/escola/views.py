# from django.shortcuts import render #*Esse é o import padrao do django, ele vem como render para renderizar paginas.
#* Mas no caso da nossa API queremos que ele devolva um Json
from django.http import JsonResponse

def alunos(request):
    if request.method == 'GET':
        aluno = {'id': 1, 'nome': 'Pedro'}
        return JsonResponse(aluno)
