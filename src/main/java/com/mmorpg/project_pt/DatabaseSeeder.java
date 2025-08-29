package com.mmorpg.project_pt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class DatabaseSeeder implements ApplicationRunner {

    private final JdbcTemplate jdbcTemplate;
    private final Environment environment;

    public DatabaseSeeder(JdbcTemplate jdbcTemplate, Environment environment) {
        this.jdbcTemplate = jdbcTemplate;
        this.environment = environment;
    }

    @Override
    public void run(ApplicationArguments args) {
        // Só executa em ambiente de desenvolvimento
        if (!isAmbienteDesenvolvimento()) {
            log.debug("Seeder ignorado - Ambiente de produção");
            return;
        }

        try {
            corrigirTodasSequencias();
        } catch (Exception e) {
            log.warn("Erro no seeder: {}", e.getMessage());
        }
    }

    private boolean isAmbienteDesenvolvimento() {
        String[] activeProfiles = environment.getActiveProfiles();
        return activeProfiles.length == 0 ||
                Arrays.asList(activeProfiles).contains("dev");
    }

    private void corrigirTodasSequencias() {
        corrigirSequencia("jogador", "id_jogador", "id_jogador_seq");
        corrigirSequencia("personagem", "id_personagem", "personagem_id_seq");
        // Adicione outras tabelas aqui
    }

    private void corrigirSequencia(String tabela, String coluna, String sequenciaNome) {
        try {
            // Verifica se a tabela existe
            Boolean tabelaExiste = jdbcTemplate.queryForObject(
                    "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = ?)",
                    Boolean.class, tabela
            );

            if (Boolean.TRUE.equals(tabelaExiste)) {
                Boolean sequenciaExiste = jdbcTemplate.queryForObject(
                        "SELECT EXISTS (SELECT 1 FROM pg_sequences WHERE sequencename = ?)",
                        Boolean.class, sequenciaNome
                );

                if (Boolean.TRUE.equals(sequenciaExiste)) {
                    jdbcTemplate.execute(
                            String.format("SELECT setval('%s', (SELECT COALESCE(MAX(%s), 0) + 1 FROM %s))",
                                    sequenciaNome, coluna, tabela)
                    );
                    log.info("Sequência {} corrigida", sequenciaNome);
                } else {
                    log.warn("Sequência {} não encontrada para tabela {}", sequenciaNome, tabela);
                }
            }

        } catch (Exception e) {
            log.warn("Erro ao corrigir sequência {}: {}", sequenciaNome, e.getMessage());
        }
    }
}