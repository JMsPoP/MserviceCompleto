package com.jmtsu.ms.core.repository;

import com.jmtsu.ms.core.model.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {

}
