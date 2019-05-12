package com.neilerua72.polycolo.NF.XML;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.neilerua72.polycolo.NF.Jeu;
import com.neilerua72.polycolo.NF.Regle;
import com.neilerua72.polycolo.NF.TypeJeu;
import com.neilerua72.polycolo.NF.Vote;
import com.neilerua72.polycolo.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class LectureVote {
    ArrayList<Regle> listeVote;
    public LectureVote(Activity app){
        listeVote = new ArrayList<>();
        InputStream inputStream = app.getResources().openRawResource(R.raw.vote);

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(inputStream, null);
            NodeList nodeListText1= XMLUtil.XPathEvaluateExpression.getNodeList("//vote/texte1",doc);
            NodeList nodeListText2= XMLUtil.XPathEvaluateExpression.getNodeList("//vote/texte2",doc);
            Log.e("lecture vote", "test");
            for(int i=0;i<nodeListText1.getLength();i++){
                Element element1 = (Element) nodeListText1.item(i);

                Element element2 = (Element) nodeListText2.item(i);

                listeVote.add(new Vote(TypeJeu.VOTE,element1.getTextContent().trim(),element2.getTextContent().trim()));
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

    public ArrayList<Regle> getListeVote() {
        return listeVote;
    }
}
