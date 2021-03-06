package com.example.phantom;

import android.os.AsyncTask;    
import android.support.v7.app.AppCompatActivity;    
import android.os.Bundle;    
import android.text.method.ScrollingMovementMethod;    
import android.view.View;    
import android.widget.Button;    
import android.widget.TextView;    

import org.jsoup.Jsoup;    
import org.jsoup.nodes.Document;    
import org.jsoup.nodes.Element;    
import org.jsoup.select.Elements;    

import java.io.IOException;    


public class MainActivity extends AppCompatActivity {    
private Elements roopp;
    private String htmlPageUrl = "http://duckso.hs.kr/calendar.list?ym=201605";    
    private TextView textviewHtmlDocument;    
	private TextView textviewHtmlDocument2;
    private String htmlContentInStringFormat;    
	private String htmlContentInStringFormat2;

    @Override    
    protected void onCreate(Bundle savedInstanceState) {    
        super.onCreate(savedInstanceState);    
        setContentView(R.layout.main);    


        textviewHtmlDocument = (TextView)findViewById(R.id.textView);    
        textviewHtmlDocument.setMovementMethod(new ScrollingMovementMethod());    
		textviewHtmlDocument2 = (TextView)findViewById(R.id.textView2);    
        textviewHtmlDocument2.setMovementMethod(new ScrollingMovementMethod());    
		
        Button htmlTitleButton = (Button)findViewById(R.id.button);    
        htmlTitleButton.setOnClickListener(new View.OnClickListener() {    
				@Override    
				public void onClick(View v) {    
					JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();    
					jsoupAsyncTask.execute();    
				}    
			});    
    }    

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {    

        @Override    
        protected void onPreExecute() {    
            super.onPreExecute();    
        }    

        @Override    
        protected Void doInBackground(Void... params) {    
            try {    
                Document doc = Jsoup.connect(htmlPageUrl).get();    
                Elements links = doc.select("#contentbox");    
				Elements links2 = links.select("td");
				
				Element sw;
				
					for(Element kkk:links2){
						if(kkk.toString().contains("<ul>")){
					
					sw=kkk.select("span").first();
							htmlContentInStringFormat2 += sw.text().trim()+"일\n";
				}
					
				}
			Elements links3 = links2.select("a[href]");
                for (Element link : links3) {    
                    htmlContentInStringFormat += "("+link.text().trim() + ")\n";    
                }    

            } catch (IOException e) {    
                e.printStackTrace();    
            }    
            
			return null;
		}    

        @Override    
        protected void onPostExecute(Void result) {    
            textviewHtmlDocument.setText(htmlContentInStringFormat.replaceAll("null",""));    
			textviewHtmlDocument2.setText(htmlContentInStringFormat2.replaceAll("null",""));
        }    
    }    


}    
