package com.example.midtermprojectdizayn;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.midtermprojectdizayn.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private String[][] countryHints ={

            {"1.445.025.408", "Renminbi", "Asya", "cince", "Pekin"},
            {"45.808.250", "Arjantin pesosu", "Güney Amerika", "İspanyolca", "Buenos Aires"},
            {"10.248.551", "Azerbaycan manatı", "Avrupa", "Azerice", "Bakü"},
            {"407.906", "Bahama doları", "Amerika", "İngilizce", "Nassau"},
            {"60.282.261", "Euro", "Avrupa", "İtalyanca", "Roma"},
            {"3.259.696", "Marka", "Avrupa", "Boşnakça", "Saraybosna"},
            {"44.936.046", "Dinar", "Afrika", "Arapça", "Cezayir"},
            {"10.723.619", "Koruna", "Avrupa", "Çekçe", "Prag"},
            {"119.499.870", "Birri", "Afrika", "Amharca", "Addis Ababa"},
            {"83.701.000", "Euro", "Avrupa", "Almanca", "Berlin"}
    };

private String[] countries = {"Cin", "Arjantin", "Azerbaycan", "Bahamalar", "İtalya", "Bosna-Hersek", "Cezayir", "Çek Cumhuriyeti", "Etiyopya", "Almanya"};
    private int currentCountryIndex;
    private int remainingGuesses = 3;
    private int score = 0;

    private TextView scoreTextView;
    private TextView remainingGuessesTextView;
    private TextView countryInfoTextView;
    private EditText guessEditText;
    private TextView hintTextView;
    private ImageView countryImageView;
    int clueNumber=0;

    private final List<Integer> imageResources = Arrays.asList(
            R.drawable.cin1,
            R.drawable.arjantin,
            R.drawable.azerbaycan,
            R.drawable.bahamalar,
            R.drawable.italya,
            R.drawable.bosnahersek,
            R.drawable.cezayir,
            R.drawable.cek,
            R.drawable.etiyopya,
            R.drawable.almanya
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTextView = findViewById(R.id.textView4);
        remainingGuessesTextView = findViewById(R.id.textView2);
        countryInfoTextView = findViewById(R.id.textView3);
        guessEditText = findViewById(R.id.editTextText);
        hintTextView = findViewById(R.id.hintTextView);
        countryImageView = findViewById(R.id.countryImageView);

        updateGame();

        List<Button> clueButtons = Arrays.asList(
                findViewById(R.id.birinciipucu),
                findViewById(R.id.ikinciipucu),
                findViewById(R.id.ucuncuipucu),
                findViewById(R.id.dorduncuipucu),
                findViewById(R.id.besinciipucu)
        );
        for (int i = 0; i < clueButtons.size(); i++) {
            int finalI = i;
            clueButtons.get(i).setOnClickListener(v -> showClue());
        }
    }
    @SuppressLint("SetTextI18n")
    private void updateGame() {
        List<Integer> indexes = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.shuffle(indexes);
        currentCountryIndex = indexes.get(0);

        scoreTextView.setText("Skor: " + score);
        remainingGuessesTextView.setText("Tahmin Hakkı: " + remainingGuesses);
        countryInfoTextView.setText("İpuçlarının görüntüleneceği alan.");
        hintTextView.setText("");

        int randomImageIndex = new Random().nextInt(imageResources.size());
        countryImageView.setImageResource(imageResources.get(randomImageIndex));

        guessEditText.setText("");
    }
    @SuppressLint("SetTextI18n")
    private void showClue() {
        if (clueNumber == 0) {
            hintTextView.setText("Nüfus: " + countryHints[][0]);
        } else if (clueNumber == 1) {
            hintTextView.setText("Para Birimi: " + countryHints[currentCountryIndex][1]);
        } else if (clueNumber == 2) {
            hintTextView.setText("Kıta: " + countryHints[currentCountryIndex][2]);
        } else if (clueNumber == 3) {
            hintTextView.setText("Resmi Dili: " + countryHints[currentCountryIndex][3]);
        } else if (clueNumber == 4) {
            hintTextView.setText("Başkenti: " + countryHints[currentCountryIndex][4]);
        }

        String userGuess = guessEditText.getText().toString();
        if (userGuess.equalsIgnoreCase(countryHints[currentCountryIndex][0])) {
            score += 100;
            updateGame();
        } else {
            remainingGuesses--;
            score -= 10;
            updateGame();

            if (remainingGuesses == 0) {
                hintTextView.setText("Tahmin hakkınız kalmadı. Doğru yanıt: " + countryHints[currentCountryIndex][0]);
                resetGame();
            }
        }
    }
    private String formatPopulationHint(int population) {
        return NumberFormat.getNumberInstance(Locale.getDefault()).format(population) + " kişi";
    }
    private int getRandomPopulation() {
        return new Random().nextInt(2000000000 - 1000000 + 1) + 1000000;
    }
    private void resetGame() {
        score = 0;
        remainingGuesses = 3;
        updateGame();
    }
}