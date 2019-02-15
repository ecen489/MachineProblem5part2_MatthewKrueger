package android.example.machineproblem5part2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class ReadWrite {
    int totalScore;

    public void writeToFile(String data, Context location){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(location.openFileOutput("scores.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addScore(int score){
        totalScore = score + totalScore;
    }

    public int returnScore(){
        return totalScore;
    }

    public String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream input = context.openFileInput("scores.txt");

            if ( input != null )
            {
                InputStreamReader inputReader = new InputStreamReader(input);
                BufferedReader buff = new BufferedReader(inputReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = buff.readLine()) != null )
                {
                    stringBuilder.append(receiveString);
                }
                input.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e)
        {
            Log.e("log:", "No File" + e.toString());
        } catch (IOException e)
        {
            Log.e("log:", "Unreadable" + e.toString());
        }
        return ret;
    }
}

public class MainActivity extends AppCompatActivity {

    private static final String[] questionTopics = {"Music", "Memes", "Movies/TV Shows"};
    ReadWrite readWrite = new ReadWrite();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListAdapter listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, questionTopics);

        ListView topicsListView = (ListView) findViewById(R.id.questionTopics);
        topicsListView.setAdapter(listAdapter);

        final Intent intent = new Intent(this, MusicActivity.class);

        topicsListView.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String selectedTopic = String.valueOf(parent.getItemAtPosition(position));
                    switch(selectedTopic) {
                        case "Music":
                            intent.putExtra("Topic", questionTopics[0]);
                            //Toast.makeText(MainActivity.this, questionTopics[0], Toast.LENGTH_LONG).show();
                            startActivityForResult(intent, 1);
                            break;
                        case "Memes":
                            intent.putExtra("Topic", questionTopics[1]);
                            startActivityForResult(intent, 1);
                            break;
                        case "Movies/TV Shows":
                            intent.putExtra("Topic", questionTopics[2]);
                            startActivityForResult(intent, 1);
                            break;
                        default:
                            break;
                    }
                }
            }
        );
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        TextView scoreView = (TextView) findViewById(R.id.score);
        if (requestCode == 1)
        {
            boolean correct = intent.getBooleanExtra("Correct", true);
            if (correct) {
                Toast.makeText(this, "Correct! +1", Toast.LENGTH_SHORT).show();
                readWrite.addScore(1);
                scoreView.setText(String.valueOf(readWrite.returnScore()));
                //readWrite.writeToFile(String.valueOf(readWrite.returnScore()));
            } else {
                Toast.makeText(this, "Incorrect... -999", Toast.LENGTH_SHORT).show();
                readWrite.addScore(-999);
                scoreView.setText(String.valueOf(readWrite.returnScore()));
            }
        }
    }
}