package org.example.validacao;

import org.example.dominio.Aluno;
import org.example.dominio.InscricaoCurso;

import java.time.LocalDate;
import java.util.Collection;

public class ValidaAWS implements ValidaInscricaoCurso {

    /***
     * @param aluno
     * @param nomeCurso tem que exixtir dentro das inscrições existentes do aluno. Este curso tem
     *                  que estar antes do inicio do curso cadastrado.
     * @return
     */


    @Override
    public boolean isValid(Aluno aluno, String nomeCurso) {
        Collection<InscricaoCurso> cursos = aluno.getInscricaoCursoList();
        InscricaoCurso inscricaoCurso = null;
        for (InscricaoCurso inscricaoCursoExixtente:cursos){
            if(inscricaoCursoExixtente.getCurso().getNome().equals(nomeCurso)){
                inscricaoCurso = inscricaoCursoExixtente;
                break;
            }
        }
        return null!=inscricaoCurso && inscricaoCurso.getDataInicioDoCurso().isBefore(LocalDate.now());
    }
}
