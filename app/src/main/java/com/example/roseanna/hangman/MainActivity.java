package com.example.roseanna.hangman;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.*;
import android.util.Log;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public String hiddenWord        = generateWord();
    public char[] displayChar       = new char[hiddenWord.length()];
    public String displayString     = "";
    public int userGuesses          = hiddenWord.length()+5;
    public boolean isDone           = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
        final Button a = (Button) findViewById(R.id.a);
        final Button b = (Button) findViewById(R.id.b);
        final Button c = (Button) findViewById(R.id.c);
        final Button d = (Button) findViewById(R.id.d);
        final Button e = (Button) findViewById(R.id.e);
        final Button f = (Button) findViewById(R.id.f);
        final Button g = (Button) findViewById(R.id.g);
        final Button h = (Button) findViewById(R.id.h);
        final Button i = (Button) findViewById(R.id.i);
        final Button j = (Button) findViewById(R.id.j);
        final Button k = (Button) findViewById(R.id.k);
        final Button l = (Button) findViewById(R.id.l);
        final Button m = (Button) findViewById(R.id.m);
        final Button n = (Button) findViewById(R.id.n);
        final Button o = (Button) findViewById(R.id.o);
        final Button p = (Button) findViewById(R.id.p);
        final Button q = (Button) findViewById(R.id.q);
        final Button r = (Button) findViewById(R.id.r);
        final Button s = (Button) findViewById(R.id.s);
        final Button t = (Button) findViewById(R.id.t);
        final Button u = (Button) findViewById(R.id.u);
        final Button v = (Button) findViewById(R.id.v);
        final Button w = (Button) findViewById(R.id.w);
        final Button x = (Button) findViewById(R.id.x);
        final Button y = (Button) findViewById(R.id.y);
        final Button z = (Button) findViewById(R.id.z);
        final Button reset = (Button) findViewById(R.id.reset);

        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        d.setOnClickListener(this);
        e.setOnClickListener(this);
        f.setOnClickListener(this);
        g.setOnClickListener(this);
        h.setOnClickListener(this);
        i.setOnClickListener(this);
        j.setOnClickListener(this);
        k.setOnClickListener(this);
        l.setOnClickListener(this);
        m.setOnClickListener(this);
        n.setOnClickListener(this);
        o.setOnClickListener(this);
        p.setOnClickListener(this);
        q.setOnClickListener(this);
        r.setOnClickListener(this);
        s.setOnClickListener(this);
        t.setOnClickListener(this);
        u.setOnClickListener(this);
        v.setOnClickListener(this);
        w.setOnClickListener(this);
        x.setOnClickListener(this);
        y.setOnClickListener(this);
        z.setOnClickListener(this);
        reset.setOnClickListener(this);

        resetDC();

    }

    public void resetDC(){
        displayChar = new char[hiddenWord.length()];
        for (int curr = 0; curr < hiddenWord.length(); curr++) {
            displayChar[curr] = '-';
        }
    }
    @Override
    public void onClick(View v){
        if (v.getId() == R.id.reset){
            hiddenWord = generateWord();
            Log.i("reset", hiddenWord);
            userGuesses = hiddenWord.length() + 5;
            isDone = false;
            resetDC();
            setText();
        }
        else if (userGuesses==0 || isDone){
            setText();
            return;
        }
        else {
            char chosen = getLetter(v.getId());
            int pos = hiddenWord.indexOf(chosen);
            if (pos >= 0) {
                for (int i = 0; i < hiddenWord.length(); i++){
                    char c = hiddenWord.charAt(i);
                    if (c == chosen) {
                        Log.i("changing letter", String.valueOf(c));
                        displayChar[i] = chosen;
                    }
                }
            }
            else
                userGuesses--;
            if (String.valueOf(displayChar).equals(hiddenWord))
                isDone = true;
            setText();
        }
    }

    private String generateWord(){
        String [] words = {"handler","against","horizon","chops","junkyard","amoeba","academy","roast",
                "countryside","children","strange","best","drumbeat","amnesiac","chant","amphibian","smuggler","fetish"};
        Random r = new Random();
        int index = r.nextInt(words.length);
        return words[index];
    }
    public char getLetter(int x) {
        int location = 0;
        char chosen;
        char [] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};        int[] ids = {R.id.a, R.id.b, R.id.c, R.id.d, R.id.e, R.id.f,
                R.id.g, R.id.h, R.id.i, R.id.j, R.id.k, R.id.l, R.id.m,
                R.id.n, R.id.o, R.id.p, R.id.q, R.id.r, R.id.s, R.id.t,
                R.id.u, R.id.v, R.id.w, R.id.x, R.id.y, R.id.z};

        for (int i = 0; i < ids.length; i++) {
            if (x == ids[i])
                location = i;
        }
        chosen = letters[location];
        return chosen;
    }
    public void setText() {
        final TextView result = (TextView) findViewById(R.id.resultOutput);
        if (userGuesses == 0)
            displayString = "The word was "+ hiddenWord + ". You lose.";
        else if(isDone)
            displayString = "You got the word, " + hiddenWord + ". Congrats!";
        else
            displayString = String.valueOf(displayChar) + ": you have " + userGuesses + " guesses left";
        result.setText(displayString);
    }

}
