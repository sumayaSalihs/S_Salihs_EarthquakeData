/*
Name: Sumaya Ahmed Salihs
Student ID: S1803463
*/

package com.example.mymac.earthquakedata;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class StatisticActivity extends BaseActivity {
    RSSReader rssReader = new RSSReader();
    RSSParser rssParser = new RSSParser();
    AsyncRSSParser1 asyncRSSParser = new AsyncRSSParser1();

    LinkedList<RecentEarthquake> linkedList;


    private TextView jan_count;
    private TextView feb_count;
    private TextView mar_count;
    private TextView sep_count;
    private TextView oct_count;
    private TextView nov_count;
    private TextView dec_count;


    private TextView page_title;
    private TextView year1;
    private TextView year2;
    private TextView jan_label;
    private TextView feb_label;
    private TextView mar_label;
    private TextView sep_label;
    private TextView oct_label;
    private TextView nov_label;
    private TextView dec_label;


    int j_c, f_c, m_c,s_c, o_c, n_c, d_c;
    String page_t,y1, y2, j_l, f_l, m_l, s_l, o_l,n_l,d_l;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);

        jan_count = (TextView) findViewById(R.id.Jan_stat);
        feb_count = (TextView) findViewById(R.id.Feb_stat);
        mar_count = (TextView) findViewById(R.id.Mar_stat);
        sep_count = (TextView) findViewById(R.id.Sep_stat);
        oct_count = (TextView) findViewById(R.id.Oct_stat);
        nov_count = (TextView) findViewById(R.id.Nov_stat);
        dec_count = (TextView) findViewById(R.id.Dec_stat);

        page_title = (TextView) findViewById(R.id.page_head);
        year1 = (TextView) findViewById(R.id.year_2020);
        year2 = (TextView) findViewById(R.id.year2019);
        jan_label = (TextView) findViewById(R.id.Jan_label);
        feb_label = (TextView) findViewById(R.id.Feb_label);
        mar_label = (TextView) findViewById(R.id.Mar_label);
        sep_label = (TextView) findViewById(R.id.Sep_label);
        oct_label = (TextView) findViewById(R.id.Oct_label);
        nov_label = (TextView) findViewById(R.id.Nov_label);
        dec_label = (TextView) findViewById(R.id.Dec_label);




        new AsyncRSSParser1().execute();


        if (savedInstanceState != null) {
            j_c = savedInstanceState.getInt("JAN_C");
            jan_count.setText(String.valueOf(j_c));

            f_c = savedInstanceState.getInt("FEB_C");
            feb_count.setText(String.valueOf(f_c));

            m_c = savedInstanceState.getInt("MAR_C");
            mar_count.setText(String.valueOf(m_c));

            s_c = savedInstanceState.getInt("SEP_C");
            sep_count.setText(String.valueOf(s_c));

            o_c = savedInstanceState.getInt("OCT_C");
            oct_count.setText(String.valueOf(o_c));

            n_c = savedInstanceState.getInt("NOV_C");
            nov_count.setText(String.valueOf(n_c));

            d_c = savedInstanceState.getInt("DEC_C");
            dec_count.setText(String.valueOf(d_c));


            j_l = savedInstanceState.getString("JAN_L");
            jan_label.setText(j_l);

            f_l = savedInstanceState.getString("FEB_L");
            feb_label.setText(f_l);

            m_l = savedInstanceState.getString("MAR_L");
            mar_label.setText(m_l);

            s_l = savedInstanceState.getString("SEP_L");
            sep_label.setText(s_l);

            o_l = savedInstanceState.getString("OCT_L");
            oct_label.setText(o_l);

            n_l = savedInstanceState.getString("NOV_L");
            nov_label.setText(n_l);

            d_l = savedInstanceState.getString("DEC_L");
            dec_label.setText(d_l);


            page_t = savedInstanceState.getString("page_t");
            page_title.setText(page_t);

            y1 = savedInstanceState.getString("y1");
            year1.setText(y1);

            y2 = savedInstanceState.getString("y2");
            year2.setText(y2);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private class AsyncRSSParser1 extends AsyncTask<Void, Void, Void> {


        protected Void doInBackground(Void... nothing) {
            LinkedList<RecentEarthquake> recentEarthquakeList = null;

            // Fetch RSS data from BGS
            rssReader.FetchRSS();

            // Parse the xml of the RSS into a LinkedList of Recent earthquakes
            rssParser.parseRSSString(rssReader.getRssString());

            // Return the LinkedList containing Recent Earthquakes
            linkedList = rssParser.recentEarthquakeList;

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {

            if(linkedList!=null){
                List<RecentEarthquake> list = new ArrayList<RecentEarthquake>(linkedList);
                int j_count = 0;
                int f_count = 0;
                int m_count = 0;
                int s_count = 0;
                int o_count = 0;
                int n_count = 0;
                int d_count = 0;
                int other_month = 0;

                for (int i=0;i<list.size();i++) {
                    if(list.get(i).getPubDate().contains("2020")){
                        if (list.get(i).getPubDate().contains("Jan")){
                            j_count++;
                        }else if(list.get(i).getPubDate().contains("Feb")){
                            f_count++;
                        }else if(list.get(i).getPubDate().contains("Mar")){
                            m_count++;
                        }else {
                            other_month++;
                        }
                    }else {
                        if(list.get(i).getPubDate().contains("2019")){
                            if (list.get(i).getPubDate().contains("Sep")){
                                s_count++;
                            }else if(list.get(i).getPubDate().contains("Oct")){
                                o_count++;
                            }else if(list.get(i).getPubDate().contains("Nov")){
                                n_count++;
                            }
                            else if(list.get(i).getPubDate().contains("Dec")){
                                d_count++;
                            }else {
                                other_month++;
                            }
                        }
                    }
                }
                jan_count.setText(Integer.toString(j_count));
                feb_count.setText(Integer.toString(f_count));
                mar_count.setText(Integer.toString(m_count));
                sep_count.setText(Integer.toString(s_count));
                oct_count.setText(Integer.toString(o_count));
                nov_count.setText(Integer.toString(n_count));
                dec_count.setText(Integer.toString(d_count));

                page_title.setText("Earthquake Monthly Counts");
                year1.setText("Year 2020");
                year2.setText("Year 2019");
                jan_label.setText("January: ");
                feb_label.setText("February: ");
                mar_label.setText("March: ");
                sep_label.setText("September: ");
                oct_label.setText("October: ");
                nov_label.setText("November: ");
                dec_label.setText("December: ");

            }else {
                jan_count.setText("None");
                feb_count.setText("None");
                mar_count.setText("None");

            }

        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("JAN_C",j_c);
        outState.putInt("FEB_C",f_c);
        outState.putInt("MAR_C",m_c);

        outState.putInt("SEP_C",s_c);
        outState.putInt("OCT_C",o_c);
        outState.putInt("NOV_C",n_c);
        outState.putInt("DEC_C",d_c);


        outState.putString("JAN_L",j_l);
        outState.putString("FEB_L",f_l);
        outState.putString("MAR_L",m_l);

        outState.putString("SEP_L",s_l);
        outState.putString("OCT_L",o_l);
        outState.putString("NOV_L",n_l);
        outState.putString("DEC_L",d_l);

        outState.putString("page_t", page_t);
        outState.putString("y1", y1);
        outState.putString("y2",y2);

    }
}

