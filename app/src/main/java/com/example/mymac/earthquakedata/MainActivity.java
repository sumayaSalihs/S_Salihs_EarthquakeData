/*
Name: Sumaya Ahmed Salihs
Student ID: S1803463
*/

package com.example.mymac.earthquakedata;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends BaseActivity {

    RSSReader rssReader = new RSSReader();
    RSSParser rssParser = new RSSParser();
    AsyncRSSParser asyncRSSParser = new AsyncRSSParser();

    private ListView ListView_LIST;
    private  ArrayAdapter arrayAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView_LIST = findViewById(R.id.ListView_LIST);
        asyncRSSParser.execute();

        ListView_LIST.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected name
                String selectedTitle = (String)parent.getItemAtPosition(position);

                // Look for Selected Title, once found
                // Display the details of the Title in TextView_TEXTVIEW
                for(int i = 0; i < rssParser.recentEarthquakeList.size();i++)
                {
                    if(selectedTitle.equals(rssParser.recentEarthquakeList.get(i).getTitle()))
                    {

                        String text = rssParser.recentEarthquakeList.get(i).toString();

                        Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                        intent.putExtra("text", text);
                        startActivity(intent);

                        break;
                    }
                }
            }
        });

    }




    private class AsyncRSSParser extends AsyncTask<Void, Void, LinkedList<RecentEarthquake>> {


        protected LinkedList<RecentEarthquake> doInBackground(Void... nothing) {
            LinkedList<RecentEarthquake> recentEarthquakeList = null;

            // Fetch RSS data from BGS
            rssReader.FetchRSS();

            // Parse the xml of the RSS into a LinkedList of Recent Earthquakes
            rssParser.parseRSSString(rssReader.getRssString());

            // Return the LinkedList containing 100 Recent Earthquakes
            return rssParser.recentEarthquakeList;
        }

        // Print the First Element from the recentEarthquakeList returned by doInBackground()
        protected void onPostExecute(LinkedList<RecentEarthquake> recentEarthquakeList) {

            arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, rssParser.titleList);
            ListView_LIST.setAdapter(arrayAdapter);
            // END
        }

   }


}
