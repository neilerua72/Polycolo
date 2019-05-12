package com.neilerua72.polycolo.NF.XML;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.neilerua72.polycolo.NF.Pourcmb;
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

public class LecturePourcmb {
    ArrayList<Regle> listePourcmb;
    public LecturePourcmb(Activity app){
        listePourcmb = new ArrayList<>();
        InputStream inputStream = app.getResources().openRawResource(R.raw.pourcmb);

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(inputStream, null);
            NodeList nodeListText1= XMLUtil.XPathEvaluateExpression.getNodeList("//pourcmb",doc);
            Log.e("lecture pourcmb", "test");
            for(int i=0;i<nodeListText1.getLength();i++){
                Element element = (Element) nodeListText1.item(i);


                listePourcmb.add(new Pourcmb(element.getTextContent().trim()));
                Log.e("Valeur texte :", element.getTextContent().trim());
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

    public ArrayList<Regle> getListePourcmb() {
        return listePourcmb;
    }
}
