/*
Created by: Rafael Andara <rafael.andara@gmail.com>
Date: 2014-02-10
Usage: scala whoisthewinner.scala file file file ....

Description: 
	First scala exercise on Seven Languages in Sever Weeks.
	Write a game that will take a tic-tac-toe board with x, o, and blank characters 
	and detect the winner or whether there is a tie or no winner yet. 
	Use classes where appropriate.
*/


class WhoIsTheWinner(){
	
	var file = ""
	var board = ""
	var result = 0
	
	args.foreach{ arg => 

		print("Reading file: ")
		file = arg 
		println(file)
		try {
			var source = scala.io.Source.fromFile(file) //try/catch
			board = source.mkString
			source.close()
		
			if(isValidBoard(board)){

				result = boardAnalysis(board)
				displayResults(result,board,file)


			}
			else{
				println("Board on file '"+file+"' not valid")
			}
		}
		catch {
			case e: Exception => println("Exception caught: " + e);
		}	



	}


	def isValidBoard(board: String): Boolean = {
		println ("Checking board...")
		if( 
			(board.count(_ == 'x') <=5) &&
			(board.count(_ == 'o') <=5) &&
			(board.count(_ == ' ') <=9)) {
			return true
		}
		else{
			return false
		}
	}

	def boardAnalysis(board: String): Int = {
		
		var lines = board.split("\n")

		println("Analysing Rows")

		lines.foreach{ line => 
		
			if (line.matches("ooo")) return 1

			if (line.matches("xxx")) return 2

		}

		println("Analysing Columns")

		var col1 = ""
		var col2 = ""
		var col3 = ""

		lines.foreach{ line =>
				col1 += line{0}
				col2 += line{1}
				col3 += line{2}
		}

		var cols = new Array[String](3)

		cols(0) = col1
		cols(1) = col2
		cols(2) = col3

		cols.foreach{ col => 

			if (col.matches("ooo")) return 1

			if (col.matches("xxx")) return 2

		}

		println("Analysing Diagonals")

		var diag1 = ""
		var diag2 = ""
		var di = 0
		var dj = 2
		lines.foreach{ line =>
				diag1 += line{di}
				diag2 += line{dj}
				di=di+1
				dj=dj-1
		}

		var diags = new Array[String](2)

		diags(0) = diag1
		diags(1) = diag2

		diags.foreach{ diag => 

			if (diag.matches("ooo")) return 1

			if (diag.matches("xxx")) return 2

		}

		if (board.count(_ == ' ') >= 1 ) return 3 
			
		return 0
		
	}

	def displayResults(result: Int, board: String, file: String) {
		println("Board on "+file)
		println(board)
		if(result == 1){
			println("and the winner is O")
		}
		else{
			if(result == 2){
				println("and the winner is X")	
			}
			else{
				if(result == 3){
					println("no winner yet")
				}
				else{
					println("is a draw")
				}
			}
		}

	}
	
}


val tictactoe = new WhoIsTheWinner