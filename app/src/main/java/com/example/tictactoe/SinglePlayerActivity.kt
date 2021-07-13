package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.max
import kotlin.math.min

class SinglePlayerActivity : AppCompatActivity() , View.OnClickListener{
    var turn = true
    var count = 0
    var isActive = true
    var boardState = Array<Array<Int>>(3){ arrayOf(-1,-1,-1)}
    lateinit var board :Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "you will take the first turn", Toast.LENGTH_SHORT).show()
        setContentView(R.layout.activity_single_player)
        board = arrayOf(
            arrayOf(btn1, btn2, btn3),
            arrayOf(btn4, btn5, btn6),
            arrayOf(btn7, btn8, btn9)
        )
        initialiseBoard()
        for (i in board) {
            for(button in i) {
                button.setOnClickListener(this)
            }
        }
        reset.setOnClickListener (View.OnClickListener {
            initialiseBoard()
        })
    }
    private fun initialiseBoard() {
        for(i in 0..2) {
            for (j in 0..2) {
                count = 0
                turn = true
                board[i][j].isClickable = true
                board[i][j].text = ""
                boardState[i][j] = -1;
                displaytv.text = getString(R.string.playerX)
                textView2.text = ""
            }
        }
    }

    private fun updateValue(row:Int,col:Int,turn:Boolean) {
        count++
        val text = if(turn) "X" else "O"
        board[row][col].apply{
            isClickable = false
            this.text = text
        }
        boardState[row][col] = if(turn) 1 else 0
        displaytv.text = if(turn) "Player O turn" else "Player X turn"
        val winner = checkWinner()
        if(winner != "moves_left") {
            textView2.text = "Now you can press reset button to start a new game"

            if(winner == "Draw") {
                displaytv.text = "DRAW"
            }else {
                displaytv.text = "Player $winner wins"
            }
            disableButton()
        }

    }

    private fun findTheBestMove() : Move {
        var best : Int = -1000
        var bestMove = Move(-1,-1)
        for (i in 0..2) {
            for(j in 0..2) {
                if(boardState[i][j] == -1) {
                    boardState[i][j] = 0;
                    val moveVal = minimax(0, false);
                    boardState[i][j] = -1;
                    if (moveVal > best) {
                        bestMove.x = i;
                        bestMove.y = j;
                        best = moveVal;
                    }
                }
            }
        }
        return bestMove
    }

    private fun checkBoardState() : Int{
        var count = 0;
        for( i in 0..2) {
            for(j in 0..2) {
                if(boardState[i][j] != -1) count += 1
            }
        }
        return count
    }

    private fun lookup(winner : String):Int {
        if(winner == "X") return -1
        if(winner == "O") return 1;
        if(winner == "Draw") return 0;
        return 2;
    }

    private fun minimax(depth : Int, isMax : Boolean) : Int{
        val winner = checkWinner()
        if(lookup(winner) != 2) return lookup(winner)

        if(isMax) {
            var best = -1000
            for(i in 0..2) {
                for(j in 0..2) {
                    if(boardState[i][j] == -1) {
                        boardState[i][j] = 0;
                        val score = minimax(depth + 1,false)
                        boardState[i][j] = -1;
                        best = max(best,score)
                    }

                }
            }
            return best
        }else {
            var best = 1000
            for(i in 0..2) {
                for(j in 0..2) {
                    if(boardState[i][j] == -1) {
                        boardState[i][j] = 1;
                        val score = minimax(depth + 1,true)
                        best = min(best,score)
                        boardState[i][j] = -1
                    }
                }
            }
            return best
        }
    }


    private fun disableButton() {
        for(i in 0..2) {
            for(j in 0..2) {
                board[i][j].isClickable = false
            }
        }
    }

    private fun enableButton() {
        for(i in 0..2) {
            for(j in 0..2) {
                board[i][j].isClickable = true
            }
        }
    }

    private fun checkWinner() : String {
        // horizontal rows
        for (i in 0..2) {
            if(boardState[i][0]!=-1&&boardState[i][0]==boardState[i][1] && boardState[i][0]==boardState[i][2]) {
                return if(boardState[i][0]==1) "X" else "O"
            }
        }
//        vertical columns
        for (i in 0..2) {
            if(boardState[0][i]!=-1&&boardState[0][i]==boardState[1][i] && boardState[0][i]==boardState[2][i]) {
                return if(boardState[0][i]==1) "X" else "O"
            }
        }
//        main diagonal
        if(boardState[0][0]!=-1&&boardState[0][0]==boardState[1][1] && boardState[0][0]==boardState[2][2]) {
            return if(boardState[0][0]==1) "X" else "O"
        }
//        other diagonal
        if(boardState[0][2]!=-1&&boardState[0][2]==boardState[1][1] && boardState[0][2]==boardState[2][0]) {
            return if(boardState[0][2]==1) "X" else "O"
        }
        if(checkBoardState() == 9) return "Draw"
        return "moves_left"
    }

    override fun onClick(view: View) {

        when(view.id) {
            R.id.btn1 ->{
                updateValue(0,0,turn)
//                turn = !turn
            }
            R.id.btn2 ->{
                updateValue(0,1,turn)
//                turn = !turn
            }
            R.id.btn3 ->{
                updateValue(0,2,turn)
//                turn = !turn
            }
            R.id.btn4 ->{
                updateValue(1,0,turn)
//                turn = !turn
            }
            R.id.btn5 ->{
                updateValue(1,1,turn)
//                turn = !turn
            }
            R.id.btn6 ->{
                updateValue(1,2,turn)
//                turn = !turn
            }
            R.id.btn7 ->{
                updateValue(2,0,turn)
//                turn = !turn
            }
            R.id.btn8 ->{
                updateValue(2,1,turn)
//                turn = !turn
            }
            R.id.btn9 ->{
                updateValue(2,2,turn)
//                turn = !turn
            }
        }
        if(displaytv.text == "Player O turn") {
            val bestMove = findTheBestMove();
            updateValue(bestMove.x,bestMove.y,false)
        }
    }

}
data class Move(var x:Int, var y:Int)