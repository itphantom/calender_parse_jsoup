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
    private String htmlPageUrl = "http://duckso.hs.kr/board.list?mcode=1110?mcode=1110";    
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
                Elements links = doc.select("tbody");    
				Elements links2 = links.select("tr");		
				for(Element kk:links2){
					kk.select("a");
				
                    htmlContentInStringFormat += "("+kk.text().trim() + ")\n";    
                }    

            } catch (IOException e) {    
                e.printStackTrace();    
            }    
            
			return null;
		}    

        @Override    
        protected void onPostExecute(Void result) {    
            textviewHtmlDocument.setText(htmlContentInStringFormat);    
			}
    }    


}    
