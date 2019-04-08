package com.neilerua72.polycolo.NF.XML;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.neilerua72.polycolo.NF.Jeu;
import com.neilerua72.polycolo.NF.Regle;
import com.neilerua72.polycolo.NF.TypeJeu;
import com.neilerua72.polycolo.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class LectureJeu {
    ArrayList<Regle> listeJeu;
    public LectureJeu(Activity app){
        listeJeu = new ArrayList<>();
        InputStream inputStream = app.getResources().openRawResource(R.raw.jeu);

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(inputStream, null);
            NodeList nodeListJeu= XMLUtil.XPathEvaluateExpression.getNodeList("//jeu",doc);
            for(int i=0;i<nodeListJeu.getLength();i++){
                Element element = (Element) nodeListJeu.item(i);
                listeJeu.add(new Jeu(TypeJeu.JEU,element.getTextContent().trim()));
            }

        } catch (java.io.FileNotFoundException e) {
            Toast.makeText(app, "FileNotFoundException", Toast.LENGTH_LONG);
        } catch (IOException e) {
            Toast.makeText(app, "FileNotFoundException", Toast.LENGTH_LONG);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Regle> getListeJeu() {
        return listeJeu;
    }
}
