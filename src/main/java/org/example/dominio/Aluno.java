package org.example.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Data
//@NoArgsConstructor
//@AllArgsConstructor

public class Aluno {
        private String nome;
        private String matricula;
        private Date dataNascimento;

        private List<InscricaoCurso> inscricaoCursoList;

        public String apresentar(){

            LocalDate dataNascimento = this.dataNascimento.toInstant().
                    atZone(ZoneId.systemDefault()).toLocalDate();
            return String.format("Aluno: %s de matricula %s com data de nascimento %s (%d anos)" +
                             ", %n/t Cursos %n%s",
                    this.getNome(), this.getMatricula(),
                    DateTimeFormatter.ofPattern("dd/MM/yyy").format(dataNascimento),
                    Period.between(dataNascimento,LocalDate.now()).getYears()
            ,getCursos());
        }

        public String apresentarDate() {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dataNascimento);
            String dataFormatada = String.format("%1$te/%1$tm%1$tY", calendar);
            return String.format("Aluno: %s de matricula %s com data de nascimento %s " +
                            ", %n \t Cursos$n%s",
                    this.getNome(), this.getMatricula(),
                    dataFormatada
                    , getCursos());
        }

        private String getCursos() {
            StringBuilder builder = new StringBuilder();
            builder.append("Curso \t Data Inicio \t Data Término \t Dias para começar o curso\n");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (InscricaoCurso objetoInscricaoCurso: inscricaoCursoList) {
                final LocalDate dataTermino = objetoInscricaoCurso.getDataInicioDoCurso()
                        .plusMonths(objetoInscricaoCurso.getCurso().getDuracao());
                builder.append(String.format("%S \t %s \t %s \t %d %n",
                        objetoInscricaoCurso.getCurso().getNome()
                        ,formatter.format(objetoInscricaoCurso.getDataInicioDoCurso()),
                        formatter.format(dataTermino), "< Resultado aqui >"));
            }
            return builder.toString();
        }

        public boolean containsCurso(String nomeCurso){
            List<String> nomesCursos = getNomesDosCursos(this.getInscricaoCursoList());
            return nomesCursos.contains(nomeCurso);

        }

    private List<String> getNomesDosCursos(Collection<InscricaoCurso> inscricaoCursoList){
        List<String> nomesDosCursos = new ArrayList<>();
        for (InscricaoCurso inscricaoCurso : inscricaoCursoList){
            nomesDosCursos.add(inscricaoCurso.getCurso().getNome());
        }
        return nomesDosCursos;
    }



        }


