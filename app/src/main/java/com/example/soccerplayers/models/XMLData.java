package com.example.soccerplayers.models;

import android.content.Context;
import android.util.Log;

import com.example.soccerplayers.R;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class XMLData {
    private List <SoccerPlayer>  soccerPlayers;

    public XMLData(Context context) {

        // parse soccer_player.xml to construct data
        InputStream stream = context.getResources().openRawResource(R.raw.soccer_player);
        DocumentBuilder builder;
        Document xmlDoc;

        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            xmlDoc = builder.parse(stream);
            createPlayersListFomXML(xmlDoc);
        } catch (Exception e) {
            Log.d("XML_Parse_Error", "Error at document building.");
        }

    }

    private void createPlayersListFomXML(Document xmlDoc) {

        // chop xmlDoc for tags name, ...
        NodeList nameList = xmlDoc.getElementsByTagName("name");
        NodeList birthList = xmlDoc.getElementsByTagName("birth");
        NodeList heightList = xmlDoc.getElementsByTagName("height");
        NodeList citizenshipList = xmlDoc.getElementsByTagName("citizenship");
        NodeList positionList = xmlDoc.getElementsByTagName("position");
        NodeList currentClubList = xmlDoc.getElementsByTagName("current_club");
        NodeList imageUrlList = xmlDoc.getElementsByTagName("image");
        NodeList infoUrlList = xmlDoc.getElementsByTagName("info");

        // traverse these lists to extract name, ...
        soccerPlayers = new ArrayList<>();

        String name, birth, height, citizenship, position, currentClub, imageUrl,infoUrl;

        for (int index = 0; index < nameList.getLength(); index++) {

            name = getNodeElement(nameList.item(index).getFirstChild().getNodeValue());
            birth = getNodeElement(birthList.item(index).getFirstChild().getNodeValue());
            height = getNodeElement(heightList.item(index).getFirstChild().getNodeValue());
            citizenship = getNodeElement(citizenshipList.item(index).getFirstChild().getNodeValue());
            position = getNodeElement(positionList.item(index).getFirstChild().getNodeValue());
            currentClub = getNodeElement(currentClubList.item(index).getFirstChild().getNodeValue());
            imageUrl = getNodeElement(imageUrlList.item(index).getFirstChild().getNodeValue());
            infoUrl = getNodeElement(infoUrlList.item(index).getFirstChild().getNodeValue());

            soccerPlayers.add(new SoccerPlayer(name, birth, height, citizenship, position, currentClub, imageUrl, infoUrl));
        }
    }

    // some gets methods

    private String getNodeElement(String nodeValue) {
        if (nodeValue != null) {
            return nodeValue;
        }

        return "";
    }

    public List <SoccerPlayer> getPlayers() {
        return soccerPlayers;
    }
}
