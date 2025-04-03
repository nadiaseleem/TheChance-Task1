fun main() {
    // Helper function to create boards from string representation
    fun createBoard(input: String, size: Int = 9): Array<Array<Any>> {
        require(input.length == size * size) { "Input length must match board size" }
        return Array(size) { row ->
            Array(size) { col ->
                val c = input[row * size + col]
                if (c == '-') '-' else c.toString().toInt()
            }
        }
    }

    // Valid Complete Boards
    check(
        "Complete valid 3x3 board should return true",
        result = isSudokuValid(
            createBoard(
                "123" +
                        "312" +
                        "231",
                3
            )
        ),
        correctResult = true
    )

    check(
        "Complete valid 4x4 board should return true",
        result = isSudokuValid(
            createBoard(
                "1234" +
                        "3412" +
                        "2341" +
                        "4123", 4
            )
        ),
        correctResult = true
    )

    check(
        "Complete valid 9x9 board should return true",
        result = isSudokuValid(
            createBoard(
                "534678912" +
                        "672195348" +
                        "198342567" +
                        "859761423" +
                        "426853791" +
                        "713924856" +
                        "961537284" +
                        "287419635" +
                        "345286179"
            )
        ),
        correctResult = true
    )

    check(
        "16x16 valid board should return true",
        result = isSudokuValid(
            Array(16) { row ->
                Array(16) { col ->
                    ((row * 4 + row / 4 + col) % 16 + 1)
                }
            }
        ),
        correctResult = true
    )


    //Valid Incomplete Boards
    check(
        "Valid incomplete 9x9 board with empty cells should return true",
        result = isSudokuValid(
            createBoard(
                "53--7----" +
                        "6--195---" +
                        "-98----6-" +
                        "8---6---3" +
                        "4--8-3--1" +
                        "7---2---6" +
                        "-6----28-" +
                        "---419--5" +
                        "----8--79"
            )
        ),
        correctResult = true
    )

    check(
        "Empty 9x9 board should return true",
        result = isSudokuValid(Array(9) { Array(9) { '-' } }),
        correctResult = true
    )

    // Row Violations
    // Duplicate in first row
    check(
        "Board with duplicate in first row should return false",
        result = isSudokuValid(
            createBoard(
                "523678912" +  // Duplicate 2 in first row
                        "672195348" +
                        "198342567" +
                        "859761423" +
                        "426853791" +
                        "713924856" +
                        "961537284" +
                        "287419635" +
                        "345286179"
            )
        ),
        correctResult = false
    )

    // Duplicate in middle row
    check(
        "Board with duplicate in middle row should return false",
        result = isSudokuValid(
            createBoard(
                "534678912" +
                        "672195348" +
                        "198342567" +
                        "859761423" +
                        "426853791" +
                        "713924855" +  // Duplicate 5 in middle row
                        "961537284" +
                        "287419635" +
                        "345286179"
            )
        ),
        correctResult = false
    )

    // Duplicate in last row
    check(
        "Board with duplicate in last row should return false",
        result = isSudokuValid(
            createBoard(
                "534678912" +
                        "672195348" +
                        "198342567" +
                        "859761423" +
                        "426853791" +
                        "713924856" +
                        "961537284" +
                        "287419635" +
                        "345286177"  // Duplicate 7 in last row
            )
        ),
        correctResult = false
    )

    // Column Violations
    // Duplicate in first column
    check(
        "Board with duplicate in first column should return false",
        result = isSudokuValid(
            createBoard(
                "534678912" +
                        "672195348" +
                        "198342567" +
                        "859761423" +
                        "426853791" +
                        "713924856" +
                        "961537284" +
                        "287419635" +
                        "545286179"  // Duplicate 5 in first column
            )
        ),
        correctResult = false
    )

    // Duplicate in middle column
    check(
        "Board with duplicate in middle column should return false",
        result = isSudokuValid(
            createBoard(
                "534678912" +
                        "672195348" +
                        "198342567" +
                        "859761423" +
                        "426853791" +
                        "713924856" +
                        "961537284" +
                        "287419635" +
                        "345286179"
            ).apply {
                this[4][4] = 8  // Force duplicate 8 in middle column
                this[8][4] = 8
            }
        ),
        correctResult = false
    )

    // Duplicate in last column
    check(
        "Board with duplicate in last column should return false",
        result = isSudokuValid(
            createBoard(
                "534678912" +
                        "672195348" +
                        "198342567" +
                        "859761423" +
                        "426853791" +
                        "713924856" +
                        "961537284" +
                        "287419635" +
                        "345286173"  // Duplicate 3 in last column
            )
        ),
        correctResult = false
    )

    // Subgrid Violations
    // Duplicate in top-left subgrid
    check(
        "Board with duplicate in top-left subgrid should return false",
        result = isSudokuValid(
            createBoard(
                "534678912" +
                        "672195348" +
                        "198342567" +
                        "859761423" +
                        "426853791" +
                        "713924856" +
                        "961537284" +
                        "287419635" +
                        "345286179"
            ).apply {
                this[1][1] = 5  // Force duplicate 5 in top-left subgrid
            }
        ),
        correctResult = false
    )

    // Duplicate in top-center subgrid
    check(
        "Board with duplicate in top-center subgrid should return false",
        result = isSudokuValid(
            createBoard(
                "534678912" +
                        "672195348" +
                        "198342567" +
                        "859761423" +
                        "426853791" +
                        "713924856" +
                        "961537284" +
                        "287419635" +
                        "345286179"
            ).apply {
                this[1][4] = 7  // Force duplicate 7 in top-center subgrid
            }
        ),
        correctResult = false
    )

    // Duplicate in top-right subgrid
    check(
        "Board with duplicate in top-right subgrid should return false",
        result = isSudokuValid(
            createBoard(
                "534678912" +
                        "672195348" +
                        "198342567" +
                        "859761423" +
                        "426853791" +
                        "713924856" +
                        "961537284" +
                        "287419635" +
                        "345286179"
            ).apply {
                this[1][7] = 2  // Force duplicate 2 in top-right subgrid
            }
        ),
        correctResult = false
    )

    // Duplicate in middle-left subgrid
    check(
        "Board with duplicate in middle-left subgrid should return false",
        result = isSudokuValid(
            createBoard(
                "534678912" +
                        "672195348" +
                        "198342567" +
                        "859761423" +
                        "426853791" +
                        "713924856" +
                        "961537284" +
                        "287419635" +
                        "345286179"
            ).apply {
                this[4][1] = 8  // Force duplicate 8 in middle-left subgrid
            }
        ),
        correctResult = false
    )

    // Duplicate in center subgrid
    check(
        "Board with duplicate in center subgrid should return false",
        result = isSudokuValid(
            createBoard(
                "534678912" +
                        "672195348" +
                        "198342567" +
                        "859761423" +
                        "426853791" +
                        "713924856" +
                        "961537284" +
                        "287419635" +
                        "345286179"
            ).apply {
                this[4][4] = 7  // Force duplicate 7 in center subgrid
            }
        ),
        correctResult = false
    )

    // Duplicate in middle-right subgrid
    check(
        "Board with duplicate in middle-right subgrid should return false",
        result = isSudokuValid(
            createBoard(
                "534678912" +
                        "672195348" +
                        "198342567" +
                        "859761423" +
                        "426853791" +
                        "713924856" +
                        "961537284" +
                        "287419635" +
                        "345286179"
            ).apply {
                this[4][7] = 6  // Force duplicate 6 in middle-right subgrid
            }
        ),
        correctResult = false
    )

    // Duplicate in bottom-left subgrid
    check(
        "Board with duplicate in bottom-left subgrid should return false",
        result = isSudokuValid(
            createBoard(
                "534678912" +
                        "672195348" +
                        "198342567" +
                        "859761423" +
                        "426853791" +
                        "713924856" +
                        "961537284" +
                        "287419635" +
                        "345286179"
            ).apply {
                this[7][1] = 9  // Force duplicate 9 in bottom-left subgrid
            }
        ),
        correctResult = false
    )

    // Duplicate in bottom-center subgrid
    check(
        "Board with duplicate in bottom-center subgrid should return false",
        result = isSudokuValid(
            createBoard(
                "534678912" +
                        "672195348" +
                        "198342567" +
                        "859761423" +
                        "426853791" +
                        "713924856" +
                        "961537284" +
                        "287419635" +
                        "345286179"
            ).apply {
                this[7][4] = 4  // Force duplicate 4 in bottom-center subgrid
            }
        ),
        correctResult = false
    )

    // Duplicate in bottom-right subgrid
    check(
        "Board with duplicate in bottom-right subgrid should return false",
        result = isSudokuValid(
            createBoard(
                "534678912" +
                        "672195348" +
                        "198342567" +
                        "859761423" +
                        "426853791" +
                        "713924856" +
                        "961537284" +
                        "287419635" +
                        "345286174"  // Duplicate 4 in bottom-right subgrid
            )
        ),
        correctResult = false
    )

    //Edge Cases
    check(
        "1x1 empty board should return true",
        result = isSudokuValid(arrayOf(arrayOf('-'))),
        correctResult = true
    )

    check(
        "1x1 filled board should return true",
        result = isSudokuValid(arrayOf(arrayOf(1))),
        correctResult = true
    )

    check(
        "Non-square board should return false",
        result = isSudokuValid(
            arrayOf(
                arrayOf(1, 2),
                arrayOf(2, 1),
                arrayOf(3, 4)
            )
        ),
        correctResult = false
    )

    //Value Checks
    check(
        "Board with value below 1 should return false",
        result = isSudokuValid(
            createBoard(
                "034678912" +  // 0 is invalid
                        "672195348" +
                        "198342567" +
                        "859761423" +
                        "426853791" +
                        "713924856" +
                        "961537284" +
                        "287419635" +
                        "345286179"
            )
        ),
        correctResult = false
    )

    check(
        "Board with value above size should return false",
        result = isSudokuValid(
            createBoard(
                "123" +
                        "312" +
                        "234",// 4 is invalid
                3
            )
        ),
        correctResult = false
    )


    check(
        "Board with all same numbers should return false",
        result = isSudokuValid(Array(9) { Array(9) { 1 } }),
        correctResult = false
    )

    //Type Validation
    check(
        "Board with invalid character should return false",
        result = isSudokuValid(
            arrayOf(
                arrayOf(1, 2, 3),
                arrayOf(4, '*', 6),  // '*' is invalid
                arrayOf(7, 8, 9)
            )
        ),
        correctResult = false
    )

    check(
        "Board with floating point numbers should return false",
        result = isSudokuValid(
            arrayOf(
                arrayOf(1, 2, 3),
                arrayOf(4, 2.5, 6),  // 2.5 is invalid
                arrayOf(7, 8, 9)
            )
        ),
        correctResult = false
    )
}

fun check(name: String, result: Boolean, correctResult: Boolean) {
    if (result == correctResult) {
        println("Success - $name")
    } else {
        println("Failed - $name")
    }
}
