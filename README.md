# 🎯 Gra Statki – JAVA Battleship

Projekt konsolowej gry "Statki", stworzony w języku Java. Gracz rozstawia swoją flotę i mierzy się z inteligentną AI na planszy 10x10. Projekt zawiera system statystyk, zdarzenia losowe oraz elementy programowania obiektowego.

## 📌 Funkcjonalności

- 🔐 Logowanie użytkownika (login wprowadzany na początku gry)
- 🎮 Wybór trybu rozstawienia statków (manualny / automatyczny)
- 🧠 Inteligentna AI, która strzela wokół trafionych pól
- 📊 Zapis statystyk do plików XML (`XMLEncoder`)
- 🎲 Losowe wydarzenia co kilka rund (np. pominięcie tury, podwójny strzał)
- 🧹 Wykorzystanie polimorfizmu i wyjątków
- 🏠 Menu główne: Gra | Statystyki | Twórcy | Wyjście

## 🗂 Struktura projektu

```
pl.battleship/
├── Main.java             - uruchomienie gry
├── Game.java             - główna logika gry
├── MainMenu.java         - menu główne
│
├── model/                - modele: Board, Ship, Coordinate
├── shooter/              - interfejs Shooter, klasy HumanShooter, SmartAiShooter
├── exception/            - niestandardowe wyjątki (np. InvalidPositionException)
├── stats/                - zapis i odczyt statystyk gracza (GameStats, StatsSaver)
├── events/               - zdarzenia losowe w grze (SkipTurnEvent, DoubleShotEvent)
```

## 🔧 Technologie

- Java 21+
- OOP (dziedziczenie, interfejsy, polimorfizm)
- XML (zapis statystyk)
- Logger (`java.util.logging`)
- Prosta AI (strategia traf-hunt)

## ⚠ Obsługa wyjątków

Projekt posiada własne klasy wyjątków:
- `InvalidPositionException` – błędna współrzędna
- `AlreadyShotException` – pole już zostało trafione

## 📁 Statystyki gracza

Po zakończeniu gry, dane zapisywane są w katalogu `stats/` jako pliki `.xml`:

- login gracza
- liczba oddanych strzałów
- liczba trafień
- wynik gry (wygrana/przegrana)

## 🪪 Przykładowe zdarzenia losowe

```java
public class SkipTurnEvent implements GameEvent {
    public void trigger(Game game) {
        if (Math.random() < 0.5)
            game.setSkipPlayerTurn(true);
        else
            game.setSkipAiTurn(true);
    }
}
```

## 📷 Przykładowy widok planszy

```
   1  2  3  4  5  6  7  8  9 10
 1 .  .  .  .  .  .  .  .  .  .
 2 .  o  .  .  .  .  .  x  .  .
 ...
```

## 🔗 Repozytorium

Kod Źródłowy dostępny publicznie na GitHub:  
👉 [github.com/Holingo/JAVA_Battleship](https://github.com/Holingo/JAVA_Battleship)

---

> Projekt stworzony w ramach zajęć z Programowania w Javie – Politechnika Krakowska, 2025.
