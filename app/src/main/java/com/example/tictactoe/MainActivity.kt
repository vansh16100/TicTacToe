package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener {
    var turn = true
    var count = 0
    var isActive = true
    var boardState = Array<Array<Int>>(3){ arrayOf(-1,-1,-1)}
   lateinit var board :Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        if(checkWinner()) disableButton()
    }
    private fun disableButton() {
        for(i in 0..2) {
            for(j in 0..2) {
                board[i][j].isClickable = false
            }
        }
    }
    private fun checkWinner() : Boolean {
        // horizontal rows
        for (i in 0..2) {
           if(boardState[i][0]!=-1&&boardState[i][0]==boardState[i][1] && boardState[i][0]==boardState[i][2]) {
               val winner = if(boardState[i][0]==1) "X" else "O"
               displaytv.text = "Player $winner wins"
               textView2.text = "Now you can press reset button to start a new game"
               return true
           }
        }
//        vertical columns
        for (i in 0..2) {
            if(boardState[0][i]!=-1&&boardState[0][i]==boardState[1][i] && boardState[0][i]==boardState[2][i]) {
                val winner = if(boardState[0][i]==1) "X" else "O"
                displaytv.text = "Player $winner wins"
                textView2.text = "Now you can press reset button to start a new game"
                return true
            }
        }
//        main diagonal
        if(boardState[0][0]!=-1&&boardState[0][0]==boardState[1][1] && boardState[0][0]==boardState[2][2]) {
            val winner = if(boardState[0][0]==1) "X" else "O"
            displaytv.text = "Player $winner wins"
            textView2.text = "Now you can press reset button to start a new game"
            return true
        }
//        other diagonal
        if(boardState[0][2]!=-1&&boardState[0][2]==boardState[1][1] && boardState[0][2]==boardState[2][0]) {
            val winner = if(boardState[0][2]==1) "X" else "O"
            displaytv.text = "Player $winner wins"
            textView2.text = "Now you can press reset button to start a new game"
            return true
        }
        if(count==9) {
            displaytv.text = "DRAW"
            textView2.text = "Now you can press reset button to start a new game"
            return true
        }

//        disableButton()
        return false
    }
    override fun onClick(view: View) {

            when(view.id) {
                R.id.btn1 ->{
                    updateValue(0,0,turn)
                    turn = !turn
                }
                R.id.btn2 ->{
                    updateValue(0,1,turn)
                    turn = !turn
                }
                R.id.btn3 ->{
                    updateValue(0,2,turn)
                    turn = !turn
                }
                R.id.btn4 ->{
                    updateValue(1,0,turn)
                    turn = !turn
                }
                R.id.btn5 ->{
                    updateValue(1,1,turn)
                    turn = !turn
                }
                R.id.btn6 ->{
                    updateValue(1,2,turn)
                    turn = !turn
                }
                R.id.btn7 ->{
                    updateValue(2,0,turn)
                    turn = !turn
                }
                R.id.btn8 ->{
                    updateValue(2,1,turn)
                    turn = !turn
                }
                R.id.btn9 ->{
                    updateValue(2,2,turn)
                    turn = !turn
                }
            }
    }

}