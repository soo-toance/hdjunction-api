package api.hdjunction.persistence;

import api.hdjunction.domain.Seqno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeqnoRepository extends JpaRepository<Seqno, Long> {
    Seqno findByPrefix(String prefix);
}
