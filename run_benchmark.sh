#!/bin/bash

# --- Konfiguracja ---
# Ile razy chcesz uruchomić testy?
TOTAL_RUNS=10
# Nazwa podfolderu na wyniki dla tego frameworka
FRAMEWORK_NAME="playwright"
# Główny katalog na wyniki na pulpicie
RESULTS_BASE_DIR="$HOME/Pulpit/wyniki"
# --- Koniec konfiguracji ---

# Pełna ścieżka do katalogu z wynikami tego frameworka
RAW_RESULTS_DIR="$RESULTS_BASE_DIR/${FRAMEWORK_NAME}-raw-results"
# Katalog, w którym zostanie wygenerowany finalny raport HTML
REPORT_DIR="$RESULTS_BASE_DIR/${FRAMEWORK_NAME}-report"

echo "Czyszczenie starych wyników i przygotowanie katalogów: $RAW_RESULTS_DIR i $REPORT_DIR"
# Usuń stare katalogi (jeśli istnieją) i utwórz nowe, czyste
rm -rf "$RAW_RESULTS_DIR"
rm -rf "$REPORT_DIR"
mkdir -p "$RAW_RESULTS_DIR"

# Licznik nieudanych przebiegów
FAILED_RUNS=0

# Pętla uruchamiająca testy i generująca raport N razy
for i in $(seq 1 $TOTAL_RUNS)
do
  echo ""
  echo "--- Uruchomienie #$i z $TOTAL_RUNS ---"
  echo ""

  # 1. Uruchomienie testów Maven. Wyniki trafią do domyślnego folderu `allure-results`
  mvn clean test

  # 2. Sprawdź, czy uruchomienie zawierało błędy
  if [ $? -ne 0 ]; then
    echo "OSTRZEŻENIE: Uruchomienie #$i zawierało błędy."
    ((FAILED_RUNS++))
  else
    echo "Testy w uruchomieniu #$i zakończone sukcesem."
  fi

  # 3. Przenieś historię z poprzedniego raportu do bieżących wyników
  if [ -d "$REPORT_DIR/history" ]; then
    echo "Kopiowanie historii z poprzedniego raportu..."
    cp -r "$REPORT_DIR/history" "allure-results/"
  fi

  # 4. Wygeneruj raport, nadpisując stary, ale zachowując nową historię
  echo "Generowanie raportu dla uruchomienia #$i..."
  allure generate allure-results --clean -o "$REPORT_DIR"

  # 5. Przenieś wyniki do archiwum i posprzątaj
  # To jest opcjonalne, ale utrzymuje porządek.
  # Tworzymy unikalny folder dla wyników z tego przebiegu.
  mkdir -p "$RAW_RESULTS_DIR/run-$i"
  mv allure-results/* "$RAW_RESULTS_DIR/run-$i/"
done

echo ""
echo "--- PODSUMOWANIE ---"
echo "Zakończono $TOTAL_RUNS przebiegów."
echo "Liczba przebiegów z błędami: $FAILED_RUNS"
echo "Surowe wyniki JSON znajdują się w: $RAW_RESULTS_DIR"
echo "Wygenerowany raport HTML znajduje się w: $REPORT_DIR"
echo "--------------------"
echo ""
echo "Aby otworzyć wygenerowany raport, użyj komendy:"
echo "allure open \"$REPORT_DIR\""
echo ""