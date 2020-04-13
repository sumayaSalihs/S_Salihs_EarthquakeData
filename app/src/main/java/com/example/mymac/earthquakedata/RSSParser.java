/*
Name: Sumaya Ahmed Salihs
Student ID: S1803463
*/

package com.example.mymac.earthquakedata;

import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class RSSParser
{
    // Linked List containing recent Earthquakes each with its own title, description and pubDate
    LinkedList <RecentEarthquake> recentEarthquakeList = null;

    // A LINKED LIST FOR HOLDING THE TITLES OF recent earthquakes TO BE USED IN LISTVIEW
    LinkedList <String>  titleList = null;


    public LinkedList<RecentEarthquake> parseRSSString(String rssString)
    {
        RecentEarthquake recentEarthquake = null;

        try
        {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader( rssString ) );
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT)
            {

                // Found a start tag
                if(eventType == XmlPullParser.START_TAG)
                {
                    // Check which Tag we have
                    if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        recentEarthquakeList  = new LinkedList<RecentEarthquake>();
                        titleList     = new LinkedList<String>();

                        for(int i = 0; i<24; i++)
                        {
                            eventType = xpp.next();
                        }
                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        recentEarthquake = new RecentEarthquake();
                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("title"))
                    {
                        // Now just get the associated text
                        String temp = xpp.nextText();
                        recentEarthquake.setTitle(temp);

                    }
                    else
                        // Check which Tag we have
                        if (xpp.getName().equalsIgnoreCase("description"))
                        {
                            // Now just get the associated text
                            String temp = xpp.nextText();
                            recentEarthquake.setDescription(temp);
                        }
                        else
                            // Check which Tag we have
                            if (xpp.getName().equalsIgnoreCase("pubDate"))
                            {
                                // Now just get the associated text
                                String temp = xpp.nextText();
                                recentEarthquake.setPubDate(temp);
                            }
                }
                else
                if(eventType == XmlPullParser.END_TAG)
                {
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        recentEarthquakeList.add(recentEarthquake);
                        titleList.add(recentEarthquake.getTitle());
                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        int size;
                        size = recentEarthquakeList.size();
                    }
                }

                // Get the next event
                eventType = xpp.next();
            }

        }

        catch (XmlPullParserException e)
        {
            System.out.println("Parsing Error "+e.toString());
        }

        catch (IOException e)
        {
            System.out.println("Parsing Error "+e.toString());
        }
        return recentEarthquakeList;
    }


}

