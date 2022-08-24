package br.com.fiap.games.repository;

import br.com.fiap.games.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {



}
