package com.neilerua72.polycolo.NF;

import android.app.AppComponentFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.neilerua72.polycolo.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LectureJeu {
    ArrayList<Jeu> listeJeu;
    public LectureJeu(AppCompatActivity app){
        listeJeu = new ArrayList<>();
        InputStream inputStream = app.getResources().openRawResource(R.raw.jeu);
        try {
            if (inputStream != null) {
                // open a reader on the inputStream
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                // String used to store the lines
                String str;
                StringBuilder buf = new StringBuilder();

                // Read the file
                while ((str = reader.readLine()) != null) {
                    buf.append(str + "\r\n");
                }
                // Close streams
                reader.close();
                inputStream.close();

            }
        } catch (java.io.FileNotFoundException e) {
            Toast.makeText(app, "FileNotFoundException", Toast.LENGTH_LONG);
        } catch (IOException e) {
            Toast.makeText(app, "FileNotFoundException", Toast.LENGTH_LONG);
        }
    }
}
