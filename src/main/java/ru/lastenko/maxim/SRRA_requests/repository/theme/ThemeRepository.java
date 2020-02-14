package ru.lastenko.maxim.SRRA_requests.repository.theme;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lastenko.maxim.SRRA_requests.entity.theme.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Integer> {
}
