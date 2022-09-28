package com.allstate.speedyclaims.domain.data;

import com.allstate.speedyclaims.domain.Claim;
import com.allstate.speedyclaims.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ClaimRepository extends JpaRepository<Claim,Integer> {
    public Optional<Claim> findById(Integer id);
    public Optional<Claim> findByUser(User user);
    public Claim save(Claim claim);
    List<Claim> findAllByUser(User user);
}
