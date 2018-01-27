package com.example.android.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    //max piece numbers
    static int max_pawns = 8;
    static int max_rooks_bishops_knights = 2;
    static int max_queens = 1;
    //min piece number
    static int min_piece = 0;
    //piece values
    static int pawn_value = 1;
    static int knight_and_bishop_value = 3;
    static int rook_value = 5;
    static int queen_value = 9;
    //'who's winning textview'
    TextView whos_winning;
    //score views
    TextView white_score_view;
    TextView black_score_view;
    //sore variables
    int score_for_white = 0;
    int score_for_black = 0;
    //variables for white pieces left
    int whiteQueensNumber = 1;
    int whiteRooksNumber = 2;
    int whiteBishopsNumber = 2;
    int whiteKnightsNumber = 2;
    int whitePawnsNumber = 8;
    //variables for black pieces left
    int blackQueensNumber = 1;
    int blackRooksNumber = 2;
    int blackBishopsNumber = 2;
    int blackKnightsNumber = 2;
    int blackPawnsNumber = 8;

    //variables to store dimens
    float reg_piece_xy_dimens;
    float higher_alpha;
    float lower_alpha;

    ArrayList<String> img_ids;

    ImageView black_queen, black_rook, black_bishop, black_knight, black_pawn;
    ImageView white_queen, white_rook, white_bishop, white_knight, white_pawn;

    ArrayList<ImageButton> img_plus;
    ArrayList<ImageButton> img_minus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_ids = new ArrayList<String>();
        img_minus = new ArrayList<ImageButton>();
        img_plus = new ArrayList<ImageButton>();

        //find views by id
        black_queen = findViewById(R.id.black_queen_image);
        black_rook = findViewById(R.id.black_rook_image);
        black_bishop = findViewById(R.id.black_bishop_image);
        black_knight = findViewById(R.id.black_knight_image);
        black_pawn = findViewById(R.id.black_pawn_image);

        white_queen = findViewById(R.id.white_queen_image);
        white_rook = findViewById(R.id.white_rook_image);
        white_bishop = findViewById(R.id.white_bishop_image);
        white_knight = findViewById(R.id.white_knight_image);
        white_pawn = findViewById(R.id.white_pawn_image);

        white_score_view = findViewById(R.id.score_for_white_text_view);
        black_score_view = findViewById(R.id.score_for_black_text_view);
        whos_winning = findViewById(R.id.bottom_text_view);

        //find by id and add to img_plus array list
        img_plus.add((ImageButton) findViewById(R.id.plus_black_queen));
        img_plus.add((ImageButton) findViewById(R.id.plus_black_rook));
        img_plus.add((ImageButton) findViewById(R.id.plus_black_bishop));
        img_plus.add((ImageButton) findViewById(R.id.plus_black_knight));
        img_plus.add((ImageButton) findViewById(R.id.plus_black_pawn));

        //find by id and add to img_plus array list
        img_plus.add((ImageButton) findViewById(R.id.plus_white_queen));
        img_plus.add((ImageButton) findViewById(R.id.plus_white_rook));
        img_plus.add((ImageButton) findViewById(R.id.plus_white_bishop));
        img_plus.add((ImageButton) findViewById(R.id.plus_white_knight));
        img_plus.add((ImageButton) findViewById(R.id.plus_white_pawn));

        //find by id and add to img_minus array list
        img_minus.add((ImageButton) findViewById(R.id.minus_black_queen));
        img_minus.add((ImageButton) findViewById(R.id.minus_black_rook));
        img_minus.add((ImageButton) findViewById(R.id.minus_black_bishop));
        img_minus.add((ImageButton) findViewById(R.id.minus_black_knight));
        img_minus.add((ImageButton) findViewById(R.id.minus_black_pawn));

        //find by id and add to img_minus array list
        img_minus.add((ImageButton) findViewById(R.id.minus_white_queen));
        img_minus.add((ImageButton) findViewById(R.id.minus_white_rook));
        img_minus.add((ImageButton) findViewById(R.id.minus_white_bishop));
        img_minus.add((ImageButton) findViewById(R.id.minus_white_knight));
        img_minus.add((ImageButton) findViewById(R.id.minus_white_pawn));


        TypedValue outValue = new TypedValue();

        getResources().getValue(R.dimen.reg_piece_scale_xy_dimens, outValue, true);
        reg_piece_xy_dimens = outValue.getFloat();

        getResources().getValue(R.dimen.higher_alpha, outValue, true);
        higher_alpha = outValue.getFloat();

        getResources().getValue(R.dimen.lower_alpha, outValue, true);
        lower_alpha = outValue.getFloat();


        //minus white
        ImageButton minus_white_queen = findViewById(R.id.minus_white_queen);
        minus_white_queen.setOnClickListener(this);

        ImageButton minus_white_rook = findViewById(R.id.minus_white_rook);
        minus_white_rook.setOnClickListener(this);

        ImageButton minus_white_bishop = findViewById(R.id.minus_white_bishop);
        minus_white_bishop.setOnClickListener(this);


        ImageButton minus_white_knight = findViewById(R.id.minus_white_knight);
        minus_white_knight.setOnClickListener(this);


        ImageButton minus_white_pawn = findViewById(R.id.minus_white_pawn);
        minus_white_pawn.setOnClickListener(this);


        //minus black

        ImageButton minus_black_queen = findViewById(R.id.minus_black_queen);
        minus_black_queen.setOnClickListener(this);

        ImageButton minus_black_rook = findViewById(R.id.minus_black_rook);
        minus_black_rook.setOnClickListener(this);

        ImageButton minus_black_bishop = findViewById(R.id.minus_black_bishop);
        minus_black_bishop.setOnClickListener(this);


        ImageButton minus_black_knight = findViewById(R.id.minus_black_knight);
        minus_black_knight.setOnClickListener(this);


        ImageButton minus_black_pawn = findViewById(R.id.minus_black_pawn);
        minus_black_pawn.setOnClickListener(this);


        //plus white

        ImageButton plus_white_queen = findViewById(R.id.plus_white_queen);
        plus_white_queen.setOnClickListener(this);

        ImageButton plus_white_rook = findViewById(R.id.plus_white_rook);
        plus_white_rook.setOnClickListener(this);

        ImageButton plus_white_bishop = findViewById(R.id.plus_white_bishop);
        plus_white_bishop.setOnClickListener(this);


        ImageButton plus_white_knight = findViewById(R.id.plus_white_knight);
        plus_white_knight.setOnClickListener(this);


        ImageButton plus_white_pawn = findViewById(R.id.plus_white_pawn);
        plus_white_pawn.setOnClickListener(this);


        //plus black

        ImageButton plus_black_queen = findViewById(R.id.plus_black_queen);
        plus_black_queen.setOnClickListener(this);

        ImageButton plus_black_rook = findViewById(R.id.plus_black_rook);
        plus_black_rook.setOnClickListener(this);

        ImageButton plus_black_bishop = findViewById(R.id.plus_black_bishop);
        plus_black_bishop.setOnClickListener(this);


        ImageButton plus_black_knight = findViewById(R.id.plus_black_knight);
        plus_black_knight.setOnClickListener(this);


        ImageButton plus_black_pawn = findViewById(R.id.plus_black_pawn);
        plus_black_pawn.setOnClickListener(this);


        Button reset = findViewById(R.id.reset_button);
        reset.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {


        switch (view.getId()) {
//minus black
            case R.id.minus_black_queen:
                if (blackQueensNumber < max_queens) {
                    decreaseScoreForWhite(queen_value);
                    blackQueensNumber++;
                    decrease_alpha(R.id.black_queen_image);
                    check_or_fix_plusORminus_alpha(R.id.plus_black_queen, R.id.minus_black_queen, max_queens - blackQueensNumber, max_queens);
                }
                break;
            case R.id.minus_black_rook:
                if (blackRooksNumber < max_rooks_bishops_knights) {
                    decreaseScoreForWhite(rook_value);
                    delete_piece(25, max_rooks_bishops_knights - blackRooksNumber, R.id.black_rook_image);
                    blackRooksNumber++;
                    check_or_fix_plusORminus_alpha(R.id.plus_black_rook, R.id.minus_black_rook, max_rooks_bishops_knights - blackRooksNumber, max_rooks_bishops_knights);
                }
                break;
            case R.id.minus_black_bishop:
                if (blackBishopsNumber < max_rooks_bishops_knights) {
                    decreaseScoreForWhite(knight_and_bishop_value);
                    delete_piece(23, max_rooks_bishops_knights - blackBishopsNumber, R.id.black_bishop_image);
                    blackBishopsNumber++;
                    check_or_fix_plusORminus_alpha(R.id.plus_black_bishop, R.id.minus_black_bishop, max_rooks_bishops_knights - blackBishopsNumber, max_rooks_bishops_knights);
                }
                break;
            case R.id.minus_black_knight:
                if (blackKnightsNumber < max_rooks_bishops_knights) {
                    decreaseScoreForWhite(knight_and_bishop_value);
                    delete_piece(22, max_rooks_bishops_knights - blackKnightsNumber, R.id.black_knight_image);
                    blackKnightsNumber++;
                    check_or_fix_plusORminus_alpha(R.id.plus_black_knight, R.id.minus_black_knight, max_rooks_bishops_knights - blackKnightsNumber, max_rooks_bishops_knights);
                }
                break;
            case R.id.minus_black_pawn:
                if (blackPawnsNumber < max_pawns) {
                    decreaseScoreForWhite(pawn_value);
                    delete_piece(21, max_pawns - blackPawnsNumber, R.id.black_pawn_image);
                    blackPawnsNumber++;
                    check_or_fix_plusORminus_alpha(R.id.plus_black_pawn, R.id.minus_black_pawn, max_pawns - blackPawnsNumber, max_pawns);
                }
                break;
//minus white
            case R.id.minus_white_queen:
                if (whiteQueensNumber < max_queens) {
                    decreaseScoreForBlack(queen_value);
                    whiteQueensNumber++;
                    decrease_alpha(R.id.white_queen_image);
                    check_or_fix_plusORminus_alpha(R.id.plus_white_queen, R.id.minus_white_queen, max_queens - whiteQueensNumber, max_queens);
                }
                break;
            case R.id.minus_white_rook:
                if (whiteRooksNumber < max_rooks_bishops_knights) {
                    decreaseScoreForBlack(rook_value);
                    delete_piece(15, max_rooks_bishops_knights - whiteRooksNumber, R.id.white_rook_image);
                    whiteRooksNumber++;
                    check_or_fix_plusORminus_alpha(R.id.plus_white_rook, R.id.minus_white_rook, max_rooks_bishops_knights - whiteRooksNumber, max_rooks_bishops_knights);
                }
                break;
            case R.id.minus_white_bishop:
                if (whiteBishopsNumber < max_rooks_bishops_knights) {
                    decreaseScoreForBlack(knight_and_bishop_value);
                    delete_piece(13, max_rooks_bishops_knights - whiteBishopsNumber, R.id.white_bishop_image);
                    whiteBishopsNumber++;
                    check_or_fix_plusORminus_alpha(R.id.plus_white_bishop, R.id.minus_white_bishop, max_rooks_bishops_knights - whiteBishopsNumber, max_rooks_bishops_knights);
                }
                break;
            case R.id.minus_white_knight:
                if (whiteKnightsNumber < max_rooks_bishops_knights) {
                    decreaseScoreForBlack(knight_and_bishop_value);
                    delete_piece(12, max_rooks_bishops_knights - whiteKnightsNumber, R.id.white_knight_image);
                    whiteKnightsNumber++;
                    check_or_fix_plusORminus_alpha(R.id.plus_white_knight, R.id.minus_white_knight, max_rooks_bishops_knights - whiteKnightsNumber, max_rooks_bishops_knights);
                }
                break;
            case R.id.minus_white_pawn:
                if (whitePawnsNumber < max_pawns) {
                    decreaseScoreForBlack(pawn_value);
                    delete_piece(11, max_pawns - whitePawnsNumber, R.id.white_pawn_image);
                    whitePawnsNumber++;
                    check_or_fix_plusORminus_alpha(R.id.plus_white_pawn, R.id.minus_white_pawn, max_pawns - whitePawnsNumber, max_pawns);
                }
                break;
//plus black
            case R.id.plus_black_queen:
                if (blackQueensNumber > min_piece) {
                    increaseScoreForWhite(queen_value);
                    blackQueensNumber--;
                    increase_alpha(R.id.black_queen_image);
                    check_or_fix_plusORminus_alpha(R.id.plus_black_queen, R.id.minus_black_queen, max_queens - blackQueensNumber, max_queens);

                }
                break;
            case R.id.plus_black_rook:
                if (blackRooksNumber > min_piece) {
                    increaseScoreForWhite(rook_value);
                    blackRooksNumber--;
                    add_piece(R.drawable.black_rook, R.id.two_black_rooks,
                            max_rooks_bishops_knights - blackRooksNumber
                            , R.id.black_rook_image,
                            create_Id(2, 5, max_rooks_bishops_knights - blackRooksNumber));
                    check_or_fix_plusORminus_alpha(R.id.plus_black_rook, R.id.minus_black_rook, max_rooks_bishops_knights - blackRooksNumber, max_rooks_bishops_knights);

                }
                break;
            case R.id.plus_black_bishop:
                if (blackBishopsNumber > min_piece) {
                    increaseScoreForWhite(knight_and_bishop_value);
                    blackBishopsNumber--;
                    add_piece(R.drawable.black_bishop, R.id.two_black_bishops,
                            max_rooks_bishops_knights - blackBishopsNumber
                            , R.id.black_bishop_image,
                            create_Id(2, 3, max_rooks_bishops_knights - blackBishopsNumber));
                    check_or_fix_plusORminus_alpha(R.id.plus_black_bishop, R.id.minus_black_bishop, max_rooks_bishops_knights - blackBishopsNumber, max_rooks_bishops_knights);

                }
                break;
            case R.id.plus_black_knight:
                if (blackKnightsNumber > min_piece) {
                    increaseScoreForWhite(knight_and_bishop_value);
                    blackKnightsNumber--;
                    add_piece(R.drawable.black_knight, R.id.two_black_knights,
                            max_rooks_bishops_knights - blackKnightsNumber
                            , R.id.black_knight_image,
                            create_Id(2, 2, max_rooks_bishops_knights - blackKnightsNumber));
                    check_or_fix_plusORminus_alpha(R.id.plus_black_knight, R.id.minus_black_knight, max_rooks_bishops_knights - blackKnightsNumber, max_rooks_bishops_knights);

                }
                break;
            case R.id.plus_black_pawn:
                if (blackPawnsNumber > min_piece) {
                    increaseScoreForWhite(pawn_value);
                    blackPawnsNumber--;
                    add_piece(R.drawable.black_pawn, R.id.eight_black_pawns,
                            max_pawns - blackPawnsNumber
                            , R.id.black_pawn_image,
                            create_Id(2, 1, max_pawns - blackPawnsNumber));
                    check_or_fix_plusORminus_alpha(R.id.plus_black_pawn, R.id.minus_black_pawn, max_pawns - blackPawnsNumber, max_pawns);
                }
                break;
//plus white
            case R.id.plus_white_queen:
                if (whiteQueensNumber > min_piece) {
                    increaseScoreForBlack(queen_value);
                    whiteQueensNumber--;
                    increase_alpha(R.id.white_queen_image);
                    check_or_fix_plusORminus_alpha(R.id.plus_white_queen, R.id.minus_white_queen, max_queens - whiteQueensNumber, max_queens);

                }
                break;
            case R.id.plus_white_rook:
                if (whiteRooksNumber > min_piece) {
                    whiteRooksNumber--;
                    increaseScoreForBlack(rook_value);

                    add_piece(R.drawable.white_rook, R.id.two_white_rooks,
                            max_rooks_bishops_knights - whiteRooksNumber
                            , R.id.white_rook_image,
                            create_Id(1, 5, max_rooks_bishops_knights - whiteRooksNumber));
                    check_or_fix_plusORminus_alpha(R.id.plus_white_rook, R.id.minus_white_rook, max_rooks_bishops_knights - whiteRooksNumber, max_rooks_bishops_knights);


                }
                break;
            case R.id.plus_white_bishop:
                if (whiteBishopsNumber > min_piece) {
                    increaseScoreForBlack(knight_and_bishop_value);
                    whiteBishopsNumber--;
                    add_piece(R.drawable.white_bishop, R.id.two_white_bishops,
                            max_rooks_bishops_knights - whiteBishopsNumber
                            , R.id.white_bishop_image,
                            create_Id(1, 3, max_rooks_bishops_knights - whiteBishopsNumber));
                    check_or_fix_plusORminus_alpha(R.id.plus_white_bishop, R.id.minus_white_bishop, max_rooks_bishops_knights - whiteBishopsNumber, max_rooks_bishops_knights);
                }
                break;
            case R.id.plus_white_knight:
                if (whiteKnightsNumber > min_piece) {
                    increaseScoreForBlack(knight_and_bishop_value);
                    whiteKnightsNumber--;
                    add_piece(R.drawable.white_knight, R.id.two_white_knights,
                            max_rooks_bishops_knights - whiteKnightsNumber
                            , R.id.white_knight_image,
                            create_Id(1, 2, max_rooks_bishops_knights - whiteKnightsNumber));
                    check_or_fix_plusORminus_alpha(R.id.plus_white_knight, R.id.minus_white_knight, max_rooks_bishops_knights - whiteKnightsNumber, max_rooks_bishops_knights);
                }
                break;
            case R.id.plus_white_pawn:
                if (whitePawnsNumber > min_piece) {
                    increaseScoreForBlack(pawn_value);
                    whitePawnsNumber--;
                    add_piece(R.drawable.white_pawn, R.id.eight_white_pawns,
                            max_pawns - whitePawnsNumber
                            , R.id.white_pawn_image,
                            create_Id(1, 1, max_pawns - whitePawnsNumber));

                    check_or_fix_plusORminus_alpha(R.id.plus_white_pawn, R.id.minus_white_pawn, max_pawns - whitePawnsNumber, max_pawns);

                }
                break;


            case R.id.reset_button:
                reset();
                break;
        }

    }


    private void increaseScoreForWhite(int increase_value) {
        score_for_white = score_for_white + increase_value;
        displayScore();
    }

    private void increaseScoreForBlack(int increase_value) {
        score_for_black = score_for_black + increase_value;
        displayScore();
    }

    private void decreaseScoreForWhite(int decrease_value) {
        score_for_white = score_for_white - decrease_value;
        displayScore();
    }

    private void decreaseScoreForBlack(int decrease_value) {
        score_for_black = score_for_black - decrease_value;
        displayScore();
    }


    public void displayScore()

    {
        white_score_view.setText(valueOf(score_for_white));
        black_score_view.setText(valueOf(score_for_black));
    }


    private void reset() {
        //variables piece number reset
        whiteQueensNumber = 1;
        whiteRooksNumber = 2;
        whiteBishopsNumber = 2;
        whiteKnightsNumber = 2;
        whitePawnsNumber = 8;

        blackQueensNumber = 1;
        blackRooksNumber = 2;
        blackBishopsNumber = 2;
        blackKnightsNumber = 2;
        blackPawnsNumber = 8;
        //pieces alphas
        black_queen.setAlpha(lower_alpha);
        black_rook.setAlpha(lower_alpha);
        black_bishop.setAlpha(lower_alpha);
        black_knight.setAlpha(lower_alpha);
        black_pawn.setAlpha(lower_alpha);

        white_queen.setAlpha(lower_alpha);
        white_rook.setAlpha(lower_alpha);
        white_bishop.setAlpha(lower_alpha);
        white_knight.setAlpha(lower_alpha);
        white_pawn.setAlpha(lower_alpha);

        for (int i = 0; i < img_minus.size(); i++) {
            img_minus.get(i).setAlpha(lower_alpha);
            img_plus.get(i).setAlpha(higher_alpha);
        }


        for (int i = 0; i < img_ids.size(); i++) {

            String strId = img_ids.get(i);
            ImageView image = findViewById(Integer.parseInt(strId));

            try {
                image.setId(0);
                image.setVisibility(View.GONE);
            } catch (NullPointerException n) {
            } finally {

            }


        }
        score_for_white = 0;
        score_for_black = 0;


        displayScore();
        display_difference_message();
    }

    //methods handling piece images

    private void add_piece(int what_image, int layout_id,
                           int number_of_pieces, int id_of_image_to_change_its_alpha,
                           int new_piece_id)

    {

        if (number_of_pieces == 1)

        {

            increase_alpha(id_of_image_to_change_its_alpha);
        } else {

            ImageView iv = new ImageView(this);
            iv.setImageResource(what_image);
            iv.setScaleX(reg_piece_xy_dimens);
            iv.setScaleY(reg_piece_xy_dimens);
            LinearLayout rl = findViewById(layout_id);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.weight = 1.0f;
            iv.setId(new_piece_id);
            rl.addView(iv, lp);

            img_ids.add(valueOf(new_piece_id));


        }

    }

    private void increase_alpha(int view_id) {
        ImageView image = findViewById(view_id);
        image.setAlpha(higher_alpha);

    }

    private void decrease_alpha(int view_id) {
        ImageView image = findViewById(view_id);
        image.setAlpha(lower_alpha);


    }

    // first number of id is team 1 for white, 2 for black; second is value (3 for bishop, 2 for knight); third number of added piece
