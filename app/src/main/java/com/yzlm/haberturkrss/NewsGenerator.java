package com.yzlm.haberturkrss;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by yzlm on 14.06.2017.
 */

public class NewsGenerator  extends AsyncTask<String, Void, List<News>> {
    NewsResultListener listener;

    SimpleDateFormat form = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z",Locale.ENGLISH);
    SimpleDateFormat form2 = new SimpleDateFormat("dd/mm/yyyy",Locale.ENGLISH);

    public NewsGenerator(NewsResultListener listener) {
        this.listener = listener;
    }
    @Override

    protected List<News> doInBackground(String... params) {
        String selectedCategory = params[0];
        String url = "http://www.haberturk.com/rss/" + selectedCategory+".xml";
        ArrayList<News> news = new ArrayList<>();


        try {
            URL xmlUrl = new URL(url);
            InputStream stream = xmlUrl.openStream();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document doc = documentBuilder.parse(stream);

            NodeList list = doc.getElementsByTagName("item");

            for(int i = 0 ; i < list.getLength() ; i++)
            {
                try {
                Element element = (Element) list.item(i);
                String title = element.getElementsByTagName("title").item(0).getFirstChild().getTextContent();
                String link = element.getElementsByTagName("link").item(0).getFirstChild().getTextContent();
                String description = element.getElementsByTagName("description").item(0).getLastChild().getTextContent();
                String image = element.getElementsByTagName("image").item(0).getFirstChild().getTextContent();
                String category = element.getElementsByTagName("category").item(0).getFirstChild().getTextContent();
                String pubDate = element.getElementsByTagName("pubDate").item(0).getFirstChild().getTextContent();

                //CharSequence date=  android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss a", new java.util.Date());

                Date date=form.parse( pubDate);
                pubDate = form2.format(date);
                String id = element.getElementsByTagName("guid").item(0).getFirstChild().getTextContent();
                news.add(new News(id,category,title,description,image,pubDate,link));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            }
        } catch (MalformedURLException e) {
            // Rss kaynağı değişti
            e.printStackTrace();
            listener.onFail("Rss kaynağı değişti");
        } catch (IOException e) {
            // URL bozuk
            e.printStackTrace();
            listener.onFail("URL bozuk");
        } catch (ParserConfigurationException e) {
            // Br hata oluştu
            e.printStackTrace();
            listener.onFail("Br hata oluştu");
        } catch (SAXException e) {
            // Bir hata oluştu
            e.printStackTrace();
            listener.onFail("Bir hata oluştu");
        }

        return news;

    }

    protected void onPostExecute(List<News> news) {

        if(news.isEmpty())
        {
            listener.onFail("Liste boş!");
        }
        else
        {
            listener.onSuccess(news);
        }

        super.onPostExecute(news);
    }
}
