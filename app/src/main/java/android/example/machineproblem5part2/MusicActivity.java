package android.example.machineproblem5part2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

class whatQuestion{
    public String theExactQuestion;
    public int answer;

    public void setQuestion (String X){
        Random num = new Random();
        int ranVal = num.nextInt(2);
        switch(X){
            case "Music":
                if(ranVal == 0) theExactQuestion = "Breakup"; //question about how bands broke up
                else theExactQuestion = "Worst Band"; //what is the worst band
                break;
            case "Memes":
                if(ranVal == 0) theExactQuestion = "Lamb Sauce"; //Where's the lamb sauce?
                else theExactQuestion = "Cursed Image"; //911
                break;
            case "Movies/TV Shows":
                if(ranVal == 0) theExactQuestion = "Shrek"; //is love
                else theExactQuestion = "Dang it Bobby"; //That boy ain't right
                break;
            default:
                break;
        }
    }
    public String returnQuestion (){
        System.out.print(theExactQuestion);
        return theExactQuestion;
    }
    public void setAnswer (int X){
        answer = X;
    }
    public int returnAnswer (){
        return answer;
    }
}

public class MusicActivity extends AppCompatActivity {

    //final Intent intent = new Intent(this, MainActivity.class);
    whatQuestion whichQuestion = new whatQuestion();
    //Intent intent = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        Intent intent = getIntent();

        String temp = intent.getStringExtra("Topic");
        whichQuestion.setQuestion(temp);

        EditText typedAnswer = findViewById(R.id.textAnswer);
        RadioGroup radioButtonGroup = findViewById(R.id.radioGroup);
        ImageView theImage = findViewById(R.id.imageView);
        RadioButton button1 = findViewById(R.id.radioButton);
        RadioButton button2 = findViewById(R.id.radioButton2);
        RadioButton button3 = findViewById(R.id.radioButton3);
        TextView questionTitle = findViewById(R.id.questionText);

        radioButtonGroup.setVisibility(View.GONE);
        typedAnswer.setVisibility(View.GONE);

        if(whichQuestion.returnQuestion() == "Breakup") {
            questionTitle.setText("How did the Eagles break up?");
            radioButtonGroup.setVisibility(View.VISIBLE);
            button1.setText("John Lennon's wife, Yoko Ono, was a demanding bitch and pulled the band apart.");
            button2.setText("On stage in 1980, Don Felder said to Glen Frey, 'Only three more songs till I kick your ass, pal'");
            button3.setText("Most of the members died in plane crash in 1977, so that was pretty much the end.");
            theImage.setImageResource(R.drawable.eagles);
        }
        else if (whichQuestion.returnQuestion() == "Worst Band") {
            questionTitle.setText("Who is the worst artist of all time?");
            radioButtonGroup.setVisibility(View.VISIBLE);
            button1.setText("Taylor Swift");
            button2.setText("Imagine Dragons");
            button3.setText("The Black Eyed Peas");
            theImage.setImageResource(R.drawable.lookatthisphotograph);
        }
        else if (whichQuestion.returnQuestion() == "Lamb Sauce") {
            questionTitle.setText("Who is the chef that famously said 'Where is the lamb sauce!?'");
            typedAnswer.setVisibility(View.VISIBLE);
            theImage.setImageResource(R.drawable.whereisit);
        }
        else if (whichQuestion.returnQuestion() == "Cursed Image") {
            questionTitle.setText("What is this meme format referred to as?");
            radioButtonGroup.setVisibility(View.VISIBLE);
            button1.setText("haunted .jpg");
            button2.setText("hexed picture");
            button3.setText("cursed image");
            theImage.setImageResource(R.drawable.cursedimage);
        }
        else if (whichQuestion.returnQuestion() == "Shrek") {
            questionTitle.setText("Please complete:\nShrek is love, Shrek is -----.");
            typedAnswer.setVisibility(View.VISIBLE);
            theImage.setImageResource(R.drawable.shrekislove);
        }
        else if (whichQuestion.returnQuestion() == "Dang it Bobby") {
            questionTitle.setText("What cartoon are these characters from?");
            radioButtonGroup.setVisibility(View.VISIBLE);
            button1.setText("SpongeBob");
            button2.setText("King of the Hill");
            button3.setText("Bojack Horseman");
            theImage.setImageResource(R.drawable.spongebobby);
        }
            else{
        }
    }
    public void radioClick(View view) {
        RadioButton button1 = findViewById(R.id.radioButton);
        RadioButton button2 = findViewById(R.id.radioButton2);
        RadioButton button3 = findViewById(R.id.radioButton3);

        if(view.getId() == R.id.radioButton){
            button1.setChecked(true);
            whichQuestion.setAnswer(1);
        }
        else if(view.getId() == R.id.radioButton2){
            button2.setChecked(true);
            whichQuestion.setAnswer(2);
        }
        else if(view.getId() == R.id.radioButton3){
            button3.setChecked(true);
            whichQuestion.setAnswer(3);
        }
        else{}
    }

    public void submitClick(View view) {
        EditText typedAnswer = findViewById(R.id.textAnswer);
        String answer = typedAnswer.getText().toString();
        boolean questionResults = false;

        //Toast.makeText(MusicActivity.this, whichQuestion.returnAnswer(), Toast.LENGTH_LONG).show();
        //Toast.makeText(MusicActivity.this, answer, Toast.LENGTH_LONG).show();

        if(whichQuestion.returnQuestion() == "Breakup") {
            if(whichQuestion.returnAnswer() == 2) {questionResults = true;}
            else {questionResults = false;}
        }
        if(whichQuestion.returnQuestion() == "Worst Band") {
            if(whichQuestion.returnAnswer() == 1 || whichQuestion.returnAnswer() == 2 || whichQuestion.returnAnswer() == 3) {questionResults = true;}
            else {questionResults = false;}
        }
        if(whichQuestion.returnQuestion() == "Lamb Sauce") {
            if(answer.equals("gordon ramsay") || answer.equals("Gordon Ramsay")) {questionResults = true;}
            else {questionResults = false;}
        }
        if(whichQuestion.returnQuestion() == "Cursed Image") {
            if(whichQuestion.returnAnswer() == 3) {questionResults = true;}
            else {questionResults = false;}
        }
        if(whichQuestion.returnQuestion() == "Shrek") {
            if(answer.equals("life") || answer.equals("Life")) {questionResults = true;}
            else {questionResults = false;}
        }
        if(whichQuestion.returnQuestion() == "Dang it Bobby") {
            if(whichQuestion.returnAnswer() == 1 || whichQuestion.returnAnswer() == 2) {questionResults = true;}
            else {questionResults = false;}
        }
        else {}

        //if(questionResults){ Toast.makeText(MusicActivity.this, "Correct", Toast.LENGTH_LONG).show();}

        Intent sendBack = new Intent();
        sendBack.putExtra("Correct", questionResults);

        setResult(RESULT_OK, sendBack);
        finish();
    }
}
