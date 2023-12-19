package school.sptech.projeto6orm;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<Jogo, Long>{ //Dentro do JpaRepository vamos passar nosso objeto e o tipo do ID



}
