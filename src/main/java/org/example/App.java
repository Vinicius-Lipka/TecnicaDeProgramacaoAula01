package org.example;

import org.example.dominio.Aluno;
import org.example.dominio.Curso;
import org.example.dominio.InscricaoCurso;
import org.example.validacao.ValidaAWS;
import org.example.validacao.ValidaInscricaoCurso;
import org.example.validacao.ValidaTecnicasProgramacao;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       Scanner scanner = new Scanner(System.in);

       Aluno aluno = new Aluno();

       Curso curso = new Curso();

       curso.setNome("Técnicas de programação 4");
       curso.setDuracao(6L);

       InscricaoCurso inscricaoCurso = new InscricaoCurso();

       inscricaoCurso.setCurso(curso);
       inscricaoCurso.setDataInicioDoCurso(LocalDate.now().plusMonths(2L));

       aluno.setInscricaoCursoList(new ArrayList<>());
       aluno.getInscricaoCursoList().add(inscricaoCurso);

       Curso cursoTecnicasProgramacao5 = new Curso("Técnicas de programação 5", 8L);

       aluno.getInscricaoCursoList()
               .add(new InscricaoCurso(aluno, curso, LocalDate.now().plusMonths(1L),
               "Técnicas de programação 4", new ValidaTecnicasProgramacao()));

       Curso cursoAWS = new Curso ("Serviço Cloud", 3L);

       aluno.getInscricaoCursoList()
               .add(new InscricaoCurso(aluno, cursoAWS, LocalDate.now().plusMonths(2L),
                       "Deployment",new ValidaAWS()));

       //Clasee Anonima
        aluno.getInscricaoCursoList()
                .add(new InscricaoCurso(aluno, cursoAWS, LocalDate.now().plusMonths(2L),
                        "Deployment",
                        new ValidaInscricaoCurso() {
                    @Override
                    public boolean isValid(Aluno aluno, String nomeCurso) {
                        return aluno.containsCurso(nomeCurso);
                    }
                }));

        aluno.getInscricaoCursoList()
                .add(new InscricaoCurso(aluno, cursoAWS, LocalDate.now().plusMonths(2L),
                        "Deployment",
                        (Aluno a, String s) -> aluno.containsCurso(s) ));



       aluno.setMatricula("qualquer");
       aluno.setNome("Ronald");
       LocalDate localDataNascimento = LocalDate.of(2000, 9, 11);
       aluno.setDataNascimento(
               Date.from(localDataNascimento.atStartOfDay()
                       .atZone(ZoneId.systemDefault())
                       .toInstant())
       );

        System.out.println(aluno.apresentar());

        LocalDateTime.now(ZoneId.of("Europe/Paris"));
        //System.out.println(LocalDate.now());
        //System.out.println(LocalDateTime.now());
        //System.out.println(LocalDateTime.now(ZoneId.of("Europe/Amsterdam")));

        //System.out.println(ZoneId.getAvailableZoneIds());
    }
}
