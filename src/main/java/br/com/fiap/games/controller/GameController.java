package br.com.fiap.games.controller;

import br.com.fiap.games.model.Game;
import br.com.fiap.games.repository.GameRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/games")
    public ResponseEntity<List<Game>> getAllGames(){
        return ResponseEntity.ok(gameRepository.findAll());
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id) {

        Optional<Game> game = gameRepository.findById(id);

        if (game.isPresent()) {
            return ResponseEntity.ok(gameRepository.findById(id).get());
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping("/games")
    public ResponseEntity<Game> saveGame(@RequestBody Game game) {
        return ResponseEntity.ok(gameRepository.save(game));
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity deleteGame(@PathVariable Long id){

        Optional<Game> game = gameRepository.findById(id);

        if (game.isPresent()) {
            gameRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();

    }

    @PutMapping("/games/{id}")
    public ResponseEntity<Game> updateGame(@RequestBody Game game, @PathVariable Long id){

        Optional<Game> optionalGame = gameRepository.findById(id);

        if (optionalGame.isPresent()) {
            Game currentGame = optionalGame.get();
            BeanUtils.copyProperties(game, currentGame, "id");
            gameRepository.save(currentGame);
            return ResponseEntity.ok(currentGame);
        }

        return ResponseEntity.notFound().build();

    }

}
