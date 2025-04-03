import kotlin.math.sqrt

fun isSudokuValid(board: Array<Array<Any>>): Boolean {
    if (!isValidBoardDimensions(board)) return false
    if (!isValidBoardValues(board)) return false
    return isValidRows(board) && isValidColumns(board) && isValidBoxes(board)
}

private fun isValidBoardDimensions(board: Array<Array<Any>>): Boolean {
    if (board.isEmpty()) return false
    val size = board.size
    if (board.any { it.size != size }) return false
    return size in 1..3 || isPerfectSquare(size)
}

private fun isPerfectSquare(n: Int): Boolean {
    val sqrt = sqrt(n.toDouble()).toInt()
    return sqrt * sqrt == n
}

private fun isValidBoardValues(board: Array<Array<Any>>): Boolean {
    val size = board.size
    return board.all { row ->
        row.all { value ->
            when (value) {
                is Int -> value in 1..size
                '-' -> true
                else -> false
            }
        }
    }
}

private fun validateUniqueInGroup(
    group: Iterable<Any>
): Boolean {
    val filledValues = group.filterIsInstance<Int>()
    return filledValues.toSet().size == filledValues.size
}

private fun isValidRows(board: Array<Array<Any>>): Boolean {
    return board.indices.all { row ->
        validateUniqueInGroup(board[row].toList())
    }
}

private fun isValidColumns(board: Array<Array<Any>>): Boolean {
    return board.indices.all { col ->
        validateUniqueInGroup(board.map { it[col] })
    }
}

private fun isValidBoxes(board: Array<Array<Any>>): Boolean {
    val size = board.size
    if (size <= 3) return true

    val boxSize = sqrt(size.toDouble()).toInt()
    return (0..<size step boxSize).all { boxRow ->
        (0..<size step boxSize).all { boxCol ->
            isValidBox(board, boxRow, boxCol, boxSize)
        }
    }
}

private fun isValidBox(
    board: Array<Array<Any>>,
    startRow: Int,
    startCol: Int,
    boxSize: Int
): Boolean {
    val boxValues = (startRow..<startRow + boxSize).flatMap { row ->
        (startCol..<startCol + boxSize).map { col ->
            board[row][col]
        }
    }
    return validateUniqueInGroup(boxValues)
}

fun isValidIPv4(ip: String): Boolean {
    val trimmedIp = ip.trim()
    if (trimmedIp.isEmpty() || trimmedIp.length > 15) return false

    val segments = trimmedIp.split(".")

    if (segments.size != 4) return false

    for (segment in segments) {
        if (segment.isEmpty()) return false
        if (segment.length > 1 && segment[0] == '0') return false
        if (!segment.all { it.isDigit() }) return false
        val value = segment.toIntOrNull() ?: return false
        if (value < 0 || value > 255) return false
    }

    return true
}