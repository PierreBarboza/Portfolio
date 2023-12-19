package atipedroleite.valendonota2.questao1.classes;

public class Boletim {

    private String aluno;
    private double nota1;
    private double nota2;

    public Boletim(String aluno, double nota1, double nota2) {
        this.aluno = aluno;
        this.nota1 = nota1;
        this.nota2 = nota2;
    }

    public Boletim() {
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getMedia(){

        return ((this.nota1 + this.nota2) / 2);
    }
}
