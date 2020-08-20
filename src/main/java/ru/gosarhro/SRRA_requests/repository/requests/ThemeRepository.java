package ru.gosarhro.SRRA_requests.repository.requests;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gosarhro.SRRA_requests.entity.requests.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Integer> {
}