//arguments ie pawn, white, third: 113

    private int create_Id(int team, int piece, int number) {

        String t = valueOf(team);
        String p = valueOf(piece);
        String n = valueOf(number);
        String a = t + p + n;
        int id = Integer.parseInt(a);
        return id;
    }


    private void delete_piece(int id_1st_and_2nd_number, int third_number, int id_of_image_to_change_its_alpha
    ) {

        String a = valueOf(id_1st_and_2nd_number);
        String b = valueOf(third_number);

        String c = a + b;
        int view_id = Integer.parseInt(c);
        ImageView image = findViewById(view_id);


        if (third_number == 1) {
            decrease_alpha(id_of_image_to_change_its_alpha);

        } else {
            img_ids.remove(valueOf(view_id));
            image.setId(0);

            image.setVisibility(View.GONE);

        }


    }

    private void check_or_fix_plusORminus_alpha(int plus_id, int minus_id, int numberOfPieces, int max_number_of_pieces)

    //also calls display_difference_message method
    {
        if (numberOfPieces == 0) {
            decrease_alpha(minus_id);

        } else {
            increase_alpha(minus_id);
        }
        if (numberOfPieces == max_number_of_pieces)

        {
            decrease_alpha(plus_id);
        } else {
            increase_alpha(plus_id);
        }
        display_difference_message();

    }

    public void display_difference_message()

    {

        whos_winning.setText(valueOf(create_difference_message()));
    }


    private String create_difference_message() //1 for white 2 for black 0 for a draw

    {
        int difference = count_difference();
        String who_is_winning = tell_who_is_winning();
        String d = valueOf(difference);
        String p = "";

        String message = "";

        if (difference == 1) {
            p = getString(R.string.dif_mes_point);
        } else if (difference != 0) {
            p = getString(R.string.dif_mes_points);

        }
        message = who_is_winning + getString(R.string.dim_mes_is) + d + " " + p + getString(R.string.dif_mes_ahead);
        if (difference == 0) {
            message = getString(R.string.dif_mes_draw);
        }

        return message;

    }


    private String tell_who_is_winning() {

        String who = "";
        if (score_for_black > score_for_white) {
            who = getString(R.string.Black);
        }
        if (score_for_black < score_for_white) {
            who = getString(R.string.White);
        }
        if (score_for_black == score_for_white) {
            who = "";
        }

        return who;

    }

    private int count_difference() {
        int difference = abs(score_for_white - score_for_black);

        return difference;


    }


}



