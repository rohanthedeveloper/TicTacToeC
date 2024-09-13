package com.example.tictactoec.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tictactoec.ui.theme.Montserrat
import com.example.tictactoec.ui.theme.Pacifico
import kotlinx.coroutines.delay


@Composable
fun TicTacToeGame(navController: NavController) {
    var board by remember { mutableStateOf(Array(3) { arrayOfNulls<String>(3) }) }
    var currentPlayer by remember { mutableStateOf("X") }
    var gameStatus by remember { mutableStateOf("In Progress") }
    var score1 by remember { mutableStateOf(0) }
    var score2 by remember { mutableStateOf(0) }
    fun resetGame() {
        board = Array(3) { arrayOfNulls<String>(3) }
        currentPlayer = "X"
        gameStatus = "In Progress"
    }
    // This effect is triggered when the game status changes
    LaunchedEffect(gameStatus) {
        if (gameStatus != "In Progress") {
            // Delay before resetting the game
            delay(1000)
            resetGame()
        }
    }

    fun resetScore() {
        score1 = 0
        score2 = 0
        resetGame()
    }
    fun checkWin(player: String): Boolean {
        // Check rows, columns, and diagonals
        return (0..2).any { row -> board[row].all { it == player } } ||  // Check rows
                (0..2).any { col -> board.all { it[col] == player } } || // Check columns
                (board[0][0] == player && board[1][1] == player && board[2][2] == player) || // Check diagonal
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)   // Check anti-diagonal
    }
    fun makeMove(row: Int, col: Int) {
        if (board[row][col] == null && gameStatus == "In Progress") {
            board[row][col] = currentPlayer
            if (checkWin(currentPlayer)) {
                gameStatus = "$currentPlayer Wins!"
                if (currentPlayer == "X"){
                    score1++
                }
                if(currentPlayer == "O"){
                    score2++
                }
            } else if (board.flatten().none { it == null }) {
                gameStatus = "Draw!"
            } else {
                currentPlayer = if (currentPlayer == "X") "O" else "X"
            }
        }
    }

    HomeScreen(
        navController = navController,
        board = board,
        currentPlayer = currentPlayer,
        gameStatus = gameStatus,
        onReset = ::resetGame,
        onMakeMove = ::makeMove,
        score1 = score1 ,
        score2 = score2 ,
        onResetScore = ::resetScore
    )
}


@Composable
fun HomeScreen(
    navController: NavController ,
    board: Array<Array<String?>>,
    currentPlayer: String,
    gameStatus: String,
    onReset: () -> Unit,
    onMakeMove: (Int, Int) -> Unit,
    score1 : Int,
    score2 : Int,
    onResetScore : () -> Unit
) {

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF121212),
                        Color(0xFF555555)
                    ))
                ) , horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(53.dp))
            Text("TicTacToe", fontSize = 36.sp, fontFamily = Pacifico, color = Color(0xFFFFFFFF))
            Spacer(modifier = Modifier.height(20.dp))
            Surface(
                modifier = Modifier
                    .height(140.dp)
                    .width(300.dp),
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFF343434),
                shadowElevation = 8.dp
            ) {
                Spacer(modifier = Modifier.height(6.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Score",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = Montserrat,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Row {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Player 1\n(X)",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = Montserrat,
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFFFFFFFF),
                                    textAlign = TextAlign.Center,
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "$score1",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontFamily = Montserrat,
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFFFFFFFF),
                                )
                            )
                        }
                        Spacer(modifier = Modifier.width(56.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Player 2\n(O)",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = Montserrat,
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFFFFFFFF),
                                    textAlign = TextAlign.Center,
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "$score2",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontFamily = Montserrat,
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFFFFFFFF),
                                )
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(35.dp))
            Surface(
                modifier = Modifier
                    .height(300.dp)
                    .width(300.dp)
                    .border(3.dp, color = Color(0xFF343434), shape = RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column {
                    board.forEachIndexed { rowIndex, row ->
                        Row {
                            row.forEachIndexed { colIndex, cell ->
                                Button(
                                    onClick = { onMakeMove(rowIndex, colIndex) },
                                    modifier = Modifier
                                        .height(100.dp)
                                        .width(100.dp)
                                        .border(
                                            2.dp,
                                            color = Color(0xFFFFFFFF),
                                            shape = RoundedCornerShape(0.dp)
                                        ),
                                    shape = RectangleShape,
                                    colors = ButtonDefaults.buttonColors(Color(0xFF343434))
                                ) {
                                    Text(
                                        text = cell ?: "",
                                        style = TextStyle(
                                            fontSize = 24.sp,
                                            fontFamily = Montserrat,
                                            fontWeight = FontWeight(700),
                                            color = Color(0xFFFFFFFF),
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(43.dp))
            Row() {
            Button(
                onClick = onReset,
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF343434))
            ) {
                Text(
                    text = "Restart Game",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000),
                    )
                )
            }
                Spacer(modifier = Modifier.width(12.dp))
            Button(
                onClick = onResetScore,
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF343434))
            ) {
                Text(
                    text = "Reset Score",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000),
                    )
                )
            }
        }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = gameStatus,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF252525),
                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "crafted by Rohan",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                )
            )
        }
    }
}
