package segundoteste;

import java.util.ArrayList;
import java.util.List;

public class Segundo {
    private int lastId = 0;
    private List<Candidato> candidatos = new ArrayList<>();
    private List<Integer> aprovadosList = new ArrayList<>();

    public int iniciarProcesso(String nome) {
        if (nome == null) {
            System.out.println("Nome inválido.");
        }

        int id = ++lastId;
        Candidato candidato = new Candidato(id, nome, "Recebido");
        candidatos.add(candidato);
        return id;
    }

    public void marcarEntrevista(int codCandidato) {
        Candidato candidato = encontrarCandidato(codCandidato);
        if (candidato != null) {
            verificarParticipacaoProcesso(codCandidato);

            candidato.setStatus("Qualificado");
        } else {
            System.out.println("Candidato não encontrado.");
        }
    }

    public void desqualificarCandidato(int codCandidato) {
        Candidato candidato = encontrarCandidato(codCandidato);
        if (candidato != null) {
            candidatos.remove(candidato);
        } else {
            System.out.println("Candidato não encontrado.");
        }
    }

    public String verificarStatusCandidato(int codCandidato) {
        Candidato candidato = encontrarCandidato(codCandidato);
        if (candidato != null) {
            return candidato.getStatus();
        } else {
            return "Candidato não encontrado.";
        }
    }

    public void aprovarCandidato(int codCandidato) {
        Candidato candidato = encontrarCandidato(codCandidato);
        if (candidato != null) {
            verificarParticipacaoProcesso(codCandidato);

            candidato.setStatus("Aprovado");
            aprovadosList.add(codCandidato);
        } else {
            System.out.println("Candidato não encontrado.");
        }
    }

    public List<String> obterAprovados() {
        List<String> aprovadosNomes = new ArrayList<>();
        for (int i = 0; i < aprovadosList.size(); i++) {
            int id = aprovadosList.get(i);
            Candidato candidato = encontrarCandidato(id);
            aprovadosNomes.add(candidato.getStatus());
        }
        return aprovadosNomes;
    }


    private Candidato encontrarCandidato(int codCandidato) {
        for (Candidato candidato : candidatos) {
            if (candidato.getId() == codCandidato) {
                return candidato;
            }
        }
        return null;
    }

    private void verificarParticipacaoProcesso(int codCandidato) {
        if (aprovadosList.contains(codCandidato)) {
            System.out.println("Candidato já participa do processo.");
        }
    }

    private static class Candidato {
        private int id;
        private String nome;
        private String status;

        public Candidato(int id, String nome, String status) {
            this.id = id;
            this.nome = nome;
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
